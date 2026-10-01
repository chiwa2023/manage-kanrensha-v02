package net.seijishikin.jp.normalize.manage.kanrensha.service.year.undefined;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearSavePartnerApiLoginHistoryService;

/**
 * SwitchYearSavePartnerApiLoginHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SwitchYearSavePartnerApiLoginHistoryUndefinedServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearSavePartnerApiLoginHistoryService switchYearSavePartnerApiLoginHistoryService;

    @Test
    @Tag("TableTruncate")
    void test2019() throws Exception {

        PartnerAccessHistoryBaseEntity baseEntity = new PartnerAccessHistoryBaseEntity();
        baseEntity.setAccessUrl("/123/456");
        baseEntity.setAttemptTime(LocalDateTime.of(1001, 4, 19, 12, 3, 45));
        baseEntity.setIpAddress("127.0.0.1"); // NOPMD
        baseEntity.setIsSuccess(true);
        baseEntity.setUserAgent("NetScape");
        baseEntity.setUserCode(190);
        baseEntity.setUserName("管理者　太郎");

        assertThrows(IllegalArgumentException.class,
                () -> switchYearSavePartnerApiLoginHistoryService.practice(baseEntity));

    }

}
