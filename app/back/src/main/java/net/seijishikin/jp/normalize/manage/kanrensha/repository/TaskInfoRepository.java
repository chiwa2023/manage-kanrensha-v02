package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;

/**
 * task_info接続用Repository
 */
public interface TaskInfoRepository
        extends JpaRepository<TaskInfoEntity, Integer>, PagingAndSortingRepository<TaskInfoEntity, Integer> {

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM task_info WHERE task_info_code LIKE ?2 "
            + " AND task_info_name LIKE ?1 And is_latest = 1", nativeQuery = true)
    List<TaskInfoEntity> findFullTextAndType(String searchWords, String infoType, Pageable pageable);

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT count(*) FROM task_info WHERE task_info_code LIKE ?2 "
            + " AND task_info_name LIKE ?1 And is_latest = 1", nativeQuery = true)
    Integer countFullTextAndType(String searchWords, String infoType);

    /**
     * タスクコードが同一(かつ最新)のデータを取得する
     *
     * @param taskCode タスクコード
     * @return 検索結果
     */
    List<TaskInfoEntity> findByTaskInfoCodeAndIsLatestTrue(Integer taskCode);
}
