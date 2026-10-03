package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NotifyPartnerApiLimitCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * NotificationPartnerApiTokenLimitAsyncService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("NotificationPartnerApiTokenLimitServiceTest.sql")
class NotificationPartnerApiTokenLimitAsyncServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private NotificationPartnerApiTokenLimitAsyncService notificationPartnerApiTokenLimitAsyncService;

    @Test
    @Tag("ExternalService")
    void test() {

        LocalDateTime creaDateTime = LocalDateTime.of(2025, 8, 1, 11, 22, 33);
        NotifyPartnerApiLimitCapsuleDto capsuleDto = new NotifyPartnerApiLimitCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setCheckDate(LocalDate.of(2025, 7, 1));

        assertDoesNotThrow(() -> notificationPartnerApiTokenLimitAsyncService.practice(creaDateTime, capsuleDto));
    }

}
