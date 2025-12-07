package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
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
}
