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

    /**
     * ユーザコード条件で削除する
     * 
     * @param userCode 操作者ユーザコード
     * @return 処理件数
     * 
     */
    Integer deleteByInsertUserCode(Integer userCode);

    /**
     * 最新かつユーザコード条件で検索する
     * 
     * @param userCode 操作者ユーザコード
     * @param pageable ページング
     * @return 検索結果
     */
    Page<WkTblAddressRsdtFileEntity> findByInsertUserCodeAndIsLatestTrue(Integer userCode, Pageable pageable);

    /**
     * 住所番地代表住所(建物まで住所が空白)を取得する
     * 
     * @param userCode        ユーザコード
     * @param machiazaId      町字Id
     * @param blkId           地番Id
     * @param prcId           街区Id
     * @param rsdtId          住居Id
     * @param rsdt2Id         住居2Id
     * @param addressBuilding 建物まで住所
     * @return 検索結果
     */
    List<WkTblAddressRsdtFileEntity> //
            findFirstByInsertUserCodeAndMachiazaIdAndBlkIdAndPrcIdAndRsdtIdAndRsdt2IdAndAddressBuildingAndIsLatestTrue( // NOPMD
                    Integer userCode, String machiazaId, String blkId, String prcId, String rsdtId, String rsdt2Id,
                    String addressBuilding);

}
