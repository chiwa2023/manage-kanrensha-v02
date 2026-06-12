package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;

/**
 * wk_tbl_postal_edit接続用Repository
 */
public interface WkTblPostalEditRepository extends JpaRepository<WkTblPostalEditEntity, Integer> {

    /**
     * ユーザコード条件で削除する
     * 
     * @param userCode 操作者ユーザコード
     * @return 処理件数
     * 
     */
    Integer deleteByInsertUserCode(Integer userCode);

    /**
     * 該当する変更理由で作業内容ごとに取得する
     * 
     * @param userocde   ユーザコード
     * @param henkouRiyu 変更理由
     * @return 検索結果
     */
    @Query(value = "SELECT works_text FROM wk_tbl_postal_edit WHERE insert_user_code = ?1 AND is_latest =1 and is_repair =1 "
            + " AND flg_henkou_riyu = ?2 GROUP BY works_text", nativeQuery = true)
    List<String> findGroupByWorksTextRepair(Integer userocde, String henkouRiyu);

    /**
     * 郵便番号7桁で検索する
     * 
     * @param userocde   ユーザコード
     * @param postalcode 郵便番号7桁
     * @return 検索結果
     */
    List<WkTblPostalEditEntity> findByInsertUserCodeAndPostalcode7AndIsLatestTrue(Integer userocde, String postalcode);

    /**
     * 指定の変更理由で処理内容未設定を取得する
     * 
     * @param userocde ユーザコード
     * @param listRiyu 検索条件変更理由リスト
     * @return 検索結果
     */
    List<WkTblPostalEditEntity> findByInsertUserCodeAndFlgHenkouRiyuInAndIsLatestTrueAndIsRepairIsNull(Integer userocde,
            List<String> listRiyu);

    /**
     * 変更理由と地名で検索する
     * 
     * @param userocde   ユーザコード
     * @param henkouRiyu 変更理由
     * @param prefName   県名
     * @param cityName   市区町村名
     * @param orgName    地名原記述
     * @return 検索結果
     */
    List<WkTblPostalEditEntity> //
            findByInsertUserCodeAndFlgHenkouRiyuAndIsLatestTrueAndPrefNameAndCityNameAndOrgName( // NOPMD CreanerAPI
                    Integer userocde, String henkouRiyu, String prefName, String cityName, String orgName);

    /**
     * 変更理由と郵便番号で検索する
     * 
     * @param userocde   ユーザコード
     * @param henkouRiyu 変更理由
     * @param postalcode 郵便番号
     * @return 検索結果
     */
    List<WkTblPostalEditEntity> findByInsertUserCodeAndFlgHenkouRiyuAndIsLatestTrueAndPostalcode7(Integer userocde,
            String henkouRiyu, String postalcode);

    /**
     * 作業内容で検索する
     * 
     * @param userocde ユーザコード
     * @param workText 作業内容
     * @return 検索結果
     */
    List<WkTblPostalEditEntity> findByInsertUserCodeAndWorksTextAndIsLatestTrueOrderByFlgHenkouRiyu(Integer userocde,
            String workText);

    /**
     * ユーザコード、最新該否検索条件、修復該否検索条件で検索する
     * 
     * @param userocde         ユーザコード
     * @param listSearchLatest 新該否検索条件
     * @param listSearchRepair 修復該否検索条件
     * @param pageable ページング
     * @return 検索結果
     */
    List<WkTblPostalEditEntity> findByInsertUserCodeAndIsLatestInAndIsRepairInOrderByFlgHenkouRiyu(Integer userocde,
            List<Boolean> listSearchLatest, List<Boolean> listSearchRepair, Pageable pageable);

    /**
     * ユーザコード、最新該否検索条件、修復該否検索条件で件数を取得する
     * 
     * @param userocde         ユーザコード
     * @param listSearchLatest 新該否検索条件
     * @param listSearchRepair 修復該否検索条件
     * @return 件数
     */
    Integer countByInsertUserCodeAndIsLatestInAndIsRepairIn(Integer userocde, List<Boolean> listSearchLatest,
            List<Boolean> listSearchRepair);

}
