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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NewComerDto;

/**
 * VerifyNewUserService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("VerifyNewUserServiceTest.sql")
class VerifyNewUserServiceTest {

    /** テスト対象 */
    @Autowired
    private VerifyNewUserService verifyNewUserService;

    @Test
    @Tag("TableTruncate")
    void test() {

        // 無効トークン
        NewComerDto capsuleDto0 = new NewComerDto();
        capsuleDto0.setVerifyToken("12345");
        NewComerDto resultDto0 = verifyNewUserService.practice(capsuleDto0);
        assertEquals("無効なトークンです。", resultDto0.getMessage());
        assertTrue(resultDto0.getIsFailure());
        
        // 有効期限
        NewComerDto capsuleDto1 = new NewComerDto();
        capsuleDto1.setVerifyToken("53da8d6f-92e3-466a-9f43-b2be7437b81b");
        NewComerDto resultDto1 = verifyNewUserService.practice(capsuleDto1);
        assertEquals("トークンの有効期限が切れています。", resultDto1.getMessage());
        assertTrue(resultDto1.getIsFailure());
        
        // 認証成功
        NewComerDto capsuleDto2 = new NewComerDto();
        capsuleDto2.setVerifyToken("99da8d6f-92e3-466a-9f43-b2be7437b81b");
        NewComerDto resultDto2 = verifyNewUserService.practice(capsuleDto2);
        assertEquals("認証に成功しました。", resultDto2.getMessage());
        assertFalse(resultDto2.getIsFailure());
        assertEquals("bbb@example.com", resultDto2.getMailAddress());
        assertEquals("99a6e3c5-1143-43b4-ad68-7d2160fe71ad", resultDto2.getRegistCode());
    }

}
