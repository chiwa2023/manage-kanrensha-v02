package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
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
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AddXmlMinMasterBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("AddXmlMinMasterBatchConfigurationTest.sql")
class AddXmlMinMasterBatchConfigurationTest {

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(AddXmlMinMasterBatchConfiguration.JOB_NAME)
    @Autowired
    private Job addXmlMinMasterBatchConfiguration;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(AddXmlMinMasterBatchConfiguration.JOB_NAME, addXmlMinMasterBatchConfiguration.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        jobLauncherTestUtils.setJob(addXmlMinMasterBatchConfiguration);

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder(
                addXmlMinMasterBatchConfiguration.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");
    }

}
