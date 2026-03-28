package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.block;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * UpdatePostalCodeOtherItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("UpdatePostalCodeOtherItemReaderTest.sql")
class UpdatePostalCodeOtherItemReaderTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdatePostalCodeOtherItemReader updatePostalCodeOtherItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        updatePostalCodeOtherItemReader.beforeStep(execution);
        updatePostalCodeOtherItemReader.open(execution.getExecutionContext());

        AddressPostalEntity entity0 = updatePostalCodeOtherItemReader.read();
        assertEquals(853, entity0.getAddressPostalId());

        AddressPostalEntity entity1 = updatePostalCodeOtherItemReader.read();
        assertEquals(854, entity1.getAddressPostalId());

        AddressPostalEntity entity2 = updatePostalCodeOtherItemReader.read();
        assertEquals(null, entity2);
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("lgCodePref", "01").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
