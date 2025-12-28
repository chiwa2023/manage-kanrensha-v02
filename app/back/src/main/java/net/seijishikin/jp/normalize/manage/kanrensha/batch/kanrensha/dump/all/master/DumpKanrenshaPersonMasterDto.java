package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.master;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Id;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * master_person接続用Entity
 */
public class DumpKanrenshaPersonMasterDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public DumpKanrenshaPersonMasterDto() { // NOPMD TODO 安定した段階でフィールドを引数とするコンストラクタを作成

    }

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_person_master_id")
    private Integer kanrenshaPersonMasterId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaPersonMasterId() {
        return kanrenshaPersonMasterId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaPersonMasterId テーブルId
     */
    public void setKanrenshaPersonMasterId(final Integer kanrenshaPersonMasterId) {
        this.kanrenshaPersonMasterId = kanrenshaPersonMasterId;
    }

    /** 個人関連者コード */
    @Column(name = "person_kanrensha_code")
    private String personKanrenshaCode = INIT_STRING;

    /**
     * 個人関連者コードを取得する
     *
     * @return 個人関連者コード
     */
    public String getPersonKanrenshaCode() {
        return personKanrenshaCode;
    }

    /**
     * 個人関連者コードを設定する
     *
     * @param personKanrenshaCode 個人関連者コード
     */
    public void setPersonKanrenshaCode(final String personKanrenshaCode) {
        this.personKanrenshaCode = personKanrenshaCode;
    }

    /** 最新該否 */
    @Column(name = "is_latest")
    private Boolean isLatest = INIT_BOOLEAN;

    /**
     * 最新該否を取得する
     *
     * @return 最新該否
     */
    public Boolean getIsLatest() {
        return isLatest;
    }

    /**
     * 最新該否を設定する
     *
     * @param isLatest 最新該否
     */
    public void setIsLatest(final Boolean isLatest) {
        this.isLatest = isLatest;
    }

    /** 個人名 */
    @Column(name = "kanrensha_name")
    private String kanrenshaName = INIT_STRING;

    /**
     * 個人名を取得する
     *
     * @return 個人名
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 個人名を設定する
     *
     * @param kanrenshaName 個人名
     */
    public void setKanrenshaName(final String kanrenshaName) {
        this.kanrenshaName = kanrenshaName;
    }

    /** 個人全住所 */
    @Column(name = "all_address")
    private String allAddress = INIT_STRING;

    /**
     * 個人全住所を取得する
     *
     * @return 個人全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 個人全住所を設定する
     *
     * @param allAddress 個人全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
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

    /** 名称比較用 */
    @Column(name = "compare_name_text")
    private String compareNameText = INIT_STRING;

    /**
     * 名称比較用を取得する
     *
     * @return 名称比較用
     */
    public String getCompareNameText() {
        return compareNameText;
    }

    /**
     * 名称比較用を設定する
     *
     * @param compareNameText 名称比較用
     */
    public void setCompareNameText(final String compareNameText) {
        this.compareNameText = compareNameText;
    }

    /** 挿入ユーザId */
    @Column(name = "insert_user_id")
    private Integer insertUserId = INIT_INTEGER;

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    public Integer getInsertUserId() {
        return insertUserId;
    }

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
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
    public Integer getInsertUserCode() {
        return insertUserCode;
    }

    /**
     * 挿入ユーザコードを設定する
     *
     * @param insertUserCode 挿入ユーザコード
     */
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
    public String getInsertUserName() {
        return insertUserName;
    }

    /**
     * 挿入ユーザ名称を設定する
     *
     * @param insertUserName 挿入ユーザ名称
     */
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
    public LocalDateTime getInsertTimestamp() {
        return insertTimestamp;
    }

    /**
     * 挿入日時を設定する
     *
     * @param insertTimestamp 挿入日時
     */
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
    public Integer getDeleteUserId() {
        return deleteUserId;
    }

    /**
     * 無効ユーザIdを設定する
     *
     * @param deleteUserId 無効ユーザId
     */
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
    public Integer getDeleteUserCode() {
        return deleteUserCode;
    }

    /**
     * 無効ユーザコードを設定する
     *
     * @param deleteUserCode 無効ユーザコード
     */
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
    public String getDeleteUserName() {
        return deleteUserName;
    }

    /**
     * 無効ユーザ名称を設定する
     *
     * @param deleteUserName 無効ユーザ名称
     */
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
    public LocalDateTime getDeleteTimestamp() {
        return deleteTimestamp;
    }

    /**
     * 無効日時を設定する
     *
     * @param deleteTimestamp 無効日時
     */
    public void setDeleteTimestamp(final LocalDateTime deleteTimestamp) {
        this.deleteTimestamp = deleteTimestamp;
    }

    /** 電話番号 */
    @Column(name = "phon")
    private String phon = INIT_STRING;

    /**
     * 電話番号を取得する
     *
     * @return 電話番号
     */
    public String getPhon() {
        return phon;
    }

    /**
     * 電話番号を設定する
     *
     * @param phon 電話番号
     */
    public void setPhon(final String phon) {
        this.phon = phon;
    }

    /** 代表(公式)サイトurl */
    @Column(name = "my_portal_url")
    private String myPortalUrl = INIT_STRING;

    /**
     * 代表(公式)サイトurlを取得する
     *
     * @return 代表(公式)サイトurl
     */
    public String getMyPortalUrl() {
        return myPortalUrl;
    }

    /**
     * 代表(公式)サイトurlを設定する
     *
     * @param myPortalUrl 代表(公式)サイトurl
     */
    public void setMyPortalUrl(final String myPortalUrl) {
        this.myPortalUrl = myPortalUrl;
    }

}
