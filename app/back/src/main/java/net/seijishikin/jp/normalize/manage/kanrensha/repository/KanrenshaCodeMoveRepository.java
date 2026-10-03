package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;

/**
 * kanrensha_code_move接続用Repository
 */
public interface KanrenshaCodeMoveRepository extends JpaRepository<KanrenshaCodeMoveEntity, Integer> {

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<KanrenshaCodeMoveEntity> findFirstByOrderByKanrenshaCodeMoveCodeDesc();

    /**
     * 期間と申請状態で該当件数を取得する
     * 
     * @param starDate   検索期間開始
     * @param endDate    検索期間終了
     * @param listStatus 申請状態リスト
     * @return 該当件数
     */
    Integer countByIsLatestTrueAndInsertTimestampBetweenAndMoveStatusIn(LocalDateTime starDate, LocalDateTime endDate,
            List<Short> listStatus);

    /**
     * 期間と申請状態で該当を取得する
     * 
     * @param starDate   検索期間開始
     * @param endDate    検索期間終了
     * @param listStatus 申請状態リスト
     * @return 検索結果
     */
    List<KanrenshaCodeMoveEntity> findByIsLatestTrueAndInsertTimestampBetweenAndMoveStatusIn(LocalDateTime starDate,
            LocalDateTime endDate, List<Short> listStatus, Pageable pageable);

    /**
     * ユーザが申請履歴を取得する
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM kanrensha_code_move WHERE is_latest = 1"
            + "  and ( origin_kanrensha_code = ?1 or abolish_kanrensha_code = ?1)", nativeQuery = true)
    List<KanrenshaCodeMoveEntity> findMyselfData(String kanrenshaCode);

}
