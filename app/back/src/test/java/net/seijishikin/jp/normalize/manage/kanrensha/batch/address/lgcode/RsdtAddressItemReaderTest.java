package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

/**
 * RsdtAddressItemReader単体テスト
 */
@SpringBootTest
@SpringBatchTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RsdtAddressItemReaderTest.sql")
class RsdtAddressItemReaderTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private RsdtAddressItemReader rsdtAddressItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecution();

        StepScopeTestUtils.doInStepScope(stepExecution, () -> {

            rsdtAddressItemReader.open(stepExecution.getExecutionContext());
            rsdtAddressItemReader.setPageSize(100);

            assertEquals(624, rsdtAddressItemReader.read().getAddressRsdtId());
            assertEquals(625, rsdtAddressItemReader.read().getAddressRsdtId());
            assertNull(rsdtAddressItemReader.read());

            rsdtAddressItemReader.close();

            return null;
        });
    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("lgCode", "827637").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
