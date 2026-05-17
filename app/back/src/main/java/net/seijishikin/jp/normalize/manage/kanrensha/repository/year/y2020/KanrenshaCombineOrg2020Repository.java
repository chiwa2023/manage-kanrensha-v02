package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2020;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2020.KanrenshaCombineOrg2020Entity;



/**
 * kanrensha_combine_org_2020接続用Repository
 */
public interface KanrenshaCombineOrg2020Repository  extends JpaRepository<KanrenshaCombineOrg2020Entity, Integer>{

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<KanrenshaCombineOrg2020Entity> findFirstByOrderByKanrenshaCombineOrgCode();

}
