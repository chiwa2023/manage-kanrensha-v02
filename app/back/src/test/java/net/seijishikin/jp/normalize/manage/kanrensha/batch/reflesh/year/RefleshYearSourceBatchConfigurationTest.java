package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.year;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.test.JobOperatorTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.BackApplication;

/**
 * RefleshYearSourceBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearSourceBatchConfigurationTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テストユーティリティ */
    @Autowired
    private JobOperatorTestUtils jobOperatorTestUtils;

    /** 起動をするJob */
    @Qualifier(RefleshYearSourceBatchConfiguration.JOB_NAME)
    @Autowired
    private Job refleshYearSourceBatchConfiguration;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(RefleshYearSourceBatchConfiguration.JOB_NAME, refleshYearSourceBatchConfiguration.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        jobOperatorTestUtils.setJob(refleshYearSourceBatchConfiguration);

        JobParameters jobParameters = new JobParametersBuilder(refleshYearSourceBatchConfiguration // NOPMD
                .getJobParametersIncrementer().getNext(new JobParameters()))
                .addLocalDateTime("exe_datetitme", LocalDateTime.now()) //
                .addLong(AddSwithYearCaseTasklet.KEY_SRC_YEAR, 2025L) //
                .addLong(AddSwithYearCaseTasklet.KEY_COPY_YEAR, 2019L)
                .addString(AddSwithYearCaseTasklet.KEY_BACKUP, "c:/temp/service").toJobParameters();

        @SuppressWarnings("removal")
        JobExecution jobExecution = jobOperatorTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");
    }

}
