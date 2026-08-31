package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
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
    List<RiyoushaCombineOrgEntity> findByPersonCodeAndIsLatestTrue(Integer userCode);

    /**
     * 組織コードから紐づきを検索する
     * 
     * @param orgCode 組織コード
     * @return 検索結果
     */
    List<RiyoushaCombineOrgEntity> findByOrgRiyoushaCodeAndIsLatestTrue(Integer orgCode);

    /**
     * 利用者個人コードから紐づきを検索する
     * 
     * @param personCode 利用者個人コード
     * @return 検索結果
     */
    List<RiyoushaCombineOrgEntity> findByPersonRiyoushaCodeAndRiyoushaRoleAndIsLatestTrue(Integer personCode,String riyoushaRole);

    /**
     * 最大コードをもつEntityを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<RiyoushaCombineOrgEntity> findFirstByOrderByRiyoushaCombineOrgCodeDesc();

}
