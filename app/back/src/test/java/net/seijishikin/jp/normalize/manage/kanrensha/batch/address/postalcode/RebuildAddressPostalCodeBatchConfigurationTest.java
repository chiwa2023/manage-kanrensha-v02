package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
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
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RebuildAddressPostalCodeBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RebuildAddressPostalCodeBatchConfigurationTest.sql")
class RebuildAddressPostalCodeBatchConfigurationTest {

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(RebuildAddressPostalCodeBatchConfiguration.JOB_NAME)
    @Autowired
    private Job rebuildAddressPostalCode;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(RebuildAddressPostalCodeBatchConfiguration.JOB_NAME, rebuildAddressPostalCode.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        jobLauncherTestUtils.setJob(rebuildAddressPostalCode);

        // 本番用データ作成ファイル指定
        // Path pathRoot = Paths.get(GetCurrentResourcePath.getBackSrcPath(""));
        // Path pathBase =
        // Paths.get(pathRoot.getParent().getParent().getParent().toString(),"/config/file_address/postal/");

        // Path pathOneLine = Paths.get(pathBase.toString(),"/utf_ken_all/"
        // ,"utf_ken_all.csv");
        // Path pathJigyousha = Paths.get(pathBase.toString(),"/jigyousho/"
        // ,"JIGYOSYO.CSV");
        // Path pathOneLine = Paths.get(pathBase.toString(),"/utf_ken_all/"
        // ,"temp.csv");
        // Path pathJigyousha = Paths.get(pathBase.toString(),"/jigyousho/"
        // ,"temp.csv");

        Path pathOneLine = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/postalcode/",
                "utf_add_2601.csv");
        Path pathJigyousha = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/postalcode/",
                "JIGYOSYO_SAMPLE.CSV");

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder(
                rebuildAddressPostalCode.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addString("readFilePathOneLine", pathOneLine.toString())
                .addString("readFilePathJigyousha", pathJigyousha.toString())
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");

    }

}
