package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * EditPostalCodeAddItemReader単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EditPostalCodeAddItemReaderTest {

    /** テスト対象 */
    @Autowired
    private EditPostalCodeAddItemReader editPostalCodeAddItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        editPostalCodeAddItemReader.beforeStep(execution);
        editPostalCodeAddItemReader.open(execution.getExecutionContext());

        EditPostalCodeOneLineDto csvDto0 = editPostalCodeAddItemReader.read();
        assertEquals("01428", csvDto0.getLgCode());
        assertEquals("06913", csvDto0.getPostalcode5());
        assertEquals("0691336", csvDto0.getPostalcode7());
        assertEquals("ホッカイドウ", csvDto0.getPrefNameKana());
        assertEquals("ユウバリグンナガヌマチョウ", csvDto0.getCityNameKana());
        assertEquals("サカエマチ", csvDto0.getOrgNameKana());
        assertEquals("北海道", csvDto0.getPrefName());
        assertEquals("夕張郡長沼町", csvDto0.getCityName());
        assertEquals("栄町", csvDto0.getOrgName());

        assertEquals("0", csvDto0.getFlgProp1());
        assertEquals("0", csvDto0.getFlgProp2());
        assertEquals("0", csvDto0.getFlgProp3());
        assertEquals("0", csvDto0.getFlgProp4());

        assertEquals("1", csvDto0.getFlgKoushin());
        assertEquals("5", csvDto0.getFlgHenkouRiyu());

        EditPostalCodeOneLineDto csvDto1 = editPostalCodeAddItemReader.read();
        assertEquals("6190248", csvDto1.getPostalcode7());
        assertEquals("新精台", csvDto1.getOrgName());

        EditPostalCodeOneLineDto csvDto2 = editPostalCodeAddItemReader.read();
        assertEquals("5670081", csvDto2.getPostalcode7());
        assertEquals("彩都あけぼの", csvDto2.getOrgName());

        assertNull(editPostalCodeAddItemReader.read());
    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/postalcode/",
                "utf_add_2601.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePathAdd", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
