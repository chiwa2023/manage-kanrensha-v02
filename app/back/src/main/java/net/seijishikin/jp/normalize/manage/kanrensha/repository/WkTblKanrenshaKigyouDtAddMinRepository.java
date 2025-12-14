package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min.KanrenshaKigyouDtMasterUniquekeyDto;
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

    /**
     * 現在の最大コードを所持するデータ取得する
     * 
     * @return 検索結果
     */
    Optional<WkTblKanrenshaKigyouDtAddMinEntity> findFirstByOrderByWkTblKanrenshaKigyouDtAddMinCodeDesc();

    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblKanrenshaKigyouDtAddMinEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode,
            boolean isLatest, boolean isAffected, boolean isFinish, Pageable pageable);

    /**
     * 編集用に検索を行う際の該当件数を返却する
     *
     * @param userCode   ユーザコード
     * @param listLatest 検索条件履歴
     * @param isAffected 検索条件反映行
     * @param listFinish 検索条件勝利完了
     * @return 件数
     */
    Integer countByInsertUserCodeAndIsLatestInAndIsAffectedInAndIsFinishIn(Integer userCode, List<Boolean> listLatest,
            List<Boolean> isAffected, List<Boolean> listFinish);

    /**
     * 重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct kanrensha_name, all_address, kigyou_dt_delegate FROM wk_tbl_kanrensha_kigyou_dt_add_min WHERE insert_user_code = ?1 "
            + "GROUP BY kanrensha_name, all_address, kigyou_dt_delegate HAVING count(*) >1", nativeQuery = true)
    List<KanrenshaKigyouDtMasterUniquekeyDto> findDuplicateUniqueKey(Integer userCode);

    /**
     * 全項目が合致するリストを取得する(重複除去用)
     *
     * @param partnerName  関連者名称
     * @param allAddress   全住所
     * @param corpDelegate 企業・団体代表者
     * @param userCode     ユーザコード
     * @return 検索結果
     */
    List<WkTblKanrenshaKigyouDtAddMinEntity> findByKanrenshaNameAndAllAddressAndKigyouDtDelegateAndInsertUserCodeOrderByWkTblKanrenshaKigyouDtAddMinIdAsc( // NOPMD
            String partnerName, String allAddress, String corpDelegate, Integer userCode);

    
}
