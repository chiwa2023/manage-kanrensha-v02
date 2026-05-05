package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;

/**
 * kanrensha_kigyou_dt_master接続用Repository
 */
public interface KanrenshaKigyouDtMasterRepository extends JpaRepository<KanrenshaKigyouDtMasterEntity, Integer> {

    /**
     * 最新かつ比較用名称が合致するリストを取得する
     *
     * @param nameText 比較用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<KanrenshaKigyouDtMasterEntity> findByCompareNameTextAndIsLatest(String nameText, Boolean isLatest);

    /**
     * 最新かつ関連者コードと比較用名称リストを取得する
     *
     * @param code     関連者コード
     * @param nameText 比較用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<KanrenshaKigyouDtMasterEntity> findByKigyouDtKanrenshaCodeAndCompareNameTextAndIsLatest(String code,
            String nameText, Boolean isLatest);

    /**
     * 最新かつ関連者コードが同一の最初の1件を取得する(1件しかない運用をする)
     * 
     * @param kanrenshaCode 関連者コード
     * @return 関連者コード同一Entity
     */
    Optional<KanrenshaKigyouDtMasterEntity> findFirstByKigyouDtKanrenshaCodeAndIsLatestTrueOrderByKanrenshaKigyouDtMasterIdDesc(
            String kanrenshaCode);

    /**
     * 基準時間より前の最新データを取得する
     *
     * @param dateTime 基準日時開始
     * @param isLatest 最新該否
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<KanrenshaKigyouDtMasterEntity> findByInsertTimestampLessThanAndIsLatest(LocalDateTime dateTime,
            boolean isLatest, Pageable pageable);

    /**
     * 基準時間開始以上かつ終了より前の最新を取得する
     *
     * @param dateTimeStart 基準日時開始
     * @param dateTimeEnd   基準日時終了
     * @param isLatest      最新該否
     * @param pageable      ページング条件
     * @return 検索結果
     */
    Page<KanrenshaKigyouDtMasterEntity> findByInsertTimestampGreaterThanEqualAndInsertTimestampLessThanAndIsLatest(
            LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, boolean isLatest, Pageable pageable);

    /**
     * 関連者コードで検索する
     * 
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<KanrenshaKigyouDtMasterEntity> findByKigyouDtKanrenshaCodeOrderByKanrenshaKigyouDtMasterIdDesc(
            String kanrenshaCode);

    /**
     * 検索条件で検索する
     * 
     * @param houjinNo 法人番号
     * @param name     名称
     * @param address  住所
     * @param delegate 団体名
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM kanrensha_kigyou_dt_master" //
            + " WHERE houjin_no LIKE ?1 AND kanrensha_name LIKE ?2 " //
            + "     AND all_address LIKE ?3 AND kigyou_dt_delegate LIKE ?4 AND is_latest=1" //
            , nativeQuery = true)
    List<KanrenshaKigyouDtMasterEntity> findSearchCondition(String houjinNo, String name, // NOPMD CleanerAPI
            String address, String delegate, Pageable pageable);

    /**
     * 検索条件での該当件数を返却する
     * 
     * @param houjinNo 法人番号
     * @param name     名称
     * @param address  住所
     * @param delegate 団体名
     * @return 該当件数
     */
    @Query(value = "SELECT count(*) FROM kanrensha_kigyou_dt_master" //
            + " WHERE houjin_no LIKE ?1 AND kanrensha_name LIKE ?2 " //
            + "     AND all_address LIKE ?3 AND kigyou_dt_delegate LIKE ?4 AND is_latest=1" //
            , nativeQuery = true)
    Integer countSearchCondition(String houjinNo, String name, String address, String delegate); // NOPMD CleanerAPI

}
