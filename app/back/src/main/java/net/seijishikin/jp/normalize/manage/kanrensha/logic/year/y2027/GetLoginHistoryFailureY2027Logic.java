package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2027.LoginHistory2027Repository;

/**
 * ログイン失敗履歴件数取得Logic
 */
@Component
public class GetLoginHistoryFailureY2027Logic {

    /** ログイン履歴Repository(2027) */
    @Autowired
    private LoginHistory2027Repository loginHistory2027Repository;

    /**
     * 処理を行う
     * 
     * @param email         メールアドレス
     * @param startDatetime 検索条件開始日時
     * @param endDatetime   検索条件終了日時
     * @return ログイン失敗該当件数
     */
    public int practice(final String email, final LocalDateTime startDatetime, final LocalDateTime endDatetime) {

        return loginHistory2027Repository.countByEmailAndAttemptTimeBetweenAndIsSuccessFalse(email, startDatetime,
                endDatetime);
    }
}
