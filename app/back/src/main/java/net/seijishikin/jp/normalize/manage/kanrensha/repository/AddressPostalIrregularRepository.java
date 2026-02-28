package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionIntegerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * address_postal_irregular接続用Repository
 */
public interface AddressPostalIrregularRepository extends JpaRepository<AddressPostalIrregularEntity, Integer> {

    /**
     * 郵便番号から住所を取得する
     *
     * @param postal1 郵便番号
     * @return 検索結果
     */
    @Query(value = "SELECT address_postal_irregular_id AS value , address_postal AS text"
            + " FROM address_postal_irregular WHERE postalcode1 = ?1 AND postalcode2 = ?2"
            + " AND is_latest = 1", nativeQuery = true)
    List<SelectOptionIntegerDto> findByPostalCode(String postal1, String postal2);

    /**
     * 同一建物=住所名称を取得する
     *
     * @param words        建物名
     * @param isRepairRsdt 修正完了の有無
     * @return 検索結果
     */
    List<AddressPostalIrregularEntity> findByAddressOrgContainingAndIsRepairRsdtAndIsLatestTrue(String words, Boolean isRepairRsdt);

    /**
     * 住所名称が一致する郵便番号不規則を取得する
     *
     * @param words 住所名
     * @return 住所名が一致するデータ
     */
    List<AddressPostalIrregularEntity> findByAddressNameAndIsLatestTrue(String words);

}
