package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.SaveLoginStatusHistoryY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026.SaveLoginStatusHistoryY2026Logic;

/**
 * ログイン履歴記録Service
 */
@Service
public class SwitchYearLoginHistoryService {

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** ログイン履歴複写Logic(2025) */
    @Autowired
    private SaveLoginStatusHistoryY2025Logic copyLoginStatusHistoryY2025Logic;

    /** 登録対応年(2026) */
    private static final int YEAR_2026 = 2026;
    /** ログイン履歴複写Logic(2026) */
    @Autowired
    private SaveLoginStatusHistoryY2026Logic copyLoginStatusHistoryY2026Logic;

    /**
     * 処理を行う
     * 
     * @param username  ユーザ名
     * @param ipAddress IPアドレス
     * @param userAgent ユーザエージェント
     * @param success   ログイン成功フラグ
     */
    @Transactional
    public Integer practice(final String username, final String ipAddress, final String userAgent,
            final boolean success) {
        LoginHistoryBaseEntity baseEntity = new LoginHistoryBaseEntity();
        baseEntity.setEmail(username);
        baseEntity.setIpAddress(ipAddress);
        baseEntity.setUserAgent(userAgent);
        baseEntity.setIsSuccess(success);
        LocalDateTime now = LocalDateTime.now();
        baseEntity.setAttemptTime(now);

        switch (now.getYear()) {
            case YEAR_2025:
                return copyLoginStatusHistoryY2025Logic.practice(baseEntity);
            case YEAR_2026:
                return copyLoginStatusHistoryY2026Logic.practice(baseEntity);
            default:
                throw new IllegalArgumentException("Unexpected value: " + now.getYear());
        }

    }
}
