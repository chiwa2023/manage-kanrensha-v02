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
    
    List<RiyoushaCombineOrgTempEntity> findByPersonCodeAndIsLatestTrue(Integer personCode);
    
    List<RiyoushaCombineOrgTempEntity> findByPersonRiyoushaCodeAndOrgRiyoushaCodeAndRiyoushaRoleAndIsLatestTrue(Integer personCode,Integer orgCode,String userRole);

}
