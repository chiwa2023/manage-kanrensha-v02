package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PartnerAccessTokenEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.PartnerAccessTokenRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearSavePartnerApiLoginHistoryService;

/**
 * API接続ログインService
 * 
 * <p>
 * トークン確認と路銀履歴記録を行う
 * </p>
 */
@Service
public class PartnerApiLoginService {

    /** APIパートナー長期トークンRepository */
    @Autowired
    private PartnerAccessTokenRepository partnerAccessTokenRepository;

    /** APIパートナー長期トークンEncoder */
    @Autowired
    private PartnerTokenEncoder partnerTokenEncoder;

    /** APIパートナーログイン履歴 */
    @Autowired
    private SwitchYearSavePartnerApiLoginHistoryService switchYearSavePartnerApiLoginHistoryService;

    /** APIパートナー長期トークンEncoder */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

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

        String tokenEncoded = partnerTokenEncoder.encode(partnerToken);

        // Uniqueトークンをキーにして管理状態を取得
        Optional<PartnerAccessTokenEntity> optional = partnerAccessTokenRepository.findByAccessTokenHash(tokenEncoded);

        // 取得できない場合はログインさせない
        if (optional.isEmpty()) {
            // ユーザ情報が取得できない
            baseEntity.setUserCode(0);
            baseEntity.setUserName("");
            baseEntity.setIsSuccess(false);
            // 履歴をアクセス失敗で記録
            this.saveHitory(baseEntity);
            throw new InvalidBearerTokenException("指定されたTokenは登録されていません");
        }

        // 管理状態が取得できた
        PartnerAccessTokenEntity tokenEntity = optional.get();
        baseEntity.setUserCode(tokenEntity.getUserCode());
        baseEntity.setUserName(tokenEntity.getUserName());

        // 有効期間を過ぎているか、資格剥奪時に入力がある場合は資格失効
        if (!Objects.isNull(tokenEntity.getRevokedAt()) || loginTime.isAfter(tokenEntity.getExpiresAt())) {
            baseEntity.setIsSuccess(false);
            // 履歴をアクセス失敗で記録
            this.saveHitory(baseEntity);
            throw new LockedException("このTokenは有効期間経過などの理由で失効しています");
        }

        // IPアドレスが登録されており、一致しない場合はエラー
        if (!ipAddress.equals(tokenEntity.getIpAddress())) {
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
        userDto.setUserPersonCode(tokenEntity.getUserCode());
        userDto.setUserPersonName(tokenEntity.getUserName());
        return userDto;
    }

    private void saveHitory(final PartnerAccessHistoryBaseEntity baseEntity) {

        try {
            // ログイン履歴を記録
            switchYearSavePartnerApiLoginHistoryService.practice(baseEntity);

        } catch (Exception exception) { // NOPMD
            saveStackTraceService.practice(exception, baseEntity.getAttemptTime().getYear(), 0);
            // TODO 資格に問題がないが履歴が登録できない場合の処理は決定後実装する
        }
    }

}
