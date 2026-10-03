package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;

/**
 * kanrensha_kigyou_dt_access接続用Repository
 */
public interface KanrenshaKigyouDtAccessRepository extends JpaRepository<KanrenshaKigyouDtAccessEntity, Integer> {

    /**
     * 関連者コードで検索する
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<KanrenshaKigyouDtAccessEntity> findByKigyouDtKanrenshaCodeOrderByKanrenshaKigyouDtAccessIdDesc(
            String kanrenshaCode);

    /**
     * 関連者企業・団体Idと最新フラグで検索する
     *
     * @param masterId 関連者企業・団体Id
     * @param isLatest 最新フラグ
     * @return 検索結果
     */
    List<KanrenshaKigyouDtAccessEntity> findByKanrenshaKigyouDtIdAndIsLatest(Integer masterId, boolean isLatest);

    /**
     * 関連者コードと最新フラグで検索する
     *
     * @param kanrenshaCode 関連者コード
     * @param isLatest      最新フラグ
     * @return 検索結果
     */
    List<KanrenshaKigyouDtAccessEntity> findByKigyouDtKanrenshaCodeAndIsLatestOrderByKanrenshaKigyouDtAccessIdDesc(
            String kanrenshaCode, Boolean isLatest);

}
