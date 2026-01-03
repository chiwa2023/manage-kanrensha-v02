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
     * 最新フラグかつemailで検索する
     *
     * @param isLatest 最新フラグ
     * @param email    メールアドレス
     * @return 権限リスト
     */
    List<UserRoleEntity> findByIsLatestAndEmail(boolean isLatest, String email);

}
