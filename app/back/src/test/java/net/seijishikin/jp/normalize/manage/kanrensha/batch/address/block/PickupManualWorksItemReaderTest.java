package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;

/**
 * PickupManualWorksItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PickupManualWorksItemReaderTest.sql")
class PickupManualWorksItemReaderTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private PickupManualWorksItemReader pickupManualWorksItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        pickupManualWorksItemReader.beforeStep(execution);
        pickupManualWorksItemReader.open(execution.getExecutionContext());

        AddressPostalEntity entity0 = pickupManualWorksItemReader.read();
        assertEquals(243, entity0.getAddressPostalId());

        AddressPostalEntity entity1 = pickupManualWorksItemReader.read();
        assertEquals(244, entity1.getAddressPostalId());

        assertNull(pickupManualWorksItemReader.read());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("lgCodePref", "01").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
