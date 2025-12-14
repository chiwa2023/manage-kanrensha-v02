package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.add_min.KanrenshaKigyouDtMasterUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaKigyouDtMasterEntity;

/**
 * wk_tbl_kanrensha_kigyou_dt_master接続用Repository
 */
public interface WkTblKanrenshaKigyouDtMasterRepository
        extends JpaRepository<WkTblKanrenshaKigyouDtMasterEntity, Integer> {

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
    Page<WkTblKanrenshaKigyouDtMasterEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode,
            boolean isLatest, boolean isAffected, boolean isFinish, Pageable pageable);

    /**
     * 現在の最大コードを所持するデータ取得する
     * 
     * @return 検索結果
     */
    Optional<WkTblKanrenshaKigyouDtMasterEntity> findFirstByOrderByWkTblKanrenshaKigyouDtMasterCodeDesc();

    /**
     * 重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct kanrensha_name, all_address, kigyou_dt_delegate FROM wk_tbl_kanrensha_kigyou_dt_master WHERE insert_user_code = ?1 "
            + "GROUP BY kanrensha_name, all_address, kigyou_dt_delegate HAVING count(*) >1", nativeQuery = true)
    List<KanrenshaKigyouDtMasterUniquekeyDto> findDuplicateUniqueKey(Integer userCode);

    List<WkTblKanrenshaKigyouDtMasterEntity> findByKanrenshaNameAndAllAddressAndKigyouDtDelegateAndInsertUserCodeOrderByWkTblKanrenshaKigyouDtMasterIdAsc(
            String name, String address, String delegate, Integer userCode);
}
