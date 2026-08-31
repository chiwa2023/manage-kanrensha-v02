package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalRepairLogRepository;

/**
 * 郵便番号修復ログ初期化Tasklet
 * 
 * <p>
 * この処理を行う前に、必ず作業確定かつ最新(作業しようとマークしたが実際に作業をしていない)が存在しないことを確認しなければならない
 * </p>
 */
@Component
public class PickupManualWorksInitializeTasklet implements Tasklet, StepExecutionListener {

    /** 郵便番号修復ログRepository */
    @Autowired
    private AddressPostalRepairLogRepository addressPostalRepairLogRepository;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** ユーザ最低限Dto */
    private LeastUserDto userDto;

    /** 地方行政区コード */
    private String lgCodePref;

    /**
     * BeforeStep(ユーザ情報設定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        
        userDto = createUserLeastDtoByBatchParamUtil.practice(stepExecution);
        lgCodePref = stepExecution.getJobParameters().getString("lgCodePref") + "%";
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        addressPostalRepairLogRepository.initializeByLgcode(lgCodePref, userDto.getUserPersonId(),
                userDto.getUserPersonCode(), userDto.getUserPersonName());

        // 処理終了
        return RepeatStatus.FINISHED;

    }

}
