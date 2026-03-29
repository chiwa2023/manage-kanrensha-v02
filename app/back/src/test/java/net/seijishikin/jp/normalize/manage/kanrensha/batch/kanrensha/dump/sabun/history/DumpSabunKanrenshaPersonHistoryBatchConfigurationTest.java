package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.sabun.history;

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
 * DumpSabunKanrenshaPersonHistoryBatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("DumpSabunKanrenshaPersonHistoryBatchConfiguration.sql")
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class DumpSabunKanrenshaPersonHistoryBatchConfigurationTest {
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
    @Qualifier(DumpSabunKanrenshaPersonHistoryBatchConfiguration.JOB_NAME)
    @Autowired
    private Job dumpSabunKanrenshaPersonHistoryBatchConfiguration;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(DumpSabunKanrenshaPersonHistoryBatchConfiguration.JOB_NAME,
                dumpSabunKanrenshaPersonHistoryBatchConfiguration.getName(), "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        jobLauncherTestUtils.setJob(dumpSabunKanrenshaPersonHistoryBatchConfiguration);

        final String folder = "/sabun_person_history";

        JobParameters jobParameters = new JobParametersBuilder(dumpSabunKanrenshaPersonHistoryBatchConfiguration // NOPMD
                .getJobParametersIncrementer().getNext(new JobParameters()))
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addString("srcPath", Paths.get(storageFolder, folder).toString())
                .addLocalDateTime("datetimeStart", LocalDateTime.of(2024, 1, 1, 0, 0, 0))
                .addLocalDateTime("datetimeEnd", LocalDateTime.of(2025, 1, 1, 0, 0, 0))
                .addString("writeFilePath01",
                        Paths.get(storageFolder, folder, "sabun_person_history_01.csv").toString())
                .addString("writeFilePath02",
                        Paths.get(storageFolder, folder, "sabun_person_history_02.csv").toString())
                .addString("writeFilePath03",
                        Paths.get(storageFolder, folder, "sabun_person_history_03.csv").toString())
                .addString("writeFilePath04",
                        Paths.get(storageFolder, folder, "sabun_person_history_04.csv").toString())
                .addString("writeFilePath05",
                        Paths.get(storageFolder, folder, "sabun_person_history_05.csv").toString())
                .addString("writeFilePath06",
                        Paths.get(storageFolder, folder, "sabun_person_history_06.csv").toString())
                .addString("writeFilePath07",
                        Paths.get(storageFolder, folder, "sabun_person_history_07.csv").toString())
                .addString("writeFilePath08",
                        Paths.get(storageFolder, folder, "sabun_person_history_08.csv").toString())
                .addString("writeFilePath09",
                        Paths.get(storageFolder, folder, "sabun_person_history_09.csv").toString())
                .addString("writeFilePath10",
                        Paths.get(storageFolder, folder, "sabun_person_history_10.csv").toString())
                .addString("writeFilePath11",
                        Paths.get(storageFolder, folder, "sabun_person_history_11.csv").toString())
                .addString("writeFilePath12",
                        Paths.get(storageFolder, folder, "sabun_person_history_12.csv").toString())
                .addString("writeFilePath13",
                        Paths.get(storageFolder, folder, "sabun_person_history_13.csv").toString())
                .addString("writeFilePath14",
                        Paths.get(storageFolder, folder, "sabun_person_history_14.csv").toString())
                .addString("writeFilePath15",
                        Paths.get(storageFolder, folder, "sabun_person_history_15.csv").toString())
                .addString("writeFilePath16",
                        Paths.get(storageFolder, folder, "sabun_person_history_16.csv").toString())
                .addString("writeFilePath17",
                        Paths.get(storageFolder, folder, "sabun_person_history_17.csv").toString())
                .addString("writeFilePath18",
                        Paths.get(storageFolder, folder, "sabun_person_history_18.csv").toString())
                .addString("writeFilePath19",
                        Paths.get(storageFolder, folder, "sabun_person_history_19.csv").toString())
                .addString("writeFilePath20",
                        Paths.get(storageFolder, folder, "sabun_person_history_20.csv").toString())
                .addString("writeFilePath21",
                        Paths.get(storageFolder, folder, "sabun_person_history_21.csv").toString())
                .addString("writeFilePath22",
                        Paths.get(storageFolder, folder, "sabun_person_history_22.csv").toString())
                .addString("writeFilePath23",
                        Paths.get(storageFolder, folder, "sabun_person_history_23.csv").toString())
                .addString("writeFilePath24",
                        Paths.get(storageFolder, folder, "sabun_person_history_24.csv").toString())
                .addString("writeFilePath25",
                        Paths.get(storageFolder, folder, "sabun_person_history_25.csv").toString())
                .addString("writeFilePath26",
                        Paths.get(storageFolder, folder, "sabun_person_history_26.csv").toString())
                .addString("writeFilePath27",
                        Paths.get(storageFolder, folder, "sabun_person_history_27.csv").toString())
                .addString("writeFilePath28",
                        Paths.get(storageFolder, folder, "sabun_person_history_28.csv").toString())
                .addString("writeFilePath29",
                        Paths.get(storageFolder, folder, "sabun_person_history_29.csv").toString())
                .addString("writeFilePath30",
                        Paths.get(storageFolder, folder, "sabun_person_history_30.csv").toString())
                .addString("writeFilePath31",
                        Paths.get(storageFolder, folder, "sabun_person_history_31.csv").toString())
                .addString("writeFilePath32",
                        Paths.get(storageFolder, folder, "sabun_person_history_32.csv").toString())
                .addString("writeFilePath33",
                        Paths.get(storageFolder, folder, "sabun_person_history_33.csv").toString())
                .addString("writeFilePath34",
                        Paths.get(storageFolder, folder, "sabun_person_history_34.csv").toString())
                .addString("writeFilePath35",
                        Paths.get(storageFolder, folder, "sabun_person_history_35.csv").toString())
                .addString("writeFilePath36",
                        Paths.get(storageFolder, folder, "sabun_person_history_36.csv").toString())
                .addString("writeFilePath37",
                        Paths.get(storageFolder, folder, "sabun_person_history_37.csv").toString())
                .addString("writeFilePath38",
                        Paths.get(storageFolder, folder, "sabun_person_history_38.csv").toString())
                .addString("writeFilePath39",
                        Paths.get(storageFolder, folder, "sabun_person_history_39.csv").toString())
                .addString("writeFilePath40",
                        Paths.get(storageFolder, folder, "sabun_person_history_40.csv").toString())
                .addString("writeFilePath41",
                        Paths.get(storageFolder, folder, "sabun_person_history_41.csv").toString())
                .addString("writeFilePath42",
                        Paths.get(storageFolder, folder, "sabun_person_history_42.csv").toString())
                .addString("writeFilePath43",
                        Paths.get(storageFolder, folder, "sabun_person_history_43.csv").toString())
                .addString("writeFilePath44",
                        Paths.get(storageFolder, folder, "sabun_person_history_44.csv").toString())
                .addString("writeFilePath45",
                        Paths.get(storageFolder, folder, "sabun_person_history_45.csv").toString())
                .addString("writeFilePath46",
                        Paths.get(storageFolder, folder, "sabun_person_history_46.csv").toString())
                .addString("writeFilePath47",
                        Paths.get(storageFolder, folder, "sabun_person_history_47.csv").toString())
                .addString("writeFilePath99",
                        Paths.get(storageFolder, folder, "sabun_person_history_99.csv").toString())
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");
    }

}
