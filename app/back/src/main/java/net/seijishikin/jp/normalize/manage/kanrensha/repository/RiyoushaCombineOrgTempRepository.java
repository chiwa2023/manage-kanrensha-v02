package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgTempEntity;

/**
 * riyousha_combine_org_temp接続用Repository
 */
public interface RiyoushaCombineOrgTempRepository extends JpaRepository<RiyoushaCombineOrgTempEntity, Integer> {

    /**
     * 最大コードをもつEntityを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<RiyoushaCombineOrgTempEntity> findFirstByOrderByRiyoushaCombineOrgTempCodeDesc();

    /**
     * 最新を個人コードから検索する
     * 
     * @param personCode 個人コード
     * @return 検索結果
     */
    List<RiyoushaCombineOrgTempEntity> findByPersonCodeAndIsLatestTrue(Integer personCode);

    /**
     * 最新かつ個人コード、組織コード、権限で検索する
     * 
     * @param personCode 個人コード
     * @param orgCode    組織コード
     * @param userRole   個人権限
     * @return 検索結果
     */
    List<RiyoushaCombineOrgTempEntity> findByPersonRiyoushaCodeAndOrgRiyoushaCodeAndRiyoushaRoleAndIsLatestTrue(
            Integer personCode, Integer orgCode, String userRole);

}
