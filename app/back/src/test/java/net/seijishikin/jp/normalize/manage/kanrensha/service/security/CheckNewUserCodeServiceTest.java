package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

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
 * CheckNewUserCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PublishNewUserCodeServiceTest.sql")
class CheckNewUserCodeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CheckNewUserCodeService checkNewUserCodeService;

    @Test
    @Tag("TableTruncate")
    void test() {

        // 一致するメールアドレスが存在しない場合
        NewComerDto capsuleDto0 = new NewComerDto();
        NewComerDto resultDto0 = checkNewUserCodeService.practice(capsuleDto0);
        assertEquals("データが見つかりませんでした", resultDto0.getMessage());

        NewComerDto capsuleDto1 = new NewComerDto();
        capsuleDto1.setMailAddress("aaa@example.com");
        capsuleDto1.setLimitDateTime(LocalDateTime.of(2026, 12, 5, 11, 00, 00));
        NewComerDto resultDto1 = checkNewUserCodeService.practice(capsuleDto1);
        assertEquals("コードが一致しません", resultDto1.getMessage());

        NewComerDto capsuleDto2 = new NewComerDto();
        capsuleDto2.setMailAddress("aaa@example.com");
        capsuleDto2.setRegistCode("25d81593-7f31-49f8-969b-d6d93f5c994d");
        capsuleDto2.setLimitDateTime(LocalDateTime.of(2026, 12, 5, 11, 00, 00));
        NewComerDto resultDto2 = checkNewUserCodeService.practice(capsuleDto2);
        assertEquals("期限切れです", resultDto2.getMessage());

        NewComerDto capsuleDto3 = new NewComerDto();
        capsuleDto3.setMailAddress("aaa@example.com");
        capsuleDto3.setRegistCode("25d81593-7f31-49f8-969b-d6d93f5c994d");
        capsuleDto3.setLimitDateTime(LocalDateTime.of(2022, 12, 5, 11, 00, 00));
        NewComerDto resultDto3 = checkNewUserCodeService.practice(capsuleDto3);
        assertFalse(resultDto3.getIsFailure());
        
    }

}
