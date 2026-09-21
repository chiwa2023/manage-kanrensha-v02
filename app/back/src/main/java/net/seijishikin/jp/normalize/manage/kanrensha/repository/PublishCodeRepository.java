package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.PublishCodeEntity;

/**
 * publish_code接続用Repository
 */
public interface PublishCodeRepository extends JpaRepository<PublishCodeEntity, String> {

}
