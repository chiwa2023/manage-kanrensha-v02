package net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan;

import java.time.LocalDateTime;
import java.time.Year;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearTaskFailureService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearTaskSuccessService;

/**
 * Job終了後タスク計画保存リスナ
 */
@Component
public class RecordTaskPlanJobExecutionListner implements JobExecutionListener {

    /** 起動条件記録年呼び出しKey */
    public static final String KEY_YEAR = "tableYear";

    /** 起動条件タスク計画コード呼び出しKey */
    public static final String KEY_ID = "taskId";

    /** 起動条件タスク計画コード呼び出しKey */
    public static final String KEY_CODE = "taskCode";

    /** タスク計画年切替保存Service */
    @Autowired
    private SwitchYearTaskSuccessService switchYearTaskSuccessService;

    /** タスク計画年切替保存Service */
    @Autowired
    private SwitchYearTaskFailureService switchYearTaskFailureService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * ジョブ終了後処理を行う(タスク計画記録と例外取得)
     */
    @Override
    public void afterJob(final JobExecution jobExecution) {

        JobParameters parameters = jobExecution.getJobParameters();
        LeastUserDto userDto = new LeastUserDto();
        userDto.setUserPersonId(Math.toIntExact(parameters.getLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM)));
        userDto.setUserPersonCode(
                Math.toIntExact(parameters.getLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM)));
        userDto.setUserPersonName(parameters.getString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM));

        int tableYear = Math.toIntExact(parameters.getLong(KEY_YEAR));
        int taskId = Math.toIntExact(parameters.getLong(KEY_ID));
        int taskCode = Math.toIntExact(parameters.getLong(KEY_CODE));
        LocalDateTime endDatetime = LocalDateTime.now();

        try {
            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                // 成功の記録を保存
                switchYearTaskSuccessService.practice(tableYear, userDto, taskId, endDatetime);
            } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
                switchYearTaskFailureService.practice(tableYear, userDto, taskId, taskCode, endDatetime);
                // 失敗した場合には保存された例外を保存
                for (Throwable throwable : jobExecution.getAllFailureExceptions()) {
                    saveStackTraceService.practice((Exception) throwable, tableYear, taskCode);
                }
            }
            
            // メール送信
            
            
        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
        }

    }
}
