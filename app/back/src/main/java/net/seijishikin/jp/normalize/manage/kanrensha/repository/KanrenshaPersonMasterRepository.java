package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;

/**
 * kanrensha_person_master接続用Repository
 */
public interface KanrenshaPersonMasterRepository extends JpaRepository<KanrenshaPersonMasterEntity, Integer> {

}
