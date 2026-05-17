package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginStatusEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.LoginStatusRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * CustomUserDetailsManager単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CustomUserDetailsManagerUpdateTest.sql")
class CustomUserDetailsManagerDeleteTest {

    /** テスト対象 */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** ユーザ人物Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ログイン状態Repository */
    @Autowired
    private LoginStatusRepository loginStatusRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 操作ユーザを設定する(SE権限者が他人のデータを修正することがある)が今回は本人
        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        UserDetails testUser = User.withUsername(mail).password(password).roles("manager").build();

        // 力ずくでログイン状態を作成
        Authentication auth = new UsernamePasswordAuthenticationToken(mail, password, testUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        // 自ら退会
        customUserDetailsManager.deleteUser(mail);

        List<UserRoleEntity> listRole = userRoleRepository.findAll();
        assertEquals(1, listRole.size());
        UserRoleEntity entityRole0 = listRole.get(0);
        assertFalse(entityRole0.getIsLatest());

        List<UserPersonEntity> listPerson = userPersonRepository.findAll();
        assertEquals(1, listPerson.size());
        UserPersonEntity entityPerson0 = listPerson.get(0);
        assertFalse(entityPerson0.getIsLatest());

        List<LoginStatusEntity> listStatus = loginStatusRepository.findAll();
        assertEquals(1, listStatus.size());
        LoginStatusEntity entityStatus0 = listStatus.get(0);
        assertTrue(entityStatus0.getDisabled());

    }

    @Test
    @Tag("TableTruncate")
    void testNotLogin() throws Exception {
        String mail = "aaa@politician.balanse.report.net";
        // ログインしていない場合は落ちる
        assertThrows(IllegalStateException.class, () -> customUserDetailsManager.deleteUser(mail));
    }

    @Test
    @Tag("TableTruncate")
    void testWrongStatusData() throws Exception {

        String mail = "qqq@politician.balanse.report.net";
        String password = "qwerty1234";

        UserDetails testUser = User.withUsername(mail).password(password)
                .roles("manager").build();

        // 架空のユーザでログイン状態を作成
        Authentication auth = new UsernamePasswordAuthenticationToken(mail, password, testUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        // login_statusとの整合性がなく落ちる
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsManager.deleteUser(mail));
    }

}
