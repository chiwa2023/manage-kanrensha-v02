package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.all;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode.ClearRsdtTableTasklet;

/**
 * ClearRsdtTableTasklet単体テスト
 * 
 * <p>
 * 本番データ構築用<br>
 * DBの向き先はテスト専用から本番用に切り替える<br>
 * </p>
 */
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// テーブルはクリアしない
@Transactional
class ClearRsdtTableTaskletAllTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ClearRsdtTableTasklet clearRsdtTableTasklet;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        clearRsdtTableTasklet.beforeStep(getStepExecution());

        assertDoesNotThrow(() -> clearRsdtTableTasklet.execute(null, null));
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDate("abolishDate", LocalDate.of(2022, 12, 5)).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
