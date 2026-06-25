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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * PostalCodeCsvOneLineItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PostalCodeCsvOneLineItemWriterTest.sql")
class PostalCodeCsvOneLineItemReaderTest {

    /** テスト対象 */
    @Autowired
    private PostalCodeCsvOneLineItemReader postalCodeCsvOneLineItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        postalCodeCsvOneLineItemReader.beforeStep(execution);
        postalCodeCsvOneLineItemReader.open(execution.getExecutionContext());

        PostalCodeCsvOneLineDto csvDto0 = postalCodeCsvOneLineItemReader.read();
        assertEquals("01428", csvDto0.getLgCode());
        assertEquals("0691336", csvDto0.getPostalcode());
        assertEquals("北海道", csvDto0.getPref());
        assertEquals("夕張郡長沼町", csvDto0.getCity());
        assertEquals("栄町", csvDto0.getAddressOrg());

        // 26366,"61902","6190248","キョウトフ","ソウラクグンセイカチョウ","シンセイダイ","京都府","相楽郡精華町","新精台",0,0,1,0,1,3
        PostalCodeCsvOneLineDto csvDto1 = postalCodeCsvOneLineItemReader.read();
        assertEquals("26366", csvDto1.getLgCode());
        assertEquals("6190248", csvDto1.getPostalcode());
        assertEquals("京都府", csvDto1.getPref());
        assertEquals("相楽郡精華町", csvDto1.getCity());
        assertEquals("新精台", csvDto1.getAddressOrg());

        // 27211,"567","5670081","オオサカフ","イバラキシ","サイトアケボノ","大阪府","茨木市","彩都あけぼの",0,0,1,0,1,2
        PostalCodeCsvOneLineDto csvDto2 = postalCodeCsvOneLineItemReader.read();
        assertEquals("27211", csvDto2.getLgCode());
        assertEquals("5670081", csvDto2.getPostalcode());
        assertEquals("大阪府", csvDto2.getPref());
        assertEquals("茨木市", csvDto2.getCity());
        assertEquals("彩都あけぼの", csvDto2.getAddressOrg());

        assertNull(postalCodeCsvOneLineItemReader.read());
    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/postalcode/",
                "utf_add_2601.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePathOneLine", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
