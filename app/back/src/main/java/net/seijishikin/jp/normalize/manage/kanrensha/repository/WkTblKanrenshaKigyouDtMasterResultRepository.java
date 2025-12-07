package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterResultEntity;

/**
 * wk_tbl_kanrensha_kigyou_dt_master_result接続用Repository
 */
public interface WkTblKanrenshaKigyouDtMasterResultRepository
        extends JpaRepository<WkTblKanrenshaKigyouDtMasterResultEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

}
