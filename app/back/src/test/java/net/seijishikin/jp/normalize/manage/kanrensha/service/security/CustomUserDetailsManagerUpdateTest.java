package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
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
class CustomUserDetailsManagerUpdateTest {

    /** テスト対象 */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 操作ユーザを設定する(SE権限者、運営者が他人のデータを修正することがある)が今回は本人
        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        UserDetails testUser = User.withUsername(mail).password(password) // パスワードはダミー
                .roles("manager").build();

        // 力ずくでログイン状態を作成
        Authentication auth = new UsernamePasswordAuthenticationToken(mail, password, testUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        // たまたま自分の権限を触ろうとしている
        List<String> listRole = new ArrayList<>();
        listRole.add("admin");
        UserDetails userDetails = User.builder().username(mail).password(password)
                .roles(listRole.toArray(new String[listRole.size()])).build(); // NOPMD

        customUserDetailsManager.updateUser(userDetails);

        List<UserRoleEntity> listAll = userRoleRepository.findAll();
        assertEquals(2, listAll.size());

        UserRoleEntity entity0 = listAll.get(0);
        assertEquals(1, entity0.getUserRoleId());
        assertEquals("manager", entity0.getRole());
        assertFalse(entity0.getIsLatest());

        UserRoleEntity entity1 = listAll.get(1);
        assertEquals(2, entity1.getUserRoleId());
        assertEquals("ROLE_admin", entity1.getRole());
    }

}
