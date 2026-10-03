package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasswordResetEntity;

/**
 * user_password_reset接続用Repository
 */
public interface UserPasswordResetRepository extends JpaRepository<UserPasswordResetEntity, String> {

    /**
     * 期限切れを削除する
     * 
     * @param limitDate 削除起源
     * @return 削除件数
     */
    Integer deleteByLimitDatetimeLessThan(LocalDateTime limitDate);

}
