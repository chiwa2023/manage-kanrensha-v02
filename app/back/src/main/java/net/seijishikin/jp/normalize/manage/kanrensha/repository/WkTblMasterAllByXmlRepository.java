package net.seijishikin.jp.normalize.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.persistence.LockModeType;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml.XmlBikouUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml.XmlKanrenshaUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml.XmlNameAddressUniquekeyDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;

/**
 * wk_tbl_master_all_by_xml接続用Repository
 */
public interface WkTblMasterAllByXmlRepository // NOPMD TooManyMethods
        extends JpaRepository<WkTblMasterAllByXmlEntity, Integer>,
        PagingAndSortingRepository<WkTblMasterAllByXmlEntity, Integer> {

    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblMasterAllByXmlEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode,
            boolean isLatest, boolean isAffected, boolean isFinish, Pageable pageable);

    /**
     * 登録すべきデータを抽出する
     *
     * @param userCode   ユーザコード
     * @param isLatest   最新該否
     * @param isAffected 反映有無
     * @param isFinish   終了有無
     * @param pageable   ページング条件
     * @return 検索結果
     */
    Page<WkTblMasterAllByXmlEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode,
            Boolean isLatest, Boolean isAffected, Boolean isFinish, Pageable pageable);

    /**
     * 編集用に検索を行う
     *
     * @param userCode   ユーザコード
     * @param listLatest 検索条件履歴
     * @param isAffected 検索条件反映行
     * @param listFinish 検索条件勝利完了
     * @param pageable   ページング
     * @return 検索結果
     */
    List<WkTblMasterAllByXmlEntity> findByInsertUserCodeAndIsLatestInAndIsAffectedInAndIsFinishIn(Integer userCode,
            List<Boolean> listLatest, List<Boolean> isAffected, List<Boolean> listFinish, Pageable pageable);

    /**
     * 編集用に検索を行う際の該当件数を返却する
     *
     * @param userCode   ユーザコード
     * @param listLatest 検索条件履歴
     * @param isAffected 検索条件反映行
     * @param listFinish 検索条件勝利完了
     * @return 件数
     */
    Integer countByInsertUserCodeAndIsLatestInAndIsAffectedInAndIsFinishIn(Integer userCode, List<Boolean> listLatest,
            List<Boolean> isAffected, List<Boolean> listFinish);

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<WkTblMasterAllByXmlEntity> findFirstByOrderByWkTblMasterAllByXmlCodeDesc();

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

    /**
     * 備考1項目で重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct bikou FROM wk_tbl_master_all_by_xml"
            + "   WHERE insert_user_code = ?1 AND youshiki_kbn IN ('3','4','6')"
            + "   GROUP BY bikou HAVING count(*) >1", nativeQuery = true)
    List<XmlBikouUniquekeyDto> findDuplicateUniqueKeyBikou(Integer userCode);

    /**
     * 名前と住所2項目で重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct input_src_name,input_src_address FROM wk_tbl_master_all_by_xml"
            + "    WHERE insert_user_code = ?1 AND youshiki_kbn IN ('5','14','15','16')"
            + "    GROUP BY input_src_name,input_src_address HAVING count(*) >1", nativeQuery = true)
    List<XmlNameAddressUniquekeyDto> findDuplicateUniqueKeyNameAddress(Integer userCode);

    /**
     * 関連者3項目で重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct input_src_name,input_src_address,input_src_key FROM wk_tbl_master_all_by_xml"
            + "    WHERE insert_user_code = ?1 AND youshiki_kbn IN ('7','8','11','12')"
            + "    GROUP BY input_src_name,input_src_address,input_src_key HAVING count(*) >1", nativeQuery = true)
    List<XmlKanrenshaUniquekeyDto> findDuplicateUniqueKeyDecideKanrensha(Integer userCode);

    /**
     * 備考1項目だけで重複行を抽出する
     *
     * @param bikou           備考
     * @param listYoushikiKbn 様式区分リスト
     * @param userCode        ユーザコード
     * @return 検索結果
     */
    List<WkTblMasterAllByXmlEntity> findByBikouAndYoushikiKbnInAndInsertUserCodeOrderByWkTblMasterAllByXmlIdAsc(
            String bikou, List<Integer> listYoushikiKbn, Integer userCode);

    /**
     * 名前と住所2項目で重複行を抽出する
     *
     * @param name            名称
     * @param address         住所
     * @param listYoushikiKbn 様式区分リスト
     * @param userCode        ユーザコード
     * @return 検索結果
     */
    List<WkTblMasterAllByXmlEntity> findByInputSrcNameAndInputSrcAddressAndYoushikiKbnInAndInsertUserCodeOrderByWkTblMasterAllByXmlIdAsc(
            String name, String address, List<Integer> listYoushikiKbn, Integer userCode);

    /**
     * 関連者3項目で重複行を抽出する
     *
     * @param name            名称
     * @param address         住所
     * @param key             認識キー
     * @param listYoushikiKbn 様式区分リスト
     * @param userCode        ユーザコード
     * @return 検索結果
     */
    List<WkTblMasterAllByXmlEntity> findByInputSrcNameAndInputSrcAddressAndInputSrcKeyAndYoushikiKbnInAndInsertUserCodeOrderByWkTblMasterAllByXmlIdAsc(
            String name, String address, String key, List<Integer> listYoushikiKbn, Integer userCode);

    /**
     * ワークテーブル登録データから該当関連者かつ最新データを取得する
     *
     * @param userCode     ユーザコード
     * @param kanrenshaKbn 関連者区分
     * @param isLatest     最新該否
     * @return 検索結果
     */
    List<WkTblMasterAllByXmlEntity> findByInsertUserCodeAndKanrenshaKbnNotAndIsLatest(Integer userCode,
            Integer kanrenshaKbn, boolean isLatest);

}
