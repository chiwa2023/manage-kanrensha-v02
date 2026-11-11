package net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaTableHistoryKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory08Entity;

/**
 * kanrensha_kigyou_dt_history_08接続用Repository
 */
public interface KanrenshaKigyouDtHistory08Repository extends JpaRepository<KanrenshaKigyouDtHistory08Entity, Integer>,
        PagingAndSortingRepository<KanrenshaKigyouDtHistory08Entity, Integer> {

    /**
     * 検索カラムを検索対象として全文検索検索件数を取得する
     *
     * @param searchWords 検索語
     * @return 検索件数
     */
    @Query(value = "SELECT count(*) FROM kanrensha_kigyou_dt_history_08"
            // + " WHERE MATCH(search_text) AGAINST (?1 IN BOOLEAN MODE) AND is_latest=1", nativeQuery = true)
            + " WHERE search_text LIKE ?1 AND is_latest=1", nativeQuery = true)// TODO MATCH AGAINST
    Integer countFullText(String searchWords);

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT kanrensha_kigyou_dt_history_id,kigyou_dt_kanrensha_code,all_name,all_address,"
            + "org_delegate_name,org_delegate_code FROM kanrensha_kigyou_dt_history_08"
            // + " WHERE MATCH(search_text) AGAINST (?1 IN BOOLEAN MODE) AND is_latest=1", nativeQuery = true)
            + " WHERE search_text LIKE ?1 AND is_latest=1", nativeQuery = true)// TODO MATCH AGAINST
    List<KanrenshaTableHistoryKigyouDtDto> findFullText(String searchWords, Pageable pageable);

    /**
     * 企業・団体の属性でリスト取得する
     *
     * @param name     団体名称
     * @param address  住所
     * @param delegate 代表者名
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM kanrensha_kigyou_dt_history_08 " + " WHERE all_name = ?1 AND all_address = ?2 "
            + "   AND org_delegate_name = ?3 AND is_latest=1", nativeQuery = true)
    List<KanrenshaKigyouDtHistoryBaseEntity> selectByProperty(String name, String address, String delegate);

    /**
     * 基準時間より前の最新データを取得する
     *
     * @param dateTime 基準日時開始
     * @param isLatest 最新該否
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<KanrenshaKigyouDtHistory08Entity> findByInsertTimestampLessThanAndIsLatest(LocalDateTime dateTime,
            boolean isLatest, Pageable pageable);

    /**
     * 基準時間開始以上かつ終了より前の最新を取得する
     *
     * @param dateTimeStart 基準日時開始
     * @param dateTimeEnd   基準日時終了
     * @param isLatest      最新該否
     * @param pageable      ページング条件
     * @return 検索結果
     */
    Page<KanrenshaKigyouDtHistory08Entity> findByInsertTimestampGreaterThanEqualAndInsertTimestampLessThanAndIsLatest(
            LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, boolean isLatest, Pageable pageable);

    /**
     * 関連者コードが一致かつ最新
     * 
     * @param code 関連者コード
     * @return 検索結果
     */
    List<KanrenshaKigyouDtHistory08Entity> findByKigyouDtKanrenshaCodeAndIsLatestTrue(String code);

}
