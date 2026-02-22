package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;

/**
 * address_postal接続用Repository
 */
public interface AddressPostalRepository extends JpaRepository<AddressPostalEntity, Integer> {

}
