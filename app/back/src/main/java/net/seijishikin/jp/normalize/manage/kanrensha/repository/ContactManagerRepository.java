package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.ContactManagerEntity;

/**
 * contact_manager接続用Repository
 */
public interface ContactManagerRepository extends JpaRepository<ContactManagerEntity, Integer> {

    /**
     * 問い合わせコード最新を取得する
     * 
     * @return 検索結果
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ContactManagerEntity> findFirstByOrderByContactManagerCodeDesc();

    /**
     * 問い合わせコード最新を取得する
     * 
     * @param contactCode 問い合わせコード
     * @return 問い合わせコード最新
     */
    Optional<ContactManagerEntity> findFirstByContactManagerCodeAndIsLatestTrueOrderByContactManagerIdDesc(
            Integer contactCode);

    /**
     * 同一問い合わせ履歴を取得する
     * 
     * @param contactCode 問い合わせコード
     * @return 検索結果
     */
    List<ContactManagerEntity> findByContactManagerCodeOrderByContactManagerIdAsc(Integer contactCode);

    /**
     * 指定期間の問い合わせ最新件数を取得する
     * 
     * @param startDateTime 検索期間開始
     * @param endDateTime   検索期間終了
     * @param listClose     検索条件クローズ該当
     * @return 件数
     */
    Integer countByIsLatestTrueAndFirstTimestampBetweenAndIsClosedIn(LocalDateTime startDateTime,
            LocalDateTime endDateTime, List<Boolean> listClose);

    /**
     * 指定期間の問い合わせ最新を検索する
     * 
     * @param startDateTime 検索期間開始
     * @param endDateTime   検索期間終了
     * @param listClose     検索条件クローズ該当
     * @param pageable      ページング
     * @return 検索結果
     */
    List<ContactManagerEntity> findByIsLatestTrueAndFirstTimestampBetweenAndIsClosedIn(LocalDateTime startDateTime,
            LocalDateTime endDateTime, List<Boolean> listClose, Pageable pageable);

    /**
     * 初回問い合わせ者の最新問い合わせ件数を取得する
     * 
     * @param userCode 問い合わせ者ユーザコード
     * @return 件数
     */
    Integer countByInquireUserCodeAndIsLatestTrue(Integer userCode);

    /**
     * 初回問い合わせ者の最新問い合わせを検索する
     * 
     * @param userCode 問い合わせ者ユーザコード
     * @param pageable ページング
     * @return 検索結果
     */
    List<ContactManagerEntity> findByInquireUserCodeAndIsLatestTrue(Integer userCode, Pageable pageable);

}
