package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPersonPropertyEntity;

/**
 * riyousha_person_property接続用Repository
 */
public interface RiyoushaPersonPropertyRepository extends JpaRepository<RiyoushaPersonPropertyEntity, Integer> {


    /**
     * 最大コードをもつEntityを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<RiyoushaPersonPropertyEntity> findFirstByOrderByRiyoushaPersonPropertyCodeDesc();

}
