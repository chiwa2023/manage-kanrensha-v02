package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.min;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.BackApplication;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan.RecordTaskPlanJobExecutionListner;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * DumpMinSeijidantaiBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DumpMinSeijidantaiBatchConfigurationTest.sql")
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class DumpMinSeijidantaiBatchConfigurationTest {
    // CHECKSTYLE:OFF MagicNumber

    /** propertiesからインジェクションされた最上位保存フォルダ絶対パス */
    private String storageFolder;

    /**
     * 最上位保存フォルダ絶対パスを取得する
     *
     * @return 最上位保存フォルダ絶対パス
     */
    public String getStorageFolder() {
        return storageFolder;
    }

    /**
     * 最上位保存フォルダ絶対パスを設定する
     *
     * @param storageFolder 最上位保存フォルダ絶対パス
     */
    public void setStorageFolder(final String storageFolder) {
        this.storageFolder = storageFolder;
    }

    /** テストユーティリティ */
    @Autowired
    private JobOperatorTestUtils jobOperatorTestUtils;

    /** 起動をするJob */
    @Qualifier(DumpMinSeijidantaiBatchConfiguration.JOB_NAME)
    @Autowired
    private Job dumpMinSeijidantaiBatchConfiguration;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(DumpMinSeijidantaiBatchConfiguration.JOB_NAME, dumpMinSeijidantaiBatchConfiguration.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        jobOperatorTestUtils.setJob(dumpMinSeijidantaiBatchConfiguration);

        JobParameters jobParameters = new JobParametersBuilder(
                dumpMinSeijidantaiBatchConfiguration.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addLocalDateTime("datetimeEnd", LocalDateTime.of(2024, 1, 1, 0, 0, 0))
                .addString("writeFilePath", Paths.get(storageFolder, "seijidantai_min.csv").toString())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM, (long) userDto.getUserPersonId())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM, (long) userDto.getUserPersonCode())
                .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) 2026) //
                .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) 453) //
                .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) 187).toJobParameters();

        @SuppressWarnings("removal")
        JobExecution jobExecution = jobOperatorTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");
    }

}
