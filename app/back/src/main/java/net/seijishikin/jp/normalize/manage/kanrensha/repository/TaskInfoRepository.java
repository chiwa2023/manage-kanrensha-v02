package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;

/**
 * task_info接続用Repository
 */
public interface TaskInfoRepository extends JpaRepository<TaskInfoEntity, Integer> {

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_info WHERE saishin_kbn= 1" // TODO NATCH AGAINST
            + " AND task_info_name LIKE ?1", nativeQuery = true)
    List<TaskInfoEntity> findFullText(String searchWords);

    /**
     * タスクコードが同一(かつ最新)のデータを取得する
     *
     * @param taskCode タスクコード
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<TaskInfoEntity> findByTaskInfoCodeAndIsLatest(Integer taskCode, boolean isLatest);

}
