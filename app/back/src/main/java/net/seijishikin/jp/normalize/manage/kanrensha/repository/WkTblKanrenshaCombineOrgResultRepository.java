package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgResultEntity;

/**
 * wk_tbl_kanrensha_combine_org_result接続用Repository
 */
public interface WkTblKanrenshaCombineOrgResultRepository
        extends JpaRepository<WkTblKanrenshaCombineOrgResultEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

}
