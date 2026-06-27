package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.all;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.BackApplication;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode.WalkDirectoryParcelAddressInsertBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * WalkDirectoryParcelAddressInsertBatchConfiguration全実行
 * 
 * <p>
 * 本番データ構築用<br>
 * DBの向き先はテスト専用から本番用に切り替える<br>
 * </p>
 */
@SpringJUnitConfig
@SpringBootTest
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class WalkDirectoryParcelAddressInsertBatchConfigurationAllTest {

    /** テストユーティリティ */
    @Autowired
    private JobOperatorTestUtils jobOperatorTestUtils;

    /** 起動をするJob */
    @Qualifier(WalkDirectoryParcelAddressInsertBatchConfiguration.JOB_NAME)
    @Autowired
    private Job walkDirectoryParcelAddressInsert;

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        jobOperatorTestUtils.setJob(walkDirectoryParcelAddressInsert);

        Path pathRoot = Paths.get(GetCurrentResourcePath.getBackSrcPath(""));
        Path pathBase = Paths.get(pathRoot.getParent().getParent().getParent().toString(),
                "/config/file_address/base_registory/temp/parcel/");

        // 処理する県の地方自治体コード
        final String prefCode = "13";

        Path path = Paths.get(pathBase.toString(), prefCode);

        LeastUserDto userDto = CreateSystemInitialUserUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder(
                walkDirectoryParcelAddressInsert.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addString("readDirectory", path.toString())
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        @SuppressWarnings("removal")
        JobExecution jobExecution = jobOperatorTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");

    }

}
