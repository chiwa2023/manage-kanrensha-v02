package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;

/**
 * kanrensha_seijidantai_address接続用Repository
 */
public interface KanrenshaSeijidantaiAddressRepository
        extends JpaRepository<KanrenshaSeijidantaiAddressEntity, Integer> {

}
