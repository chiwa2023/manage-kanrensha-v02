package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryResultEntity;

/**
 * wk_tbl_kanrensha_person_history_result接続用Repository
 */
public interface WkTblKanrenshaPersonHistoryResultRepository
        extends JpaRepository<WkTblKanrenshaPersonHistoryResultEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

    /**
     * ユーザが一致するデータ取得する
     * 
     * @return 検索結果
     */
    Page<WkTblKanrenshaPersonHistoryResultEntity> findByInsertUserCode(Integer userCode, Pageable pageable);

    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblKanrenshaPersonHistoryResultEntity> findByInsertUserCodeAndIsLatest(Integer userCode, boolean isLatest,
            Pageable pageable);

}
