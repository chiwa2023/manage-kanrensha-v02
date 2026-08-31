package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchViewCombineAliveRiyoushaResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.ViewCombineAliveRiyoushaEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.ViewCombineAliveRiyoushaPrimaryKey;

/**
 * 最新利用者全権限混合Repository
 */
public interface ViewCombineAliveRiyoushaRepository
        extends JpaRepository<ViewCombineAliveRiyoushaEntity, ViewCombineAliveRiyoushaPrimaryKey>,
        PagingAndSortingRepository<ViewCombineAliveRiyoushaEntity, ViewCombineAliveRiyoushaPrimaryKey> {

    /**
     * 権限と検索語で該当する件数を返却する
     * 
     * @param words    検索語
     * @param listRole 権限リスト
     * @return 検索結果
     */
    @Query(value = "SELECT count(*) FROM view_combine_alive_riyousha "
            + "   WHERE search_text LIKE ?1 AND role_has in ?2", nativeQuery = true)
    Integer countByFullTextAndRole(String words, List<String> listRole);

    /**
     * 権限と検索語で検索する
     * 
     * @param words    検索語
     * @param listRole 権限リスト
     * @param pageable ページング
     * @return 検索結果
     */
    @Query(value = "SELECT riyousha_id , riyousha_code , role_base , role_has , all_name FROM view_combine_alive_riyousha "
            + "   WHERE search_text LIKE ?1 AND role_has in ?2", nativeQuery = true)
    List<SearchViewCombineAliveRiyoushaResultDto> findByFullTextAndRole(String words, List<String> listRole,
            Pageable pageable);

}
