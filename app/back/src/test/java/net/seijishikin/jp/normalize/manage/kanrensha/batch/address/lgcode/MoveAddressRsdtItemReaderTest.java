package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

/**
 * MoveAddressRsdtItemReader単体テスト
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveAddressRsdtItemReaderTest.sql")
class MoveAddressRsdtItemReaderTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MoveAddressRsdtItemReader moveAddressRsdtItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecution();

        StepScopeTestUtils.doInStepScope(stepExecution, () -> {

            moveAddressRsdtItemReader.open(stepExecution.getExecutionContext());
            moveAddressRsdtItemReader.setPageSize(100);

            assertEquals(623, moveAddressRsdtItemReader.read().getAddressRsdtId());
            assertEquals(625, moveAddressRsdtItemReader.read().getAddressRsdtId());
            assertNull(moveAddressRsdtItemReader.read());

            moveAddressRsdtItemReader.close();

            return null;
        });
    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()) //
                .addString("srcLgCode", "827637").addString("copyLgCode", "827637").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
