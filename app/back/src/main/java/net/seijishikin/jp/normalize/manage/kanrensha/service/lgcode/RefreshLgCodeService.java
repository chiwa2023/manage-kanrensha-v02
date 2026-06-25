package net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode;

import java.time.LocalDateTime;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode.InsertAddressAllCityBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanWithUseFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 地方自治体コード最新化Service
 */
@Service
public class RefreshLgCodeService {

    /** 起動をするJob */
    @Qualifier(InsertAddressAllCityBatchConfiguration.JOB_NAME)
    @Autowired
    private Job insertAddressAllCity;

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** Stacktrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param year        発生年
     * @param userDto     ユーザ最小限
     * @param planFileDto タスク計画と読み取りファイルDto
     */
    @Async
    public void practice(final Integer year, final LeastUserDto userDto, final TaskPlanWithUseFileDto planFileDto) {

        try {

            JobParameters jobParameters = new JobParametersBuilder(
                    insertAddressAllCity.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                    .addLocalDateTime("executeTime", LocalDateTime.now())
                    .addString("readFilePath", planFileDto.getReadFile().toString()) // NOPMD LowDemeter
                    .addLong("userId", (long) userDto.getUserPersonId())
                    .addLong("userCode", (long) userDto.getUserPersonCode())
                    .addString("userName", userDto.getUserPersonName())
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) planFileDto.getTaskYear())
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) planFileDto.getTaskPlanId())
                    .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) planFileDto.getTaskPlanCode())
                    .toJobParameters();

            jobLauncher.run(insertAddressAllCity, jobParameters);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで補足できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, year, planFileDto.getTaskPlanCode());
        }
    }

}
