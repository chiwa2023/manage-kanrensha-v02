package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.PartnerApiTokenCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.PartnerApiTokenResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessTokenEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PartnerAccessTokenRepository;

/**
 * トークン発行Service
 */
@Service
public class ReplacePartnerApiRefreshTokenService {

    /** 長期有効期限月 */
    public static final Short EXPIRES_LIMIT_MONTH = 6;

    /** APIパートナー長期トークン管理Repository */
    @Autowired
    private PartnerAccessTokenRepository partnerAccessTokenRepository;

    /** token生成Service */
    @Autowired
    private JwtService jwtService;

    /** APIパートナートークン用PasswordEncoder */
    @Autowired
    private PartnerTokenEncoder partnerTokenEncoder;

    /**
     * 処理を行う
     * 
     * @param capsuleDto     長期トークン発行ユーザ情報
     * @param createDateTime 作成時間
     * @return 処理Id
     */
    public PartnerApiTokenResultDto practice(final PartnerApiTokenCapsuleDto capsuleDto,
            final LocalDateTime createDateTime) {

        LeastUserDto userDto = capsuleDto.getUserDto();
        // 作成前に常に過去データは失効
        List<PartnerAccessTokenEntity> list = partnerAccessTokenRepository
                .findByUserCodeAndRevokedAtNull(userDto.getUserPersonCode());
        for (PartnerAccessTokenEntity entity : list) {
            entity.setRevokedAt(createDateTime);
        }
        partnerAccessTokenRepository.saveAll(list);

        // 新規作成
        LocalDateTime now = LocalDateTime.from(createDateTime);
        LocalDateTime expiresAt = now.plusMonths(EXPIRES_LIMIT_MONTH); // 有効期間6か月
        ZoneOffset zoneOffset = ZoneOffset.UTC;
        Instant issuedAtInstant = now.toInstant(zoneOffset);
        Instant expiresAtInstant = expiresAt.toInstant(zoneOffset);
        Map<String, Object> claims = new TreeMap<>();
        claims.put("role", UserRoleConstants.PARTNER_API);

        // 新しいトークンの生成
        String token = jwtService.createToken(userDto.getUserPersonName(),
                issuedAtInstant, expiresAtInstant, claims);

        // トークンのencode
        String tokenHashed = partnerTokenEncoder.encode(token);

        PartnerAccessTokenEntity accessTokenEntity = new PartnerAccessTokenEntity();
        accessTokenEntity.setUserCode(userDto.getUserPersonCode());
        accessTokenEntity.setUserName(userDto.getUserPersonName());
        accessTokenEntity.setAccessTokenHash(tokenHashed);
        accessTokenEntity.setCreatedAt(now);
        accessTokenEntity.setExpiresAt(expiresAt);
        accessTokenEntity.setIpAddress(capsuleDto.getIpAddress());

        // 失効日時と最終使用日時はnull
        accessTokenEntity.setRevokedAt(null);
        accessTokenEntity.setLastUsedAt(null);

        accessTokenEntity.setPartnerAccessTokenId(0); // auto increment明記

        PartnerApiTokenResultDto resultDto = new PartnerApiTokenResultDto();
        if (0 == partnerAccessTokenRepository.save(accessTokenEntity).getPartnerAccessTokenId()) {
            return null;
        } else {
            resultDto.setToken(token);
            return resultDto;
        }
    }
}
