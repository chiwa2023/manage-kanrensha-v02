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
    List<KanrenshaPersonAccessEntity> findByPersonKanrenshaCodeOrderByKanrenshaPersonAccessIdDesc(
            String kanrenshaCode);

}
