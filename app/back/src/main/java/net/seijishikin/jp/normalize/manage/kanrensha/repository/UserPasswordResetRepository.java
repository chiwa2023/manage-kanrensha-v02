package net.seijishikin.jp.normalize.manage.kanrensha.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasswordResetEntity;

/**
 * user_password_reset接続用Repository
 */
public interface UserPasswordResetRepository  extends JpaRepository<UserPasswordResetEntity, String>{

}
