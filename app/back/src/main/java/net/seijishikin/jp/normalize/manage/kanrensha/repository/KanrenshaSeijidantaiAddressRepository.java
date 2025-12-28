package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;

/**
 * kanrensha_seijidantai_address接続用Repository
 */
public interface KanrenshaSeijidantaiAddressRepository
        extends JpaRepository<KanrenshaSeijidantaiAddressEntity, Integer> {

    /**
     * 関連者コードで検索する
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<KanrenshaSeijidantaiAddressEntity> findBySeijidantaiKanrenshaCodeOrderByKanrenshaSeijidantaiAddressIdDesc(
            String kanrenshaCode);

}
