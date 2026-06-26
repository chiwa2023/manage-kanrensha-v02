package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history;

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
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history.RetryKanrenshaKigyouDtHistoryBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * Csv読み取り企業団体履歴登録Service
 */
@Service
public class RetryBatchHistoryKigyouDtService {

    /** 起動をするJob */
    @Qualifier(RetryKanrenshaKigyouDtHistoryBatchConfiguration.JOB_NAME)
    @Autowired
    private Job retryKanrenshaKigyouDtHistory;

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobOperator jobOperator;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param userDto ユーザDto
     * @param year    登録年
     * @param planDto タスク計画情報
     */
    @Async
    public void practice(final LeastUserDto userDto, final Integer year, final InsertTaskPlanResultDto planDto) {

        try {
            JobParameters jobParameters = new JobParametersBuilder(
                    retryKanrenshaKigyouDtHistory.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                    .addLocalDateTime("executeTime", LocalDateTime.now())
                    .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM,
                            Long.parseLong(userDto.getUserPersonId().toString()))
                    .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM,
                            Long.parseLong(userDto.getUserPersonCode().toString()))
                    .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) year)
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) planDto.getTaskPlanId())
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) planDto.getTaskPlanCode())
                    .toJobParameters();

            jobOperator.run(retryKanrenshaKigyouDtHistory, jobParameters);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで補足できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, year, planDto.getTaskPlanCode());
        }
    }

}
