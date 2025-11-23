package net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory10Entity;

/**
 * kanrensha_kigyou_dt_history_10接続用Repository
 */
public interface KanrenshaKigyouDtHistory10Repository extends JpaRepository<KanrenshaKigyouDtHistory10Entity, Integer> {

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM kanrensha_kigyou_dt_history_10"
            + " WHERE search_text like ?1 AND is_latest=1", nativeQuery = true) // TODO MATCH AGAINST
    List<KanrenshaKigyouDtHistory10Entity> findFullText(String searchWords);
}
