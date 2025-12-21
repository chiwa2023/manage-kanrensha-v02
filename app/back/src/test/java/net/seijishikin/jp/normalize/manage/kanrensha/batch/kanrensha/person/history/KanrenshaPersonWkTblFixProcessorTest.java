package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryResultEntity;

/**
 * KanrenshaPersonWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaPersonWkTblFixProcessorTest.sql")
class KanrenshaPersonWkTblFixProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaPersonWkTblFixProcessor kanrenshaPersonWkTblFixProcessor;

    @Test
    @Tag("TableTruncate")
    void testProcess() throws Exception {
        WkTblKanrenshaPersonHistoryResultEntity resultEntity = new WkTblKanrenshaPersonHistoryResultEntity();
        resultEntity.setWkKanrenshaPersonHistoryId(3001);
        resultEntity.setIsAffected(true);
        resultEntity.setJudgeReason("更新理由");

        WkTblKanrenshaPersonHistoryEntity processedEntity = kanrenshaPersonWkTblFixProcessor.process(resultEntity);

        assertNotNull(processedEntity);
        assertEquals(3001, processedEntity.getWkKanrenshaPersonHistoryId());
        assertEquals(true, processedEntity.getIsAffected());
        assertEquals("更新理由", processedEntity.getJudgeReason());
    }
}