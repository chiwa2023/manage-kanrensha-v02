package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

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
 * ChangePrepareAddressRsdtBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("ChangePrepareAddressRsdtBatchConfigurationTest.sql")
class ChangePrepareAddressRsdtBatchConfigurationTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(ChangePrepareAddressRsdtBatchConfiguration.JOB_NAME)
    @Autowired
    private Job changePrepareAddressRsdt;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(ChangePrepareAddressRsdtBatchConfiguration.JOB_NAME, changePrepareAddressRsdt.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        jobLauncherTestUtils.setJob(changePrepareAddressRsdt);

        Path pathParcel = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/address_base/parcel",
                "mt_parcel_city011045_sample.csv");

        Path pathRsdt = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/address_base/rsdt",
                "mt_rsdtdsp_rsdt_pref01_sample.csv");

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder(
                changePrepareAddressRsdt.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addString("readFilePathParcel", pathParcel.toString()) //
                .addString("readFilePathRsdt", pathRsdt.toString()) //
                .addString("lgCode", "011045") //
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) 2026) //
                .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) 459) //
                .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) 153).toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");
    }

}
