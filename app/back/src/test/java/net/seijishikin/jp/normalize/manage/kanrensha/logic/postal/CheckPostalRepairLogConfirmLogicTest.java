package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * CheckPostalRepairLogConfirmLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CheckPostalRepairLogConfirmLogicTest.sql")
class CheckPostalRepairLogConfirmLogicTest {

    /** テスト対象 */
    @Autowired
    private CheckPostalRepairLogConfirmLogic checkPostalRepairLogConfirmLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertTrue(checkPostalRepairLogConfirmLogic.practice("13"));

        assertThrows(IncorrectResultSizeDataAccessException.class,
                () -> checkPostalRepairLogConfirmLogic.practice("01"));
    }

}
