package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.all;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.BackApplication;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block.RepairPostalCodeIrregularBatchConfiguration;

/**
 * 郵便番号以降地区処理
 * 
 * <p>
 * 本番データ構築用<br>
 * DBの向き先はテスト専用から本番用に切り替える<br>
 * </p>
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// テーブルクリアはしない
class RepairPostalCodeIrregularBatchConfigurationAllTest {

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(RepairPostalCodeIrregularBatchConfiguration.JOB_NAME)
    @Autowired
    private Job repairPostalCodeIrregularBatchConfiguration;

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        jobLauncherTestUtils.setJob(repairPostalCodeIrregularBatchConfiguration);

        LeastUserDto userDto = CreateSystemInitialUserUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder(
                repairPostalCodeIrregularBatchConfiguration.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addString("lgCodePref", "131") //
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");

    }
}
