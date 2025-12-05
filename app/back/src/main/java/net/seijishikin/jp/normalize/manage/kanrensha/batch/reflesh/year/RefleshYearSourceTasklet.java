package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.year;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * 年ごとで異なるソースの年展開を行う
 */
@Component
public class RefleshYearSourceTasklet implements Tasklet, StepExecutionListener {

    /** プロジェクトディレクトリ */
    private static final String DIR_ROOT = "main/java/net/seijishikin/jp/normalize/manage/kanrensha";

    /** テストディレクトリ */
    private static final String DIR_TEST_ROOT = "test/java/net/seijishikin/jp/normalize/manage/kanrensha";

    /** テストリソースディレクトリ */
    private static final String DIR_TEST_SOURCE_ROOT = "test/resources/net/seijishikin/jp/normalize/manage/kanrensha";

    /** Entityディレクトリ */
    private static final String DIR_ENTITY = "/entity/year";

    /** Repositoryディレクトリ */
    private static final String DIR_REPOSITORY =  "/repository/year";

    /** Logicディレクトリ */
    private static final String DIR_LOGIC = "/logic/year";

    /** 複写元年保存キー */
    public static final String KEY_SRC_YEAR = "srcYear";
    /** 複写元年保存キー */
    public static final String KEY_COPY_YEAR = "copyYear";

    /** 複写元年 */
    private String srcYear = "";
    /** 複写先年 */
    private String copyYear = "";

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {

        JobParameters parameters = stepExecution.getJobParameters();

        srcYear = String.valueOf(Math.toIntExact(parameters.getLong(KEY_SRC_YEAR)));
        copyYear = String.valueOf(Math.toIntExact(parameters.getLong(KEY_COPY_YEAR)));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        // entity,repository,logicの複写を行う
        this.copy(DIR_ROOT + DIR_ENTITY);
        this.copy(DIR_ROOT + DIR_REPOSITORY);
        this.copy(DIR_ROOT + DIR_LOGIC);

        // テスト
        //this.copy(DIR_TEST_ROOT + DIR_ENTITY);
        //this.copy(DIR_TEST_ROOT + DIR_REPOSITORY);
        this.copy(DIR_TEST_ROOT + DIR_LOGIC);

        // テストリソース
        //this.copy(DIR_TEST_SOURCE_ROOT + DIR_ENTITY);
        //this.copy(DIR_TEST_SOURCE_ROOT + DIR_REPOSITORY);
        this.copy(DIR_TEST_SOURCE_ROOT + DIR_LOGIC);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    /**
     * 複写作業を行う
     * 
     * @param copyRoot 元Dir
     * @throws IOException ファイル作成時例外
     */
    private void copy(final String copyRoot) throws IOException {

        String dir = GetCurrentResourcePath.getBackSrcPath(copyRoot);

        Path pathSrc = Paths.get(dir, "y" + srcYear);

        if (!Files.exists(pathSrc)) {
            throw new IllegalArgumentException("複写元が存在しません" + pathSrc.toString());
        }

        Path pathCopy = Paths.get(dir, "y" + copyYear);

        this.worksByOneFunction(pathSrc, pathCopy);
    }

    /**
     * 一機能分の複写作業を行う
     * 
     * @param srcPath  複写元
     * @param copyPath 複写先
     * @throws IOException ファイル作成時例外
     */
    private void worksByOneFunction(final Path srcPath, final Path copyPath) throws IOException {

        // コピー先のフォルダを作成する
        Files.createDirectories(copyPath);

        // 取得できたファイルの分だけループする
        List<Path> list = Files.list(srcPath).toList();
        for (Path entry : list) {
            this.copyFile(entry, copyPath);
        }
    }

    /**
     * ファイル1件の複写を行う
     *
     * @param entry    複写元ファイル
     * @param copyPath 複写先
     * @throws IOException ファイル作成例外
     */
    private void copyFile(final Path entry, final Path copyPath) throws IOException {

        // 内容はbaseYearをcopyYearで全置換
        String baseContent = Files.readString(entry);
        String copyContent = baseContent.replaceAll(srcYear, copyYear);

        // ファイル名も同様に置換
        String fileName = entry.getFileName().toString().replaceAll(srcYear, copyYear);

        Files.writeString(Paths.get(copyPath.toString(), fileName), copyContent);
    }

}
