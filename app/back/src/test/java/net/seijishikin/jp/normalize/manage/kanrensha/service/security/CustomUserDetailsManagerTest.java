package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * CustomUserDetailsManager単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CustomUserDetailsManagerTest.sql")
class CustomUserDetailsManagerTest {

    /** テスト対象 */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    @Test
    @Tag("TableTruncate")
    void testLoadUserByUsernameLoginSuccess() {

        // 正常ケース
        String mail = "aaa@politician.balanse.report.net";
        UserDetails userDetails = customUserDetailsManager.loadUserByUsername(mail);

        assertEquals(mail, userDetails.getUsername());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());

        List<GrantedAuthority> listAuthority = new ArrayList<>(userDetails.getAuthorities());

        GrantedAuthority authority0 = listAuthority.get(0);
        assertEquals("ROLE_" + "manager", authority0.getAuthority());

        // MEMO ログイン履歴は別Serviceで行うようになった
    }

    @Test
    @Tag("TableTruncate")
    void testFailLogin() {

        // 存在しないユーザ(NoSuchElementExceptionを出して親でキャッチする)
        assertThrows(NoSuchElementException.class,
                () -> customUserDetailsManager.loadUserByUsername("aa@politician.balanse.report.net"));

        UserDetails userDetails11 = customUserDetailsManager.loadUserByUsername("bbb@politician.balanse.report.net");
        // 信頼期間から外れている
        assertFalse(userDetails11.isCredentialsNonExpired());
        // アカウント長期活動なし
        assertFalse(userDetails11.isAccountNonExpired());
        // 使用可否
        assertFalse(userDetails11.isEnabled());

        // TODO アカウントがロックは現状使っていないが、必要ならば実装する
    }

    @Test
    @Tag("TableTruncate")
    void testUserExist() {
        // 存在するメールであればtrue
        assertTrue(customUserDetailsManager.userExists("aaa@politician.balanse.report.net"));
    }

}
