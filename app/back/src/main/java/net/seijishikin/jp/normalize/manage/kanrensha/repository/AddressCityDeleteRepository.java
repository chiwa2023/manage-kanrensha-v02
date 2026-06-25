package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressCityDeleteEntity;

/**
 * address_city_delete接続用Repository
 */
public interface AddressCityDeleteRepository extends JpaRepository<AddressCityDeleteEntity, Integer> {

    /**
     * 最新を取得する
     * 
     * @param pageable ページング
     * @return 検索結果
     */
    List<AddressCityDeleteEntity> findByIsLatestTrue(Pageable pageable);

    /**
     * 最新の件数を取得する
     * 
     * @return 件数
     */
    Integer countByIsLatestTrue();

}
