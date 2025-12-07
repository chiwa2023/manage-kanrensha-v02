package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;

/**
 * kanrensha_person_address接続用Repository
 */
public interface KanrenshaPersonAddressRepository extends JpaRepository<KanrenshaPersonAddressEntity, Integer> {

}
