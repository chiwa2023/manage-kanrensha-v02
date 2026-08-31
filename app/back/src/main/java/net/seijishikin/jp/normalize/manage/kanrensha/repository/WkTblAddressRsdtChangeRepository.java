package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;

/**
 * wk_tbl_address_rsdt_change接続用Repository
 */
public interface WkTblAddressRsdtChangeRepository extends JpaRepository<WkTblAddressRsdtChangeEntity, Integer>,
        PagingAndSortingRepository<WkTblAddressRsdtChangeEntity, Integer> {

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

    /**
     * 作業ユーザコードかつ最新条件で検索する
     * 
     * @param userocde         ユーザコード
     * @param listSearchLatest 最新該否検索条件
     * @param pageable         ページング
     * @return 検索結果
     */
    List<WkTblAddressRsdtChangeEntity> findByInsertUserCodeAndIsLatestIn(Integer userocde,
            List<Boolean> listSearchLatest, Pageable pageable);

    /**
     * 作業ユーザコードかつ最新条件の件数を取得する
     * 
     * @param userocde         ユーザコード
     * @param listSearchLatest 最新該否検索条件
     * @return 検索件数
     */
    Integer countByInsertUserCodeAndIsLatestIn(Integer userocde, List<Boolean> listSearchLatest);

}
