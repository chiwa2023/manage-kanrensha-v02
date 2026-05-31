package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;

/**
 * wk_tbl_address_rsdt_change接続用Repository
 */
public interface WkTblAddressRsdtChangeRepository extends JpaRepository<WkTblAddressRsdtChangeEntity, Integer> {

    /**
     * ユーザコード条件で削除する
     * 
     * @param userCode 操作者ユーザコード
     * @return 処理件数
     * 
     */
    Integer deleteByInsertUserCode(Integer userCode);

    /**
     * 最新かつユーザコード条件で検索する
     * 
     * @param userCode 操作者ユーザコード
     * @param pageable ページング
     * @return 検索結果
     */
    Page<WkTblAddressRsdtChangeEntity> findByInsertUserCodeAndIsLatestTrue(Integer userCode, Pageable pageable);

}
