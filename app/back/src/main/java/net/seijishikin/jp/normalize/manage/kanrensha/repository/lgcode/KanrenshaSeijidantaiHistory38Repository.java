package net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory38Entity;

/**
 * kanrensha_seijidantai_history_38接続用Repository
 */
public interface KanrenshaSeijidantaiHistory38Repository
        extends JpaRepository<KanrenshaSeijidantaiHistory38Entity, Integer> {

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM kanrensha_seijidantai_history_38"
            + " WHERE search_text LIKE ?1 AND is_latest=1", nativeQuery = true) // TODO MATCH AGAINST
    List<KanrenshaSeijidantaiHistory38Entity> findFullText(String searchWords);

    /**
     * 政治団体の属性でリスト取得する
     *
     * @param name     団体名称
     * @param address  住所
     * @param delegate 代表者名
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM partner_poli_org_history_01 " + " WHERE partner_name = ?1 AND all_address = ?2 "
            + "   AND poli_org_delegate = ?3 AND is_latest=1", nativeQuery = true)
    List<KanrenshaSeijidantaiHistoryBaseEntity> selectByProperty(String name, String address, String delegate);

}
