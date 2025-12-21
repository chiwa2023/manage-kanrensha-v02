package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;

/**
 * kanrensha_seijidantai_master接続用Repository
 */
public interface KanrenshaSeijidantaiMasterRepository extends JpaRepository<KanrenshaSeijidantaiMasterEntity, Integer> {

    /**
     * 個人名で検索する
     *
     * @param nameText 団体名自然検索用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<KanrenshaSeijidantaiMasterEntity> findByCompareNameTextAndIsLatest(String nameText, Boolean isLatest);

    /**
     * 最新かつ関連者コードと比較用名称リストを取得する
     *
     * @param code     関連者コード
     * @param nameText 比較用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<KanrenshaSeijidantaiMasterEntity> findBySeijidantaiKanrenshaCodeAndCompareNameTextAndIsLatest(String code,
            String nameText, Boolean isLatest);

    /**
     * 最新かつ関連者コードが同一の最初の1件を取得する(1件しかない運用をする)
     * 
     * @param kanrenshaCode 関連者コード
     * @param isLatest      最新該否
     * @return 関連者コード同一Entity
     */
    Optional<KanrenshaSeijidantaiMasterEntity> findFirstBySeijidantaiKanrenshaCodeAndIsLatest(String kanrenshaCode,
            boolean isLatest);

}
