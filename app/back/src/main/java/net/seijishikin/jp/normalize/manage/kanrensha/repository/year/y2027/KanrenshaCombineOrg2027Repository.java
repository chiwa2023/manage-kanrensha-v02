package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2027;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2027.KanrenshaCombineOrg2027Entity;



/**
 * kanrensha_combine_org_2027接続用Repository
 */
public interface KanrenshaCombineOrg2027Repository  extends JpaRepository<KanrenshaCombineOrg2027Entity, Integer>{

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<KanrenshaCombineOrg2027Entity> findFirstByOrderByKanrenshaCombineOrgCode();

}
