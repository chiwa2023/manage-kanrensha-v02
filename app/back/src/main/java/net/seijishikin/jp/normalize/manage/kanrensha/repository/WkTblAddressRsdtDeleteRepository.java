package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;

/**
 * wk_tbl_address_rsdt_delete接続用Repository
 */
public interface WkTblAddressRsdtDeleteRepository extends JpaRepository<WkTblAddressRsdtDeleteEntity, Integer> {


    Integer deleteByInsertUserCode(Integer userCode);

}
