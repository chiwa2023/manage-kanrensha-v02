package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * address_postal_irregular接続用Repository
 */
public interface AddressPostalIrregularRepository extends JpaRepository<AddressPostalIrregularEntity, Integer> {
}
