package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;

/**
 * user_person接続用Repository
 */
public interface UserPersonRepository
        extends JpaRepository<UserPersonEntity, Integer>, PagingAndSortingRepository<UserPersonEntity, Integer> {

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

    /**
     * 名称と権限からユーザを検索する
     * 
     * @param name     名称(部分一致)
     * @param listRole 権限リスト
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM user_person "
            + "   WHERE email IN (SELECT email FROM user_role WHERE role IN ?2 AND is_latest = 1) "
            + "     AND is_latest = 1 AND CASE WHEN ?1<> '' THEN user_person_name LIKE ?1 ELSE 1=1 END ORDER BY user_person_id", nativeQuery = true)
    List<UserPersonEntity> findNameAndRoles(String name, List<String> listRole, Pageable pageable);

    /**
     * 名称と権限からユーザを検索したときの全件数を取得する
     * 
     * @param name     名称(部分一致)
     * @param listRole 権限リスト
     * @return 全件数
     */
    @Query(value = "SELECT count(*) FROM user_person "
            + "   WHERE email IN (SELECT email FROM user_role WHERE role IN ?2 AND is_latest = 1) "
            + "     AND is_latest = 1 AND CASE WHEN ?1<> '' THEN user_person_name LIKE ?1 ELSE 1=1 END ORDER BY user_person_id", nativeQuery = true)
    Integer countNameAndRoles(String name, List<String> listRole);

}
