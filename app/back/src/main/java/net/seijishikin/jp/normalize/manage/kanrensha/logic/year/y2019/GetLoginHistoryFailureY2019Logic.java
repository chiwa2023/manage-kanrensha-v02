package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2019;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2019.LoginHistory2019Repository;

/**
 * ログイン失敗履歴件数取得Logic
 */
@Component
public class GetLoginHistoryFailureY2019Logic {

    /** ログイン履歴Repository(2019) */
    @Autowired
    private LoginHistory2019Repository loginHistory2019Repository;

    /**
     * 処理を行う
     * 
     * @param email         メールアドレス
     * @param startDatetime 検索条件開始日時
     * @param endDatetime   検索条件終了日時
     * @return ログイン失敗該当件数
     */
    public int practice(final String email, final LocalDateTime startDatetime, final LocalDateTime endDatetime) {

        return loginHistory2019Repository.countByEmailAndAttemptTimeBetweenAndIsSuccessFalse(email, startDatetime,
                endDatetime);
    }
}
