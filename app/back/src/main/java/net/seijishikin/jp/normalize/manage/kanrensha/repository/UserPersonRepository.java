package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;

/**
 * user_person接続用Repository
 */
public interface UserPersonRepository extends JpaRepository<UserPersonEntity, Integer> {

    /**
     * 最大のコードを持つデータを取得する
     *
     * @return ユーザ人物Entity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<UserPersonEntity> findFirstByOrderByUserPersonCodeDesc();

    /**
     * 最新かつemailが一致するユーザ人物を取得する
     *
     * @param email メール
     * @return ユーザ人物Entity
     */
    @Query(value = "SELECT * FROM user_person WHERE is_latest = 1 AND email = ?1", nativeQuery = true)
    Optional<UserPersonEntity> findLatestByMail(String email);

    /**
     * emailが同一で最新データを取得する
     * 
     * @param email email
     * @return 検索結果
     */
    Optional<UserPersonEntity> findByEmailAndIsLatestTrue(String email);

}
