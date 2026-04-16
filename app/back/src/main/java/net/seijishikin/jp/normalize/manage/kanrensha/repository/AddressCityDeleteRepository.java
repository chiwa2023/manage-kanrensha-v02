package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressCityDeleteEntity;

/**
 * address_city_delete接続用Repository
 */
public interface AddressCityDeleteRepository extends JpaRepository<AddressCityDeleteEntity, Integer> {

}
