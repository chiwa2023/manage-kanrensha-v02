package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;

/**
 * kanrensha_person_property接続用Repository
 */
public interface KanrenshaPersonPropertyRepository extends JpaRepository<KanrenshaPersonPropertyEntity, Integer> {

}
