package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.KanrenshaCombineOrgDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.KanrenshaCombineOrg2026Entity;

/**
 * kanrensha_combine_org_2026接続用Repository
 */
public interface KanrenshaCombineOrg2026Repository extends JpaRepository<KanrenshaCombineOrg2026Entity, Integer> {

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<KanrenshaCombineOrg2026Entity> findFirstByOrderByKanrenshaCombineOrgCodeDesc();

    /**
     * コード一致する最新を取得する
     * 
     * @param personCode   関連者紐づけ個人コード
     * @param orgCode      関連者紐づけ団体コード
     * @param kanrenshaKbn 関連者区分
     * @return 検索結果
     */
    List<KanrenshaCombineOrg2026Entity> findByPersonKanrenshaCodeAndOrgKanrenshaCodeAndKanrenshaKbnAndIsLatestTrue(
            String personCode, String orgCode, Short kanrenshaKbn);

    /**
     * 関連者紐づけ検索件数を取得する
     * 
     * @param personCode   関連者個人コード
     * @param orgCode      関連者団体コード
     * @param kanrenshaKbn 関連者区分
     * @return 検索件数
     */
    @Query(value = "SELECT count(*) FROM kanrensha_combine_org_2026" //
            + "   WHERE is_latest = 1" //
            + "     AND CASE WHEN ?1 <> '' THEN person_kanrensha_code LIKE ?1 ELSE 1=1 END " //
            + "     AND CASE WHEN ?2 <> '' THEN org_kanrensha_code LIKE ?2 ELSE 1=1 END " //
            + "     AND CASE WHEN ?3 <> 0 THEN kanrensha_kbn = ?3 ELSE 1=1 END " //
            + "", nativeQuery = true)
    Integer countCodeAndKbn(String personCode, String orgCode, Short kanrenshaKbn);

    /**
     * 関連者紐づけを検索する
     * 
     * @param personCode   関連者個人コード
     * @param orgCode      関連者団体コード
     * @param kanrenshaKbn 関連者区分
     * @param pageable     ページング
     * @return 検索結果リスト
     */
    @Query(value = "SELECT kanrensha_combine_org_id, kanrensha_combine_org_code, 2026 AS table_year,"
            + " kanrensha_kbn,person_kanrensha_code,person_name,org_kanrensha_code,org_name FROM kanrensha_combine_org_2026" //
            + "   WHERE is_latest = 1" //
            + "     AND CASE WHEN ?1 <> '' THEN person_kanrensha_code LIKE ?1 ELSE 1=1 END " //
            + "     AND CASE WHEN ?2 <> '' THEN org_kanrensha_code LIKE ?2 ELSE 1=1 END " //
            + "     AND CASE WHEN ?3 <> 0 THEN kanrensha_kbn = ?3 ELSE 1=1 END " //
            + "", nativeQuery = true)
    List<KanrenshaCombineOrgDto> findCodeAndKbn(String personCode, String orgCode, Short kanrenshaKbn,
            Pageable pageable);

}
