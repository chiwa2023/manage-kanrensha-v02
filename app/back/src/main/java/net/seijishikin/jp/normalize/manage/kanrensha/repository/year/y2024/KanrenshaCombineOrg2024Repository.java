package net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2024;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2024.KanrenshaCombineOrg2024Entity;



/**
 * kanrensha_combine_org_2024接続用Repository
 */
public interface KanrenshaCombineOrg2024Repository  extends JpaRepository<KanrenshaCombineOrg2024Entity, Integer>{

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<KanrenshaCombineOrg2024Entity> findFirstByOrderByKanrenshaCombineOrgCode();

}
