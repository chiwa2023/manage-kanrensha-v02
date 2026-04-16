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
 * wk_tbl_postal_common接続用Entity
 */
@Entity
@Table(name = "wk_tbl_postal_common")
public class WkTblPostalCommonEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wk_tbl_postal_common_id")
    private Integer wkTblPostalCommonId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getWkTblPostalCommonId() {
        return wkTblPostalCommonId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param wkTblPostalCommonId テーブルId
     */
    public void setWkTblPostalCommonId(final Integer wkTblPostalCommonId) {
        this.wkTblPostalCommonId = wkTblPostalCommonId;
    }

    /** 正規テーブルId */
    @Column(name = "address_postal_id")
    private Integer addressPostalId = INIT_INTEGER;

    /**
     * 正規テーブルIdを取得する
     *
     * @return 正規テーブルId
     */
    public Integer getAddressPostalId() {
        return addressPostalId;
    }

    /**
     * 正規テーブルIdを設定する
     *
     * @param addressPostalId 正規テーブルId
     */
    public void setAddressPostalId(final Integer addressPostalId) {
        this.addressPostalId = addressPostalId;
    }

    /** 不規則Id */
    @Column(name = "address_postal_irregular_id")
    private Integer addressPostalIrregularId = INIT_INTEGER;

    /**
     * 不規則Idを取得する
     *
     * @return 不規則Id
     */
    public Integer getAddressPostalIrregularId() {
        return addressPostalIrregularId;
    }

    /**
     * 不規則Idを設定する
     *
     * @param addressPostalIrregularId 不規則Id
     */
    public void setAddressPostalIrregularId(final Integer addressPostalIrregularId) {
        this.addressPostalIrregularId = addressPostalIrregularId;
    }

    /** 修復ログId */
    @Column(name = "address_postal_repair_log_id")
    private Integer addressPostalRepairLogId = INIT_INTEGER;

    /**
     * 修復ログIdを取得する
     *
     * @return 修復ログId
     */
    public Integer getAddressPostalRepairLogId() {
        return addressPostalRepairLogId;
    }

    /**
     * 修復ログIdを設定する
     *
     * @param addressPostalRepairLogId 修復ログId
     */
    public void setAddressPostalRepairLogId(final Integer addressPostalRepairLogId) {
        this.addressPostalRepairLogId = addressPostalRepairLogId;
    }

    /** 郵便番号1 */
    @Column(name = "postalcode1")
    private String postalcode1 = INIT_STRING;

    /**
     * 郵便番号1を取得する
     *
     * @return 郵便番号1
     */
    public String getPostalcode1() {
        return postalcode1;
    }

    /**
     * 郵便番号1を設定する
     *
     * @param postalcode1 郵便番号1
     */
    public void setPostalcode1(final String postalcode1) {
        this.postalcode1 = postalcode1;
    }

    /** 郵便番号2 */
    @Column(name = "postalcode2")
    private String postalcode2 = INIT_STRING;

    /**
     * 郵便番号2を取得する
     *
     * @return 郵便番号2
     */
    public String getPostalcode2() {
        return postalcode2;
    }

    /**
     * 郵便番号2を設定する
     *
     * @param postalcode2 郵便番号2
     */
    public void setPostalcode2(final String postalcode2) {
        this.postalcode2 = postalcode2;
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

    /** 地方自治体コード */
    @Column(name = "lg_code")
    private String lgCode = INIT_STRING;

    /**
     * 地方自治体コードを取得する
     *
     * @return 地方自治体コード
     */
    public String getLgCode() {
        return lgCode;
    }

    /**
     * 地方自治体コードを設定する
     *
     * @param lgCode 地方自治体コード
     */
    public void setLgCode(final String lgCode) {
        this.lgCode = lgCode;
    }

    /** 原文書住所 */
    @Column(name = "address_org")
    private String addressOrg = INIT_STRING;

    /**
     * 原文書住所を取得する
     *
     * @return 原文書住所
     */
    public String getAddressOrg() {
        return addressOrg;
    }

    /**
     * 原文書住所を設定する
     *
     * @param addressOrg 原文書住所
     */
    public void setAddressOrg(final String addressOrg) {
        this.addressOrg = addressOrg;
    }

    /** 住所郵便番号まで */
    @Column(name = "address_name")
    private String addressName = INIT_STRING;

    /**
     * 住所郵便番号までを取得する
     *
     * @return 住所郵便番号まで
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * 住所郵便番号までを設定する
     *
     * @param addressName 住所郵便番号まで
     */
    public void setAddressName(final String addressName) {
        this.addressName = addressName;
    }

    /** 行政区データ該否 */
    @Column(name = "is_gyoseiku_data")
    private Boolean isGyoseikuData = INIT_BOOLEAN;

    /**
     * 行政区データ該否を取得する
     *
     * @return 行政区データ該否
     */
    public Boolean getIsGyoseikuData() {
        return isGyoseikuData;
    }

    /**
     * 行政区データ該否を設定する
     *
     * @param isGyoseikuData 行政区データ該否
     */
    public void setIsGyoseikuData(final Boolean isGyoseikuData) {
        this.isGyoseikuData = isGyoseikuData;
    }

    /** 住所郵便番号まで */
    @Column(name = "address_postal")
    private String addressPostal = INIT_STRING;

    /**
     * 住所郵便番号までを取得する
     *
     * @return 住所郵便番号まで
     */
    public String getAddressPostal() {
        return addressPostal;
    }

    /**
     * 住所郵便番号までを設定する
     *
     * @param addressPostal 住所郵便番号まで
     */
    public void setAddressPostal(final String addressPostal) {
        this.addressPostal = addressPostal;
    }

    /** 住所番地まで */
    @Column(name = "address_block")
    private String addressBlock = INIT_STRING;

    /**
     * 住所番地までを取得する
     *
     * @return 住所番地まで
     */
    public String getAddressBlock() {
        return addressBlock;
    }

    /**
     * 住所番地までを設定する
     *
     * @param addressBlock 住所番地まで
     */
    public void setAddressBlock(final String addressBlock) {
        this.addressBlock = addressBlock;
    }

    /** 郵便番号テーブル追加 */
    @Column(name = "is_add_postal")
    private Boolean isAddPostal = INIT_BOOLEAN;

    /**
     * 郵便番号テーブル追加を取得する
     *
     * @return 郵便番号テーブル追加
     */
    public Boolean getIsAddPostal() {
        return isAddPostal;
    }

    /**
     * 郵便番号テーブル追加を設定する
     *
     * @param isAddPostal 郵便番号テーブル追加
     */
    public void setIsAddPostal(final Boolean isAddPostal) {
        this.isAddPostal = isAddPostal;
    }

    /** 住居テーブル修正可否 */
    @Column(name = "is_repair_rsdt")
    private Boolean isRepairRsdt = INIT_BOOLEAN;

    /**
     * 住居テーブル修正可否を取得する
     *
     * @return 住居テーブル修正可否
     */
    public Boolean getIsRepairRsdt() {
        return isRepairRsdt;
    }

    /**
     * 住居テーブル修正可否を設定する
     *
     * @param isRepairRsdt 住居テーブル修正可否
     */
    public void setIsRepairRsdt(final Boolean isRepairRsdt) {
        this.isRepairRsdt = isRepairRsdt;
    }

    /** 状態表示 */
    @Column(name = "status_text")
    private String statusText = INIT_STRING;

    /**
     * 状態表示を取得する
     * 
     * @return 状態表示
     */
    public String getStatusText() {
        return statusText;
    }

    /**
     * 状態表示を設定する
     * 
     * @param statusText 状態表示
     */
    public void setStatusText(final String statusText) {
        this.statusText = statusText;
    }

    /** 修正承認有無 */
    @Column(name = "is_confirm")
    private Boolean isConfirm = INIT_BOOLEAN;

    /**
     * 修正承認有無を取得する
     *
     * @return 修正承認有無
     */
    public Boolean getIsConfirm() {
        return isConfirm;
    }

    /**
     * 修正承認有無を設定する
     *
     * @param isConfirm 修正承認有無
     */
    public void setIsConfirm(final Boolean isConfirm) {
        this.isConfirm = isConfirm;
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
