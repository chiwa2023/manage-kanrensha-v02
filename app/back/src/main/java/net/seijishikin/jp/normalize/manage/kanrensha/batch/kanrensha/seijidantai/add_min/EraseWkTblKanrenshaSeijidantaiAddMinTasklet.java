package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinResultRepository;

/**
 * 関連者政治団体ワークテーブル削除処理Tasklet
 */
@Component
public class EraseWkTblKanrenshaSeijidantaiAddMinTasklet implements Tasklet, StepExecutionListener {

    /** 関連者政治団体登録最小限Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinRepository wkTblKanrenshaSeijidantaiAddMinRepository;

    /** 関連者政治団体最小登録処理結果Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinResultRepository wkTblKanrenshaSeijidantaiAddMinResultRepository;

    /** 操作者ユーザコード */
    private Integer userCode;

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        wkTblKanrenshaSeijidantaiAddMinRepository.deleteByInsertUserCode(userCode);
        wkTblKanrenshaSeijidantaiAddMinResultRepository.deleteByInsertUserCode(userCode);

        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
