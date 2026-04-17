package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;

/**
 * ChangeUserInfoService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
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

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 他人編集
        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        UserDetails testUser = User.withUsername(mail).password(password).roles("manager").build();

        // 力ずくでログイン状態を作成
        Authentication auth = new UsernamePasswordAuthenticationToken(mail, password, testUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        FrameworkCapsuleDto capsuleDto = new FrameworkCapsuleDto();
        capsuleDto.getUserDto().setUserPersonId(82);
        capsuleDto.getUserDto().setUserPersonCode(83);
        final String name = "abcdefg";
        capsuleDto.getUserDto().setUserPersonName(name);
        final String role = "kigyou_dt";
        capsuleDto.getUserDto().getListRoles().add(role);

        FrameworkMessageAndResultDto resultDto = changeUserInfoService.practice(capsuleDto);
        assertFalse(resultDto.getIsFailure());

        List<UserPersonEntity> listPerson = userPersonRepository.findAll();
        assertEquals(3, listPerson.size());
        final String email = "bbb@politician.balanse.report.net";
        UserPersonEntity entityOld = listPerson.get(1);
        assertEquals(false, entityOld.getIsLatest());
        assertEquals(email, entityOld.getEmail());

        UserPersonEntity entityNew = listPerson.get(2);
        assertEquals(true, entityNew.getIsLatest());
        assertEquals(email, entityNew.getEmail());
        assertEquals(name, entityNew.getUserPersonName());
        assertEquals(81, entityNew.getInsertUserId());

        List<UserRoleEntity> listRole = userRoleRepository.findAll();
        assertEquals(3, listRole.size());

        UserRoleEntity entityRoleNew = listRole.get(2);
        assertEquals(true, entityRoleNew.getIsLatest());
        assertEquals(email, entityRoleNew.getEmail());
        assertEquals(role, entityRoleNew.getRole());
        assertEquals(81, entityRoleNew.getInsertUserId());
    }

    @Test
    @Tag("TableTruncate")
    void testWrongLoginUser() throws Exception {
        FrameworkCapsuleDto capsuleDto = new FrameworkCapsuleDto();
        capsuleDto.getUserDto().setUserPersonId(82);
        capsuleDto.getUserDto().setUserPersonCode(83);
        capsuleDto.getUserDto().setUserPersonName("abcdefg");

        // ログインしていないので操作者情報が取れない

        FrameworkMessageAndResultDto resultDto = changeUserInfoService.practice(capsuleDto);
        assertTrue(resultDto.getIsFailure());
        assertEquals("操作者ログイン情報が正常に取得できませんでした", resultDto.getMessage());
    }

    @Test
    @Tag("TableTruncate")
    void testWrongEditUser() throws Exception {
        FrameworkCapsuleDto capsuleDto = new FrameworkCapsuleDto();
        capsuleDto.getUserDto().setUserPersonId(2469);
        capsuleDto.getUserDto().setUserPersonCode(2489);
        capsuleDto.getUserDto().setUserPersonName("abcdefg");

        FrameworkMessageAndResultDto resultDto = changeUserInfoService.practice(capsuleDto);
        assertTrue(resultDto.getIsFailure());
        assertEquals("編集ユーザ情報に正常に取得できませんでした", resultDto.getMessage());
    }

}
