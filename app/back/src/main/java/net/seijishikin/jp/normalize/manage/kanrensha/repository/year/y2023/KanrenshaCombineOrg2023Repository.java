package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2023;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2023.KanrenshaCombineOrg2023Entity;



/**
 * kanrensha_combine_org_2023接続用Repository
 */
public interface KanrenshaCombineOrg2023Repository  extends JpaRepository<KanrenshaCombineOrg2023Entity, Integer>{

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<KanrenshaCombineOrg2023Entity> findFirstByOrderByKanrenshaCombineOrgCode();

}
