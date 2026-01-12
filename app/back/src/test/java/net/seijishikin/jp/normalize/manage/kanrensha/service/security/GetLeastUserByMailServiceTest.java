package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

/**
 * GetLeastUserByMailService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("GetLeastUserByMailServiceTest.sql")
class GetLeastUserByMailServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetLeastUserByMailService getLeastUserByMailService;

    /** 認証プロバイダ */
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final String mail = "aaa@politician.balanse.report.net";
        final String pass = "qwerty1234";

        // テスト時にはdaoAuthenticationProvider出ないと動かない
        Authentication authentication = daoAuthenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(mail, pass));

        LeastUserDto leastDto = getLeastUserByMailService.practice(mail, authentication);

        assertEquals(81, leastDto.getUserPersonId());
        assertEquals(80, leastDto.getUserPersonCode());
        assertEquals("aaa", leastDto.getUserPersonName());

        List<String> listAuth = leastDto.getListRoles();
        assertEquals(1, listAuth.size());
        assertEquals("ROLE_manager", listAuth.get(0));
    }

}
