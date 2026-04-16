package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;

/**
 * kanrensha_kigyou_dt_property接続用Repository
 */
public interface KanrenshaKigyouDtPropertyRepository extends JpaRepository<KanrenshaKigyouDtPropertyEntity, Integer> {

    /**
     * 関連者コードで検索する
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<KanrenshaKigyouDtPropertyEntity> findByKigyouDtKanrenshaCodeOrderByKanrenshaKigyouDtPropertyIdDesc(
            String kanrenshaCode);

}
