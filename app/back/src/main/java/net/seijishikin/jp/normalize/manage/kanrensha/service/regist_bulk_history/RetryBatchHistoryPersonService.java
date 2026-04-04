package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_history;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history.RetryKanrenshaPersonHistoryBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.WriteLogService;

/**
 * Csv読み取り個人履歴登録Service
 */
@Service
public class RetryBatchHistoryPersonService {

    /** 起動をするJob */
    @Qualifier(RetryKanrenshaPersonHistoryBatchConfiguration.JOB_NAME)
    @Autowired
    private Job retryKanrenshaPersonHistory;

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobLauncher jobLauncher;

    /** ログ書き出しService */
    @Autowired
    private WriteLogService writeLogService;

    /**
     * 処理を行う
     *
     * @param userDto ユーザDto
     */
    @Async
    public void practice(final LeastUserDto userDto) {

        JobParameters jobParameters = new JobParametersBuilder(
                retryKanrenshaPersonHistory.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addLong("userId", Long.parseLong(userDto.getUserPersonId().toString()))
                .addLong("userCode", Long.parseLong(userDto.getUserPersonCode().toString()))
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        try {
            jobLauncher.run(retryKanrenshaPersonHistory, jobParameters);

        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException exception) {
            // TODO: handle exception
            writeLogService.practiceError("",exception);
        }

    }

}
