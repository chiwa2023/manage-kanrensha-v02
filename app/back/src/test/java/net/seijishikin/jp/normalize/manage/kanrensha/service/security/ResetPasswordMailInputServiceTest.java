package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.ResetPassswordCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasswordResetEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPasswordResetRepository;

/**
 * ResetPasswordMailInputService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ResetPasswordMailInputServiceTest.sql")
class ResetPasswordMailInputServiceTest {

    /** テスト対象 */
    @Autowired
    private ResetPasswordMailInputService resetPasswordMailInputService;

    /** パスワードリセットRepository */
    @Autowired
    private UserPasswordResetRepository userPasswordResetRepository;

    @Test
    @Tag("ExternalService")
    void testUpdate() throws Exception {

        String mail = "ccc@seijishikin.net";
        ResetPassswordCapsuleDto capsuleDto = new ResetPassswordCapsuleDto();
        capsuleDto.setEmail(mail); // すでにテーブル存在するメアドでも通常送信できる

        FrameworkMessageAndResultDto resultDto = resetPasswordMailInputService.practice(capsuleDto);
        assertFalse(resultDto.getIsFailure());

        UserPasswordResetEntity entity = userPasswordResetRepository.findById(mail).get();
        assertNotEquals("12345",entity.getRegistCode());

        // その他についてはメール送信Logicテスト
        // メール内容については目視で確認
    }

    @Test
    @Tag("ExternalService")
    void testInsert() throws Exception {

        String mail = "eee@seijishikin.net";
        ResetPassswordCapsuleDto capsuleDto = new ResetPassswordCapsuleDto();
        capsuleDto.setEmail(mail); 

        FrameworkMessageAndResultDto resultDto = resetPasswordMailInputService.practice(capsuleDto);
        assertFalse(resultDto.getIsFailure());

        UserPasswordResetEntity entity = userPasswordResetRepository.findById(mail).get();
        assertNotEquals("12345",entity.getRegistCode());

        // その他についてはメール送信Logicテスト
        // メール内容については目視で確認
    }

}
