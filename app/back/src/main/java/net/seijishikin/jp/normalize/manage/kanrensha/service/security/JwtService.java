package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.config.JwtConfig;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.JwtTokenDto;

//import mitei.mitei.political.balancesheet.manage.kanrensha.config.JwtConfig;

/**
 * JWTサービス
 */
@Service
public class JwtService {

    /** JwtEncoder */
    @Autowired
    private JwtEncoder jwtEncoder;

    /** JwtConfig */
    @Autowired
    private JwtConfig jwtConfig;

    /**
     * 認証情報からトークンを作成する
     *
     * @param authentication 認証情報
     * @return トークン
     */
    public JwtTokenDto generateToken(final Authentication authentication) {
        return generateToken(authentication.getName(), authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    }

    /**
     * ユーザ情報からトークンを生成する
     *
     * @param userDetails ユーザ情報
     * @return トークンDto
     */
    public JwtTokenDto generateToken(final UserDetails userDetails) {
        return generateToken(userDetails.getUsername(),
                userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    }

    /**
     * ユーザ名と役割からトークンを作成する
     *
     * @param username ユーザ名
     * @param roles 役割
     * @return トークン
     */
    public JwtTokenDto generateToken(final String username, final Iterable<String> roles) {
        // 現在の時刻
        Instant now = Instant.now();

        // アクセストークンの有効期限
        Instant accessTokenExpiry = now.plus(jwtConfig.getExpiration(), ChronoUnit.MILLIS);

        // リフレッシュトークンの有効期限
        Instant refreshTokenExpiry = now.plus(jwtConfig.getRefreshExpiration(), ChronoUnit.MILLIS);

        // アクセストークンに含めるクレーム
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);

        // アクセストークンの生成
        String accessToken = createToken(username, now, accessTokenExpiry, claims);

        // リフレッシュトークンの生成（権限情報は含めない）
        String refreshToken = createToken(username, now, refreshTokenExpiry, new HashMap<>());

        return new JwtTokenDto(accessToken, refreshToken, Date.from(accessTokenExpiry));
    }

    private String createToken(final String subject, final Instant issuedAt, final Instant expiresAt,
            final Map<String, Object> claims) {
        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder().subject(subject).issuedAt(issuedAt)
                .expiresAt(expiresAt);

        // カスタムクレームの追加
        claims.forEach(claimsBuilder::claim);

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsBuilder.build())).getTokenValue();
    }
}