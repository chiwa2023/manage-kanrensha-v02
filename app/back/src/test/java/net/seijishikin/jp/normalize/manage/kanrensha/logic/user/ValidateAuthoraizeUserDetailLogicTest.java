package net.seijishikin.jp.normalize.manage.kanrensha.logic.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ValidateAuthoraizeUserDetailLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("ValidateAuthoraizeUserDetailLogcicTest.sql")
class ValidateAuthoraizeUserDetailLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /** 認証プロバイダ */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        // ログイン処理
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LeastUserDto userDto0 = CreateLeastUserForTestUtil.practice();
        assertThrows(UsernameNotFoundException.class, () -> validateAuthoraizeUserDetailLogic.practice(userDto0));
        userDto0.setUserPersonId(81);
        assertThrows(UsernameNotFoundException.class, () -> validateAuthoraizeUserDetailLogic.practice(userDto0));
        userDto0.setUserPersonCode(80);
        assertThrows(UsernameNotFoundException.class, () -> validateAuthoraizeUserDetailLogic.practice(userDto0));
        userDto0.setUserPersonName("aaa");
        assertThrows(UsernameNotFoundException.class, () -> validateAuthoraizeUserDetailLogic.practice(userDto0));

        // 編集対象がない場合のユーザ一致
        LeastUserDto userDto1 = CreateLeastUserForTestUtil.practice();
        userDto1.setUserPersonId(81);
        userDto1.setUserPersonCode(80);
        userDto1.setUserPersonName("aaa");
        userDto1.getListRoles().add("ROLE_manager");
        assertEquals(mail, validateAuthoraizeUserDetailLogic.practice(userDto1));

    }

    @Test
    @Tag("TableTruncate")
    void testRiyousha() throws Exception {

        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        // ログイン処理
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LeastUserDto userDto1 = CreateLeastUserForTestUtil.practice();
        userDto1.setUserPersonId(81);
        userDto1.setUserPersonCode(80);
        userDto1.setUserPersonName("aaa");
        userDto1.getListRoles().add("ROLE_manager");
        assertEquals(mail, validateAuthoraizeUserDetailLogic.practice(userDto1));

        final int editRiyoushaCode = 244; // 編集Id
        // 他者編集をするが権限の指定がない場合は許可しない(実装ミス)
        assertThrows(IllegalArgumentException.class,
                () -> validateAuthoraizeUserDetailLogic.practiceRiyousha(userDto1, editRiyoushaCode, null));

        // 権限不足
        assertFalse(validateAuthoraizeUserDetailLogic.practiceRiyousha(userDto1, editRiyoushaCode,
                UserRoleConstants.PARTNER_API, UserRoleConstants.ADMIN, UserRoleConstants.KANRENSHA_PERSON));

        // 権限があるので編集許可(編集対象コードが一致していなくてもよい)
        assertTrue(validateAuthoraizeUserDetailLogic.practiceRiyousha(userDto1, 127, UserRoleConstants.MANAGER,
                UserRoleConstants.MANAGER, UserRoleConstants.PARTNER_API));

        // 本人なので許可
        assertTrue(validateAuthoraizeUserDetailLogic.practiceRiyousha(userDto1, editRiyoushaCode,
                UserRoleConstants.MANAGER, UserRoleConstants.ADMIN));

    }

    @Test
    @Tag("TableTruncate")
    void testKanrensha() throws Exception {

        String mail = "nnnn@politician.balanse.report.net";
        String password = "nnnn";

        // ログイン処理
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LeastUserDto userDto1 = CreateLeastUserForTestUtil.practice();
        userDto1.setUserPersonId(208);
        userDto1.setUserPersonCode(196);
        userDto1.setUserPersonName("nnnn");
        userDto1.getListRoles().add("ROLE_kanrensha_person");
        assertEquals(mail, validateAuthoraizeUserDetailLogic.practice(userDto1));

        final String editKanrenshaCode = "DH-mkzsu-2mMW-rB8Y-Pl4zr";
        // 他者編集をするが権限の指定がない場合は許可しない(実装ミス)
        assertThrows(IllegalArgumentException.class,
                () -> validateAuthoraizeUserDetailLogic.practiceKanrensha(userDto1, editKanrenshaCode, null));

        // 権限不足
        assertFalse(validateAuthoraizeUserDetailLogic.practiceKanrensha(userDto1, editKanrenshaCode,
                UserRoleConstants.KANRENSHA_KIGYOU_DT, UserRoleConstants.ADMIN, UserRoleConstants.KANRENSHA_SEIJIDANTAI));

        // 権限があるので編集許可(編集対象コードが一致していなくてもよい)
        assertTrue(validateAuthoraizeUserDetailLogic.practiceKanrensha(userDto1, "12356",
                UserRoleConstants.KANRENSHA_PERSON, UserRoleConstants.KANRENSHA_PERSON, UserRoleConstants.PARTNER_API));

        // 本人であるので編集許可
        assertTrue(validateAuthoraizeUserDetailLogic.practiceKanrensha(userDto1, editKanrenshaCode,
                UserRoleConstants.KANRENSHA_PERSON, UserRoleConstants.MANAGER, UserRoleConstants.PARTNER_API));
    }

}
