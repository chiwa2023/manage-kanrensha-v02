package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.CustomUserDetails;

/**
 * CustomUserDetailsManager単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CustomUserDetailsManagerFailureHistoryTest.sql")
class CustomUserDetailsManagerFailureHistoryTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    @Test
    @Tag("TableTruncate")
    void test() {

        // ログイン失敗ケース
        // MEMO このクラスの中に現在日時を利用しているのでテスト実施ごとにテストデータの調整が必要

        String mail = "aaa@politician.balanse.report.net";
        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsManager.loadUserByUsername(mail);

        assertEquals(mail, userDetails.getUsername());

        assertFalse(userDetails.isAccountNonExpired());
        assertFalse(userDetails.isAccountNonLocked());
        assertFalse(userDetails.isCredentialsNonExpired());
        assertFalse(userDetails.isEnabled());

        assertEquals(0, userDetails.getUserPersonId());
        assertEquals(0, userDetails.getUserPersonCode());
        assertEquals("", userDetails.getUserPersonName());

        assertTrue(userDetails.getAuthorities().isEmpty());
    }

}
