package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_std;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterResultEntity;

/**
 * MasterKigyouDtAddStdWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("MasterKigyouDtAddStdWkTblFixProcessorTest.sql")
class MasterKigyouDtAddStdWkTblFixProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MasterKigyouDtAddStdWkTblFixProcessor masterKigyouDtAddStdWkTblFixProcessor;

    @Test
    @Tag("TableTruncate")
    void testProcess() throws Exception {
        WkTblKanrenshaKigyouDtMasterResultEntity resultEntity = new WkTblKanrenshaKigyouDtMasterResultEntity();
        resultEntity.setWkTblKanrenshaKigyouDtMasterId(1001); // 既存データのID

        WkTblKanrenshaKigyouDtMasterEntity processedEntity = masterKigyouDtAddStdWkTblFixProcessor.process(resultEntity);

        assertNotNull(processedEntity);
        assertEquals(true, processedEntity.getIsFinish());
        assertEquals("正常終了", processedEntity.getJudgeReason());
        assertEquals(1001, processedEntity.getWkTblKanrenshaKigyouDtMasterId());
    }
}
