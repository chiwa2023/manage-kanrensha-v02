package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
 * ChoicePostalCodeIrregularItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ChoicePostalCodeIrregularItemReaderTest.sql")
class ChoicePostalCodeIrregularItemReaderTest {

    /** テスト対象 */
    @Autowired
    private ChoicePostalCodeIrregularItemReader choicePostalCodeIrregularItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        choicePostalCodeIrregularItemReader.beforeStep(execution);
        choicePostalCodeIrregularItemReader.open(execution.getExecutionContext());

        AddressPostalIrregularEntity entity00 = choicePostalCodeIrregularItemReader.read();

        assertEquals("011011", entity00.getLgCode());
        assertEquals("060", entity00.getPostalcode1());
        assertEquals("0042", entity00.getPostalcode2());
        assertEquals("大通西（１〜１９丁目）", entity00.getAddressOrg());
        assertEquals("札幌市中央区大通西", entity00.getAddressName());
        assertEquals("", entity00.getAddressPostal());
        assertEquals("", entity00.getAddressBlock());

        assertNull(choicePostalCodeIrregularItemReader.read());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("lgCodePref", "01").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
