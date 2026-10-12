package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.LoginHistory2026Entity;

/**
 * login_history_2026接続用Repository
 */
public interface LoginHistory2026Repository extends JpaRepository<LoginHistory2026Entity, Integer> {

    /**
     * 指定期間内ログイン失敗件数を取得する
     * 
     * @param email         メールアドレス
     * @param startDatetime 検索条件開始日時
     * @param endDatetime   検索条件終了日時
     * @return 該当件数
     */
    Integer countByEmailAndAttemptTimeBetweenAndIsSuccessFalse(String email, LocalDateTime startDatetime,
            LocalDateTime endDatetime);
}
