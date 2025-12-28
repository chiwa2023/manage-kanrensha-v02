package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.sabun.history;

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
 * DumpSabunKanrenshaSeijidantaiHistory26ItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("DumpSabunKanrenshaSeijidantaiHistory26ItemReaderTest.sql")
class DumpSabunKanrenshaSeijidantaiHistory26ItemReaderTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DumpSabunKanrenshaSeijidantaiHistory26ItemReader dumpSabunKanrenshaSeijidantaiHistory26ItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        dumpSabunKanrenshaSeijidantaiHistory26ItemReader.beforeStep(this.getStepExecution());

        assertEquals(639, dumpSabunKanrenshaSeijidantaiHistory26ItemReader.read().getKanrenshaSeijidantaiHistoryId());
        assertNull(dumpSabunKanrenshaSeijidantaiHistory26ItemReader.read());
    }

    private StepExecution getStepExecution() {
        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("datetimeStart", LocalDateTime.of(2024, 1, 1, 0, 0, 0))
                .addLocalDateTime("datetimeEnd", LocalDateTime.of(2025, 1, 1, 0, 0, 0)).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
