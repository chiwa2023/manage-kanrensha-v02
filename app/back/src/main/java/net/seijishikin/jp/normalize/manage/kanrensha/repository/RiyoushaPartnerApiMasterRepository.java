package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;

/**
 * riyousha_partner_api_master接続用Repository
 */
public interface RiyoushaPartnerApiMasterRepository extends JpaRepository<RiyoushaPartnerApiMasterEntity, Integer>,
        PagingAndSortingRepository<RiyoushaPartnerApiMasterEntity, Integer> {

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM riyousha_partner_api_master" // TODO MATCH AGAINST
            + " WHERE is_latest = 1 AND search_text LIKE ?1", nativeQuery = true)
    List<RiyoushaPartnerApiMasterEntity> findFullText(String searchWords, Pageable pageable);

    /**
     * 全文検索件数を取得する
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT count(*) FROM riyousha_partner_api_master" // TODO MATCH AGAINST
            + " WHERE is_latest = 1 AND search_text LIKE ?1", nativeQuery = true)
    Integer countFullText(String searchWords);

    /**
     * 最大コードをもつEntityを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<RiyoushaPartnerApiMasterEntity> findFirstByOrderByRiyoushaPartnerApiMasterCodeDesc();

    /**
     * コードが一致する最新を取得する
     *
     * @return 最大コードをもつEntity
     */
    Optional<RiyoushaPartnerApiMasterEntity> findFirstByRiyoushaPartnerApiMasterCodeAndIsLatestTrueOrderByRiyoushaPartnerApiMasterIdDesc(
            Integer code);

    /**
     * マスタコード最新データかつ名称一致を取得する
     * 
     * @param masterCode マスタコード
     * @return 利用者組織マスタEnity
     */
    Optional<RiyoushaPartnerApiMasterEntity> findByRiyoushaPartnerApiMasterCodeAndAllNameAndIsLatestTrue(
            Integer masterCode, String name);

    /**
     * コードが一致する最新をリスト形式で取得する
     *
     * @return 最大コードをもつEntity
     */
    List<RiyoushaPartnerApiMasterEntity> findByRiyoushaPartnerApiMasterCodeAndIsLatestTrue(Integer code);

}
