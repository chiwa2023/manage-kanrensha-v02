package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;

/**
 * wk_tbl_kanrensha_combine_org接続用Repository
 */
public interface WkTblKanrenshaCombineOrgRepository extends JpaRepository<WkTblKanrenshaCombineOrgEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

}
