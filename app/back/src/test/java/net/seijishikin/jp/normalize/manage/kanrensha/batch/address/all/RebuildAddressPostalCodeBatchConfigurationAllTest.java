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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.BackApplication;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode.RebuildAddressPostalCodeBatchConfiguration;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * 郵便番号再構成処理
 *
 * <p>
 * 本番データ構築用<br>
 * DBの向き先はテスト専用から本番用に切り替える<br>
 * 「英数＋条」の住所は漢数字に変換する。特に北海道(データの段階で修正)<br>
 * 「（東・西・南・北）」の住所は〇丁目～〇丁目に展開する(データの段階で修正)<br>
 * 建物住所が「×号館」となっていて、番地住所に号がない場合は号を補う<br>
 * 処理後に事業所データで全角入れ位置がおかしいものがどうしても出てしまう(３丁目 左7とか)ので修正する<br>
 * </p>
 * 
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RebuildAddressPostalCodeBatchConfigurationAllTest.sql")
class RebuildAddressPostalCodeBatchConfigurationAllTest {

    /** テストユーティリティ */
    @Autowired
    private JobOperatorTestUtils jobOperatorTestUtils;

    /** 起動をするJob */
    @Qualifier(RebuildAddressPostalCodeBatchConfiguration.JOB_NAME)
    @Autowired
    private Job rebuildAddressPostalCode;

    @Test
    @Tag("TableTruncate")
    void testExecute() throws Exception {

        jobOperatorTestUtils.setJob(rebuildAddressPostalCode);

        // 本番用データ作成ファイル指定
        Path pathRoot = Paths.get(GetCurrentResourcePath.getBackSrcPath(""));
        Path pathBase = Paths.get(pathRoot.getParent().getParent().getParent().toString(),
                "/config/file_address/postal/");

        Path pathOneLine = Paths.get(pathBase.toString(), "/utf_ken_all/", "utf_ken_all.csv");
        Path pathJigyousha = Paths.get(pathBase.toString(), "/jigyousho/", "JIGYOSYO.CSV");
        // Path pathOneLine = Paths.get(pathBase.toString(),"/utf_ken_all/"
        // ,"temp.csv");
        // Path pathJigyousha = Paths.get(pathBase.toString(),"/jigyousho/"
        // ,"temp.csv");

        LeastUserDto userDto = CreateSystemInitialUserUtil.practice();

        // 最初の1回だけは初期化して、残りは@Sqlを無効化する

        JobParameters jobParameters = new JobParametersBuilder(
                rebuildAddressPostalCode.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now())
                .addString("readFilePathOneLine", pathOneLine.toString())
                .addString("readFilePathJigyousha", pathJigyousha.toString()).addString("lgCode", "131")
                .addLong("userId", (long) userDto.getUserPersonId())
                .addLong("userCode", (long) userDto.getUserPersonCode())
                .addString("userName", userDto.getUserPersonName()).toJobParameters();

        JobExecution jobExecution = jobOperatorTestUtils.startJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");

    }

}
