package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.sabun.master;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.BackApplication;

/**
 * DumpSabunMasterKigyouDtBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DumpSabunMasterKigyouDtBatchConfigurationTest.sql")
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class DumpSabunMasterKigyouDtBatchConfigurationTest {
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
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(DumpSabunMasterKigyouDtBatchConfiguration.JOB_NAME)
    @Autowired
    private Job dumpSabunMasterKigyouDtBatchConfiguration;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(DumpSabunMasterKigyouDtBatchConfiguration.JOB_NAME,
                dumpSabunMasterKigyouDtBatchConfiguration.getName(), "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        jobLauncherTestUtils.setJob(dumpSabunMasterKigyouDtBatchConfiguration);

        JobParameters jobParameters = new JobParametersBuilder(
                dumpSabunMasterKigyouDtBatchConfiguration.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addLocalDateTime("datetimeStart", LocalDateTime.of(2024, 1, 1, 0, 0, 0))
                .addLocalDateTime("datetimeEnd", LocalDateTime.of(2025, 1, 1, 0, 0, 0))
                .addString("writeFilePath", Paths.get(storageFolder, "sabun_master_kigyou_dt.csv").toString())
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");
    }

}
