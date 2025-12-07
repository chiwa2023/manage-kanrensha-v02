package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryResultEntity;

/**
 * wk_tbl_kanrensha_seijidantai_history_result接続用Repository
 */
public interface WkTblKanrenshaSeijidantaiHistoryResultRepository
        extends JpaRepository<WkTblKanrenshaSeijidantaiHistoryResultEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

}
