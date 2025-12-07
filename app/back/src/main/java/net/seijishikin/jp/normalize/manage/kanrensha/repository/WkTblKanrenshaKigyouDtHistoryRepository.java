package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;

/**
 * wk_tbl_kanrensha_kigyou_dt_history接続用Repository
 */
public interface WkTblKanrenshaKigyouDtHistoryRepository
        extends JpaRepository<WkTblKanrenshaKigyouDtHistoryEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

}
