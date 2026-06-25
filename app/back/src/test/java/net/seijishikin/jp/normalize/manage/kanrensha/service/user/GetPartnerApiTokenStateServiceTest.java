package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.PartnerAccessTokenStateDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * GetPartnerApiTokenStateService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("GetPartnerApiTokenStateServiceTest.sql")
class GetPartnerApiTokenStateServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetPartnerApiTokenStateService getPartnerApiTokenStateService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 指定したユーザが存在しないとき
        assertThrows(EmptyResultDataAccessException.class,
                () -> getPartnerApiTokenStateService.practice(new FrameworkCapsuleDto()));

        // 正常取得
        FrameworkCapsuleDto capsuleDto = new FrameworkCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        PartnerAccessTokenStateDto stateDto = getPartnerApiTokenStateService.practice(capsuleDto);

        assertEquals(326, stateDto.getPartnerAccessTokenId());
        assertEquals(190, stateDto.getUserCode());
        assertEquals("name_aaa", stateDto.getUserName());
        assertEquals(LocalDateTime.of(2022, 12, 5, 12, 34, 56), stateDto.getCreatedAt());
        assertEquals(LocalDateTime.of(2023, 6, 5, 12, 34, 56), stateDto.getExpiresAt());
        assertEquals(LocalDateTime.of(2023, 1, 2, 12, 34, 56), stateDto.getLastUsedAt());
        assertNull(stateDto.getRevokedAt());
        assertEquals("127.0.0.1", stateDto.getIpAddress()); // NOPMD

        // 作成実装から複数取得は想定しない
    }

}
