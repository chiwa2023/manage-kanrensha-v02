package net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory01Entity;

/**
 * kanrensha_kigyou_dt_history_01接続用Repository
 */
public interface KanrenshaKigyouDtHistory01Repository extends JpaRepository<KanrenshaKigyouDtHistory01Entity, Integer> {

    /**
     * 名称を検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM kanrensha_kigyou_dt_history_01"
            + " WHERE search_text like ?1 AND is_latest=1", nativeQuery = true) // TODO MATCH AGAINST
    List<KanrenshaKigyouDtHistory01Entity> findFullText(String searchWords);
    
    /**
     * 企業・団体の属性でリスト取得する
     *
     * @param name     団体名称
     * @param address  住所
     * @param delegate 代表者名
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM kanrensha_kigyou_dt_history_01 " + " WHERE all_name = ?1 AND all_address = ?2 "
            + "   AND org_delegate_name = ?3 AND is_latest=1", nativeQuery = true)
    List<KanrenshaKigyouDtHistoryBaseEntity> selectByProperty(String name, String address, String delegate);

}
