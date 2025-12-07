package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

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

import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtHistoryRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaKigyouDtHistoryResultRepository;

/**
 * EraseWkTblKanrenshaKigyouDtHistoryTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("EraseWkTblKanrenshaKigyouDtHistoryTaskletTest.sql")
class EraseWkTblKanrenshaKigyouDtHistoryTaskletTest {

    /** テスト対象 */
    @Autowired
    private EraseWkTblKanrenshaKigyouDtHistoryTasklet eraseWkTblKanrenshaKigyouDtHistoryTasklet;

    /** 関連者企業・団体履歴Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtHistoryRepository wkTblKanrenshaKigyouDtHistoryRepository;

    /** 関連者企業・団体履歴処理結果Repository */
    @Autowired
    private WkTblKanrenshaKigyouDtHistoryResultRepository wkTblKanrenshaKigyouDtHistoryResultRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        assertEquals(5, wkTblKanrenshaKigyouDtHistoryRepository.count());
        assertEquals(5, wkTblKanrenshaKigyouDtHistoryResultRepository.count());

        eraseWkTblKanrenshaKigyouDtHistoryTasklet.beforeStep(this.getStepExecution());
        eraseWkTblKanrenshaKigyouDtHistoryTasklet.execute(null, null);

        assertEquals(1, wkTblKanrenshaKigyouDtHistoryRepository.count());
        assertEquals(1, wkTblKanrenshaKigyouDtHistoryResultRepository.count());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
