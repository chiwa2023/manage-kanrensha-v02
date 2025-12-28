package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;

/**
 * kanrensha_kigyou_dt_address接続用Repository
 */
public interface KanrenshaKigyouDtAddressRepository extends JpaRepository<KanrenshaKigyouDtAddressEntity, Integer> {

    /**
     * 関連者コードで検索する
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<KanrenshaKigyouDtAddressEntity> findByKigyouDtKanrenshaCodeOrderByKanrenshaKigyouDtAddressIdDesc(
            String kanrenshaCode);

}
