package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.PromoteAdminEntity;

/**
 * promote_admin接続用Repository
 */
public interface PromoteAdminRepository extends JpaRepository<PromoteAdminEntity, Integer> {

    /**
     * 最新コードを取得する
     * 
     * @return 最新コード
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<PromoteAdminEntity> findFirstByOrderByPromoteAdminCodeDesc();

    /**
     * 同一人かつ諾否を回答しなければいけないデータをすべて抽出する
     * 
     * @param userCode ユーザコード
     * @return 検索結果
     */
    List<PromoteAdminEntity> findByPromoteUserCodeAndIsLatestTrueOrderByInsertTimestampDesc(Integer userCode);
}
