package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaCombineOrgResultRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaCombineOrgRepository;

/**
 * 個人団体紐づけワークテーブル削除処理Tasklet
 */
@Component
public class EraseWkTblCombineOrgTasklet implements Tasklet, StepExecutionListener {

    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaCombineOrgRepository wkTblKanrenshaCombineOrgRepository;

    /** 個人団体紐づけワークテーブルRepository */
    @Autowired
    private WkTblKanrenshaCombineOrgResultRepository wkTblKanrenshaCombineOrgResultRepository;

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

        wkTblKanrenshaCombineOrgRepository.deleteByInsertUserCode(userCode);
        wkTblKanrenshaCombineOrgResultRepository.deleteByInsertUserCode(userCode);

        // 処理終了
        return RepeatStatus.FINISHED;
    }

}
