package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.history.KanrenshaSeijidantaiUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;

/**
 * wk_tbl_kanrensha_seijidantai_history接続用Repository
 */
public interface WkTblKanrenshaSeijidantaiHistoryRepository
        extends JpaRepository<WkTblKanrenshaSeijidantaiHistoryEntity, Integer> {

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
    Page<WkTblKanrenshaSeijidantaiHistoryEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode, boolean isLatest,
            boolean isAffected,boolean isFinish, Pageable pageable);


    /**
     * 現在の最大コードを所持するデータ取得する
     * 
     * @return 検索結果
     */
    Optional<WkTblKanrenshaSeijidantaiHistoryEntity> findFirstByOrderByWkKanrenshaSeijidantaiHistoryCodeDesc();

    /**
     * 重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct kanrensha_name, all_address, seijidantai_delegate, seijidantai_kanrensha_code FROM wk_tbl_kanrensha_seijidantai_history WHERE insert_user_code = ?1 "
            + "GROUP BY kanrensha_name, all_address, seijidantai_delegate, seijidantai_kanrensha_code HAVING count(*) >1", nativeQuery = true)
    List<KanrenshaSeijidantaiUniquekeyDto> findDuplicateUniqueKey(Integer userCode);

    List<WkTblKanrenshaSeijidantaiHistoryEntity> findByKanrenshaNameAndAllAddressAndSeijidantaiDelegateAndSeijidantaiKanrenshaCodeAndInsertUserCodeOrderByWkKanrenshaSeijidantaiHistoryIdAsc(String name,String address,String delegate,String code,Integer userCode);
}
