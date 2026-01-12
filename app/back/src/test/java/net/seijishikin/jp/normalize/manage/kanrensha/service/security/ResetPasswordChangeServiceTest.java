package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginStatusEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.LoginStatusRepository;

/**
 * ResetPasswordChangeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ResetPasswordChangeServiceTest.sql")
class ResetPasswordChangeServiceTest {

    /** テスト対象 */
    @Autowired
    private ResetPasswordChangeService resetPasswordChangeService;
    
    /** ログイン状態Repository */
    @Autowired
    private LoginStatusRepository loginStatusRepository;

    @Test
    void test() {
        
        final LocalDateTime testStartTime = LocalDateTime.now();
        
        // メールアドレス違い
        ResetPassswordCapsuleDto capsuleDto0 = new ResetPassswordCapsuleDto();
        capsuleDto0.setEmail("ddd@seijishikin.net");
        capsuleDto0.setAccessCode("12345");

        FrameworkMessageAndResultDto resultDto0 = resetPasswordChangeService.practice(capsuleDto0);
        assertTrue(resultDto0.getIsFailure());
        assertEquals("ご指定のアドレスでユーザ登録が見つかりませんでした", resultDto0.getMessage());

        // 他のチェックはResetPasswordSetCodeServiceTestで確認済
        
        // 正常更新
        ResetPassswordCapsuleDto capsuleDto1 = new ResetPassswordCapsuleDto();
        capsuleDto1.setEmail("eee@seijishikin.net");
        capsuleDto1.setAccessCode("23456");
        capsuleDto1.setPassword("998877"); // 実際には暗号化されている

        FrameworkMessageAndResultDto resultDto1 = resetPasswordChangeService.practice(capsuleDto1);
        System.out.println(resultDto1.getMessage());
        assertFalse(resultDto1.getIsFailure());
        
        LoginStatusEntity statusEntity = loginStatusRepository.findById(capsuleDto1.getEmail()).get();
        assertEquals(capsuleDto1.getPassword(), statusEntity.getPassword());
        assertTrue(testStartTime.isBefore(statusEntity.getPassChangeTime()));
    }

}
