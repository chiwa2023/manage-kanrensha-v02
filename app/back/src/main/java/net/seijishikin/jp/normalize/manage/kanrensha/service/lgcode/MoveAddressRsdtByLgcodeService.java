package net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode;

import java.time.LocalDateTime;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode.MoveAddressRsdtByLgcodeBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.EditAddressCityDeleteCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 地方自治体コード移動非同期Service
 */
@Service
public class MoveAddressRsdtByLgcodeService {

    /** 起動をするJob */
    @Qualifier(MoveAddressRsdtByLgcodeBatchConfiguration.JOB_NAME)
    @Autowired
    private Job moveAddressRsdtByLgcode;

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobOperator jobOperator;

    /** Stacktrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param year       発生年
     * @param planDto    タスク計画Dto
     * @param capsuleDto 地方自治体コード移行条件Dto
     */
    @Async
    public void practice(final Integer year, final InsertTaskPlanResultDto planDto,
            final EditAddressCityDeleteCapsuleDto capsuleDto) {

        try {
            LeastUserDto userDto = capsuleDto.getUserDto();
            JobParameters jobParameters = new JobParametersBuilder(
                    moveAddressRsdtByLgcode.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                    .addLocalDateTime("executeTime", LocalDateTime.now())
                    .addString("srcLgCode", capsuleDto.getEditEntity().getLgCode())
                    .addString("copyLgCode", capsuleDto.getMoveLgCode())
                    .addString("srcLgName", capsuleDto.getSrcLgName())
                    .addString("copyLgName", capsuleDto.getMoveLgName())
                    .addLong("userId", (long) userDto.getUserPersonId())
                    .addLong("userCode", (long) userDto.getUserPersonCode())
                    .addString("userName", userDto.getUserPersonName())
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) planDto.getTaskYear())
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) planDto.getTaskPlanId())
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) planDto.getTaskPlanCode())
                    .toJobParameters();

            jobOperator.run(moveAddressRsdtByLgcode, jobParameters);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで補足できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, year, planDto.getTaskPlanCode());
        }
    }

}
