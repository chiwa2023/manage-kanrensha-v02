package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;

/**
 * kanrensha_person_access接続用Repository
 */
public interface KanrenshaPersonAccessRepository extends JpaRepository<KanrenshaPersonAccessEntity, Integer> {

    /**
     * 関連者コードで検索する
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<KanrenshaPersonAccessEntity> findByPersonKanrenshaCodeOrderByKanrenshaPersonAccessIdDesc(String kanrenshaCode);

    /**
     * 最新かつマスタIdに紐づくEntityを取得する
     * 
     * @param masterId マスタId
     * @param isLatest 最新
     * @return 連絡先
     */
    List<KanrenshaPersonAccessEntity> findByKanrenshaPersonIdAndIsLatest(Integer masterId, boolean isLatest);

    /**
     * 関連者コードで連絡先Id順で取得
     * 
     * @param kanrenshaCode 関連者コード
     * @param isLatest      最新該非
     * @return 関連者個人連絡先リスト
     */
    List<KanrenshaPersonAccessEntity> findByPersonKanrenshaCodeAndIsLatestOrderByKanrenshaPersonAccessIdDesc(
            String kanrenshaCode, Boolean isLatest);

}
