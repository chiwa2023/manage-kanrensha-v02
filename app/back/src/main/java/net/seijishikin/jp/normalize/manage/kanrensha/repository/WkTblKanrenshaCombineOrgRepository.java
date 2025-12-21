package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org.KanrenshaCombineOrgUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaCombineOrgEntity;

/**
 * wk_tbl_kanrensha_combine_org接続用Repository
 */
public interface WkTblKanrenshaCombineOrgRepository extends JpaRepository<WkTblKanrenshaCombineOrgEntity, Integer> {

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
    Page<WkTblKanrenshaCombineOrgEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode,
            boolean isLatest, boolean isAffected, boolean isFinish, Pageable pageable);

    /**
     * 該当ユーザかつ最新データを取得する
     *
     * @param userCode ユーザコード
     * @param isLatest 最新該否
     * @param pageable ページング
     * @return 検索結果
     */
    Page<WkTblKanrenshaCombineOrgEntity> findByInsertUserCodeAndIsLatest(Integer userCode, Boolean isLatest,
            Pageable pageable);

    /**
     * 最大コードを持つEntityをoptionalで取得する
     * 
     * @return 最大コードを持つEntit
     */
    Optional<WkTblKanrenshaCombineOrgEntity> findFirstByOrderByWkTblKanrenshaCombineOrgCodeDesc();

    /**
     * 重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct kanrensha_kbn,person_kanrensha_code,org_kanrensha_code,year_array_text "
            + "       FROM wk_tbl_kanrensha_combine_org WHERE insert_user_code = ?1"
            + "          GROUP BY kanrensha_kbn,person_kanrensha_code,org_kanrensha_code,year_array_text"
            + "          HAVING count(*) >1", nativeQuery = true)
    List<KanrenshaCombineOrgUniquekeyDto> findDuplicateUniqueKey(Integer userCode);

    /**
     * 全項目が合致するリストを取得する(重複除去用)
     *
     * @param personCode   個人関連者コード
     * @param orgCode      団体関連者コード
     * @param yearText     年配列
     * @param kanrenshaKbn 関連者区分
     * @param userCode     ユーザコード
     * @return 検索結果
     */
    List<WkTblKanrenshaCombineOrgEntity> findByPersonKanrenshaCodeAndOrgKanrenshaCodeAndYearArrayTextAndKanrenshaKbnAndInsertUserCodeOrderByWkTblKanrenshaCombineOrgIdAsc(
            String personCode, String orgCode, String yearText, Short kanrenshaKbn, Integer userCode);

}
