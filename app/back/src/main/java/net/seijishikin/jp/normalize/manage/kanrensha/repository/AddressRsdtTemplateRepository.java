package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * address_rsdt_template接続用Repository
 */
public interface AddressRsdtTemplateRepository extends JpaRepository<AddressRsdtTemplateEntity, Integer> {

}
