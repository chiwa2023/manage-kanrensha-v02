package net.seijishikin.jp.normalize.manage.kanrensha.logic.address.registory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * GetChibanCsvLogic単体テスト(外部実行用)
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetChibanCsvLogicTest.sql")
class GetChibanCsvLogicTest {

    /** テスト対象 */
    @Autowired
    private GetChibanCsvLogic getChibanCsvLogic;

    @Test
    @Tag("TableTruncate")
    void testLimit() throws Exception {

        final String lgCodePref = "47";
        assertDoesNotThrow(() -> getChibanCsvLogic.practice(lgCodePref, GetChibanConstants.STORED_PATH));
    }

}
