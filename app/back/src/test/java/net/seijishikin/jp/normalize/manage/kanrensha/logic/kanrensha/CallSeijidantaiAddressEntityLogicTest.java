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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;

/**
 * CallSeijidantaiAddressEntityLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallSeijidantaiAddressEntityLogicTest.sql")
class CallSeijidantaiAddressEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallSeijidantaiAddressEntityLogic callSeijidantaiAddressEntityLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // テーブルに該当コードデータが存在しない(そのコードは最小で登録されている)
        final String codeNothing = "100";
        KanrenshaSeijidantaiAddressEntity entity01 = callSeijidantaiAddressEntityLogic.practice(codeNothing);
        assertEquals(codeNothing, entity01.getSeijidantaiKanrenshaCode());
        assertEquals(0, entity01.getKanrenshaSeijidantaiAddressId());

        // 一意のコードが取得される
        final String codeNormal = "200";
        KanrenshaSeijidantaiAddressEntity entity02 = callSeijidantaiAddressEntityLogic.practice(codeNormal);
        assertEquals(codeNormal, entity02.getSeijidantaiKanrenshaCode());
        assertEquals(256, entity02.getKanrenshaSeijidantaiAddressId());

        // 挿入データにエラーが存在する
        assertThrows(DataRetrievalFailureException.class, () -> callSeijidantaiAddressEntityLogic.practice("300"));

        // 削除データを復活させるなど最新履歴を取得
        final String codeHistory = "400";
        KanrenshaSeijidantaiAddressEntity entity03 = callSeijidantaiAddressEntityLogic.practice(codeHistory);
        assertEquals(codeHistory, entity03.getSeijidantaiKanrenshaCode());
        assertEquals(259, entity03.getKanrenshaSeijidantaiAddressId());
    }

}
