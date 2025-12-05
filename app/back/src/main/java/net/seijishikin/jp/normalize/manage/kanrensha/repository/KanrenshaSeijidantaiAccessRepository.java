package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;

/**
 * kanrensha_seijidantai_access接続用Repository
 */
public interface KanrenshaSeijidantaiAccessRepository extends JpaRepository<KanrenshaSeijidantaiAccessEntity, Integer> {

}
