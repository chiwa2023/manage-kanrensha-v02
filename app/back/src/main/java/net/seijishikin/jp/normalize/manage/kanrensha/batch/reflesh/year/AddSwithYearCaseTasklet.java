package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.year;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.YearOptionEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.CopyFolderWalkTreeAllLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.YearOptionRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * 年切替処理追加Tasklet
 */
@Component
public class AddSwithYearCaseTasklet implements Tasklet, StepExecutionListener {

    /** 複写元年保存キー */
    public static final String KEY_SRC_YEAR = "srcYear";
    /** 複写元年保存キー */
    public static final String KEY_COPY_YEAR = "copyYear";
    /** 複写元年保存キー */
    public static final String KEY_BACKUP = "backup";

    /** 複写元年 */
    private int srcYear;
    /** 複写先年 */
    private int copyYear;

    /** 退避先 */
    private String backup = "";

    /** プロジェクトディレクトリ */
    private static final String DIR_ROOT = "main/java/net/seijishikin/jp/normalize/manage/kanrensha";

    /** 年切替サービスディレクトリ */
    private static final String DIR_SWITCH = "service/year";

    /** 置換位置指定キーワード(field) */
    private static final String ADD_KEY_FIELD = "// field次回追加位置";

    /** 置換位置指定キーワード(case) */
    private static final String ADD_KEY_CASE = "// case次回追加位置";

    /** 置換位置指定キーワード(field) */
    private static final String KAIGYOU = "\r";

    /** 配下をすべて複写する */
    @Autowired
    private CopyFolderWalkTreeAllLogic copyFolderWalkTreeAllLogic;

    /** 紐づけ年選択肢Repository */
    @Autowired
    private YearOptionRepository yearOptionRepository;

    /** 紐づけ年選択肢Repository */
    @Autowired
    private WriteLogService writeLogService;

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {

        JobParameters parameters = stepExecution.getJobParameters();

        srcYear = Math.toIntExact(parameters.getLong(KEY_SRC_YEAR));
        copyYear = Math.toIntExact(parameters.getLong(KEY_COPY_YEAR));

        backup = parameters.getString(KEY_BACKUP);
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        // クラスimportとソースフォーマットは手作業

        // 現在のソースをバックアップする
        String dir = GetCurrentResourcePath.getBackSrcPath(DIR_ROOT);
        Path srcPath = Paths.get(dir, DIR_SWITCH);
        copyFolderWalkTreeAllLogic.practice(srcPath, Paths.get(backup));

        // 取得できたファイルの分だけループする
        List<Path> list = Files.list(srcPath).toList();
        for (Path entry : list) {
            this.editFile(entry);
        }

        // 年対応を行ったことをすでに行った記録がなければDBに保存
        Optional<YearOptionEntity> optional = yearOptionRepository.findById(copyYear);
        if (optional.isEmpty()) {
            YearOptionEntity entity = new YearOptionEntity();
            entity.setSelectedYear(copyYear);
            entity.setIsSelected(false);
            yearOptionRepository.save(entity);
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    private boolean editFile(final Path entry) throws IOException {

        // 全ファイルを読み込み
        String baseContent = Files.readString(entry);

        // フィールド部
        // 指定語から指定語までの置換追加部分を取り出し年で置換する
        String filedStartKey = "/** 実施年(" + srcYear;
        int fieldStartPos = baseContent.indexOf(filedStartKey);
        String filedEndKey = "Y" + srcYear + "Logic;";
        int fieldEndPos = baseContent.indexOf(filedEndKey, fieldStartPos);

        // 万が一フィールド部が条件を満たしていなければ処理不能で落とす
        if (fieldStartPos == -1 || fieldEndPos == -1) {
            writeLogService.writeWarn("field 処理不能" + entry);
        }

        String fieldSrcContent = baseContent.substring(fieldStartPos, fieldEndPos + filedEndKey.length());
        String srcYearText = String.valueOf(srcYear);
        String copyYearText = String.valueOf(copyYear);

        String fieldCopyContent = fieldSrcContent.replace(srcYearText, copyYearText);

        // switch部
        // 指定語から指定語までの置換追加部分を取り出し年で置換する
        String caseStartKey = "// " + srcYear + "年";
        int caseStartPos = baseContent.indexOf(caseStartKey);
        String caseEndKey = "// " + (srcYear + 1) + "年";
        int caseEndPos = baseContent.indexOf(caseEndKey);

        // 万が一case部が条件を満たしていなければ処理不能で落とす
        if (caseStartPos == -1 || caseEndPos == -1) {
            writeLogService.writeWarn("case部 処理不能" + entry);
        }

        String caseSrcContent = baseContent.substring(caseStartPos, caseEndPos);
        String caseCopyContent = caseSrcContent.replace(srcYearText, copyYearText);

        // 置換する
        String copyContent = baseContent.replace(ADD_KEY_FIELD, fieldCopyContent + KAIGYOU + KAIGYOU + ADD_KEY_FIELD)
                .replace(ADD_KEY_CASE, caseCopyContent + ADD_KEY_CASE);

        Files.writeString(entry, copyContent);

        return true;
    }

}
