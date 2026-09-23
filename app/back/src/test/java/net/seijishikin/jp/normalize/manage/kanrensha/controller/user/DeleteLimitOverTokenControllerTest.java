package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * DeleteLimitOverTokenController単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("../../service/security/DeleteLimitOverTokenServiceTest.sql")
class DeleteLimitOverTokenControllerTest {

    /** テスト対象 */
    @Autowired
    private DeleteLimitOverTokenController deleteLimitOverTokenController;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertTrue(deleteLimitOverTokenController.practice());
    }

}
