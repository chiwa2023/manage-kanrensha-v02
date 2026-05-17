package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2022;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2022.KanrenshaCombineOrg2022Entity;



/**
 * kanrensha_combine_org_2022接続用Repository
 */
public interface KanrenshaCombineOrg2022Repository  extends JpaRepository<KanrenshaCombineOrg2022Entity, Integer>{

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<KanrenshaCombineOrg2022Entity> findFirstByOrderByKanrenshaCombineOrgCode();

}
