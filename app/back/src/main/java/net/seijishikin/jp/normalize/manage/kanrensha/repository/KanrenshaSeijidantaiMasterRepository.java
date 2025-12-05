package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;

/**
 * kanrensha_seijidantai_master接続用Repository
 */
public interface KanrenshaSeijidantaiMasterRepository extends JpaRepository<KanrenshaSeijidantaiMasterEntity, Integer> {

}
