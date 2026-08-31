package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.SnsServiceOptionDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.SnsServiceEntity;

/**
 * sns_service接続用Repository
 */
public interface SnsServiceRepository
        extends JpaRepository<SnsServiceEntity, Integer>, PagingAndSortingRepository<SnsServiceEntity, Integer> {

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM sns_service" // TODO MATCH AGAINST
            + " WHERE is_latest = 1 AND search_text LIKE ?1", nativeQuery = true)
    List<SnsServiceEntity> findFullText(String searchWords, Pageable pageable);

    /**
     * 名称を検索対象として全文検索ときの件数を取得する
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT count(*) FROM sns_service" // TODO MATCH AGAINST
            + " WHERE is_latest = 1 AND search_text LIKE ?1", nativeQuery = true)
    Integer countFullText(String searchWords);

    /**
     * 最大コードをもつEntityを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SnsServiceEntity> findFirstByOrderBySnsServiceCodeDesc();

    /**
     * 最新データをセレクトボックス項目で取得する
     * 
     * @return 項目リスト
     */
    @Query(value = "SELECT sns_service_id AS value , sns_service_name AS text , "
            + " sns_service_code AS service_code , sns_portal_url AS portal_url "
            + "FROM sns_service WHERE is_latest = 1", nativeQuery = true)
    List<SnsServiceOptionDto> getSelectOptions();
}
