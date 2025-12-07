package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min;

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

import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonAddMinResultRepository;

/**
 * EraseWkTblKanrenshaPersonAddMinTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("EraseWkTblKanrenshaPersonAddMinTaskletTest.sql")
class EraseWkTblKanrenshaPersonAddMinTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EraseWkTblKanrenshaPersonAddMinTasklet eraseWkTblKanrenshaPersonAddMinTasklet;

    /** 関連者個人登録最小限Repository */
    @Autowired
    private WkTblKanrenshaPersonAddMinRepository wkTblKanrenshaPersonAddMinRepository;

    /** 関連者個人最小登録処理結果Repository */
    @Autowired
    private WkTblKanrenshaPersonAddMinResultRepository wkTblKanrenshaPersonAddMinResultRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        assertEquals(5, wkTblKanrenshaPersonAddMinRepository.count());
        assertEquals(5, wkTblKanrenshaPersonAddMinResultRepository.count());

        eraseWkTblKanrenshaPersonAddMinTasklet.beforeStep(this.getStepExecution());
        eraseWkTblKanrenshaPersonAddMinTasklet.execute(null, null);

        assertEquals(1, wkTblKanrenshaPersonAddMinRepository.count());
        assertEquals(1, wkTblKanrenshaPersonAddMinResultRepository.count());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}