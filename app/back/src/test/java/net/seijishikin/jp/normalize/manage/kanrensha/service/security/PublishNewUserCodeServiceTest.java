package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
 * PublishNewUserCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PublishNewUserCodeServiceTest.sql")
class PublishNewUserCodeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private PublishNewUserCodeService publishNewUserCodeService;

    @Test
    @Tag("ExternalService")
    void test() throws Exception {

        // 再発行
        NewComerDto capsuleDto0 = new NewComerDto();
        capsuleDto0.setMailAddress("aaa@example.com");
        capsuleDto0.setLimitDateTime(LocalDateTime.of(2022, 12, 5, 12, 34, 56));

        NewComerDto resultDto0 = publishNewUserCodeService.practice(capsuleDto0);
        assertNotNull(resultDto0);
        assertFalse(resultDto0.getIsFailure());
        assertEquals(capsuleDto0.getLimitDateTime(), resultDto0.getLimitDateTime());
        assertEquals(capsuleDto0.getMailAddress(), resultDto0.getMailAddress());

        // 新規
        NewComerDto capsuleDto1 = new NewComerDto();
        capsuleDto1.setMailAddress("bbb@example.com");
        capsuleDto1.setLimitDateTime(LocalDateTime.of(2023, 12, 5, 12, 34, 56));

        NewComerDto resultDto1 = publishNewUserCodeService.practice(capsuleDto1);
        assertNotNull(resultDto1);
        assertFalse(resultDto1.getIsFailure());
        assertEquals(capsuleDto1.getLimitDateTime(), resultDto1.getLimitDateTime());
        assertEquals(capsuleDto1.getMailAddress(), resultDto1.getMailAddress());
    }

}
