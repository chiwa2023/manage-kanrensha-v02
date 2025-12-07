package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;

/**
 * kanrensha_seijidantai_property接続用Repository
 */
public interface KanrenshaSeijidantaiPropertyRepository
        extends JpaRepository<KanrenshaSeijidantaiPropertyEntity, Integer> {

}
