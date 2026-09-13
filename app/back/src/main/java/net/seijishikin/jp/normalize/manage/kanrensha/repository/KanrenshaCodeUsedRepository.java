package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeUsedEntity;

/**
 * kanrensha_code_used接続用Repository
 */
public interface KanrenshaCodeUsedRepository extends JpaRepository<KanrenshaCodeUsedEntity, String> {

}
