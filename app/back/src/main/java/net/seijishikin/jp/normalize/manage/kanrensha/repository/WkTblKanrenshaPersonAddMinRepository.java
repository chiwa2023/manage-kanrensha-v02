package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min.KanrenshaPersonMasterUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;

/**
 * wk_tbl_kanrensha_person_add_min接続用Repository
 */
public interface WkTblKanrenshaPersonAddMinRepository extends JpaRepository<WkTblKanrenshaPersonAddMinEntity, Integer> {

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
    Page<WkTblKanrenshaPersonAddMinEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode,
            boolean isLatest, boolean isAffected, boolean isFinish, Pageable pageable);

    /**
     * 現在の最大コードを所持するデータ取得する
     * 
     * @return 検索結果
     */
    Optional<WkTblKanrenshaPersonAddMinEntity> findFirstByOrderByWkTblKanrenshaPersonAddMinCodeDesc();

    /**
     * 重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct kanrensha_name, all_address, person_shokugyou FROM wk_tbl_kanrensha_person_add_min WHERE insert_user_code = ?1 "
            + "GROUP BY kanrensha_name, all_address, person_shokugyou HAVING count(*) >1", nativeQuery = true)
    List<KanrenshaPersonMasterUniquekeyDto> findDuplicateUniqueKey(Integer userCode);

    /**
     * 同一データリストを取得する
     * 
     * @param name      名称
     * @param address   住所
     * @param shokugyou 職業
     * @param userCode  ユーザコード
     * @return 同一データリスト
     */
    List<WkTblKanrenshaPersonAddMinEntity> findByKanrenshaNameAndAllAddressAndPersonShokugyouAndInsertUserCodeOrderByWkTblKanrenshaPersonAddMinIdAsc(
            String name, String address, String shokugyou, Integer userCode);
}
