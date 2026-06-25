package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hibernate.exception.SQLGrammarException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * SearchNotExistKyuAzaLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchNotExistKyuAzaLogicTest.sql")
class SearchNotExistKyuAzaLogicTest {

    /** テスト対象 */
    @Autowired
    private SearchNotExistKyuAzaLogic searchNotExistKyuAzaLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final String lgCode = "011029";

        assertFalse(searchNotExistKyuAzaLogic.practice(lgCode, "架空"));
        assertTrue(searchNotExistKyuAzaLogic.practice(lgCode, "実在"));

        assertThrows(SQLGrammarException.class, () -> searchNotExistKyuAzaLogic.practice("987", ""));
    }

}
