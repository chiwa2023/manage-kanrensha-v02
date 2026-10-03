package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.security.DeleteLimitOverTokenBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 期限切れトークン・コード削除Service
 */
@Service
public class DeleteLimitOverTokenService {

    /** 起動をするJob */
    @Qualifier(DeleteLimitOverTokenBatchConfiguration.JOB_NAME)
    @Autowired
    private Job deleteLimitOverToken;

    /** 起動をつかさどるランチャー */
    @Autowired
    private JobOperator jobOperator;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param limitDate 削除起源日付
     */
    @Async
    public void practice(final LocalDate limitDate) {

        JobParameters jobParameters = new JobParametersBuilder(
                deleteLimitOverToken.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addLocalDate("limitDate", limitDate)
                .toJobParameters();

        try {
            jobOperator.run(deleteLimitOverToken, jobParameters);

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            // ここで補足できる例外はバッチ起動に関する例外のみで、バッチ動作に関する例外は別で処理する
            saveStackTraceService.practice(exception, Year.now().getValue(), 0);
        }

    }

}
