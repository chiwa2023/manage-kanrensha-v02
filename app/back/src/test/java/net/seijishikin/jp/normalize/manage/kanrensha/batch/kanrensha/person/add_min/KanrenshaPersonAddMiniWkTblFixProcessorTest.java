package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinResultEntity;

/**
 * KanrenshaPersonAddMiniWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaPersonAddMiniWkTblFixProcessorTest.sql")
class KanrenshaPersonAddMiniWkTblFixProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaPersonAddMiniWkTblFixProcessor kanrenshaPersonAddMiniWkTblFixProcessor;

    @Test
    @Tag("TableTruncate")
    void testProcess() throws Exception {
        WkTblKanrenshaPersonAddMinResultEntity resultEntity = new WkTblKanrenshaPersonAddMinResultEntity();
        resultEntity.setWkTblKanrenshaPersonAddMinId(2001); // 既存データのID

        WkTblKanrenshaPersonAddMinEntity processedEntity = kanrenshaPersonAddMiniWkTblFixProcessor.process(resultEntity);

        assertNotNull(processedEntity);
        assertEquals(true, processedEntity.getIsFinish());
        assertEquals("正常終了", processedEntity.getJudgeReason());
        assertEquals(2001, processedEntity.getWkTblKanrenshaPersonAddMinId());
    }
}