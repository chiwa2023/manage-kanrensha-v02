package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;

/**
 * kanrensha_kigyou_dt_master接続用Repository
 */
public interface KanrenshaKigyouDtMasterRepository extends JpaRepository<KanrenshaKigyouDtMasterEntity, Integer> {

}
