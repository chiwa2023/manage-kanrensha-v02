package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;

/**
 * wk_tbl_kanrensha_seijidantai_history接続用Repository
 */
public interface WkTblKanrenshaSeijidantaiHistoryRepository
        extends JpaRepository<WkTblKanrenshaSeijidantaiHistoryEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

}
