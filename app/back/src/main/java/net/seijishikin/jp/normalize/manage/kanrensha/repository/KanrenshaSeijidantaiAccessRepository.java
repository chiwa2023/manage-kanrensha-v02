package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;

/**
 * kanrensha_seijidantai_access接続用Repository
 */
public interface KanrenshaSeijidantaiAccessRepository extends JpaRepository<KanrenshaSeijidantaiAccessEntity, Integer> {

    /**
     * 関連者コードで検索する
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<KanrenshaSeijidantaiAccessEntity> findBySeijidantaiKanrenshaCodeOrderByKanrenshaSeijidantaiAccessIdDesc(
            String kanrenshaCode);

    /**
     * 関連者政治団体Idと最新フラグで検索する
     *
     * @param masterId 関連者政治団体Id
     * @param isLatest 最新フラグ
     * @return 検索結果
     */
    List<KanrenshaSeijidantaiAccessEntity> findByKanrenshaSeijidantaiIdAndIsLatest(Integer masterId, boolean isLatest);

    /**
     * 関連者コードと最新フラグで検索する
     *
     * @param kanrenshaCode 関連者コード
     * @param isLatest      最新フラグ
     * @return 検索結果
     */
    List<KanrenshaSeijidantaiAccessEntity> findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiAccessIdDesc(
            String kanrenshaCode, Boolean isLatest);

}
