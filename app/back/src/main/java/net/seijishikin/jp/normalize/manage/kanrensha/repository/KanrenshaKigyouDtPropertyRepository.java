package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;

/**
 * kanrensha_kigyou_dt_property接続用Repository
 */
public interface KanrenshaKigyouDtPropertyRepository extends JpaRepository<KanrenshaKigyouDtPropertyEntity, Integer> {

}
