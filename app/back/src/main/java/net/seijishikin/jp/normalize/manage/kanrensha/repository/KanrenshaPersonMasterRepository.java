package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;

/**
 * kanrensha_person_master接続用Repository
 */
public interface KanrenshaPersonMasterRepository extends JpaRepository<KanrenshaPersonMasterEntity, Integer> {

    /**
     * 個人名で検索する
     *
     * @param nameText 団体名自然検索用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<KanrenshaPersonMasterEntity> findByCompareNameTextAndIsLatest(String nameText, Boolean isLatest);

    /**
     * 最新かつ関連者コードと比較用名称リストを取得する
     *
     * @param code     関連者コード
     * @param nameText 比較用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<KanrenshaPersonMasterEntity> findByPersonKanrenshaCodeAndCompareNameTextAndIsLatest(String code, String nameText,
            Boolean isLatest);

}
