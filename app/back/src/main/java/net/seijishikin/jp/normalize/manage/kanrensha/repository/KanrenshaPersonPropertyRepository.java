package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;

/**
 * kanrensha_person_property接続用Repository
 */
public interface KanrenshaPersonPropertyRepository extends JpaRepository<KanrenshaPersonPropertyEntity, Integer> {

    /**
     * 関連者コードで検索する
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<KanrenshaPersonPropertyEntity> findByPersonKanrenshaCodeOrderByKanrenshaPersonPropertyIdDesc(
            String kanrenshaCode);

}
