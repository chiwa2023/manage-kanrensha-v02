package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2019;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2019.KanrenshaCombineOrg2019Entity;



/**
 * kanrensha_combine_org_2019接続用Repository
 */
public interface KanrenshaCombineOrg2019Repository  extends JpaRepository<KanrenshaCombineOrg2019Entity, Integer>{

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<KanrenshaCombineOrg2019Entity> findFirstByOrderByKanrenshaCombineOrgCode();

}
