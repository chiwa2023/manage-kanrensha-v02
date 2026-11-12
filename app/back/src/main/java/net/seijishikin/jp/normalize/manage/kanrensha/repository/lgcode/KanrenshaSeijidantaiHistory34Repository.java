package net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaTableHistorySeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory34Entity;

/**
 * kanrensha_seijidantai_history_34接続用Repository
 */
public interface KanrenshaSeijidantaiHistory34Repository
        extends JpaRepository<KanrenshaSeijidantaiHistory34Entity, Integer>,
        PagingAndSortingRepository<KanrenshaSeijidantaiHistory34Entity, Integer> {

    /**
     * 検索用カラムを検索対象とした場合の検索件数を取得する
     *
     * @param searchWords 検索語
     * @return 検索件数
     */
    @Query(value = "SELECT count(*) FROM kanrensha_seijidantai_history_34"
            + " WHERE MATCH(search_text) AGAINST (?1 IN BOOLEAN MODE) AND is_latest=1", nativeQuery = true)
    Integer countFullText(String searchWords);

    /**
     * 検索用カラムを検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT kanrensha_seijidantai_history_id ,seijidantai_kanrensha_code ,all_name "
            + " ,all_address ,org_delegate_name ,org_delegate_code FROM kanrensha_seijidantai_history_34"
            + " WHERE MATCH(search_text) AGAINST (?1 IN BOOLEAN MODE) AND is_latest=1", nativeQuery = true)
    List<KanrenshaTableHistorySeijidantaiDto> findFullText(String searchWords, Pageable pageable);

    /**
     * 政治団体の属性でリスト取得する
     *
     * @param name     団体名称
     * @param address  住所
     * @param delegate 代表者名
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM kanrensha_seijidantai_history_34 " + " WHERE all_name = ?1 AND all_address = ?2 "
            + "   AND org_delegate_name = ?3 AND is_latest=1", nativeQuery = true)
    List<KanrenshaSeijidantaiHistoryBaseEntity> selectByProperty(String name, String address, String delegate);

    /**
     * 基準時間より前の最新データを取得する
     *
     * @param dateTime 基準日時開始
     * @param isLatest 最新該否
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<KanrenshaSeijidantaiHistory34Entity> findByInsertTimestampLessThanAndIsLatest(LocalDateTime dateTime,
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
    Page<KanrenshaSeijidantaiHistory34Entity> findByInsertTimestampGreaterThanEqualAndInsertTimestampLessThanAndIsLatest(
            LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, boolean isLatest, Pageable pageable);

    /**
     * 関連者コードが一致かつ最新
     * 
     * @param code 関連者コード
     * @return 検索結果
     */
    List<KanrenshaSeijidantaiHistory34Entity> findBySeijidantaiKanrenshaCodeAndIsLatestTrue(String code);

}
