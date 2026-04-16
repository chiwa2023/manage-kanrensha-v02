package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

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

import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.WkTblKanrenshaPersonMasterResultRepository;

/**
 * EraseWkTblMasterPersonAddStdTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("EraseWkTblMasterPersonAddStdTaskletTest.sql")
class EraseWkTblMasterPersonAddStdTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private EraseWkTblMasterPersonAddStdTasklet eraseWkTblMasterPersonAddStdTasklet;

    /** 関連者個人登録標準Repository */
    @Autowired
    private WkTblKanrenshaPersonMasterRepository wkTblKanrenshaPersonMasterRepository;

    /** 関連者個人標準登録処理結果Repository */
    @Autowired
    private WkTblKanrenshaPersonMasterResultRepository wkTblKanrenshaPersonMasterResultRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        assertEquals(5, wkTblKanrenshaPersonMasterRepository.count());
        assertEquals(5, wkTblKanrenshaPersonMasterResultRepository.count());

        eraseWkTblMasterPersonAddStdTasklet.beforeStep(this.getStepExecution());
        eraseWkTblMasterPersonAddStdTasklet.execute(null, null);

        assertEquals(1, wkTblKanrenshaPersonMasterRepository.count());
        assertEquals(1, wkTblKanrenshaPersonMasterResultRepository.count());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userCode", 190L).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}