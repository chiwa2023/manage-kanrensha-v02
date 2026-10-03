package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2020;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2020.LoginHistory2020Entity;


/**
 * login_history_2020接続用Repository
 */
public interface LoginHistory2020Repository extends JpaRepository<LoginHistory2020Entity, Integer> {

}
