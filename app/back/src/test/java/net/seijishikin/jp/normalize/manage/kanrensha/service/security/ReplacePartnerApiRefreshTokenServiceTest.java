package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.List;

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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.PartnerApiTokenCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.PartnerApiTokenResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessTokenEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PartnerAccessTokenRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ReplacePartnerApiRefreshTokenService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("ReplacePartnerApiRefreshTokenServiceTest.sql")
class ReplacePartnerApiRefreshTokenServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ReplacePartnerApiRefreshTokenService replacePartnerApiRefreshTokenService;

    /** APIパートナー長期トークン管理Repository */
    @Autowired
    private PartnerAccessTokenRepository partnerAccessTokenRepository;

    /** APIパートナートークン用PasswordEncoder */
    @Autowired
    private PartnerTokenEncoder partnerTokenEncoder;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PartnerApiTokenCapsuleDto capsuleDto = new PartnerApiTokenCapsuleDto();
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto.setUserDto(userDto);
        capsuleDto.setIpAddress("1.2.3.4"); // NOPMD

        List<PartnerAccessTokenEntity> listPre = partnerAccessTokenRepository
                .findByUserCodeAndRevokedAtNull(userDto.getUserPersonCode());
        assertEquals(2, listPre.size()); // 実際には2件未失効データが存在するのはあってはならないがテスト

        LocalDateTime createTime = LocalDateTime.of(2022, 12, 5, 12, 34, 56);

        PartnerApiTokenResultDto resultDto = replacePartnerApiRefreshTokenService.practice(capsuleDto, createTime);

        // 処理後に有効データを再取得
        List<PartnerAccessTokenEntity> listPro = partnerAccessTokenRepository
                .findByUserCodeAndRevokedAtNull(userDto.getUserPersonCode());
        // 今作成したデータだけがユーザに紐づく有効な長期トークン状態として存在
        assertEquals(1, listPro.size());
        PartnerAccessTokenEntity tokenEntity = listPro.get(0);

        assertEquals(userDto.getUserPersonCode(), tokenEntity.getUserCode());
        assertEquals(userDto.getUserPersonName(), tokenEntity.getUserName());
        assertEquals(createTime, tokenEntity.getCreatedAt());
        assertEquals(LocalDateTime.of(2023, 6, 5, 12, 34, 56), tokenEntity.getExpiresAt());
        assertNull(tokenEntity.getLastUsedAt());
        assertNull(tokenEntity.getRevokedAt());
        assertEquals("1.2.3.4" ,tokenEntity.getIpAddress()); // NOPMD

        // トークンのハッシュが空でないことと、SHA-256の長さであることを検証
        assertNotNull(tokenEntity.getAccessTokenHash());
        assertEquals(64, tokenEntity.getAccessTokenHash().length());

        // 返り値がハッシュでないことを確認
        assertNotEquals(tokenEntity.getAccessTokenHash(), resultDto.getToken());
        // ハッシュ化したら一致すること
        assertEquals(tokenEntity.getAccessTokenHash(), partnerTokenEncoder.encode(resultDto.getToken()));
    }

}
