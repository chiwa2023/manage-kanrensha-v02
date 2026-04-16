package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiHistoryResultRepository;

/**
 * 関連者政治団体履歴登録処理ワークテーブルデータを操作者で消去する
 */
@Component
public class EraseWkTblKanrenshaSeijidantaiHistoryTasklet implements Tasklet ,StepExecutionListener{

    /** 関連者政治団体履歴Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiHistoryRepository wkTblKanrenshaSeijidantaiHistoryRepository;

    /** 関連者政治団体本登録判断Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiHistoryResultRepository wkTblKanrenshaSeijidantaiHistoryResultRepository;

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

        wkTblKanrenshaSeijidantaiHistoryRepository.deleteByInsertUserCode(userCode);

        wkTblKanrenshaSeijidantaiHistoryResultRepository.deleteByInsertUserCode(userCode);

        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
