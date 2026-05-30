package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;

/**
 * wk_tbl_address_rsdt_change接続用Repository
 */
public interface WkTblAddressRsdtChangeRepository extends JpaRepository<WkTblAddressRsdtChangeEntity, Integer> {


    Integer deleteByInsertUserCode(Integer userCode);

}
