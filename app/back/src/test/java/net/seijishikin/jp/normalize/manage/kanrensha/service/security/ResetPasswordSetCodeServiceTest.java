package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.ResetPassswordCapsuleDto;

/**
 * ResetPasswordSetCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ResetPasswordSetCodeServiceTest.sql")
class ResetPasswordSetCodeServiceTest {

    /** テスト対象 */
    @Autowired
    private ResetPasswordSetCodeService resetPasswordSetCodeService;

    @Test
    @Tag("TableTruncate")
    void test() {

        // メールアドレス違い
        ResetPassswordCapsuleDto capsuleDto0 = new ResetPassswordCapsuleDto();
        capsuleDto0.setEmail("ddd@seijishikin.net");

        FrameworkMessageAndResultDto resultDto0 = resetPasswordSetCodeService.practice(capsuleDto0);
        assertTrue(resultDto0.getIsFailure());
        assertEquals("パスワードリセットのためのコード送信が行われていません", resultDto0.getMessage());

        // コード入力間違い
        ResetPassswordCapsuleDto capsuleDto1 = new ResetPassswordCapsuleDto();
        capsuleDto1.setEmail("eee@seijishikin.net");
        capsuleDto1.setAccessCode("12345");
        FrameworkMessageAndResultDto resultDto1 = resetPasswordSetCodeService.practice(capsuleDto1);
        assertTrue(resultDto1.getIsFailure());
        assertEquals("入力されたコードが異なります", resultDto1.getMessage());

        // コード有効期限切れ
        ResetPassswordCapsuleDto capsuleDto2 = new ResetPassswordCapsuleDto();
        capsuleDto2.setEmail("eee@seijishikin.net");
        capsuleDto2.setAccessCode("99887");

        FrameworkMessageAndResultDto resultDto2 = resetPasswordSetCodeService.practice(capsuleDto2);
        assertTrue(resultDto2.getIsFailure());
        assertEquals("コードの有効期間を過ぎています", resultDto2.getMessage());

        // 正常認証
        ResetPassswordCapsuleDto capsuleDto3 = new ResetPassswordCapsuleDto();
        capsuleDto3.setEmail("fff@seijishikin.net");
        capsuleDto3.setAccessCode("77665");

        FrameworkMessageAndResultDto resultDto3 = resetPasswordSetCodeService.practice(capsuleDto3);
        assertFalse(resultDto3.getIsFailure());
    }

}
