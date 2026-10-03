package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2022;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2022.LoginHistory2022Entity;


/**
 * login_history_2022接続用Repository
 */
public interface LoginHistory2022Repository extends JpaRepository<LoginHistory2022Entity, Integer> {

}
