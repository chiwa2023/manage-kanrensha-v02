package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginStatusEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.LoginStatusRepository;

/**
 * CustomUserDetailsManager単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CustomUserDetailsManagerUpdateTest.sql")
class CustomUserDetailsManagerChangePasswordTest {

    /** テスト対象 */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    /** ログイン状態Repository */
    @Autowired
    private LoginStatusRepository loginStatusRepository;

    /** ログイン状態Repository */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 操作ユーザを設定する(SE権限者が他人のデータを修正することがある)が今回は本人
        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";
        UserDetails testUser = User.withUsername(mail).password(password)
                .roles("manager").build();

        // 力ずくでログイン状態を作成
        Authentication auth = new UsernamePasswordAuthenticationToken(mail, password, testUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String newPassword = "asdfg12345"; // 本当はEncoderされた文字列
        customUserDetailsManager.changePassword(passwordEncoder.encode(password), newPassword);

        List<LoginStatusEntity> listStatus = loginStatusRepository.findAll();
        assertEquals(1, listStatus.size());
        LoginStatusEntity entityStatus0 = listStatus.get(0);
        assertEquals(newPassword, entityStatus0.getPassword());

    }

    @Test
    @Tag("TableTruncate")
    void testNotLogin() throws Exception {
        String password = "qwerty1234";
        String newPassword = "asdfg12345";
        // ログインしていない場合は落ちる
        assertThrows(IllegalStateException.class, () -> customUserDetailsManager.changePassword(password, newPassword));
    }

    @Test
    @Tag("TableTruncate")
    void testWrongStatusData() throws Exception {

        String mail = "qqq@politician.balanse.report.net";
        String password = "qwerty1234";

        UserDetails testUser = User.withUsername(mail).password(password) // パスワードはダミー
                .roles("manager").build();

        // 架空のユーザでログイン状態を作成
        Authentication auth = new UsernamePasswordAuthenticationToken(mail, password, testUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String newPassword = "asdfg12345";

        // login_statusとの整合性がなく落ちる
        assertThrows(UsernameNotFoundException.class,
                () -> customUserDetailsManager.changePassword(password, newPassword));
    }
}
