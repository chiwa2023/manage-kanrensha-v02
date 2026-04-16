package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgPropertyEntity;

/**
 * riyousha_org_property接続用Repository
 */
public interface RiyoushaOrgPropertyRepository extends JpaRepository<RiyoushaOrgPropertyEntity, Integer> {

    /**
     * 最大コードをもつEntityを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<RiyoushaOrgPropertyEntity> findFirstByOrderByRiyoushaOrgPropertyCodeDesc();

}
