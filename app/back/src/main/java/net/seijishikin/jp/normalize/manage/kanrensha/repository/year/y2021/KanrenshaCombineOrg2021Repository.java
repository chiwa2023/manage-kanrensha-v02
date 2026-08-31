package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2021;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2021.KanrenshaCombineOrg2021Entity;



/**
 * kanrensha_combine_org_2021接続用Repository
 */
public interface KanrenshaCombineOrg2021Repository  extends JpaRepository<KanrenshaCombineOrg2021Entity, Integer>{

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<KanrenshaCombineOrg2021Entity> findFirstByOrderByKanrenshaCombineOrgCode();

}
