package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.BackApplication;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AddMinKanrenshaPersonMasterBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("AddMinKanrenshaPersonMasterBatchConfigurationTest.sql")
class AddMinKanrenshaPersonMasterBatchConfigurationTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(AddMinKanrenshaPersonMasterBatchConfiguration.JOB_NAME)
    @Autowired
    private Job addMinKanrenshaPersonMasterBatchConfiguration;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(AddMinKanrenshaPersonMasterBatchConfiguration.JOB_NAME,
                addMinKanrenshaPersonMasterBatchConfiguration.getName(), "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        jobLauncherTestUtils.setJob(addMinKanrenshaPersonMasterBatchConfiguration);

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/batch/kanrensha",
                "関連者個人最小登録.csv");

        JobParameters jobParameters = new JobParametersBuilder(addMinKanrenshaPersonMasterBatchConfiguration // NOPMD
                .getJobParametersIncrementer().getNext(new JobParameters()))
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addString("readFilePath", path.toString()).addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) 2026) //
                .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) 453) //
                .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) 187)
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");
    }

}
