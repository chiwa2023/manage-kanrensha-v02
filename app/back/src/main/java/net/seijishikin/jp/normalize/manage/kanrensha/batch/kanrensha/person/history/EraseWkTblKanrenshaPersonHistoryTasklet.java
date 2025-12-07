package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryResultRepository;

/**
 * 関連者個人履歴登録処理ワークテーブルデータを操作者で消去する
 */
@Component
public class EraseWkTblKanrenshaPersonHistoryTasklet implements Tasklet ,StepExecutionListener{

    /** 関連者個人履歴Repository */
    @Autowired
    private WkTblKanrenshaPersonHistoryRepository wkTblKanrenshaPersonHistoryRepository;

    /** 関連者個人本登録判断Repository */
    @Autowired
    private WkTblKanrenshaPersonHistoryResultRepository wkTblKanrenshaPersonHistoryResultRepository;

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

        wkTblKanrenshaPersonHistoryRepository.deleteByInsertUserCode(userCode);

        wkTblKanrenshaPersonHistoryResultRepository.deleteByInsertUserCode(userCode);

        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
