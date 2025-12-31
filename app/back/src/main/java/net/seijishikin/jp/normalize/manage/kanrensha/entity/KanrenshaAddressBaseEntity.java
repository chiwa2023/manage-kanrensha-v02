package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * kanrensha_kigyou_dt_address接続用Entity
 */
@Entity
public class KanrenshaAddressBaseEntity implements Serializable, DtoEntityInitialValueInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_address_id")
    private Integer kanrenshaAddressId = INIT_INTEGER;

    /** 関連者個人Id */
    @Column(name = "kanrensha_master_id")
    private Integer kanrenshaMasterId = INIT_INTEGER;

    /** 関連者個人コード */
    @Column(name = "kanrensha_code")
    private String kanrenshaCode = INIT_STRING;

    /** 関連者区分 */
    @Column(name = "kanrensha_kbn")
    private Short kanrenshaKbn = INIT_SHORT;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaAddressId() {
        return kanrenshaAddressId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaAddressId テーブルId
     */
    public void setKanrenshaAddressId(final Integer kanrenshaAddressId) {
        this.kanrenshaAddressId = kanrenshaAddressId;
    }

    /**
     * 関連者個人Idを取得する
     *
     * @return 関連者個人Id
     */
    public Integer getKanrenshaMasterId() {
        return kanrenshaMasterId;
    }

    /**
     * 関連者個人Idを設定する
     *
     * @param kanrenshaMasterId 関連者個人Id
     */
    public void setKanrenshaMasterId(final Integer kanrenshaMasterId) {
        this.kanrenshaMasterId = kanrenshaMasterId;
    }

    /**
     * 関連者個人コードを取得する
     *
     * @return 関連者個人コード
     */
    public String getKanrenshaCode() {
        return kanrenshaCode;
    }

    /**
     * 関連者個人コードを設定する
     *
     * @param kanrenshaCode 関連者個人コード
     */
    public void setKanrenshaCode(final String kanrenshaCode) {
        this.kanrenshaCode = kanrenshaCode;
    }

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

    /** 住所番地 */
    @Column(name = "address_block")
    private String addressBlock = INIT_STRING;

    /**
     * 住所番地を取得する
     *
     * @return 住所番地
     */
    public String getAddressBlock() {
        return addressBlock;
    }

    /**
     * 住所番地を設定する
     *
     * @param addressBlock 住所番地
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

    /** 地方公共団体コード */
    @Column(name = "lg_code")
    private String lgCode = INIT_STRING;

    /**
     * 地方公共団体コードを取得する
     *
     * @return 地方公共団体コード
     */
    public String getLgCode() {
        return lgCode;
    }

    /**
     * 地方公共団体コードを設定する
     *
     * @param lgCode 地方公共団体コード
     */
    public void setLgCode(final String lgCode) {
        this.lgCode = lgCode;
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

    /** 住所郵便番号まで編集有無 */
    @Column(name = "is_postal_edit")
    private Boolean isPostalEdit = INIT_BOOLEAN;

    /**
     * 住所郵便番号まで編集有無を取得する
     *
     * @return 住所郵便番号まで編集有無
     */
    public Boolean getIsPostalEdit() {
        return isPostalEdit;
    }

    /**
     * 住所郵便番号まで編集有無を設定する
     *
     * @param isPostalEdit 住所郵便番号まで編集有無
     */
    public void setIsPostalEdit(final Boolean isPostalEdit) {
        this.isPostalEdit = isPostalEdit;
    }

    /** 住所番地編集有無 */
    @Column(name = "is_block_edit")
    private Boolean isBlockEdit = INIT_BOOLEAN;

    /**
     * 住所番地編集有無を取得する
     *
     * @return 住所番地編集有無
     */
    public Boolean getIsBlockEdit() {
        return isBlockEdit;
    }

    /**
     * 住所番地編集有無を設定する
     *
     * @param isBlockEdit 住所番地編集有無
     */
    public void setIsBlockEdit(final Boolean isBlockEdit) {
        this.isBlockEdit = isBlockEdit;
    }

    /** 住所建物編集有無 */
    @Column(name = "is_building_edit")
    private Boolean isBuildingEdit = INIT_BOOLEAN;

    /**
     * 住所建物編集有無を取得する
     *
     * @return 住所建物編集有無
     */
    public Boolean getIsBuildingEdit() {
        return isBuildingEdit;
    }

    /**
     * 住所建物編集有無を設定する
     *
     * @param isBuildingEdit 住所建物編集有無
     */
    public void setIsBuildingEdit(final Boolean isBuildingEdit) {
        this.isBuildingEdit = isBuildingEdit;
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
}
