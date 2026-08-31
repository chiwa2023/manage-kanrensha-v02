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
 * wk_tbl_postal_edit接続用Entity
 */
@Entity
@Table(name = "wk_tbl_postal_edit")
public class WkTblPostalEditEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wk_tbl_postal_edit_id")
    private Integer wkTblPostalEditId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getWkTblPostalEditId() {
        return wkTblPostalEditId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param wkTblPostalEditId テーブルId
     */
    public void setWkTblPostalEditId(final Integer wkTblPostalEditId) {
        this.wkTblPostalEditId = wkTblPostalEditId;
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

    /** 編集フラグ */
    @Column(name = "flg_edit")
    private String flgEdit = INIT_STRING;

    /**
     * 編集フラグを取得する
     *
     * @return 編集フラグ
     */
    public String getFlgEdit() {
        return flgEdit;
    }

    /**
     * 編集フラグを設定する
     *
     * @param flgEdit 編集フラグ
     */
    public void setFlgEdit(final String flgEdit) {
        this.flgEdit = flgEdit;
    }

    /** 修正可否 */
    @Column(name = "is_repair")
    private Boolean isRepair = INIT_BOOLEAN;

    /**
     * 修正可否を取得する
     *
     * @return 修正可否
     */
    public Boolean getIsRepair() {
        return isRepair;
    }

    /**
     * 修正可否を設定する
     *
     * @param isRepair 修正可否
     */
    public void setIsRepair(final Boolean isRepair) {
        this.isRepair = isRepair;
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

    /** 郵便番号5桁 */
    @Column(name = "postalcode5")
    private String postalcode5 = INIT_STRING;

    /**
     * 郵便番号5桁を取得する
     *
     * @return 郵便番号5桁
     */
    public String getPostalcode5() {
        return postalcode5;
    }

    /**
     * 郵便番号5桁を設定する
     *
     * @param postalcode5 郵便番号5桁
     */
    public void setPostalcode5(final String postalcode5) {
        this.postalcode5 = postalcode5;
    }

    /** 郵便番号7桁 */
    @Column(name = "postalcode7")
    private String postalcode7 = INIT_STRING;

    /**
     * 郵便番号7桁を取得する
     *
     * @return 郵便番号7桁
     */
    public String getPostalcode7() {
        return postalcode7;
    }

    /**
     * 郵便番号7桁を設定する
     *
     * @param postalcode7 郵便番号7桁
     */
    public void setPostalcode7(final String postalcode7) {
        this.postalcode7 = postalcode7;
    }

    /** 県名カナ */
    @Column(name = "pref_name_kana")
    private String prefNameKana = INIT_STRING;

    /**
     * 県名カナを取得する
     *
     * @return 県名カナ
     */
    public String getPrefNameKana() {
        return prefNameKana;
    }

    /**
     * 県名カナを設定する
     *
     * @param prefNameKana 県名カナ
     */
    public void setPrefNameKana(final String prefNameKana) {
        this.prefNameKana = prefNameKana;
    }

    /** 市区町名カナ */
    @Column(name = "city_name_kana")
    private String cityNameKana = INIT_STRING;

    /**
     * 市区町名カナを取得する
     *
     * @return 市区町名カナ
     */
    public String getCityNameKana() {
        return cityNameKana;
    }

    /**
     * 市区町名カナを設定する
     *
     * @param cityNameKana 市区町名カナ
     */
    public void setCityNameKana(final String cityNameKana) {
        this.cityNameKana = cityNameKana;
    }

    /** 原文書名カナ */
    @Column(name = "org_name_kana")
    private String orgNameKana = INIT_STRING;

    /**
     * 原文書名カナを取得する
     *
     * @return 原文書名カナ
     */
    public String getOrgNameKana() {
        return orgNameKana;
    }

    /**
     * 原文書名カナを設定する
     *
     * @param orgNameKana 原文書名カナ
     */
    public void setOrgNameKana(final String orgNameKana) {
        this.orgNameKana = orgNameKana;
    }

    /** 県名 */
    @Column(name = "pref_name")
    private String prefName = INIT_STRING;

    /**
     * 県名を取得する
     *
     * @return 県名
     */
    public String getPrefName() {
        return prefName;
    }

    /**
     * 県名を設定する
     *
     * @param prefName 県名
     */
    public void setPrefName(final String prefName) {
        this.prefName = prefName;
    }

    /** 市区町名 */
    @Column(name = "city_name")
    private String cityName = INIT_STRING;

    /**
     * 市区町名を取得する
     *
     * @return 市区町名
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 市区町名を設定する
     *
     * @param cityName 市区町名
     */
    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    /** 原文書名 */
    @Column(name = "org_name")
    private String orgName = INIT_STRING;

    /**
     * 原文書名を取得する
     *
     * @return 原文書名
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 原文書名を設定する
     *
     * @param orgName 原文書名
     */
    public void setOrgName(final String orgName) {
        this.orgName = orgName;
    }

    /** 属性1フラグ */
    @Column(name = "flg_prop1")
    private String flgProp1 = INIT_STRING;

    /**
     * 属性1フラグを取得する
     *
     * @return 属性1フラグ
     */
    public String getFlgProp1() {
        return flgProp1;
    }

    /**
     * 属性1フラグを設定する
     *
     * @param flgProp1 属性1フラグ
     */
    public void setFlgProp1(final String flgProp1) {
        this.flgProp1 = flgProp1;
    }

    /** 属性2フラグ */
    @Column(name = "flg_prop2")
    private String flgProp2 = INIT_STRING;

    /**
     * 属性2フラグを取得する
     *
     * @return 属性2フラグ
     */
    public String getFlgProp2() {
        return flgProp2;
    }

    /**
     * 属性2フラグを設定する
     *
     * @param flgProp2 属性2フラグ
     */
    public void setFlgProp2(final String flgProp2) {
        this.flgProp2 = flgProp2;
    }

    /** 属性3フラグ */
    @Column(name = "flg_prop3")
    private String flgProp3 = INIT_STRING;

    /**
     * 属性3フラグを取得する
     *
     * @return 属性3フラグ
     */
    public String getFlgProp3() {
        return flgProp3;
    }

    /**
     * 属性3フラグを設定する
     *
     * @param flgProp3 属性3フラグ
     */
    public void setFlgProp3(final String flgProp3) {
        this.flgProp3 = flgProp3;
    }

    /** 属性4フラグ */
    @Column(name = "flg_prop4")
    private String flgProp4 = INIT_STRING;

    /**
     * 属性4フラグを取得する
     *
     * @return 属性4フラグ
     */
    public String getFlgProp4() {
        return flgProp4;
    }

    /**
     * 属性4フラグを設定する
     *
     * @param flgProp4 属性4フラグ
     */
    public void setFlgProp4(final String flgProp4) {
        this.flgProp4 = flgProp4;
    }

    /** 更新表示フラグ */
    @Column(name = "flg_koushin")
    private String flgKoushin = INIT_STRING;

    /**
     * 更新表示フラグを取得する
     *
     * @return 更新表示フラグ
     */
    public String getFlgKoushin() {
        return flgKoushin;
    }

    /**
     * 更新表示フラグを設定する
     *
     * @param flgKoushin 更新表示フラグ
     */
    public void setFlgKoushin(final String flgKoushin) {
        this.flgKoushin = flgKoushin;
    }

    /** 変更理由フラグ */
    @Column(name = "flg_henkou_riyu")
    private String flgHenkouRiyu = INIT_STRING;

    /**
     * 変更理由フラグを取得する
     *
     * @return 変更理由フラグ
     */
    public String getFlgHenkouRiyu() {
        return flgHenkouRiyu;
    }

    /**
     * 変更理由フラグを設定する
     *
     * @param flgHenkouRiyu 変更理由フラグ
     */
    public void setFlgHenkouRiyu(final String flgHenkouRiyu) {
        this.flgHenkouRiyu = flgHenkouRiyu;
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

    /** 編集内容説明 */
    @Column(name = "works_text")
    private String worksText = INIT_STRING;

    /**
     * 編集内容説明を取得する
     *
     * @return 編集内容説明
     */
    public String getWorksText() {
        return worksText;
    }

    /**
     * 編集内容説明を設定する
     *
     * @param worksText 編集内容説明
     */
    public void setWorksText(final String worksText) {
        this.worksText = worksText;
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
