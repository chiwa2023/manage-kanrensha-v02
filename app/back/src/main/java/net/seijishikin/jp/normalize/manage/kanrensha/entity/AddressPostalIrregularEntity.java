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
 * address_postal_irregular接続用Entity
 */
@Entity
@Table(name = "address_postal_irregular")
public class AddressPostalIrregularEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_postal_irregular_id")
    private Integer addressPostalIrregularId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getAddressPostalIrregularId() {
        return addressPostalIrregularId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param addressPostalIrregularId テーブルId
     */
    public void setAddressPostalIrregularId(final Integer addressPostalIrregularId) {
        this.addressPostalIrregularId = addressPostalIrregularId;
    }

    /** 郵便番号7桁 */
    @Column(name = "postalcode")
    private String postalcode = INIT_STRING;

    /**
     * 郵便番号7桁を取得する
     * 
     * @return 郵便番号7桁
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * 郵便番号7桁を設定する
     * 
     * @param postalcode 郵便番号7桁
     */
    public void setPostalcode(final String postalcode) {
        this.postalcode = postalcode;
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

    /** 表示住所 */
    @Column(name = "address_name")
    private String addressName = INIT_STRING;

    /**
     * 表示住所を取得する
     *
     * @return 表示住所
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * 表示住所を設定する
     *
     * @param addressName 表示住所
     */
    public void setAddressName(final String addressName) {
        this.addressName = addressName;
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
