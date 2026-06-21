package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    List<AddressPostalIrregularEntity> findByAddressOrgContainingAndIsRepairRsdtAndIsLatestTrue(String words,
            Boolean isRepairRsdt,Pageable pageable);

    /**
     * 同一建物=住所名称を取得する
     *
     * @param words        建物名
     * @param isRepairRsdt 修正完了の有無
     * @return 検索結果
     */
    Integer countByAddressOrgContainingAndIsRepairRsdtAndIsLatestTrue(String words,
            Boolean isRepairRsdt);

    /**
     * 住所名称が一致する郵便番号不規則を取得する
     *
     * @param words 住所名
     * @return 住所名が一致するデータ
     */
    List<AddressPostalIrregularEntity> findByAddressNameAndIsLatestTrue(String words);

    /**
     * （その他）を抽出する
     *
     * @param lgCode   地方公共団体コード(県部分)
     * @param pageable ページング
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM address_postal_irregular WHERE lg_code LIKE ?1"
            + " AND (address_org LIKE '%その他%' OR address_org LIKE '%次のビルを除く%') AND is_latest = 1", nativeQuery = true)
    Page<AddressPostalIrregularEntity> findOtherAddress(String lgCode, Pageable pageable);

    /**
     * 波文字を含み、カンマ文字がない(範囲が1項目)を取得する
     *
     * @param lgCode   地方公共団体コード(県部分)
     * @param nami     波ダッシュ文字
     * @param comma    カンマ文字
     * @param pageable ページング
     * @return 検索結果
     */
    Page<AddressPostalIrregularEntity> findByLgCodeStartingWithAndAddressOrgLikeAndAddressOrgNotLikeAndIsLatestTrue(
            String lgCode, String nami, String comma, Pageable pageable);

    /**
     * 地名(地名)といった単一地域を抽出する
     *
     * @param lgCode   地方公共団体コード(県部分)
     * @param pageable ページング
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM address_postal_irregular WHERE lg_code LIKE ?1" + " AND address_org NOT LIKE '%〜%'"
            + " AND address_org NOT LIKE '%、%' AND address_org NOT LIKE '%階）%'"
            + " AND address_org NOT LIKE '%階層不明）%' AND address_org NOT LIKE '%（その他）%'"
            + " AND address_org NOT LIKE '%（次のビルを除く）%' AND is_latest = 1", nativeQuery = true)
    Page<AddressPostalIrregularEntity> findSingleAddress(String lgCode, Pageable pageable);

    /**
     * 番地まで住所に空白文字を含むデータを取得する
     *
     * @param lgCode   地方公共団体コード(県部分)
     * @param space    空白文字
     * @param pageable ページング
     * @return 検索結果
     */
    Page<AddressPostalIrregularEntity> findByLgCodeStartingWithAndAddressBlockLikeAndIsLatestTrue(String lgCode,
            String space, Pageable pageable);

    /**
     * 原文書に読点を含むデータを取得する
     *
     * @param lgCode   地方公共団体コード(県部分)
     * @param touten   空白文字
     * @param pageable ページング
     * @return 検索結果
     */
    Page<AddressPostalIrregularEntity> findByLgCodeStartingWithAndAddressOrgLikeAndIsLatestTrue(String lgCode,
            String touten, Pageable pageable);

    /**
     * 郵便番号1と郵便番号2が一致かつ最新を検索する
     * 
     * @param postalcode1 郵便番号1
     * @param postalcode2 郵便番号2
     * @return 検索結果
     */
    List<AddressPostalIrregularEntity> findByPostalcode1AndPostalcode2AndIsLatestTrue(String postalcode1,
            String postalcode2);

}
