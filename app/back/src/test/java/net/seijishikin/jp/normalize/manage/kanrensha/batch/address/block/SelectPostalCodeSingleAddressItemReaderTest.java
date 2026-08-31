package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * SelectPostalCodeSingleAddressItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SelectPostalCodeSingleAddressItemReaderTest.sql")
class SelectPostalCodeSingleAddressItemReaderTest {

    /** テスト対象 */
    @Autowired
    private SelectPostalCodeSingleAddressItemReader selectPostalCodeSingleAddressItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        selectPostalCodeSingleAddressItemReader.beforeStep(execution);
        selectPostalCodeSingleAddressItemReader.open(execution.getExecutionContext());

        AddressPostalIrregularEntity entity0 = selectPostalCodeSingleAddressItemReader.read();
        assertEquals("八幡町（五の沢）", entity0.getAddressOrg());

        AddressPostalIrregularEntity entity1 = selectPostalCodeSingleAddressItemReader.read();
        assertEquals("八幡町（高岡）", entity1.getAddressOrg());

        AddressPostalIrregularEntity entity2 = selectPostalCodeSingleAddressItemReader.read();
        assertEquals(null, entity2);
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("lgCodePref", "01").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }
}
