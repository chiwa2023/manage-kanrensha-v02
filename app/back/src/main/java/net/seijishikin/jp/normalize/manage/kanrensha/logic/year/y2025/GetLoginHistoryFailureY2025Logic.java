package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.LoginHistory2025Repository;

/**
 * ログイン失敗履歴件数取得Logic
 */
@Component
public class GetLoginHistoryFailureY2025Logic {

    /** ログイン履歴Repository(2025) */
    @Autowired
    private LoginHistory2025Repository loginHistory2025Repository;

    /**
     * 処理を行う
     * 
     * @param email         メールアドレス
     * @param startDatetime 検索条件開始日時
     * @param endDatetime   検索条件終了日時
     * @return ログイン失敗該当件数
     */
    public int practice(final String email, final LocalDateTime startDatetime, final LocalDateTime endDatetime) {

        return loginHistory2025Repository.countByEmailAndAttemptTimeBetweenAndIsSuccessFalse(email, startDatetime,
                endDatetime);
    }
}
