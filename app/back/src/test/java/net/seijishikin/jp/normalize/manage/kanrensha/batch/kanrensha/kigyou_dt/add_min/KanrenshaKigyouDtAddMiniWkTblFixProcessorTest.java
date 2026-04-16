package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinResultEntity;

/**
 * KanrenshaKigyouDtAddMiniWkTblFixProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("KanrenshaKigyouDtAddMiniWkTblFixProcessorTest.sql")
class KanrenshaKigyouDtAddMiniWkTblFixProcessorTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private KanrenshaKigyouDtAddMiniWkTblFixProcessor kanrenshaKigyouDtAddMiniWkTblFixProcessor;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        WkTblKanrenshaKigyouDtAddMinResultEntity minResultEntity = new WkTblKanrenshaKigyouDtAddMinResultEntity();
        minResultEntity.setWkTblKanrenshaKigyouDtAddMinId(3);

        WkTblKanrenshaKigyouDtAddMinEntity entity = kanrenshaKigyouDtAddMiniWkTblFixProcessor.process(minResultEntity);

        assertEquals(true, entity.getIsFinish());
        assertEquals("正常終了", entity.getJudgeReason());
    }

}
