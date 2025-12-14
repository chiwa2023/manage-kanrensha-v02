package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_std;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterResultEntity;

/**
 * MasterSeijidantaiAddStdWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterSeijidantaiAddStdWkTblFixProcessorTest.sql")
class MasterSeijidantaiAddStdWkTblFixProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MasterSeijidantaiAddStdWkTblFixProcessor masterSeijidantaiAddStdWkTblFixProcessor;

    @Test
    @Tag("TableTruncate")
    void testProcess() throws Exception {
        WkTblKanrenshaSeijidantaiMasterResultEntity resultEntity = new WkTblKanrenshaSeijidantaiMasterResultEntity();
        resultEntity.setWkTblKanrenshaSeijidantaiMasterId(7001); // 既存データのID

        WkTblKanrenshaSeijidantaiMasterEntity processedEntity = masterSeijidantaiAddStdWkTblFixProcessor.process(resultEntity);

        assertNotNull(processedEntity);
        assertEquals(true, processedEntity.getIsFinish());
        assertEquals("正常終了", processedEntity.getJudgeReason());
        assertEquals(7001, processedEntity.getWkTblKanrenshaSeijidantaiMasterId());
    }
}
