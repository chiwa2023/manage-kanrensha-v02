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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;

/**
 * CallPersonMasterEntityLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallPersonMasterEntityLogicTest.sql")
class CallPersonMasterEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallPersonMasterEntityLogic callPersonMasterEntityLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // テーブルに該当コードデータが存在しない(そのコードは最小で登録されている)
        final String codeNothing = "100";
        KanrenshaPersonMasterEntity entity01 = callPersonMasterEntityLogic.practice(codeNothing);
        assertEquals(codeNothing, entity01.getPersonKanrenshaCode());
        assertEquals(0, entity01.getKanrenshaPersonMasterId());

        // 一意のコードが取得される
        final String codeNormal = "200";
        KanrenshaPersonMasterEntity entity02 = callPersonMasterEntityLogic.practice(codeNormal);
        assertEquals(codeNormal, entity02.getPersonKanrenshaCode());
        assertEquals(256, entity02.getKanrenshaPersonMasterId());

        // 挿入データにエラーが存在する
        assertThrows(DataRetrievalFailureException.class, () -> callPersonMasterEntityLogic.practice("300"));

        // 削除データを復活させるなど最新履歴を取得
        final String codeHistory = "400";
        KanrenshaPersonMasterEntity entity03 = callPersonMasterEntityLogic.practice(codeHistory);
        assertEquals(codeHistory, entity03.getPersonKanrenshaCode());
        assertEquals(259, entity03.getKanrenshaPersonMasterId());
    }

}
