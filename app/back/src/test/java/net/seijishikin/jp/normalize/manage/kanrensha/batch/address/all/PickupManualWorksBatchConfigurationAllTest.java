package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.all;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.BackApplication;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block.PickupManualWorksBatchConfiguration;

/**
 * PickupManualWorksBatchConfigurationテスト実行用
 */
@SpringJUnitConfig
@SpringBootTest
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("PickupManualWorksBatchConfigurationAllTest.sql")
class PickupManualWorksBatchConfigurationAllTest {

    /** テストユーティリティ */
    @Autowired
    private JobOperatorTestUtils jobOperatorTestUtils;

    /** 起動をするJob */
    @Qualifier(PickupManualWorksBatchConfiguration.JOB_NAME)
    @Autowired
    private Job pickupManualWorksBatchConfiguration;

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        jobOperatorTestUtils.setJob(pickupManualWorksBatchConfiguration);

        LeastUserDto userDto = CreateSystemInitialUserUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder(
                pickupManualWorksBatchConfiguration.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addString("lgCodePref", "01") //
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM,
                        Long.parseLong(userDto.getUserPersonId().toString()))
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM,
                        Long.parseLong(userDto.getUserPersonCode().toString()))
                .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                .toJobParameters();

        @SuppressWarnings("removal")
        JobExecution jobExecution = jobOperatorTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");
    }

}
