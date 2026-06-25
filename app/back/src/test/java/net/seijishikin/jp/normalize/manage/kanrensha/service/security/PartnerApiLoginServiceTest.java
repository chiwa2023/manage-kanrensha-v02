package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessTokenEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.PartnerAccessHistory2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PartnerAccessTokenRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.PartnerAccessHistory2026Repository;

/**
 * PartnerApiLoginService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("PartnerApiLoginServiceTest.sql")
class PartnerApiLoginServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private PartnerApiLoginService partnerApiLoginService;

    /** APIパートナー長期トークンRepository */
    @Autowired
    private PartnerAccessTokenRepository partnerAccessTokenRepository;

    /** APIパートナー履歴Repository(2026) */
    @Autowired
    private PartnerAccessHistory2026Repository partnerAccessHistory2026Repository;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testVaildToken() throws Exception {

        // tokenが登録されていない
        final String token = "qwerty";
        final String accessUrl = "/abc-def/ghi-jkl"; // NOPMD
        final String ipAddress = "127.0.0.1"; // NOPMD
        final String usrAgent = "NetScape"; // NOPMD
        final LocalDateTime loginTime = LocalDateTime.of(2026, 4, 12, 12, 34, 56);

        assertThrows(InvalidBearerTokenException.class,
                () -> partnerApiLoginService.practice(token, accessUrl, usrAgent, ipAddress, loginTime));
    }

    @Test
    @Tag("TableTruncate")
    void testOverExpireAt() throws Exception {

        // tokenが失効している
        final String token = "9988754";
        final String accessUrl = "/abc-def/ghi-jkl";
        final String ipAddress = "127.0.0.1"; // NOPMD
        final String usrAgent = "NetScape";
        final LocalDateTime loginTime = LocalDateTime.of(2026, 4, 12, 12, 34, 56);

        assertThrows(LockedException.class,
                () -> partnerApiLoginService.practice(token, accessUrl, usrAgent, ipAddress, loginTime));
    }

    @Test
    @Tag("TableTruncate")
    void testWrongIpAddress() throws Exception {

        // IPアドレスが不正
        final String token = "12345";
        final String accessUrl = "/abc-def/ghi-jkl";
        final String ipAddress = "9.8.7.6"; // NOPMD
        final String usrAgent = "NetScape";
        final LocalDateTime loginTime = LocalDateTime.of(2026, 4, 12, 12, 34, 56);

        assertThrows(AccessDeniedException.class,
                () -> partnerApiLoginService.practice(token, accessUrl, usrAgent, ipAddress, loginTime));
    }
    
    @Test
    @Tag("TableTruncate")
    void testSuccess() throws Exception {

        // ログイン成功
        final String token = "12345";
        final String accessUrl = "/abc-def/ghi-jkl";
        final String ipAddress = "127.0.0.1"; // NOPMD
        final String usrAgent = "NetScape";
        final LocalDateTime loginTime = LocalDateTime.of(2026, 4, 12, 12, 34, 56);

        LeastUserDto userDto = partnerApiLoginService.practice(token, accessUrl, usrAgent, ipAddress, loginTime);

        List<PartnerAccessTokenEntity> listState = partnerAccessTokenRepository
                .findByUserCodeAndRevokedAtNull(userDto.getUserPersonCode());
        assertEquals(1, listState.size());
        PartnerAccessTokenEntity tokenEntity = listState.get(0);

        // 最終利用時間が更新されている
        assertEquals(loginTime, tokenEntity.getLastUsedAt());

        // 2026年履歴を確認
        List<PartnerAccessHistory2026Entity> listHistory = partnerAccessHistory2026Repository.findAll();
        assertEquals(1, listHistory.size());

        PartnerAccessHistory2026Entity entity0 = listHistory.get(0);
        assertEquals(accessUrl, entity0.getAccessUrl());
        assertEquals(loginTime, entity0.getAttemptTime());
        assertEquals(ipAddress, entity0.getIpAddress());
        assertTrue(entity0.getIsSuccess());
        assertEquals(usrAgent, entity0.getUserAgent());
        assertEquals(190, entity0.getUserCode());
        assertEquals("name_aaa", entity0.getUserName());
    }
}
