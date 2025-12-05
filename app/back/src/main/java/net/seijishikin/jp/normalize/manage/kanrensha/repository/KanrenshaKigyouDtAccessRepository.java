package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;

/**
 * kanrensha_kigyou_dt_access接続用Repository
 */
public interface KanrenshaKigyouDtAccessRepository extends JpaRepository<KanrenshaKigyouDtAccessEntity, Integer> {

}
