package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2023;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2023.LoginHistory2023Entity;


/**
 * login_history_2023接続用Repository
 */
public interface LoginHistory2023Repository extends JpaRepository<LoginHistory2023Entity, Integer> {

}
