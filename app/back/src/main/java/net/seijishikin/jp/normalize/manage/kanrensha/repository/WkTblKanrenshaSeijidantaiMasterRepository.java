package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min.KanrenshaSeijidantaiMasterUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiMasterEntity;

/**
 * wk_tbl_kanrensha_seijidantai_master接続用Repository
 */
public interface WkTblKanrenshaSeijidantaiMasterRepository
        extends JpaRepository<WkTblKanrenshaSeijidantaiMasterEntity, Integer> {

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
    Page<WkTblKanrenshaSeijidantaiMasterEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode, boolean isLatest,
            boolean isAffected,boolean isFinish, Pageable pageable);

    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblKanrenshaSeijidantaiMasterEntity> findByInsertUserCodeAndIsLatestAndIsAffected(Integer userCode, boolean isLatest,
            boolean isAffected, Pageable pageable);

    /**
     * 現在の最大コードを所持するデータ取得する
     * 
     * @return 検索結果
     */
    Optional<WkTblKanrenshaSeijidantaiMasterEntity> findFirstByOrderByWkTblKanrenshaSeijidantaiMasterCodeDesc();

    /**
     * 重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct kanrensha_name, all_address, seijidantai_delegate FROM wk_tbl_kanrensha_seijidantai_master WHERE insert_user_code = ?1 "
            + "GROUP BY kanrensha_name, all_address, seijidantai_delegate HAVING count(*) >1", nativeQuery = true)
    List<KanrenshaSeijidantaiMasterUniquekeyDto> findDuplicateUniqueKey(Integer userCode);

    List<WkTblKanrenshaSeijidantaiMasterEntity> findByKanrenshaNameAndAllAddressAndSeijidantaiDelegateAndInsertUserCodeOrderByWkTblKanrenshaSeijidantaiMasterIdAsc(String name,String address,String delegateName,Integer userCode);
}
