package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.KanrenshaCombineOrg2025Entity;



/**
 * kanrensha_combine_org_2025接続用Repository
 */
public interface KanrenshaCombineOrg2025Repository  extends JpaRepository<KanrenshaCombineOrg2025Entity, Integer>{

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<KanrenshaCombineOrg2025Entity> findFirstByOrderByKanrenshaCombineOrgCode();

}
