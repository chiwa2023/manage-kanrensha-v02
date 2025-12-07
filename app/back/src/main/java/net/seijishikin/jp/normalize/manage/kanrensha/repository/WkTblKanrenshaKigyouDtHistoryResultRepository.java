package net.seijishikin.jp.normalize.manage.kanrensha.repository;

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

}
