package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

/**
 * ClearRsdtTableTasklet単体テスト
 */
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ClearRsdtTableTaskletTest.sql")
class ClearRsdtTableTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ClearRsdtTableTasklet clearRsdtTableTasklet;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // MEMO テスト作成されるテーブルは存在しない地方自治体コードなので実施後に削除すること
        // 本番環境でこのテストを実行しなければ基本的に問題ない
        
        clearRsdtTableTasklet.beforeStep(getStepExecution());
        clearRsdtTableTasklet.execute(null, null);

        // MEMO 目視で確認(別にassertしてもいいですが…)
        // 1. 112233は作成されないこと(最新でない)
        // 2. 368521は作成されること
        // 3. 695123は作成されること
        // 4. 827637は作成されること(廃止は決定したが期限前)
        // 5. 742931は作成されるないこと(廃止されている)
        
        // テストデータを作成してあったテーブルaddress_rsdt_827637はドロップされている
        Query query = entityManager
                .createNativeQuery("SELECT COUNT(*) FROM address_rsdt_827637");
        assertEquals(0L, query.getSingleResult());

    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDate("abolishDate", LocalDate.of(2022, 12, 5)).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
