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
    List<AddressPostalEntity> findByPostalcode1AndPostalcode2OrderByAddressNameAsc(String postal1, String postal2);

    /**
     * 不規則データを抽出する
     *
     * @param isSearch 地方自治体住居テーブルを検索する
     * @param pageable ページング
     * @return 検索結果
     */
    Page<AddressPostalEntity> findByIsGyoseikuDataAndIsLatestTrue(Boolean isSearch, Pageable pageable);

}
