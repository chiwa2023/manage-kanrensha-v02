package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.CustomUserDetails;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessTokenEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PartnerAccessTokenRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserRoleRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearSavePartnerApiLoginHistoryService;

/**
 * API接続ログインService
 * 
 * <p>
 * トークン確認とログイン履歴記録を行う
 * </p>
 */
@Service
public class PartnerApiLoginService {

    /** APIパートナー長期トークンRepository */
    @Autowired
    private PartnerAccessTokenRepository partnerAccessTokenRepository;

    /** APIパートナーログイン履歴 */
    @Autowired
    private SwitchYearSavePartnerApiLoginHistoryService switchYearSavePartnerApiLoginHistoryService;

    /** APIパートナー長期トークンEncoder */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** IPアドレス検証ユーティリティ */
    @Autowired
    private IpAddressMatcherUtil ipAddressMatcherUtil;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** JwtDecoder */
    @Autowired
    private JwtDecoder jwtDecoder;

    /** CustomUserDetailsManager */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    /**
     * 処理を行う
     * 
     * @param partnerToken トークン
     * @param accessUrl    接続Url
     * @param userAgent    ユーザエージェント
     * @param ipAddress    IPアドレス
     * @param loginTime    試行時間
     * @return 処理結果
     */
    public LeastUserDto practice(final String partnerToken, final String accessUrl, // NOPMD
            final String userAgent, final String ipAddress, final LocalDateTime loginTime) {

        PartnerAccessHistoryBaseEntity baseEntity = new PartnerAccessHistoryBaseEntity();
        baseEntity.setAccessUrl(accessUrl);
        baseEntity.setAttemptTime(loginTime);
        baseEntity.setIpAddress(ipAddress);
        baseEntity.setUserAgent(userAgent);

        Jwt jwt = jwtDecoder.decode(partnerToken);

        // サブジェクトからユーザー名を取得
        String username = jwt.getSubject();

        // ユーザー情報の取得
        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsManager.loadUserByUsername(username);

        // Uniqueトークンをキーにして管理状態を取得
        List<PartnerAccessTokenEntity> list = partnerAccessTokenRepository
                .findByUserCodeAndRevokedAtNull(userDetails.getUserPersonCode());

        // 取得できない場合はログインさせない
        if (list.isEmpty()) {
            // ユーザ情報が取得できない
            baseEntity.setUserCode(0);
            baseEntity.setUserName("");
            baseEntity.setIsSuccess(false);
            // 履歴をアクセス失敗で記録
            this.saveHitory(baseEntity);
            throw new InvalidBearerTokenException("指定されたTokenは登録されていません");
        }

        // 管理状態が取得できた
        PartnerAccessTokenEntity tokenEntity = list.get(0);
        baseEntity.setUserCode(tokenEntity.getUserCode());
        baseEntity.setUserName(tokenEntity.getUserName());

        // 有効期間を過ぎているか、資格剥奪時に入力がある場合は資格失効
        if (!Objects.isNull(tokenEntity.getRevokedAt()) || loginTime.isAfter(tokenEntity.getExpiresAt())) {
            baseEntity.setIsSuccess(false);
            // 履歴をアクセス失敗で記録
            this.saveHitory(baseEntity);
            throw new LockedException("このTokenは有効期間経過などの理由で失効しています");
        }

        // IPアドレスが登録されており、一致（またはCIDR範囲内）しない場合はエラー
        if (!ipAddressMatcherUtil.matches(tokenEntity.getIpAddress(), ipAddress)) {
            baseEntity.setIsSuccess(false);
            // 履歴をアクセス失敗で記録
            this.saveHitory(baseEntity);
            throw new AccessDeniedException("許可されていないIPアドレスからのアクセスです");
        }

        // 最新利用時間を更新
        tokenEntity.setLastUsedAt(loginTime);
        partnerAccessTokenRepository.save(tokenEntity);

        // 履歴をアクセス成功で記録
        baseEntity.setIsSuccess(true);
        this.saveHitory(baseEntity);

        LeastUserDto userDto = new LeastUserDto();
        userDto.setUserPersonId(userDetails.getUserPersonId());
        userDto.setUserPersonCode(userDetails.getUserPersonCode());
        userDto.setUserPersonName(userDetails.getUserPersonName());
        for (String role : userRoleRepository.findLatestRoleByMail(username)) {
            userDto.getListRoles().add("ROLE_" + role);
        }

        return userDto;
    }

    private void saveHitory(final PartnerAccessHistoryBaseEntity baseEntity) {

        try {
            // ログイン履歴を記録
            switchYearSavePartnerApiLoginHistoryService.practice(baseEntity);

        } catch (Exception exception) { // NOPMD
            saveStackTraceService.practice(exception, baseEntity.getAttemptTime().getYear(), 0);
        }
    }

}
