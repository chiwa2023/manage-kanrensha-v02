package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;

/**
 * kanrensha_person_access接続用Repository
 */
public interface KanrenshaPersonAccessRepository extends JpaRepository<KanrenshaPersonAccessEntity, Integer> {

}
