package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import net.seijishikin.jp.normalize.common_tool.entity.AllTabeDataHistoryInterface;

/**
 * wk_tbl_master_all_by_xml接続用Entity
 */
@Entity
@Table(name = "wk_tbl_master_all_by_xml")
public class WkTblMasterAllByXmlEntity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wk_tbl_master_all_by_xml_id")
    private Integer wkTblMasterAllByXmlId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getWkTblMasterAllByXmlId() {
        return wkTblMasterAllByXmlId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param wkTblMasterAllByXmlId テーブルId
     */
    public void setWkTblMasterAllByXmlId(final Integer wkTblMasterAllByXmlId) {
        this.wkTblMasterAllByXmlId = wkTblMasterAllByXmlId;
    }

    /** 紐づけコード */
    @Column(name = "wk_tbl_master_all_by_xml_code")
    private Integer wkTblMasterAllByXmlCode = INIT_INTEGER;

    /**
     * 紐づけコードを取得する
     *
     * @return 紐づけコード
     */
    public Integer getWkTblMasterAllByXmlCode() {
        return wkTblMasterAllByXmlCode;
    }

    /**
     * 紐づけコードを設定する
     *
     * @param wkTblMasterAllByXmlCode 紐づけコード
     */
    public void setWkTblMasterAllByXmlCode(final Integer wkTblMasterAllByXmlCode) {
        this.wkTblMasterAllByXmlCode = wkTblMasterAllByXmlCode;
    }

    /** 最新該否 */
    @Column(name = "is_latest")
    private Boolean isLatest = INIT_BOOLEAN;

    /**
     * 最新該否を取得する
     *
     * @return 最新該否
     */
    @Override
    public Boolean getIsLatest() {
        return isLatest;
    }

    /**
     * 最新該否を設定する
     *
     * @param isLatest 最新該否
     */
    @Override
    public void setIsLatest(final Boolean isLatest) {
        this.isLatest = isLatest;
    }

    /** 処理完了該否 */
    @Column(name = "is_finish")
    private Boolean isFinish = INIT_BOOLEAN;

    /**
     * 処理完了該否を取得する
     *
     * @return 処理完了該否
     */
    public Boolean getIsFinish() {
        return isFinish;
    }

    /**
     * 処理完了該否を設定する
     *
     * @param isFinish 処理完了該否
     */
    public void setIsFinish(final Boolean isFinish) {
        this.isFinish = isFinish;
    }

    /** 関連者区分 */
    @Column(name = "kanrensha_kbn")
    private Short kanrenshaKbn = INIT_SHORT;

    /**
     * 関連者区分を取得する
     *
     * @return 関連者区分
     */
    public Short getKanrenshaKbn() {
        return kanrenshaKbn;
    }

    /**
     * 関連者区分を設定する
     *
     * @param kanrenshaKbn 関連者区分
     */
    public void setKanrenshaKbn(final Short kanrenshaKbn) {
        this.kanrenshaKbn = kanrenshaKbn;
    }

    /** 備考 */
    @Column(name = "bikou")
    private String bikou = INIT_STRING;

    /**
     * 備考を取得する
     *
     * @return 備考
     */
    public String getBikou() {
        return bikou;
    }

    /**
     * 備考を設定する
     *
     * @param bikou 備考
     */
    public void setBikou(final String bikou) {
        this.bikou = bikou;
    }

    /** 関連者名称 */
    @Column(name = "kanrensha_name")
    private String kanrenshaName = INIT_STRING;

    /**
     * 関連者名称を取得する
     *
     * @return 関連者名称
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 関連者名称を設定する
     *
     * @param kanrenshaName 関連者名称
     */
    public void setKanrenshaName(final String kanrenshaName) {
        this.kanrenshaName = kanrenshaName;
    }

    /** 全住所 */
    @Column(name = "all_address")
    private String allAddress = INIT_STRING;

    /**
     * 全住所を取得する
     *
     * @return 全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 全住所を設定する
     *
     * @param allAddress 全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 団体代表者 */
    @Column(name = "org_delegate")
    private String orgDelegate = INIT_STRING;

    /**
     * 団体代表者を取得する
     *
     * @return 団体代表者
     */
    public String getOrgDelegate() {
        return orgDelegate;
    }

    /**
     * 団体代表者を設定する
     *
     * @param orgDelegate 団体代表者
     */
    public void setOrgDelegate(final String orgDelegate) {
        this.orgDelegate = orgDelegate;
    }

    /** 個人職業 */
    @Column(name = "person_shokugyou")
    private String personShokugyou = INIT_STRING;

    /**
     * 個人職業を取得する
     *
     * @return 個人職業
     */
    public String getPersonShokugyou() {
        return personShokugyou;
    }

    /**
     * 個人職業を設定する
     *
     * @param personShokugyou 個人職業
     */
    public void setPersonShokugyou(final String personShokugyou) {
        this.personShokugyou = personShokugyou;
    }

    /** 法人番号 */
    @Column(name = "houjin_no")
    private String houjinNo = INIT_STRING;

    /**
     * 法人番号を取得する
     *
     * @return 法人番号
     */
    public String getHoujinNo() {
        return houjinNo;
    }

    /**
     * 法人番号を設定する
     *
     * @param houjinNo 法人番号
     */
    public void setHoujinNo(final String houjinNo) {
        this.houjinNo = houjinNo;
    }

    /** 団体区分 */
    @Column(name = "dantai_kbn")
    private String dantaiKbn = INIT_STRING;

    /**
     * 団体区分を取得する
     *
     * @return 団体区分
     */
    public String getDantaiKbn() {
        return dantaiKbn;
    }

    /**
     * 団体区分を設定する
     *
     * @param dantaiKbn 団体区分
     */
    public void setDantaiKbn(final String dantaiKbn) {
        this.dantaiKbn = dantaiKbn;
    }

    /** 政治団体番号 */
    @Column(name = "poli_org_no")
    private String poliOrgNo = INIT_STRING;

    /**
     * 政治団体番号を取得する
     *
     * @return 政治団体番号
     */
    public String getPoliOrgNo() {
        return poliOrgNo;
    }

    /**
     * 政治団体番号を設定する
     *
     * @param poliOrgNo 政治団体番号
     */
    public void setPoliOrgNo(final String poliOrgNo) {
        this.poliOrgNo = poliOrgNo;
    }

    /** 様式区分 */
    @Column(name = "youshiki_kbn")
    private Short youshikiKbn = INIT_SHORT;

    /**
     * 様式区分を取得する
     *
     * @return 様式区分
     */
    public Short getYoushikiKbn() {
        return youshikiKbn;
    }

    /**
     * 様式区分を設定する
     *
     * @param youshikiKbn 様式区分
     */
    public void setYoushikiKbn(final Short youshikiKbn) {
        this.youshikiKbn = youshikiKbn;
    }

    /** 様式枝区分項目 */
    @Column(name = "youshiki_eda_kbn")
    private Short youshikiEdaKbn = INIT_SHORT;

    /**
     * 様式枝区分項目を取得する
     *
     * @return 様式枝区分項目
     */
    public Short getYoushikiEdaKbn() {
        return youshikiEdaKbn;
    }

    /**
     * 様式枝区分項目を設定する
     *
     * @param youshikiEdaKbn 様式枝区分項目
     */
    public void setYoushikiEdaKbn(final Short youshikiEdaKbn) {
        this.youshikiEdaKbn = youshikiEdaKbn;
    }

    /** 複写元名称 */
    @Column(name = "input_src_name")
    private String inputSrcName = INIT_STRING;

    /**
     * 複写元名称を取得する
     *
     * @return 複写元名称
     */
    public String getInputSrcName() {
        return inputSrcName;
    }

    /**
     * 複写元名称を設定する
     *
     * @param inputSrcName 複写元名称
     */
    public void setInputSrcName(final String inputSrcName) {
        this.inputSrcName = inputSrcName;
    }

    /** 複写元住所 */
    @Column(name = "input_src_address")
    private String inputSrcAddress = INIT_STRING;

    /**
     * 複写元住所を取得する
     *
     * @return 複写元住所
     */
    public String getInputSrcAddress() {
        return inputSrcAddress;
    }

    /**
     * 複写元住所を設定する
     *
     * @param inputSrcAddress 複写元住所
     */
    public void setInputSrcAddress(final String inputSrcAddress) {
        this.inputSrcAddress = inputSrcAddress;
    }

    /** 複写元認識キー */
    @Column(name = "input_src_key")
    private String inputSrcKey = INIT_STRING;

    /**
     * 複写元認識キーを取得する
     *
     * @return 複写元認識キー
     */
    public String getInputSrcKey() {
        return inputSrcKey;
    }

    /**
     * 複写元認識キーを設定する
     *
     * @param inputSrcKey 複写元認識キー
     */
    public void setInputSrcKey(final String inputSrcKey) {
        this.inputSrcKey = inputSrcKey;
    }

    /** 反映有無 */
    @Column(name = "is_affected")
    private Boolean isAffected = INIT_BOOLEAN;

    /**
     * 反映有無を取得する
     *
     * @return 反映有無
     */
    public Boolean getIsAffected() {
        return isAffected;
    }

    /**
     * 反映有無を設定する
     *
     * @param isAffected 反映有無
     */
    public void setIsAffected(final Boolean isAffected) {
        this.isAffected = isAffected;
    }

    /** 編集対象該否 */
    @Column(name = "is_disabled")
    private Boolean isDisabled = INIT_BOOLEAN;

    /**
     * 編集対象該否を取得する
     *
     * @return 編集対象該否
     */
    public Boolean getIsDisabled() {
        return isDisabled;
    }

    /**
     * 編集対象該否を設定する
     *
     * @param isDisabled 編集対象該否
     */
    public void setIsDisabled(final Boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    /** 判定理由 */
    @Column(name = "judge_reason")
    private String judgeReason = INIT_STRING;

    /**
     * 判定理由を取得する
     *
     * @return 判定理由
     */
    public String getJudgeReason() {
        return judgeReason;
    }

    /**
     * 判定理由を設定する
     *
     * @param judgeReason 判定理由
     */
    public void setJudgeReason(final String judgeReason) {
        this.judgeReason = judgeReason;
    }

    /** 挿入ユーザId */
    @Column(name = "insert_user_id")
    private Integer insertUserId = INIT_INTEGER;

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    @Override
    public Integer getInsertUserId() {
        return insertUserId;
    }

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
    @Override
    public void setInsertUserId(final Integer insertUserId) {
        this.insertUserId = insertUserId;
    }

    /** 挿入ユーザコード */
    @Column(name = "insert_user_code")
    private Integer insertUserCode = INIT_INTEGER;

    /**
     * 挿入ユーザコードを取得する
     *
     * @return 挿入ユーザコード
     */
    @Override
    public Integer getInsertUserCode() {
        return insertUserCode;
    }

    /**
     * 挿入ユーザコードを設定する
     *
     * @param insertUserCode 挿入ユーザコード
     */
    @Override
    public void setInsertUserCode(final Integer insertUserCode) {
        this.insertUserCode = insertUserCode;
    }

    /** 挿入ユーザ名称 */
    @Column(name = "insert_user_name")
    private String insertUserName = INIT_STRING;

    /**
     * 挿入ユーザ名称を取得する
     *
     * @return 挿入ユーザ名称
     */
    @Override
    public String getInsertUserName() {
        return insertUserName;
    }

    /**
     * 挿入ユーザ名称を設定する
     *
     * @param insertUserName 挿入ユーザ名称
     */
    @Override
    public void setInsertUserName(final String insertUserName) {
        this.insertUserName = insertUserName;
    }

    /** 挿入日時 */
    @Column(name = "insert_timestamp")
    private LocalDateTime insertTimestamp = INIT_TIMESTAMP;

    /**
     * 挿入日時を取得する
     *
     * @return 挿入日時
     */
    @Override
    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

    /**
     * 挿入日時を設定する
     *
     * @param insertTimestamp 挿入日時
     */
    @Override
    public void setInsertTimestamp(final LocalDateTime insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    /** 無効ユーザId */
    @Column(name = "delete_user_id")
    private Integer deleteUserId = INIT_INTEGER;

    /**
     * 無効ユーザIdを取得する
     *
     * @return 無効ユーザId
     */
    @Override
    public Integer getDeleteUserId() {
        return deleteUserId;
    }

    /**
     * 無効ユーザIdを設定する
     *
     * @param deleteUserId 無効ユーザId
     */
    @Override
    public void setDeleteUserId(final Integer deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    /** 無効ユーザコード */
    @Column(name = "delete_user_code")
    private Integer deleteUserCode = INIT_INTEGER;

    /**
     * 無効ユーザコードを取得する
     *
     * @return 無効ユーザコード
     */
    @Override
    public Integer getDeleteUserCode() {
        return deleteUserCode;
    }

    /**
     * 無効ユーザコードを設定する
     *
     * @param deleteUserCode 無効ユーザコード
     */
    @Override
    public void setDeleteUserCode(final Integer deleteUserCode) {
        this.deleteUserCode = deleteUserCode;
    }

    /** 無効ユーザ名称 */
    @Column(name = "delete_user_name")
    private String deleteUserName = INIT_STRING;

    /**
     * 無効ユーザ名称を取得する
     *
     * @return 無効ユーザ名称
     */
    @Override
    public String getDeleteUserName() {
        return deleteUserName;
    }

    /**
     * 無効ユーザ名称を設定する
     *
     * @param deleteUserName 無効ユーザ名称
     */
    @Override
    public void setDeleteUserName(final String deleteUserName) {
        this.deleteUserName = deleteUserName;
    }

    /** 無効日時 */
    @Column(name = "delete_timestamp")
    private LocalDateTime deleteTimestamp = INIT_TIMESTAMP;

    /**
     * 無効日時を取得する
     *
     * @return 無効日時
     */
    @Override
    public LocalDateTime getDeleteTimestamp() {
        return deleteTimestamp;
    }

    /**
     * 無効日時を設定する
     *
     * @param deleteTimestamp 無効日時
     */
    @Override
    public void setDeleteTimestamp(final LocalDateTime deleteTimestamp) {
        this.deleteTimestamp = deleteTimestamp;
    }

}
