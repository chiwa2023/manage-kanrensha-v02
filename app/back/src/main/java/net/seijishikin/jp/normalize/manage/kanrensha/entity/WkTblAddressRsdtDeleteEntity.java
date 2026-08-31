package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import net.seijishikin.jp.normalize.common_tool.entity.AllTabeDataHistoryInterface;

/**
 * wk_tbl_address_rsdt_delete接続用Entity
 */
@Entity
@Table(name = "wk_tbl_address_rsdt_delete")
public class WkTblAddressRsdtDeleteEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wk_tbl_address_rsdt_delete_id")
    private Integer wkTblAddressRsdtDeleteId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getWkTblAddressRsdtDeleteId() {
        return wkTblAddressRsdtDeleteId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param wkTblAddressRsdtDeleteId テーブルId
     */
    public void setWkTblAddressRsdtDeleteId(final Integer wkTblAddressRsdtDeleteId) {
        this.wkTblAddressRsdtDeleteId = wkTblAddressRsdtDeleteId;
    }

    /** 住居Id */
    @Column(name = "address_rsdt_id")
    private Integer addressRsdtId = INIT_INTEGER;

    /**
     * 住居Idを取得する
     *
     * @return 住居Id
     */
    public Integer getAddressRsdtId() {
        return addressRsdtId;
    }

    /**
     * 住居Idを設定する
     *
     * @param addressRsdtId 住居Id
     */
    public void setAddressRsdtId(final Integer addressRsdtId) {
        this.addressRsdtId = addressRsdtId;
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

    /** 街区住所 */
    @Column(name = "address_block")
    private String addressBlock = INIT_STRING;

    /**
     * 街区住所を取得する
     *
     * @return 街区住所
     */
    public String getAddressBlock() {
        return addressBlock;
    }

    /**
     * 街区住所を設定する
     *
     * @param addressBlock 街区住所
     */
    public void setAddressBlock(final String addressBlock) {
        this.addressBlock = addressBlock;
    }

    /** 住所建物 */
    @Column(name = "address_building")
    private String addressBuilding = INIT_STRING;

    /**
     * 住所建物を取得する
     *
     * @return 住所建物
     */
    public String getAddressBuilding() {
        return addressBuilding;
    }

    /**
     * 住所建物を設定する
     *
     * @param addressBuilding 住所建物
     */
    public void setAddressBuilding(final String addressBuilding) {
        this.addressBuilding = addressBuilding;
    }

    /** 町字Id */
    @Column(name = "machiaza_id")
    private String machiazaId = INIT_STRING;

    /**
     * 町字Idを取得する
     *
     * @return 町字Id
     */
    public String getMachiazaId() {
        return machiazaId;
    }

    /**
     * 町字Idを設定する
     *
     * @param machiazaId 町字Id
     */
    public void setMachiazaId(final String machiazaId) {
        this.machiazaId = machiazaId;
    }

    /** 街区Id */
    @Column(name = "blk_id")
    private String blkId = INIT_STRING;

    /**
     * 街区Idを取得する
     *
     * @return 街区Id
     */
    public String getBlkId() {
        return blkId;
    }

    /**
     * 街区Idを設定する
     *
     * @param blkId 街区Id
     */
    public void setBlkId(final String blkId) {
        this.blkId = blkId;
    }

    /** 地番Id */
    @Column(name = "prc_id")
    private String prcId = INIT_STRING;

    /**
     * 地番Idを取得する
     *
     * @return 地番Id
     */
    public String getPrcId() {
        return prcId;
    }

    /**
     * 地番Idを設定する
     *
     * @param prcId 地番Id
     */
    public void setPrcId(final String prcId) {
        this.prcId = prcId;
    }

    /** 住居Id */
    @Column(name = "rsdt_id")
    private String rsdtId = INIT_STRING;

    /**
     * 住居Idを取得する
     *
     * @return 住居Id
     */
    public String getRsdtId() {
        return rsdtId;
    }

    /**
     * 住居Idを設定する
     *
     * @param rsdtId 住居Id
     */
    public void setRsdtId(final String rsdtId) {
        this.rsdtId = rsdtId;
    }

    /** 住居2Id */
    @Column(name = "rsdt2_id")
    private String rsdt2Id = INIT_STRING;

    /**
     * 住居2Idを取得する
     *
     * @return 住居2Id
     */
    public String getRsdt2Id() {
        return rsdt2Id;
    }

    /**
     * 住居2Idを設定する
     *
     * @param rsdt2Id 住居2Id
     */
    public void setRsdt2Id(final String rsdt2Id) {
        this.rsdt2Id = rsdt2Id;
    }

    /** 適用開始日 */
    @Column(name = "effect_date")
    private LocalDate effectDate = INIT_DATE;

    /**
     * 適用開始日を取得する
     *
     * @return 適用開始日
     */
    public LocalDate getEffectDate() {
        return effectDate;
    }

    /**
     * 適用開始日を設定する
     *
     * @param effectDate 適用開始日
     */
    public void setEffectDate(final LocalDate effectDate) {
        this.effectDate = effectDate;
    }

    /** 廃止日 */
    @Column(name = "abolish_date")
    private LocalDate abolishDate = INIT_DATE;

    /**
     * 廃止日を取得する
     *
     * @return 廃止日
     */
    public LocalDate getAbolishDate() {
        return abolishDate;
    }

    /**
     * 廃止日を設定する
     *
     * @param abolishDate 廃止日
     */
    public void setAbolishDate(final LocalDate abolishDate) {
        this.abolishDate = abolishDate;
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
