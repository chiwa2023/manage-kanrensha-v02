package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasskeyCredentialEntity;

/**
 * user_passkey_credential接続用Repository
 */
public interface UserPasskeyCredentialRepository extends JpaRepository<UserPasskeyCredentialEntity, Long> {

    /**
     * クレデンシャルIDで検索する
     *
     * @param credentialId クレデンシャルID
     * @return パスキーEntity
     */
    Optional<UserPasskeyCredentialEntity> findByCredentialId(String credentialId);

    /**
     * ユーザ人物コードで検索する
     *
     * @param userPersonCode ユーザ人物コード
     * @return パスキーEntityリスト
     */
    List<UserPasskeyCredentialEntity> findByUserPersonCode(Integer userPersonCode);

    /**
     * ユーザ人物コードで件数を取得する
     *
     * @param userPersonCode ユーザ人物コード
     * @return 登録件数
     */
    long countByUserPersonCode(Integer userPersonCode);

    /**
     * パスキーIDとユーザ人物コードで検索する
     *
     * @param passkeyId パスキーID
     * @param userPersonCode ユーザ人物コード
     * @return パスキーEntity
     */
    Optional<UserPasskeyCredentialEntity> findByPasskeyIdAndUserPersonCode(Long passkeyId, Integer userPersonCode);

    /**
     * パスキーIDとユーザ人物コードで削除する
     *
     * @param passkeyId パスキーID
     * @param userPersonCode ユーザ人物コード
     * @return 削除件数
     */
    @Transactional
    long deleteByPasskeyIdAndUserPersonCode(Long passkeyId, Integer userPersonCode);

}
