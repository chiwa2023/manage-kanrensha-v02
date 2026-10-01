package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2021;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2021.LoginHistory2021Entity;


/**
 * login_history_2021接続用Repository
 */
public interface LoginHistory2021Repository extends JpaRepository<LoginHistory2021Entity, Integer> {

}
