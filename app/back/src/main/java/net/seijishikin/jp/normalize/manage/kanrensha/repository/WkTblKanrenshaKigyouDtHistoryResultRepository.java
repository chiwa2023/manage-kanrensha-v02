package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryResultEntity;

/**
 * wk_tbl_kanrensha_kigyou_dt_history_result接続用Repository
 */
public interface WkTblKanrenshaKigyouDtHistoryResultRepository
        extends JpaRepository<WkTblKanrenshaKigyouDtHistoryResultEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblKanrenshaKigyouDtHistoryResultEntity> findByInsertUserCodeAndIsLatest(Integer userCode, boolean isLatest,
            Pageable pageable);

    /**
     * ユーザコードが同一のデータを取得する
     * 
     * @param userCode ユーザコード
     * @param pageable ページング
     * @return 検索結果
     */
    Page<WkTblKanrenshaKigyouDtHistoryResultEntity> findByInsertUserCode(Integer userCode, Pageable pageable);
}
