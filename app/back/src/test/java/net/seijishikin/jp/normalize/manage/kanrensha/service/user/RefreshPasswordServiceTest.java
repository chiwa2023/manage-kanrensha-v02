package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.RefreshPasswordCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginStatusEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.LoginStatusRepository;

/**
 * RefreshPasswordService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("RefreshPasswordServiceTest.sql")
class RefreshPasswordServiceTest {

    /** テスト対象 */
    @Autowired
    private RefreshPasswordService refreshPasswordService;

    /** ログイン状態Repository */
    @Autowired
    private LoginStatusRepository loginStatusRepository;

    @Test
    @Tag("TableTruncate")
    void testWrongNewPassword() throws Exception {
        // メールアドレス・旧パスワードの有効性検証はControllerでしかできなかったので新旧チェックのみをおこなう
        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        // (frontでチェックしているけど)新旧パスワードが同じだとCustomUserDetailsManagerで例外
        RefreshPasswordCapsuleDto capsuleDto = new RefreshPasswordCapsuleDto();
        capsuleDto.setOldPassword(password);
        capsuleDto.setNewPassword(password);
        capsuleDto.setEmail(mail);

        FrameworkMessageAndResultDto resultDto = refreshPasswordService.practice(capsuleDto);
        assertTrue(resultDto.getIsFailure());
        assertEquals("変更前と変更後のパスワードが同じです", resultDto.getMessage());
    }

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // メールアドレス・旧パスワードの有効性検証はControllerでしかできなかったので新旧チェックのみをおこなう
        String mail = "aaa@politician.balanse.report.net";
        String password = "qwerty1234";

        UserDetails testUser = User.withUsername(mail).password(password).roles("manager").build();

        // 力ずくでログイン状態を作成
        Authentication auth = new UsernamePasswordAuthenticationToken(mail, password, testUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        final LoginStatusEntity entityPre = loginStatusRepository.findById(mail).get();

        // 新旧パスワードが同じだとCustomUserDetailsManagerで例外
        RefreshPasswordCapsuleDto capsuleDto = new RefreshPasswordCapsuleDto();
        String newPassword = "asdfg1234";
        capsuleDto.setOldPassword(password);
        capsuleDto.setNewPassword(newPassword);
        capsuleDto.setEmail(mail);

        FrameworkMessageAndResultDto resultDto = refreshPasswordService.practice(capsuleDto);
        assertFalse(resultDto.getIsFailure());
        assertEquals("パスワード更新に成功しました", resultDto.getMessage());

        LoginStatusEntity entityPro = loginStatusRepository.findById(mail).get();

        assertNotEquals(entityPre.getPassword(), entityPro.getPassword());
        assertNotEquals(entityPre.getPassChangeTime(), entityPro.getPassChangeTime());
    }

}
