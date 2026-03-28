package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalRepairLogEntity;

/**
 * address_postal_repair_log接続用Repository
 */
public interface AddressPostalRepairLogRepository extends JpaRepository<AddressPostalRepairLogEntity, Integer> {

    /**
     * 現在抽出分を初期化用に履歴とする
     * 
     * @param lgCode   地方行政区コード
     * @param userId   ユーザId
     * @param userCode ユーザコード
     * @param userName ユーザ名
     * @return 処理件数
     */
    @Modifying
    @Query(value = "UPDATE address_postal_repair_log SET is_latest = 0, delete_user_id = ?2,"
            + " delete_user_code = ?3, delete_user_name = ?4 ,delete_timestamp = now()"
            + " WHERE lg_code LIKE ?1 AND is_latest = 1", nativeQuery = true)
    int initializeByLgcode(String lgCode, Integer userId, Integer userCode, String userName);

    /**
     * 行政区コードが部分一致し、修正予定の件数を取得する
     * 
     * @param lgCodePref 行政区コード
     * @return 件数
     */
    int countByLgCodeStartingWithAndIsLatestTrueAndIsConfirmTrue(String lgCodePref);

    /**
     * 行政区コードが部分一致し、修正予定の件数を取得する
     * 
     * @param lgCodePref 行政区コード
     * @return 件数
     */
    Page<AddressPostalRepairLogEntity> findByLgCodeStartingWithAndIsLatestTrueAndIsConfirmTrueAndAddressNameContaining(
            String lgCodePref, String words, Pageable pageable);
}
