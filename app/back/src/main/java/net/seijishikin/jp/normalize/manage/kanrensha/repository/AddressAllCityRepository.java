package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressAllCityEntity;

/**
 * address_all_city接続用Repository
 */
public interface AddressAllCityRepository extends JpaRepository<AddressAllCityEntity, Integer>,
        PagingAndSortingRepository<AddressAllCityEntity, Integer> {

    /**
     * 地方公共団体コード前方一致条件で取得する
     *
     * @param lgStarts 地方公共団体コードの一部
     * @return 検索結果
     */
    List<AddressAllCityEntity> findByLgCodeStartingWith(String lgStarts);

    /**
     * 登録ファイルに存在しない地方自治体を取得する
     * 
     * @param userCode ユーザコード
     * @param pageable ページング
     * @return 検索結果
     */
    @Query(value = "select * from address_all_city where lg_code not in "
            + "      (select lg_code from wk_tbl_address_city "
            + "         where insert_user_code = ?1 and is_latest = 1)", nativeQuery = true)
    Page<AddressAllCityEntity> findAllCityNotIn(Integer userCode, Pageable pageable);

    /**
     * 最新かつ地方自治体コードが一致する地方自治体を取得する
     * 
     * @param lgCode 地方自治体コード
     * @return 検索結果
     */
    List<AddressAllCityEntity> findByLgCodeAndIsLatestTrue(String lgCode);

    /**
     * 最新かつ廃止していない地方自治体コードを取得する
     *
     * @param pageable ページング
     * @return 検索結果
     */
    @Query(value = "SELECT lg_code FROM address_all_city WHERE is_latest = 1 and (abolish_date is null or abolish_date > ?1)", nativeQuery = true)
    List<String> findLgCode(LocalDate abolishDate, Pageable pageable);

    /**
     * 最新かつ廃止していない地方自治体コードの件数を取得する
     * 
     * @param abolishDate 廃止日
     * @return 検索結果
     */
    @Query(value = "SELECT count(*) FROM address_all_city WHERE is_latest = 1 and (abolish_date is null or abolish_date > ?1)", nativeQuery = true)
    Integer countLgCode(LocalDate abolishDate);

    /**
     * 地方自治体コード5桁を県条件で検索する
     * 
     * @param prefCode 県地自体コード
     * @return 検索結果
     */
    @Query(value = "SELECT LEFT(lg_code,5) AS value, CONCAT(pref,county,city,ward) AS text FROM address_all_city "
            + "  where lg_code LIKE ?1 AND is_latest = 1 AND effect_date < NOW() "
            + "      AND (abolish_date > NOW() OR abolish_date is null) ORDER BY lg_code ASC;", nativeQuery = true)
    List<SelectOptionStringDto> findPrefCityDigit5(String prefCode);

    /**
     * 地方自治体コードを県条件で検索する
     * 
     * @param prefCode 県地自体コード
     * @return 検索結果
     */
    @Query(value = "SELECT lg_code AS value, CONCAT(pref,county,city,ward) AS text FROM address_all_city "
            + "  where lg_code LIKE ?1 AND is_latest = 1 AND effect_date < NOW() "
            + "      AND (abolish_date > NOW() OR abolish_date is null) ORDER BY lg_code ASC;", nativeQuery = true)
    List<SelectOptionStringDto> findPrefCity(String prefCode);

}
