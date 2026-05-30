package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtFileEntity;

/**
 * wk_tbl_address_rsdt_file接続用Repository
 */
public interface WkTblAddressRsdtFileRepository extends JpaRepository<WkTblAddressRsdtFileEntity, Integer> {

    Page<WkTblAddressRsdtFileEntity> findByInsertUserCodeAndIsLatestTrue(Integer userCode, Pageable pageable);

    List<WkTblAddressRsdtFileEntity> findFirstByInsertUserCodeAndMachiazaIdAndBlkIdAndPrcIdAndRsdtIdAndRsdt2IdAndAddressBuildingAndIsLatestTrue(
            Integer userCode, String machiazaId, String blkId, String prcId, String rsdtId, String rsdt2Id,
            String addressBuilding);

    Integer deleteByInsertUserCode(Integer userCode);
}
