package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinResultEntity;

/**
 * wk_tbl_kanrensha_seijidantai_add_min_result接続用Repository
 */
public interface WkTblKanrenshaSeijidantaiAddMinResultRepository
        extends JpaRepository<WkTblKanrenshaSeijidantaiAddMinResultEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

}
