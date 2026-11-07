package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * YoteiFuncDeleteLimitOverTokenService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("YoteiFuncDeleteLimitOverTokenServiceTest.sql")
class YoteiFuncDeleteLimitOverTokenServiceTest {

    /** テスト対象 */
    @Autowired
    private YoteiFuncDeleteLimitOverTokenService yoteiFuncDeleteLimitOverTokenService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertDoesNotThrow(() -> yoteiFuncDeleteLimitOverTokenService.practice());
    }

}
