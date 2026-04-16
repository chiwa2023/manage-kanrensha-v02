package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;

/**
 * riyousha_combine_org接続用Repository
 */
public interface RiyoushaCombineOrgRepository extends JpaRepository<RiyoushaCombineOrgEntity, Integer> {

    /**
     * 個人コードから紐づきを検索する
     * 
     * @param userCode ユーザコード
     * @return 検索結果
     */
    List<RiyoushaCombineOrgEntity> findByPersonRiyoushaCodeAndIsLatestTrue(Integer userCode);

    /**
     * 組織コードから紐づきを検索する
     * 
     * @param orgCode 組織コード
     * @return 検索結果
     */
    List<RiyoushaCombineOrgEntity> findByOrgRiyoushaCodeAndIsLatestTrue(Integer orgCode);

}
