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
import org.springframework.batch.test.JobOperatorTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

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
 * InsertAddressAllCityBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("InsertAddressAllCityBatchConfigurationTest.sql")
class InsertAddressAllCityBatchConfigurationTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テストユーティリティ */
    @Autowired
    private JobOperatorTestUtils jobOperatorTestUtils;

    /** 起動をするJob */
    @Qualifier(InsertAddressAllCityBatchConfiguration.JOB_NAME)
    @Autowired
    private Job insertAddressAllCity;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(InsertAddressAllCityBatchConfiguration.JOB_NAME, insertAddressAllCity.getName(), "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        jobOperatorTestUtils.setJob(insertAddressAllCity);
        // 本番用データ作成ファイル指定
        // Path pathRoot = Paths.get(GetCurrentResourcePath.getBackSrcPath(""));
        // Path pathBase =
        // Paths.get(pathRoot.getParent().getParent().getParent().toString(),
        // "/config/file_address/lg_code/");
        // Path path = Paths.get(pathBase.toString(), "mt_city_all.csv");

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/address_base/",
                "mt_city_all_sample.csv");

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder(
                insertAddressAllCity.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addString("readFilePath", path.toString())
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, 2026L) //
                .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, 453L)
                .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, 187L).toJobParameters();

        @SuppressWarnings("removal")
        JobExecution jobExecution = jobOperatorTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");

    }

}
