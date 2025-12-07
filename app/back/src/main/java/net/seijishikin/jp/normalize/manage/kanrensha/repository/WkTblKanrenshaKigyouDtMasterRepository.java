package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;

/**
 * wk_tbl_kanrensha_kigyou_dt_master接続用Repository
 */
public interface WkTblKanrenshaKigyouDtMasterRepository
        extends JpaRepository<WkTblKanrenshaKigyouDtMasterEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

}
