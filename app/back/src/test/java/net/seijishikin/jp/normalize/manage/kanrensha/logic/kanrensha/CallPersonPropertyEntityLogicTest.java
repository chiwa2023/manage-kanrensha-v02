package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;

/**
 * CallPersonPropertyEntityLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallPersonPropertyEntityLogicTest.sql")
class CallPersonPropertyEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallPersonPropertyEntityLogic callPersonPropertyEntityLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // テーブルに該当コードデータが存在しない(そのコードは最小で登録されている)
        final String codeNothing = "100";
        KanrenshaPersonPropertyEntity entity01 = callPersonPropertyEntityLogic.practice(codeNothing);
        assertEquals(codeNothing, entity01.getPersonKanrenshaCode());
        assertEquals(0, entity01.getKanrenshaPersonPropertyId());

        // 一意のコードが取得される
        final String codeNormal = "200";
        KanrenshaPersonPropertyEntity entity02 = callPersonPropertyEntityLogic.practice(codeNormal);
        assertEquals(codeNormal, entity02.getPersonKanrenshaCode());
        assertEquals(256, entity02.getKanrenshaPersonPropertyId());

        // 挿入データにエラーが存在する
        assertThrows(DataRetrievalFailureException.class, () -> callPersonPropertyEntityLogic.practice("300"));

        // 削除データを復活させるなど最新履歴を取得
        final String codeHistory = "400";
        KanrenshaPersonPropertyEntity entity03 = callPersonPropertyEntityLogic.practice(codeHistory);
        assertEquals(codeHistory, entity03.getPersonKanrenshaCode());
        assertEquals(259, entity03.getKanrenshaPersonPropertyId());

    }

}
