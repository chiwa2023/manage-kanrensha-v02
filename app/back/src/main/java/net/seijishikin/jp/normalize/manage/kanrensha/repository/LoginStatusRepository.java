package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginStatusEntity;

/**
 * login_status接続用Repository
 */
public interface LoginStatusRepository extends JpaRepository<LoginStatusEntity, String> {

}
