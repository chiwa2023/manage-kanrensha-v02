package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min.KanrenshaSeijidantaiMasterUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiAddMinEntity;

/**
 * wk_tbl_kanrensha_seijidantai_add_min接続用Repository
 */
public interface WkTblKanrenshaSeijidantaiAddMinRepository
        extends JpaRepository<WkTblKanrenshaSeijidantaiAddMinEntity, Integer> {

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
    Page<WkTblKanrenshaSeijidantaiAddMinEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(
            Integer userCode, boolean isLatest, boolean isAffected, boolean isFinish, Pageable pageable);

    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblKanrenshaSeijidantaiAddMinEntity> findByInsertUserCodeAndIsLatestAndIsAffected(Integer userCode,
            boolean isLatest, boolean isAffected, Pageable pageable);

    /**
     * 現在の最大コードを所持するデータ取得する
     * 
     * @return 検索結果
     */
    Optional<WkTblKanrenshaSeijidantaiAddMinEntity> findFirstByOrderByWkTblKanrenshaSeijidantaiAddMinCodeDesc();

    /**
     * 重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct kanrensha_name, all_address,seijidantai_delegate FROM wk_tbl_kanrensha_seijidantai_add_min WHERE insert_user_code = ?1 "
            + "GROUP BY kanrensha_name, all_address, seijidantai_delegate HAVING count(*) >1", nativeQuery = true)
    List<KanrenshaSeijidantaiMasterUniquekeyDto> findDuplicateUniqueKey(Integer userCode);

    /**
     * 同一データリストを取得する
     * 
     * @param name     名称
     * @param address  住所
     * @param delegate 団体代表者
     * @param userCode ユーザコード
     * @return 同一データリスト
     */
    List<WkTblKanrenshaSeijidantaiAddMinEntity> findByKanrenshaNameAndAllAddressAndSeijidantaiDelegateAndInsertUserCodeOrderByWkTblKanrenshaSeijidantaiAddMinIdAsc( // NOPMD
            String name, String address, String delegate, Integer userCode);
}
