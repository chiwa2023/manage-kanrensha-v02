package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;

/**
 * wk_tbl_master_all_by_xml接続用Repository
 */
public interface WkTblMasterAllByXmlRepository extends JpaRepository<WkTblMasterAllByXmlEntity, Integer> {

    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblMasterAllByXmlEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode, boolean isLatest,
            boolean isAffected,boolean isFinish, Pageable pageable);

}
