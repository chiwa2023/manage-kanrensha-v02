package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * PostalCodeCsvJigyoushaItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PostalCodeCsvJigyoushaItemReaderTest {

    /** テスト対象 */
    @Autowired
    private PostalCodeCsvJigyoushaItemReader postalCodeCsvJigyoushaItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        postalCodeCsvJigyoushaItemReader.beforeStep(execution);
        postalCodeCsvJigyoushaItemReader.open(execution.getExecutionContext());

        // 01101,"ｲﾂﾊﾟﾝｻﾞｲﾀﾞﾝﾎｳｼﾞﾝ ｻﾂﾎﾟﾛｼｺｳﾂｳｼﾞｷﾞﾖｳｼﾝｺｳｺｳｼﾔ","一般財団法人
        // 札幌市交通事業振興公社","北海道","札幌市中央区","大通西","５丁目地下鉄大通駅西側コンコース内","0608614","060
        // ","札幌中央",0,0,0
        PostalCodeCsvJigyoushoDto csvDto0 = postalCodeCsvJigyoushaItemReader.read();
        assertEquals("01101", csvDto0.getLgCode());
        assertEquals("0608614", csvDto0.getPostalcode());
        assertEquals("北海道", csvDto0.getPref());
        assertEquals("札幌市中央区", csvDto0.getCity());
        assertEquals("大通西", csvDto0.getAddressOrg());
        assertEquals("５丁目地下鉄大通駅西側コンコース内", csvDto0.getAddressBlock());

        // 01101,"ｴ-ｱｲ-ｼﾞ-ｴｼﾞｿﾝｾｲﾒｲﾎｹﾝ ｶﾌﾞｼｷｶｲｼﾔ ﾎﾂｶｲﾄﾞｳｼﾞﾑｾﾝﾀ-","ＡＩＧエジソン生命保険 株式会社
        // 北海道事務センター","北海道","札幌市中央区","北一条西","４丁目２－２札幌ノースプラザ１０Ｆ","0608613","060
        // ","札幌中央",0,0,0
        PostalCodeCsvJigyoushoDto csvDto1 = postalCodeCsvJigyoushaItemReader.read();
        assertEquals("01101", csvDto1.getLgCode());
        assertEquals("0608613", csvDto1.getPostalcode());
        assertEquals("北海道", csvDto1.getPref());
        assertEquals("札幌市中央区", csvDto1.getCity());
        assertEquals("北一条西", csvDto1.getAddressOrg());
        assertEquals("４丁目２－２札幌ノースプラザ１０Ｆ", csvDto1.getAddressBlock());

        assertNull(postalCodeCsvJigyoushaItemReader.read());
    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/postalcode/",
                "JIGYOSYO_SAMPLE.CSV");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePathJigyousha", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
