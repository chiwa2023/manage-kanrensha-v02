package net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory01Entity;

/**
 * kanrensha_person_history_01接続用Repository
 */
public interface KanrenshaPersonHistory01Repository extends JpaRepository<KanrenshaPersonHistory01Entity, Integer> {

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM kanrensha_person_history_01"
            + " WHERE search_text LIKE ?1 AND is_latest=1", nativeQuery = true) // TODO MATCH AGAINST
    List<KanrenshaPersonHistory01Entity> findFullText(String searchWords);

    /**
     * 企業・団体の属性でリスト取得する
     *
     * @param name      団体名称
     * @param address   住所
     * @param shokugyou 代表者名
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM kanrensha_person_history_01 " + " WHERE all_name = ?1 AND all_address = ?2 "
            + "   AND person_shokugyou = ?3 AND is_latest=1", nativeQuery = true)
    List<KanrenshaPersonHistoryBaseEntity> selectByProperty(String name, String address, String shokugyou);

    /**
     * 基準時間より前の最新データを取得する
     *
     * @param dateTime 基準日時開始
     * @param isLatest 最新該否
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<KanrenshaPersonHistory01Entity> findByInsertTimestampLessThanAndIsLatest(LocalDateTime dateTime, boolean isLatest,
            Pageable pageable);

    /**
     * 基準時間開始以上かつ終了より前の最新を取得する
     *
     * @param dateTimeStart 基準日時開始
     * @param dateTimeEnd   基準日時終了
     * @param isLatest      最新該否
     * @param pageable      ページング条件
     * @return 検索結果
     */
    Page<KanrenshaPersonHistory01Entity> findByInsertTimestampGreaterThanEqualAndInsertTimestampLessThanAndIsLatest(
            LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, boolean isLatest, Pageable pageable);


}
