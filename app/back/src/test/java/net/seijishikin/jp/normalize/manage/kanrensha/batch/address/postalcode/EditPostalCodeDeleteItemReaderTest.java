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
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * EditPostalCodeDeleteItemReader単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EditPostalCodeDeleteItemReaderTest {

    /** テスト対象 */
    @Autowired
    private EditPostalCodeDeleteItemReader editPostalCodeDeleteItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        editPostalCodeDeleteItemReader.beforeStep(execution);
        editPostalCodeDeleteItemReader.open(execution.getExecutionContext());

        EditPostalCodeOneLineDto csvDto0 = editPostalCodeDeleteItemReader.read();
        assertEquals("01428", csvDto0.getLgCode());
        assertEquals("06913", csvDto0.getPostalcode5());
        assertEquals("0691336", csvDto0.getPostalcode7());
        assertEquals("ホッカイドウ", csvDto0.getPrefNameKana());
        assertEquals("ユウバリグンナガヌマチョウ", csvDto0.getCityNameKana());
        assertEquals("カワゾイ", csvDto0.getOrgNameKana());
        assertEquals("北海道", csvDto0.getPrefName());
        assertEquals("夕張郡長沼町", csvDto0.getCityName());
        assertEquals("川沿", csvDto0.getOrgName());

        assertEquals("0", csvDto0.getFlgProp1());
        assertEquals("0", csvDto0.getFlgProp2());
        assertEquals("0", csvDto0.getFlgProp3());
        assertEquals("1", csvDto0.getFlgProp4());

        assertEquals("2", csvDto0.getFlgKoushin());
        assertEquals("6", csvDto0.getFlgHenkouRiyu());

        EditPostalCodeOneLineDto csvDto1 = editPostalCodeDeleteItemReader.read();
        assertEquals("0691336", csvDto1.getPostalcode7());
        assertEquals("栄町", csvDto1.getOrgName());

        assertNull(editPostalCodeDeleteItemReader.read());
    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/postalcode/",
                "utf_del_2601.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePathDelete", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
