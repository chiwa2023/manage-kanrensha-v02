package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

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

import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonHistoryResultRepository;

/**
 * EraseWkTblKanrenshaPersonHistoryTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("EraseWkTblKanrenshaPersonHistoryTaskletTest.sql")
class EraseWkTblKanrenshaPersonHistoryTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EraseWkTblKanrenshaPersonHistoryTasklet eraseWkTblKanrenshaPersonHistoryTasklet;

    /** 関連者個人履歴Repository */
    @Autowired
    private WkTblKanrenshaPersonHistoryRepository wkTblKanrenshaPersonHistoryRepository;

    /** 関連者個人履歴処理結果Repository */
    @Autowired
    private WkTblKanrenshaPersonHistoryResultRepository wkTblKanrenshaPersonHistoryResultRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        assertEquals(5, wkTblKanrenshaPersonHistoryRepository.count());
        assertEquals(5, wkTblKanrenshaPersonHistoryResultRepository.count());

        eraseWkTblKanrenshaPersonHistoryTasklet.beforeStep(this.getStepExecution());
        eraseWkTblKanrenshaPersonHistoryTasklet.execute(null, null);

        assertEquals(1, wkTblKanrenshaPersonHistoryRepository.count());
        assertEquals(1, wkTblKanrenshaPersonHistoryResultRepository.count());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}