package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonMasterResultEntity;

/**
 * MasterPersonAddStdWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterPersonAddStdWkTblFixProcessorTest.sql")
class MasterPersonAddStdWkTblFixProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MasterPersonAddStdWkTblFixProcessor masterPersonAddStdWkTblFixProcessor;

    @Test
    @Tag("TableTruncate")
    void testProcess() throws Exception {
        WkTblKanrenshaPersonMasterResultEntity resultEntity = new WkTblKanrenshaPersonMasterResultEntity();
        resultEntity.setWkTblKanrenshaPersonMasterId(4001); // 既存データのID

        WkTblKanrenshaPersonMasterEntity processedEntity = masterPersonAddStdWkTblFixProcessor.process(resultEntity);

        assertNotNull(processedEntity);
        assertEquals(true, processedEntity.getIsFinish());
        assertEquals("正常終了", processedEntity.getJudgeReason());
        assertEquals(4001, processedEntity.getWkTblKanrenshaPersonMasterId());
    }
}
