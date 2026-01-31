package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;

/**
 * task_plan_2026接続用Repository
 */
public interface TaskPlan2026Repository extends JpaRepository<TaskPlan2026Entity, Integer> {

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_plan_2026 WHERE saishin_kbn= 1 " // TODO NATCH AGAINST
            + "AND task_plan_2026_name LIKE ?1", nativeQuery = true)
    List<TaskPlan2026Entity> findFullText(String searchWords);

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TaskPlan2026Entity> findFirstByOrderByTaskPlanCodeDesc();

    /**
     * 同一コードで最新を取得する
     * 
     * @param taskCode タスクコード
     * @param isLatest 最新該否
     * @return 最新リスト(基本的に1件)
     */
    List<TaskPlan2026Entity> findByTaskPlanCodeAndIsLatest(Integer taskCode, Boolean isLatest);

    /**
     * タスク計画を検索条件で検索する
     *
     * @param startDateTime 開始日時検索条件
     * @param endDateTime   終了日時検索条件
     * @param searchWord    検索語
     * @param pageable      ページング
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_plan_2026" //
            + "   WHERE insert_timestamp BETWEEN ?2 AND ?3" //
            + "       AND is_latest = 1 AND insert_user_code = ?1" //
            // + "AND CASE WHEN ?3 <> '' THEN MATCH(task_plan_name) AGAINST (?3 IN BOOLEAN
            // MODE)" //
            + "       AND CASE WHEN ?4 <> '' THEN task_plan_name LIKE ?4 ELSE 1=1 END " //
            + "       AND CASE  WHEN ?5<2 THEN is_finished = ?5 ELSE 1=1 END "//
            + "       AND CASE  WHEN ?6<2 THEN is_start = ?6 ELSE 1=1 END "//
            + "       AND CASE  WHEN ?7<2 THEN is_suspended = ?7 ELSE 1=1 END "//
            + "       AND CASE  WHEN ?9= true THEN task_info_code IN ?8 ELSE 1=1 END "//
            , nativeQuery = true)
    List<TaskPlanBaseEntity> findTaskPlan(Integer userCode,LocalDateTime startDateTime, LocalDateTime endDateTime, String searchWord,
            Integer flgFinished, Integer flgStart, Integer flgSuspended, List<Integer> infoCodeList,
            boolean hasCodeList, Pageable pageable);

    /**
     * 検索条件該当件数を取得する
     *
     * @param startDateTime 開始日時検索条件
     * @param endDateTime   終了日時検索条件
     * @param searchWord    検索語
     * @return 該当件数
     */
    @Query(value = "SELECT count(*) FROM task_plan_2026" //
            + "   WHERE insert_timestamp BETWEEN ?2 AND ?3" //
            + "       AND is_latest = 1 AND insert_user_code = ?1" //
            // + "AND CASE WHEN ?3 <> '' THEN MATCH(task_plan_name) AGAINST (?3 IN BOOLEAN
            // MODE)" //
            + "       AND CASE WHEN ?4 <> '' THEN task_plan_name LIKE ?4 ELSE 1=1 END " //
            + "       AND CASE  WHEN ?5<2 THEN is_finished = ?5 ELSE 1=1 END "//
            + "       AND CASE  WHEN ?6<2 THEN is_start = ?6 ELSE 1=1 END "//
            + "       AND CASE  WHEN ?7<2 THEN is_suspended = ?7 ELSE 1=1 END "//
            + "       AND CASE  WHEN ?9= true THEN task_info_code IN ?8 ELSE 1=1 END "//
            , nativeQuery = true)
    Integer countTaskPlan(Integer userCode, LocalDateTime startDateTime, LocalDateTime endDateTime, String searchWord,
            Integer flgFinished, Integer flgStart, Integer flgSuspended, List<Integer> infoCodeList,
            boolean hasCodeList);

    /**
     * 更新日時降順で同一コードを取得する(履歴)
     *
     * @param taskPlanCode タスク計画コード
     * @return タスク計画リスト
     */
    List<TaskPlan2026Entity> findByTaskPlanCodeOrderByInsertTimestampAsc(Integer taskPlanCode);

    /**
     * 未処理タスクを抽出する
     * 
     * @param userCode ユーザコード
     * @param pageable ページング
     * @return 検索結果
     */
    List<TaskPlan2026Entity> findByInsertUserCodeAndIsLatestTrueAndIsFinishedFalseOrderByInsertTimestampDesc(
            Integer userCode, Pageable pageable);
}
