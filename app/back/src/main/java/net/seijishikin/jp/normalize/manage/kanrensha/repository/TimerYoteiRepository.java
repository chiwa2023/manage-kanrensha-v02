package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;

/**
 * timer_yotei接続用Repository
 */
public interface TimerYoteiRepository
        extends JpaRepository<TimerYoteiEntity, Integer>, PagingAndSortingRepository<TimerYoteiEntity, Integer> {

    /**
     * 予定区分が一致かつ次回日時が指定範囲内でである予定実行を取得する
     * 
     * @param kbnList  予定区分リスト
     * @param start    検索条件開始
     * @param end      検索条件終了
     * @param pageable ページング
     * @return 検索結果
     */

    List<TimerYoteiEntity> findByYoyakuTaskKbnInAndNextTimestampBetweenAndIsLatestTrue(List<Short> kbnList,
            LocalDateTime start, LocalDateTime end, Pageable pageable);

    /**
     * 予定区分が一致かつ次回日時が指定範囲内でである予定実行件数取得する
     * 
     * @param kbnList 予定区分リスト
     * @param start   検索条件開始
     * @param end     検索条件終了
     * @return 件数
     */
    Integer countByYoyakuTaskKbnInAndNextTimestampBetweenAndIsLatestTrue(List<Short> kbnList, LocalDateTime start,
            LocalDateTime end);

    /**
     * 予定区分が一致した予定実行を取得する
     * 
     * @param kbnList  予定区分リスト
     * @param pageable ページング
     * @return 検索結果
     */
    List<TimerYoteiEntity> findByYoyakuTaskKbnInAndIsLatestTrue(List<Short> kbnList, Pageable pageable);

    /**
     * 予定区分が一致した予定実行件数を取得する
     * 
     * @param kbnList 予定区分リスト
     * @return 件数
     */
    Integer countByYoyakuTaskKbnInAndIsLatestTrue(List<Short> kbnList);

    /**
     * 指定時間以下の予定実行を取得する
     * 
     * @param dateTime 指定時間
     * @return 検索結果
     */
    Optional<TimerYoteiEntity> findByNextTimestampLessThanEqualAndIsLatestTrueOrderByNextTimestampAscInsertTimestampAsc(
            LocalDateTime dateTime);

    /**
     * 最大コードをもつEntityを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TimerYoteiEntity> findFirstByOrderByTimerYoteiCodeDesc();
}
