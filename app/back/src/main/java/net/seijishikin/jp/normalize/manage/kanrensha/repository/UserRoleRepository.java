package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;

/**
 * user_role接続用Repository
 */
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {

    /**
     * ロールリストをメールアドレスから取得する
     *
     * @param email メールアドレス
     * @return 最低限ユーザ
     */
    @Query(value = "SELECT role FROM user_role WHERE is_latest = 1 AND email = ?1", nativeQuery = true)
    List<String> findLatestRoleByMail(String email);

    /**
     * emailとis_latestフラグでUserRoleEntityのリストを検索する
     * 
     * @param email メールアドレス
     * @return ユーザ権限Entityのリスト
     */
    List<UserRoleEntity> findByEmailAndIsLatestTrue(String email);

    /**
     * 最新フラグかつemailで検索する
     *
     * @param isLatest 最新フラグ
     * @param email    メールアドレス
     * @return 権限リスト
     */
    List<UserRoleEntity> findByIsLatestAndEmail(boolean isLatest, String email);

    /**
     * emailとis_latestフラグでUserRoleEntityのリストを検索する
     * 
     * @param email メールアドレス
     * @return ユーザ権限Entityのリスト
     */
    List<UserRoleEntity> findByEmailAndRoleAndIsLatestTrue(String email, String role);

    /**
     * 権限と利用者コードでUserRoleEntityのリストを検索する
     * 
     * @param role         権限
     * @param riyoushaCode 利用者コード
     * @return ユーザ権限Entityのリスト
     */
    @Query(value = "SELECT * FROM user_role WHERE is_latest = 1 AND email IN (SELECT email FROM "
            + " user_role WHERE is_latest = 1 AND riyousha_code =?1 AND role = ?2)", nativeQuery = true)
    List<UserRoleEntity> findRiyoushaCodeAndRole(Integer riyoushaCode, String role);

}
