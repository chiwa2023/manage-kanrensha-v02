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

}
