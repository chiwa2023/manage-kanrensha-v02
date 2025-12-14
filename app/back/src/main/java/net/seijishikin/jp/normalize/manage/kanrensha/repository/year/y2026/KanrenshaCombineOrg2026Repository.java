package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.KanrenshaCombineOrg2026Entity;



/**
 * kanrensha_combine_org_2026接続用Repository
 */
public interface KanrenshaCombineOrg2026Repository  extends JpaRepository<KanrenshaCombineOrg2026Entity, Integer>{

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<KanrenshaCombineOrg2026Entity> findFirstByOrderByKanrenshaCombineOrgCode();

}
