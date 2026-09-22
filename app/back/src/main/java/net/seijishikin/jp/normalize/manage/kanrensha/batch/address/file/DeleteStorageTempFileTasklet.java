package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.file;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.DeleteFolderWalkTreeAllLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetTempFilePathLogic;

/**
 * ストレージ一時ファイル削除処理
 */
@Component
public class DeleteStorageTempFileTasklet implements Tasklet, StepExecutionListener {

    /** 一時ファイルパス指定Logic */
    @Autowired
    private GetTempFilePathLogic getTempFilePathLogic;

    
    /** ストレージ内絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** 再帰ファイル削除Logic */
    @Autowired
    private DeleteFolderWalkTreeAllLogic deleteFolderWalkTreeAllLogic;
    

    /** 削除月 */
    private Integer monthDelete = 0;

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        monthDelete = Math.toIntExact(stepExecution.getJobParameters().getLong("month"));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        String savedDir = getTempFilePathLogic.practice(monthDelete, "").getSavedDir(); // ファイル名は指定なし
        Path path = getAbsolutePathLogic.practice(savedDir, "");

        if(Files.exists(path)) {
            // 再帰削除処理
            deleteFolderWalkTreeAllLogic.practice(path);
        }else {
            // 万が一存在しない場合は次回のためにフォルダ作成して終了
            Files.createDirectories(path);
        }
        
        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
