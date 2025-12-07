package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

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

import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaSeijidantaiAddMinResultRepository;

/**
 * EraseWkTblKanrenshaSeijidantaiAddMinTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("EraseWkTblKanrenshaSeijidantaiAddMinTaskletTest.sql")
class EraseWkTblKanrenshaSeijidantaiAddMinTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseWkTblKanrenshaSeijidantaiAddMinTasklet eraseWkTblKanrenshaSeijidantaiAddMinTasklet;

    /** 関連者政治団体登録最小限Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinRepository wkTblKanrenshaSeijidantaiAddMinRepository;

    /** 関連者政治団体最小登録処理結果Repository */
    @Autowired
    private WkTblKanrenshaSeijidantaiAddMinResultRepository wkTblKanrenshaSeijidantaiAddMinResultRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        assertEquals(5, wkTblKanrenshaSeijidantaiAddMinRepository.count());
        assertEquals(5, wkTblKanrenshaSeijidantaiAddMinResultRepository.count());

        eraseWkTblKanrenshaSeijidantaiAddMinTasklet.beforeStep(this.getStepExecution());
        eraseWkTblKanrenshaSeijidantaiAddMinTasklet.execute(null, null);

        assertEquals(1, wkTblKanrenshaSeijidantaiAddMinRepository.count());
        assertEquals(1, wkTblKanrenshaSeijidantaiAddMinResultRepository.count());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}