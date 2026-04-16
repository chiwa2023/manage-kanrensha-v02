package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryResultEntity;

/**
 * KanrenshaKigyouDtWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaKigyouDtWkTblFixProcessorTest.sql")
class KanrenshaKigyouDtWkTblFixProcessorTest {
    // CHECKSTYLE:OFF MaagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtWkTblFixProcessor kanrenshaKigyouDtWkTblFixProcessor;

    @Test
    @Tag("TableTruncate")
    void testProcess() throws Exception {
        WkTblKanrenshaKigyouDtHistoryResultEntity resultEntity = new WkTblKanrenshaKigyouDtHistoryResultEntity();
        resultEntity.setWkKanrenshaKigyouDtHistoryId(1001);
        resultEntity.setIsAffected(true);
        resultEntity.setJudgeReason("更新理由");

        WkTblKanrenshaKigyouDtHistoryEntity processedEntity = kanrenshaKigyouDtWkTblFixProcessor.process(resultEntity);

        assertNotNull(processedEntity);
        assertEquals(1001, processedEntity.getWkKanrenshaKigyouDtHistoryId());
        assertEquals(true, processedEntity.getIsAffected());
        assertEquals("更新理由", processedEntity.getJudgeReason());
    }
}
