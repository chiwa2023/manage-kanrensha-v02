package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history.KanrenshaKigyouDtUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtHistoryEntity;

/**
 * wk_tbl_kanrensha_kigyou_dt_history接続用Repository
 */
public interface WkTblKanrenshaKigyouDtHistoryRepository
        extends JpaRepository<WkTblKanrenshaKigyouDtHistoryEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblKanrenshaKigyouDtHistoryEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode,
            boolean isLatest, boolean isAffected, boolean isFinish, Pageable pageable);

    /**
     * 現在の最大コードを所持するデータ取得する
     * 
     * @return 検索結果
     */
    Optional<WkTblKanrenshaKigyouDtHistoryEntity> findFirstByOrderByWkKanrenshaKigyouDtHistoryCodeDesc();

    /**
     * 重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct kanrensha_name, all_address, kigyou_dt_delegate, kigyou_dt_kanrensha_code FROM wk_tbl_kanrensha_kigyou_dt_history WHERE insert_user_code = ?1 "
            + "GROUP BY kanrensha_name, all_address, kigyou_dt_delegate, kigyou_dt_kanrensha_code HAVING count(*) >1", nativeQuery = true)
    List<KanrenshaKigyouDtUniquekeyDto> findDuplicateUniqueKey(Integer userCode);

    /**
     * すべてが同一であるデータリストを取得する
     * 
     * @param name          名称
     * @param address       住所
     * @param orgDelegate   団体代表者
     * @param kanrenshaCode 関連者コード
     * @return 同一データリスト
     */
    List<WkTblKanrenshaKigyouDtHistoryEntity> findByKanrenshaNameAndAllAddressAndKigyouDtDelegateAndKigyouDtKanrenshaCodeAndInsertUserCodeOrderByWkKanrenshaKigyouDtHistoryIdAsc( // NOPMD
            String name, String address, String orgDelegate, String kanrenshaCode, Integer UserCode);

}
