package net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory45Entity;

/**
 * kanrensha_person_history_45接続用Repository
 */
public interface KanrenshaPersonHistory45Repository extends JpaRepository<KanrenshaPersonHistory45Entity, Integer> {

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM kanrensha_person_history_45"
            + " WHERE search_text LIKE ?1 AND is_latest=1", nativeQuery = true) // TODO MATCH AGAINST
    List<KanrenshaPersonHistory45Entity> findFullText(String searchWords);

    /**
     * 企業・団体の属性でリスト取得する
     *
     * @param name      団体名称
     * @param address   住所
     * @param shokugyou 代表者名
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM kanrensha_person_history_45 " + " WHERE all_name = ?1 AND all_address = ?2 "
            + "   AND person_shokugyou = ?3 AND is_latest=1", nativeQuery = true)
    List<KanrenshaPersonHistoryBaseEntity> selectByProperty(String name, String address, String shokugyou);
}
