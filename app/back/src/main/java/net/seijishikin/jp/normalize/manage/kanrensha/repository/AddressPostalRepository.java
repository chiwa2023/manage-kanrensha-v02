package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionIntegerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;

/**
 * address_postal接続用Repository
 */
public interface AddressPostalRepository extends JpaRepository<AddressPostalEntity, Integer> {

    /**
     * 郵便番号から住所郵便番号までを取得する
     *
     * @param postal1 郵便番号
     * @return 検索結果
     */
    @Query(value = "SELECT address_postal_id AS value , address_name AS text"
            + " FROM address_postal WHERE postalcode1 = ?1 AND postalcode2 = ?2 "
            + " AND is_gyoseiku_data = 1 AND is_latest = 1", nativeQuery = true)
    List<SelectOptionIntegerDto> findByPostalCodeAndSearchGyoseiku(String postal1, String postal2);

    /**
     * 郵便番号が同一であるデータを取得する
     *
     * @param postal1 郵便番号(7桁)
     * @return 検索結果
     */
    List<AddressPostalEntity> findByPostalcode1AndPostalcode2AndIsLatestTrueOrderByAddressNameAsc(String postal1,
            String postal2);

    /**
     * 郵便番号が同一であるデータを取得する
     *
     * @param postal1 郵便番号(7桁)
     * @return 検索結果
     */
    List<AddressPostalEntity> findByPostalcode1AndPostalcode2OrderByAddressNameAsc(String postal1, String postal2);

    /**
     * 不規則データを抽出する
     *
     * @param isSearch 地方自治体住居テーブルを検索する
     * @param pageable ページング
     * @return 検索結果
     */
    Page<AddressPostalEntity> findByIsGyoseikuDataAndIsLatestTrue(Boolean isSearch, Pageable pageable);

    /**
     * その他データを抽出する
     * 
     * @param prefCode 地方行政区コード
     * @param other    検索条件語
     * @param pageable ページング
     * @return 検索結果
     */
    Page<AddressPostalEntity> findByLgCodeStartingWithAndIsLatestTrueAndAddressOrgContaining(String prefCode,
            String other, Pageable pageable);

    /**
     * 住所原文書に特定語が含まれないデータを抽出する
     * 
     * @param prefCode 地方行政区コード
     * @param other    検索条件語
     * @param pageable ページング
     * @return 検索結果
     */
    Page<AddressPostalEntity> findByLgCodeStartingWithAndIsLatestTrueAndAddressOrgNotContaining(String prefCode,
            String other, Pageable pageable);

    /**
     * 住所名が指定語で終わるデータを抽出する
     * 
     * @param prefCode 地方行政区コード
     * @param name     指定後
     * @param pageable ページング
     * @return 検索結果
     */
    Page<AddressPostalEntity> findByLgCodeStartingWithAndIsLatestTrueAndAddressNameEndingWith(String prefCode,
            String name, Pageable pageable);

    /**
     * （が存在、以下に掲載がない場合といった、未修整データを抽出する
     * 
     * @param prefCode 地方行政区コード
     * @param pageable ページング
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM address_postal WHERE lg_code LIKE ?1 AND is_latest = 1 "
            + "AND ( address_name LIKE '%以下に掲載がない場合'  OR address_name LIKE '%（%' )", nativeQuery = true)
    Page<AddressPostalEntity> findRepairLog(String prefCode, Pageable pageable);

    /**
     * 郵便番号と住所が部分一致かつ最新を取得する
     * 
     * @param postalcode1 郵便番号1
     * @param postalcode2 郵便番号2
     * @param name        住所名
     * @param pageable    ページング
     * @return 検索結果
     */
    List<AddressPostalEntity> findByPostalcode1StartingWithAndPostalcode2StartingWithAndIsLatestTrueAndAddressNameStartingWith(
            String postalcode1, String postalcode2, String name, Pageable pageable);

    /**
     * 郵便番号と住所が部分一致かつ最新の件数を取得する
     * 
     * @param postalcode1 郵便番号1検索条件
     * @param postalcode2 郵便番号2検索条件
     * @param name        住所名
     * @return 件数
     */
    Integer countByPostalcode1StartingWithAndPostalcode2StartingWithAndIsLatestTrueAndAddressNameStartingWith(
            String postalcode1, String postalcode2, String name);

}
