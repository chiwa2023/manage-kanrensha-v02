package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2022.LoginHistory2022Repository;

/**
 * ログイン失敗履歴件数取得Logic
 */
@Component
public class GetLoginHistoryFailureY2022Logic {

    /** ログイン履歴Repository(2022) */
    @Autowired
    private LoginHistory2022Repository loginHistory2022Repository;

    /**
     * 処理を行う
     * 
     * @param email         メールアドレス
     * @param startDatetime 検索条件開始日時
     * @param endDatetime   検索条件終了日時
     * @return ログイン失敗該当件数
     */
    public int practice(final String email, final LocalDateTime startDatetime, final LocalDateTime endDatetime) {

        return loginHistory2022Repository.countByEmailAndAttemptTimeBetweenAndIsSuccessFalse(email, startDatetime,
                endDatetime);
    }
}
