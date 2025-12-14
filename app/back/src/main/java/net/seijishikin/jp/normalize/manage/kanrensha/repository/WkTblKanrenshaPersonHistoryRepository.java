package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history.KanrenshaPersonUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonHistoryEntity;

/**
 * wk_tbl_kanrensha_person_history接続用Repository
 */
public interface WkTblKanrenshaPersonHistoryRepository
        extends JpaRepository<WkTblKanrenshaPersonHistoryEntity, Integer> {

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
    Page<WkTblKanrenshaPersonHistoryEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode, boolean isLatest,
            boolean isAffected,boolean isFinish, Pageable pageable);

    /**
     * 現在の最大コードを所持するデータ取得する
     * 
     * @return 検索結果
     */
    Optional<WkTblKanrenshaPersonHistoryEntity> findFirstByOrderByWkKanrenshaPersonHistoryCodeDesc();

    /**
     * 重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct kanrensha_name, all_address, person_shokugyou, person_kanrensha_code FROM wk_tbl_kanrensha_person_history WHERE insert_user_code = ?1 "
            + "GROUP BY kanrensha_name, all_address, person_shokugyou, person_kanrensha_code HAVING count(*) >1", nativeQuery = true)
    List<KanrenshaPersonUniquekeyDto> findDuplicateUniqueKey(Integer userCode);

    List<WkTblKanrenshaPersonHistoryEntity> findByKanrenshaNameAndAllAddressAndPersonShokugyouAndPersonKanrenshaCodeAndInsertUserCodeOrderByWkKanrenshaPersonHistoryIdAsc(String name,String address,String shokugyou,String code,Integer userCode);
}
