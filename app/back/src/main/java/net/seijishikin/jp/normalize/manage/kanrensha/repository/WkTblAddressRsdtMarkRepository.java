package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtMarkEntity;

/**
 * wk_tbl_address_rsdt_mark接続用Repository
 */
public interface WkTblAddressRsdtMarkRepository extends JpaRepository<WkTblAddressRsdtMarkEntity, Integer> {

    Integer deleteByInsertUserCode(Integer userCode);

}
