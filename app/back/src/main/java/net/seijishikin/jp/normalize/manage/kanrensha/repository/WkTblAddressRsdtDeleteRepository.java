package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;

/**
 * wk_tbl_address_rsdt_delete接続用Repository
 */
public interface WkTblAddressRsdtDeleteRepository extends JpaRepository<WkTblAddressRsdtDeleteEntity, Integer> {

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
    Page<WkTblAddressRsdtDeleteEntity> findByInsertUserCodeAndIsLatestTrue(Integer userCode, Pageable pageable);

    /**
     * ユーザコードかつ最新該否を検索する
     * 
     * @param userocde         ユーザコード
     * @param listSearchLatest 最新該否検索条件
     * @param pageable         ページング
     * @return 検索結果
     */
    List<WkTblAddressRsdtDeleteEntity> findByInsertUserCodeAndIsLatestIn(Integer userocde,
            List<Integer> listSearchLatest, Pageable pageable);

    /**
     * ユーザコードかつ最新該否で件数を取得する
     * 
     * @param userocde         ユーザコード
     * @param listSearchLatest 最新該否検索条件
     * @return 件数
     */
    Integer countByInsertUserCodeAndIsLatestIn(Integer userocde, List<Integer> listSearchLatest);

}
