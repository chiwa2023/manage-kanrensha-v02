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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;

/**
 * CallKigyouDtMasterEntityLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallKigyouDtMasterEntityLogicTest.sql")
class CallKigyouDtMasterEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallKigyouDtMasterEntityLogic callKigyouDtMasterEntityLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // テーブルに該当コードデータが存在しない(そのコードは最小で登録されている)
        final String codeNothing = "100";
        KanrenshaKigyouDtMasterEntity entity01 = callKigyouDtMasterEntityLogic.practice(codeNothing);
        assertEquals(codeNothing, entity01.getKigyouDtKanrenshaCode());
        assertEquals(0, entity01.getKanrenshaKigyouDtMasterId());

        // 一意のコードが取得される
        final String codeNormal = "200";
        KanrenshaKigyouDtMasterEntity entity02 = callKigyouDtMasterEntityLogic.practice(codeNormal);
        assertEquals(codeNormal, entity02.getKigyouDtKanrenshaCode());
        assertEquals(256, entity02.getKanrenshaKigyouDtMasterId());

        // 挿入データにエラーが存在する
        assertThrows(DataRetrievalFailureException.class, () -> callKigyouDtMasterEntityLogic.practice("300"));

        // 削除データを復活させるなど最新履歴を取得
        final String codeHistory = "400";
        KanrenshaKigyouDtMasterEntity entity03 = callKigyouDtMasterEntityLogic.practice(codeHistory);
        assertEquals(codeHistory, entity03.getKigyouDtKanrenshaCode());
        assertEquals(259, entity03.getKanrenshaKigyouDtMasterId());
    }

}
