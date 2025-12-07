package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtAddMinEntity;

/**
 * wk_tbl_kanrensha_kigyou_dt_add_min接続用Repository
 */
public interface WkTblKanrenshaKigyouDtAddMinRepository
        extends JpaRepository<WkTblKanrenshaKigyouDtAddMinEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

}
