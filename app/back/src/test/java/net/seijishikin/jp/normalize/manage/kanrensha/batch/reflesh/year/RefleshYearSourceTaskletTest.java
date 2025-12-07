package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.year;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * RefleshYearSourceTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RefleshYearSourceTaskletTest {

    /** テスト対象 */
    @Autowired
    private RefleshYearSourceTasklet refleshYearSourceTasklet;

    @Test
    void test() throws Exception {

        final Long srcYear = 2025L;
        final Long copyYear = 2026L;

        refleshYearSourceTasklet.beforeStep(this.getStepExecution(srcYear, copyYear));
        refleshYearSourceTasklet.execute(null, null);

        // TODO テストコード
        
        fail("Not yet implemented");
    }

    private StepExecution getStepExecution(final Long srcYear, final Long copyYear) {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("exe_datetitme", LocalDateTime.now()) //
                .addLong(RefleshYearSourceTasklet.KEY_SRC_YEAR, srcYear) //
                .addLong(RefleshYearSourceTasklet.KEY_COPY_YEAR, copyYear)
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }
}
