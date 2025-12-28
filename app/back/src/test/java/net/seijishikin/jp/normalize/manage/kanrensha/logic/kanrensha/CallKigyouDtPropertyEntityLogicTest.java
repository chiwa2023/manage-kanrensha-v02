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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;

/**
 * CallKigyouDtPropertyEntityLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallKigyouDtPropertyEntityLogicTest.sql")
class CallKigyouDtPropertyEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallKigyouDtPropertyEntityLogic callKigyouDtPropertyEntityLogic;
    
    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        // テーブルに該当コードデータが存在しない(そのコードは最小で登録されている)
        final String codeNothing = "100";
        KanrenshaKigyouDtPropertyEntity entity01 = callKigyouDtPropertyEntityLogic.practice(codeNothing);
        assertEquals(codeNothing, entity01.getKigyouDtKanrenshaCode());
        assertEquals(0, entity01.getKanrenshaKigyouDtPropertyId());

        // 一意のコードが取得される
        final String codeNormal = "200";
        KanrenshaKigyouDtPropertyEntity entity02 = callKigyouDtPropertyEntityLogic.practice(codeNormal);
        assertEquals(codeNormal, entity02.getKigyouDtKanrenshaCode());
        assertEquals(256, entity02.getKanrenshaKigyouDtPropertyId());

        // 挿入データにエラーが存在する
        assertThrows(DataRetrievalFailureException.class, () -> callKigyouDtPropertyEntityLogic.practice("300"));

        // 削除データを復活させるなど最新履歴を取得
        final String codeHistory = "400";
        KanrenshaKigyouDtPropertyEntity entity03 = callKigyouDtPropertyEntityLogic.practice(codeHistory);
        assertEquals(codeHistory, entity03.getKigyouDtKanrenshaCode());
        assertEquals(259, entity03.getKanrenshaKigyouDtPropertyId());
        
    }

}
