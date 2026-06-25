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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * SelectPostalCodeOtherItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SelectPostalCodeOtherItemReaderTest.sql")
class SelectPostalCodeOtherItemReaderTest {

    /** テスト対象 */
    @Autowired
    private SelectPostalCodeOtherItemReader selectPostalCodeOtherItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        selectPostalCodeOtherItemReader.beforeStep(execution);
        selectPostalCodeOtherItemReader.open(execution.getExecutionContext());

        // 常盤（その他）
        AddressPostalIrregularEntity entity0 = selectPostalCodeOtherItemReader.read();
        assertEquals("常盤（その他）", entity0.getAddressOrg());

        // 藤野（その他）
        AddressPostalIrregularEntity entity1 = selectPostalCodeOtherItemReader.read();
        assertEquals("藤野（その他）", entity1.getAddressOrg());

        // 新都心（次のビルを除く））
        AddressPostalIrregularEntity entity2 = selectPostalCodeOtherItemReader.read();
        assertEquals("新都心（次のビルを除く）", entity2.getAddressOrg());

        // 簾舞（その他）
        AddressPostalIrregularEntity entity3 = selectPostalCodeOtherItemReader.read();
        assertEquals("簾舞（その他）", entity3.getAddressOrg());

        // 5-6件目はなし
        AddressPostalIrregularEntity entity4 = selectPostalCodeOtherItemReader.read();
        assertEquals(null, entity4);
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("lgCodePref", "01").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }
}
