package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaAddressBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;

/**
 * kanrensha_person_address接続用Repository
 */
public interface KanrenshaPersonAddressRepository extends JpaRepository<KanrenshaPersonAddressEntity, Integer>,
        PagingAndSortingRepository<KanrenshaPersonAddressEntity, Integer> {

    /**
     * 関連者コードで検索する
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<KanrenshaPersonAddressEntity> findByPersonKanrenshaCodeOrderByKanrenshaPersonAddressIdDesc(
            String kanrenshaCode);

    /**
     * 作業承認行を抽出する
     *
     * @param startDatetime 検索開始日時
     * @param endDatetime   検索終了日時
     * @param listAccept    作業終了リスト
     * @param pageable      ページング
     * @return 検索結果
     */
    @Query(value = "" //
            + "SELECT " //
            + "kanrensha_person_address_id AS kanrensha_address_id, 0 AS kanrensha_master_id,"
            + "                person_kanrensha_code AS kanrensha_code, 1 AS kanrensha_kbn, kanrensha_name AS kanrensha_name,"
            + "                is_latest AS is_latest, address_postal AS address_postal, address_block AS address_block,"
            + "                address_building AS address_building, postalcode1 AS postalcode1, postalcode2 AS postalcode2,"
            + "                lg_code AS lg_code, machiaza_id AS machiaza_id, blk_id AS blk_id,"
            + "                rsdt_id AS rsdt_id, rsdt2_id AS rsdt2_id, prc_id AS prc_id,"
            + "                is_postal_edit AS is_postal_edit, is_block_edit AS is_block_edit, is_building_edit AS is_building_edit,"
            + "                is_postal_accept AS is_postal_accept, is_block_accept AS is_block_accept, is_building_accept AS is_building_accept"
            + "              FROM kanrensha_person_address "
            + "                WHERE insert_timestamp between ?1 and ?2"
            + "                  AND (is_postal_edit = 1 OR is_block_edit = 1 OR is_building_edit = 1)"
            + "                  AND (is_postal_accept IN ?3 OR is_block_accept IN ?3 OR is_building_accept IN ?3)"
            + "                  AND is_latest = 1", nativeQuery = true)
    List<KanrenshaAddressBaseEntity> findIsEditData(LocalDateTime startDatetime, LocalDateTime endDatetime,
            List<Boolean> listAccept, Pageable pageable);

    /**
     * 作業承認行をカウントする
     *
     * @param startDatetime 検索開始日時
     * @param endDatetime   検索狩猟日時
     * @param listAccept    作業終了フラグリスト
     * @return 該当件数
     */
    @Query(value = "SELECT count(*) AS person_count FROM kanrensha_person_address "
            + "                           WHERE insert_timestamp between ?1 and ?2"
            + "                              AND (is_postal_edit = 1 OR is_block_edit = 1 OR is_building_edit = 1)"
            + "                              AND (is_postal_accept IN ?3 OR is_block_accept IN ?3 OR is_building_accept IN ?3)"
            + "                              AND is_latest = 1 ", nativeQuery = true)
    Integer countIsEditData(LocalDateTime startDatetime, LocalDateTime endDatetime, List<Boolean> listAccept);

}
