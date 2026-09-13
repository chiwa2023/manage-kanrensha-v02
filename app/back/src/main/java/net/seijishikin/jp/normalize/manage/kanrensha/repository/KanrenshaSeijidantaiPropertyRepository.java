package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;

/**
 * kanrensha_seijidantai_property接続用Repository
 */
public interface KanrenshaSeijidantaiPropertyRepository
        extends JpaRepository<KanrenshaSeijidantaiPropertyEntity, Integer> {

    /**
     * 関連者コードで検索する
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<KanrenshaSeijidantaiPropertyEntity> findBySeijidantaiKanrenshaCodeOrderByKanrenshaSeijidantaiPropertyIdDesc(
            String kanrenshaCode);

    /**
     * 関連者政治団体Idと最新フラグで検索する
     *
     * @param masterId 関連者政治団体Id
     * @param isLatest 最新フラグ
     * @return 検索結果
     */
    List<KanrenshaSeijidantaiPropertyEntity> findByKanrenshaSeijidantaiIdAndIsLatest(Integer masterId, boolean isLatest);

    /**
     * 関連者コードと最新フラグで検索する
     *
     * @param kanrenshaCode 関連者コード
     * @param isLatest      最新フラグ
     * @return 検索結果
     */
    List<KanrenshaSeijidantaiPropertyEntity> findBySeijidantaiKanrenshaCodeAndIsLatestOrderByKanrenshaSeijidantaiPropertyIdDesc(
            String kanrenshaCode, Boolean isLatest);

}
