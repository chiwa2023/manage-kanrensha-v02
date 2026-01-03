package net.seijishikin.jp.normalize.manage.kanrensha.config;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * AuthenticationEvents単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
//@Transactional
@Sql("AuthenticationEventsTest.sql")
class AuthenticationEventsTest {

    /** テスト対象 */
    @Autowired
    private AuthenticationEvents authenticationEvents;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {


        fail("Not yet implemented");
    }

}
