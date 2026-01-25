package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;

/**
 * task_plan_2025接続用Repository
 */
public interface TaskPlan2025Repository extends JpaRepository<TaskPlan2025Entity, Integer> {

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_plan_2025 WHERE saishin_kbn= 1 " // TODO NATCH AGAINST
            + "AND task_plan_2025_name LIKE ?1", nativeQuery = true)
    List<TaskPlan2025Entity> findFullText(String searchWords);

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<TaskPlan2025Entity> findFirstByOrderByTaskPlanCodeDesc();

    /**
     * 更新日時降順で同一コードを取得する(履歴)
     *
     * @param taskPlanCode タスク計画コード
     * @return タスク計画リスト
     */
    List<TaskPlan2025Entity> findByTaskPlanCodeOrderByInsertTimestampAsc(Integer taskPlanCode);

    /**
     * 同一コードで最新を取得する
     * 
     * @param taskCode タスクコード
     * @param isLatest 最新該否
     * @return 最新リスト(基本的に1件)
     */
    List<TaskPlan2025Entity> findByTaskPlanCodeAndIsLatest(Integer taskCode, Boolean isLatest);
    
    /**
     * タスク計画を検索条件で検索する
     *
     * @param startDateTime 開始日時検索条件
     * @param endDateTime   終了日時検索条件
     * @param searchWord    検索語
     * @param pageable      ページング
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_plan_2025" //
            + "   WHERE insert_timestamp BETWEEN ?1 AND ?2" //
            + "       AND is_latest = 1" //
            + "       AND CASE" //
            //+ "              WHEN ?3 <> '' THEN MATCH(task_plan_name) AGAINST (?3 IN BOOLEAN MODE)" //
            + "              WHEN ?3 <> '' THEN task_plan_name LIKE ?3" //
            + "              ELSE 1=1"//
            + "           END"
            , nativeQuery = true)
    List<TaskPlanBaseEntity> findTaskPlan(LocalDateTime startDateTime, LocalDateTime endDateTime, String searchWord,
            Pageable pageable);

    /**
     * 検索条件該当件数を取得する
     *
     * @param startDateTime 開始日時検索条件
     * @param endDateTime   終了日時検索条件
     * @param searchWord    検索語
     * @return 該当件数
     */
    @Query(value = "SELECT count(*) FROM task_plan_2025" //
            + "   WHERE insert_timestamp BETWEEN ?1 AND ?2" //
            + "       AND is_latest = 1" //
            + "       AND CASE" //
            //+ "              WHEN ?3 <> '' THEN MATCH(task_plan_name) AGAINST (?3 IN BOOLEAN MODE)" //
            + "              WHEN ?3 <> '' THEN task_plan_name LIKE ?3" //
            + "              ELSE 1=1"//
            + "           END"
            , nativeQuery = true)
    Integer countTaskPlan(LocalDateTime startDateTime, LocalDateTime endDateTime, String searchWord);

}
