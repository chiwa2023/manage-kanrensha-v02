package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.EditUserPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.GetLoginLeastUserLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * ChangeUserInfoService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("ChangeUserInfoServiceTest.sql")
class ChangeUserInfoServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ChangeUserInfoService changeUserInfoService;

    /** ユーザ個人Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** ログインユーザ取得Logic */
    @Autowired
    private GetLoginLeastUserLogic getLoginLeastUserLogic;

    @Test
    @Tag("TableTruncate") // NOPMD
    @WithMockUser
    void testNoRoleChange() throws Exception {

        // 他人編集
        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        final String role = "manager";
        UserDetails testUser = User.withUsername(mail).password(password).roles(role).build();

        // 力ずくでログイン状態を作成
        Authentication auth = new UsernamePasswordAuthenticationToken(mail, password, testUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        EditUserPersonCapsuleDto capsuleDto = new EditUserPersonCapsuleDto();
        capsuleDto.getUserDto().setUserPersonId(82);
        capsuleDto.getUserDto().setUserPersonCode(83);
        final String name = "abcdefg"; // NOPMD
        capsuleDto.getUserDto().setUserPersonName(name);
        capsuleDto.getUserDto().getListRoles().add(role);
        capsuleDto.setIsAlertTaskEnd(true);
        capsuleDto.setIsAlertTaskStart(true);

        LeastUserDto operateUserDto = getLoginLeastUserLogic.practice();

        FrameworkMessageAndResultDto resultDto = changeUserInfoService.practice(capsuleDto, operateUserDto);
        assertFalse(resultDto.getIsFailure());

        final String email = "bbb@politician.balanse.report.net";
        UserPersonEntity entityOld = userPersonRepository.findById(82).get();
        assertEquals(false, entityOld.getIsLatest());
        assertEquals(email, entityOld.getEmail());

        UserPersonEntity entityNew = userPersonRepository.findByEmailAndIsLatestTrue(email).get();
        assertEquals(true, entityNew.getIsLatest());
        assertEquals(email, entityNew.getEmail());
        assertEquals(name, entityNew.getUserPersonName());
        assertEquals(81, entityNew.getInsertUserId());
        assertEquals(true, entityNew.getIsAlertTaskStart());
        assertEquals(true, entityNew.getIsAlertTaskEnd());

        List<UserRoleEntity> listRole = userRoleRepository.findByEmailAndIsLatestTrue(email);
        assertEquals(1, listRole.size());

        UserRoleEntity entityRoleNew = listRole.get(0);
        assertEquals(true, entityRoleNew.getIsLatest());
        assertEquals(email, entityRoleNew.getEmail());
        assertEquals(role, entityRoleNew.getRole());
        // 権限に変更がないので何も変わっていない
        assertEquals("", entityRoleNew.getKanrenshaCode());
        assertEquals(14, entityRoleNew.getRiyoushaCode());
        assertEquals(1, entityRoleNew.getInsertUserId());
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testRoleChange() throws Exception {

        // 他人編集
        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        final String role = "manager";
        UserDetails testUser = User.withUsername(mail).password(password).roles(role).build();

        // 力ずくでログイン状態を作成
        Authentication auth = new UsernamePasswordAuthenticationToken(mail, password, testUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        EditUserPersonCapsuleDto capsuleDto = new EditUserPersonCapsuleDto();
        capsuleDto.getUserDto().setUserPersonId(82);
        capsuleDto.getUserDto().setUserPersonCode(83);
        final String name = "abcdefg";
        capsuleDto.getUserDto().setUserPersonName(name);
        capsuleDto.getUserDto().getListRoles().add(role);
        capsuleDto.getUserDto().getListRoles().add(UserRoleConstants.KANRENSHA_PERSON); // 権限追加
        capsuleDto.setIsAlertTaskEnd(true);
        capsuleDto.setIsAlertTaskStart(true);

        LeastUserDto operateUserDto = getLoginLeastUserLogic.practice();

        FrameworkMessageAndResultDto resultDto = changeUserInfoService.practice(capsuleDto, operateUserDto);
        assertFalse(resultDto.getIsFailure());

        final String email = "bbb@politician.balanse.report.net";
        UserPersonEntity entityOld = userPersonRepository.findById(82).get();
        assertEquals(false, entityOld.getIsLatest());
        assertEquals(email, entityOld.getEmail());

        UserPersonEntity entityNew = userPersonRepository.findByEmailAndIsLatestTrue(email).get();
        assertEquals(true, entityNew.getIsLatest());
        assertEquals(email, entityNew.getEmail());
        assertEquals(name, entityNew.getUserPersonName());
        assertEquals(81, entityNew.getInsertUserId());
        assertEquals(true, entityNew.getIsAlertTaskStart());
        assertEquals(true, entityNew.getIsAlertTaskEnd());

        List<UserRoleEntity> listRole = userRoleRepository.findByEmailAndIsLatestTrue(email);
        assertEquals(2, listRole.size());

        UserRoleEntity entityRoleNew = listRole.get(1);
        assertEquals(true, entityRoleNew.getIsLatest());
        assertEquals(email, entityRoleNew.getEmail());
        assertEquals(UserRoleConstants.KANRENSHA_PERSON, entityRoleNew.getRole());
        assertNotEquals("", entityRoleNew.getKanrenshaCode());
        assertEquals(0, entityRoleNew.getRiyoushaCode());
        assertEquals(81, entityRoleNew.getInsertUserId());
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testWrongLoginUser() throws Exception {
        EditUserPersonCapsuleDto capsuleDto = new EditUserPersonCapsuleDto();
        capsuleDto.getUserDto().setUserPersonId(82);
        capsuleDto.getUserDto().setUserPersonCode(83);
        capsuleDto.getUserDto().setUserPersonName("abcdefg");

        // ログインしていないので操作者情報が取れない
        LeastUserDto operateUserDto = getLoginLeastUserLogic.practice();

        FrameworkMessageAndResultDto resultDto = changeUserInfoService.practice(capsuleDto, operateUserDto);
        assertTrue(resultDto.getIsFailure());
        assertEquals("操作者ログイン情報が正常に取得できませんでした", resultDto.getMessage());
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testWrongEditUser() throws Exception {
        EditUserPersonCapsuleDto capsuleDto = new EditUserPersonCapsuleDto();
        capsuleDto.getUserDto().setUserPersonId(2469);
        capsuleDto.getUserDto().setUserPersonCode(2489);
        capsuleDto.getUserDto().setUserPersonName("abcdefg");

        LeastUserDto operateUserDto = getLoginLeastUserLogic.practice();

        FrameworkMessageAndResultDto resultDto = changeUserInfoService.practice(capsuleDto, operateUserDto);
        assertTrue(resultDto.getIsFailure());
        assertEquals("編集ユーザ情報が正常に取得できませんでした", resultDto.getMessage());
    }

}
