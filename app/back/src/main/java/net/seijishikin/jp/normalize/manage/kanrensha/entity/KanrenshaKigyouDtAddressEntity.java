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
 * kanrensha_kigyou_dt_address接続用Entity
 */
@Entity
@Table(name = "kanrensha_kigyou_dt_address")
public class KanrenshaKigyouDtAddressEntity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_kigyou_dt_address_id")
    private Integer kanrenshaKigyouDtAddressId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaKigyouDtAddressId() {
        return kanrenshaKigyouDtAddressId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaKigyouDtAddressId テーブルId
     */
    public void setKanrenshaKigyouDtAddressId(final Integer kanrenshaKigyouDtAddressId) {
        this.kanrenshaKigyouDtAddressId = kanrenshaKigyouDtAddressId;
    }

    /** 関連者企業・団体Id */
    @Column(name = "kanrensha_kigyou_dt_id")
    private Integer kanrenshaKigyouDtId = INIT_INTEGER;

    /**
     * 関連者企業・団体Idを取得する
     *
     * @return 関連者企業・団体Id
     */
    public Integer getKanrenshaKigyouDtId() {
        return kanrenshaKigyouDtId;
    }

    /**
     * 関連者企業・団体Idを設定する
     *
     * @param kanrenshaKigyouDtId 関連者企業・団体Id
     */
    public void setKanrenshaKigyouDtId(final Integer kanrenshaKigyouDtId) {
        this.kanrenshaKigyouDtId = kanrenshaKigyouDtId;
    }

    /** 関連者企業・団体コード */
    @Column(name = "kigyou_dt_kanrensha_code")
    private String kigyouDtKanrenshaCode = INIT_STRING;

    /**
     * 関連者企業・団体コードを取得する
     *
     * @return 関連者企業・団体コード
     */
    public String getKigyouDtKanrenshaCode() {
        return kigyouDtKanrenshaCode;
    }

    /**
     * 関連者企業・団体コードを設定する
     *
     * @param kigyouDtKanrenshaCode 関連者企業・団体コード
     */
    public void setKigyouDtKanrenshaCode(final String kigyouDtKanrenshaCode) {
        this.kigyouDtKanrenshaCode = kigyouDtKanrenshaCode;
    }

    /** 関連者企業・団体名称 */
    @Column(name = "kanrensha_name")
    private String kanrenshaName = INIT_STRING;

    /**
     * 関連者企業・団体名称を取得する
     *
     * @return 関連者企業・団体名称
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 関連者企業・団体名称を設定する
     *
     * @param kanrenshaName 関連者企業・団体名称
     */
    public void setKanrenshaName(final String kanrenshaName) {
        this.kanrenshaName = kanrenshaName;
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

    /** 住所郵便番号まで */
    @Column(name = "addressPostal")
    private String addresspostal = INIT_STRING;

    /**
     * 住所郵便番号までを取得する
     *
     * @return 住所郵便番号まで
     */
    public String getAddresspostal() {
        return addresspostal;
    }

    /**
     * 住所郵便番号までを設定する
     *
     * @param addresspostal 住所郵便番号まで
     */
    public void setAddresspostal(final String addresspostal) {
        this.addresspostal = addresspostal;
    }

    /** 住所番地 */
    @Column(name = "addressBlock")
    private String addressblock = INIT_STRING;

    /**
     * 住所番地を取得する
     *
     * @return 住所番地
     */
    public String getAddressblock() {
        return addressblock;
    }

    /**
     * 住所番地を設定する
     *
     * @param addressblock 住所番地
     */
    public void setAddressblock(final String addressblock) {
        this.addressblock = addressblock;
    }

    /** 住所建物 */
    @Column(name = "addressBuilding")
    private String addressbuilding = INIT_STRING;

    /**
     * 住所建物を取得する
     *
     * @return 住所建物
     */
    public String getAddressbuilding() {
        return addressbuilding;
    }

    /**
     * 住所建物を設定する
     *
     * @param addressbuilding 住所建物
     */
    public void setAddressbuilding(final String addressbuilding) {
        this.addressbuilding = addressbuilding;
    }

    /** 地方公共団体コード */
    @Column(name = "lgCode")
    private String lgcode = INIT_STRING;

    /**
     * 地方公共団体コードを取得する
     *
     * @return 地方公共団体コード
     */
    public String getLgcode() {
        return lgcode;
    }

    /**
     * 地方公共団体コードを設定する
     *
     * @param lgcode 地方公共団体コード
     */
    public void setLgcode(final String lgcode) {
        this.lgcode = lgcode;
    }

    /** 町字Id */
    @Column(name = "machiazaId")
    private String machiazaid = INIT_STRING;

    /**
     * 町字Idを取得する
     *
     * @return 町字Id
     */
    public String getMachiazaid() {
        return machiazaid;
    }

    /**
     * 町字Idを設定する
     *
     * @param machiazaid 町字Id
     */
    public void setMachiazaid(final String machiazaid) {
        this.machiazaid = machiazaid;
    }

    /** 街区Id */
    @Column(name = "blkId")
    private String blkid = INIT_STRING;

    /**
     * 街区Idを取得する
     *
     * @return 街区Id
     */
    public String getBlkid() {
        return blkid;
    }

    /**
     * 街区Idを設定する
     *
     * @param blkid 街区Id
     */
    public void setBlkid(final String blkid) {
        this.blkid = blkid;
    }

    /** 地番Id */
    @Column(name = "prcId")
    private String prcid = INIT_STRING;

    /**
     * 地番Idを取得する
     *
     * @return 地番Id
     */
    public String getPrcid() {
        return prcid;
    }

    /**
     * 地番Idを設定する
     *
     * @param prcid 地番Id
     */
    public void setPrcid(final String prcid) {
        this.prcid = prcid;
    }

    /** 住居Id */
    @Column(name = "rsdtId")
    private String rsdtid = INIT_STRING;

    /**
     * 住居Idを取得する
     *
     * @return 住居Id
     */
    public String getRsdtid() {
        return rsdtid;
    }

    /**
     * 住居Idを設定する
     *
     * @param rsdtid 住居Id
     */
    public void setRsdtid(final String rsdtid) {
        this.rsdtid = rsdtid;
    }

    /** 住居2Id */
    @Column(name = "rsdt2Id")
    private String rsdt2id = INIT_STRING;

    /**
     * 住居2Idを取得する
     *
     * @return 住居2Id
     */
    public String getRsdt2id() {
        return rsdt2id;
    }

    /**
     * 住居2Idを設定する
     *
     * @param rsdt2id 住居2Id
     */
    public void setRsdt2id(final String rsdt2id) {
        this.rsdt2id = rsdt2id;
    }

    /** 住所郵便番号まで編集有無 */
    @Column(name = "isPostalEdit")
    private Boolean ispostaledit = INIT_BOOLEAN;

    /**
     * 住所郵便番号まで編集有無を取得する
     *
     * @return 住所郵便番号まで編集有無
     */
    public Boolean getIspostaledit() {
        return ispostaledit;
    }

    /**
     * 住所郵便番号まで編集有無を設定する
     *
     * @param ispostaledit 住所郵便番号まで編集有無
     */
    public void setIspostaledit(final Boolean ispostaledit) {
        this.ispostaledit = ispostaledit;
    }

    /** 住所番地編集有無 */
    @Column(name = "isBlockEdit")
    private Boolean isblockedit = INIT_BOOLEAN;

    /**
     * 住所番地編集有無を取得する
     *
     * @return 住所番地編集有無
     */
    public Boolean getIsblockedit() {
        return isblockedit;
    }

    /**
     * 住所番地編集有無を設定する
     *
     * @param isblockedit 住所番地編集有無
     */
    public void setIsblockedit(final Boolean isblockedit) {
        this.isblockedit = isblockedit;
    }

    /** 住所建物編集有無 */
    @Column(name = "isBuildingEdit")
    private Boolean isbuildingedit = INIT_BOOLEAN;

    /**
     * 住所建物編集有無を取得する
     *
     * @return 住所建物編集有無
     */
    public Boolean getIsbuildingedit() {
        return isbuildingedit;
    }

    /**
     * 住所建物編集有無を設定する
     *
     * @param isbuildingedit 住所建物編集有無
     */
    public void setIsbuildingedit(final Boolean isbuildingedit) {
        this.isbuildingedit = isbuildingedit;
    }

    /** 住所郵便番号承認該否 */
    @Column(name = "is_postal_accept")
    private Boolean isPostalAccept = INIT_BOOLEAN;

    /**
     * 住所郵便番号承認該否を取得する
     *
     * @return 住所郵便番号承認該否
     */
    public Boolean getIsPostalAccept() {
        return isPostalAccept;
    }

    /**
     * 住所郵便番号承認該否を設定する
     *
     * @param isPostalAccept 住所郵便番号承認該否
     */
    public void setIsPostalAccept(final Boolean isPostalAccept) {
        this.isPostalAccept = isPostalAccept;
    }

    /** 住所番地承認該否 */
    @Column(name = "is_block_accept")
    private Boolean isBlockAccept = INIT_BOOLEAN;

    /**
     * 住所番地承認該否を取得する
     *
     * @return 住所番地承認該否
     */
    public Boolean getIsBlockAccept() {
        return isBlockAccept;
    }

    /**
     * 住所番地承認該否を設定する
     *
     * @param isBlockAccept 住所番地承認該否
     */
    public void setIsBlockAccept(final Boolean isBlockAccept) {
        this.isBlockAccept = isBlockAccept;
    }

    /** 住所建物承認該否 */
    @Column(name = "is_building_accept")
    private Boolean isBuildingAccept = INIT_BOOLEAN;

    /**
     * 住所建物承認該否を取得する
     *
     * @return 住所建物承認該否
     */
    public Boolean getIsBuildingAccept() {
        return isBuildingAccept;
    }

    /**
     * 住所建物承認該否を設定する
     *
     * @param isBuildingAccept 住所建物承認該否
     */
    public void setIsBuildingAccept(final Boolean isBuildingAccept) {
        this.isBuildingAccept = isBuildingAccept;
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
