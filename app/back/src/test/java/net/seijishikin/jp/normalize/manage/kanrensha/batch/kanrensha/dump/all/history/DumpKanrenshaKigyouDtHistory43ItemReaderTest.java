package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

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

/**
 * DumpKanrenshaKigyouDtHistory43ItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("DumpKanrenshaKigyouDtHistory43ItemReaderTest.sql")
class DumpKanrenshaKigyouDtHistory43ItemReaderTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DumpKanrenshaKigyouDtHistory43ItemReader dumpKanrenshaKigyouDtHistory43ItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        dumpKanrenshaKigyouDtHistory43ItemReader.beforeStep(this.getStepExecution());

        assertEquals(414, dumpKanrenshaKigyouDtHistory43ItemReader.read().getKanrenshaKigyouDtHistoryId());
        assertEquals(415, dumpKanrenshaKigyouDtHistory43ItemReader.read().getKanrenshaKigyouDtHistoryId());
        assertNull(dumpKanrenshaKigyouDtHistory43ItemReader.read());

    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("datetimeEnd", LocalDateTime.of(2025, 1, 1, 0, 0, 0)).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
