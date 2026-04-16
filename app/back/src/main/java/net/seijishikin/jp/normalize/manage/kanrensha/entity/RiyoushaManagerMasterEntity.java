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
 * riyousha_manager_master接続用Entity
 */
@Entity
@Table(name = "riyousha_manager_master")
public class RiyoushaManagerMasterEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "riyousha_manager_master_id")
    private Integer riyoushaManagerMasterId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getRiyoushaManagerMasterId() {
        return riyoushaManagerMasterId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param riyoushaManagerMasterId テーブルId
     */
    public void setRiyoushaManagerMasterId(final Integer riyoushaManagerMasterId) {
        this.riyoushaManagerMasterId = riyoushaManagerMasterId;
    }

    /** 利用者運営者コード */
    @Column(name = "riyousha_manager_master_code")
    private Integer riyoushaManagerMasterCode = INIT_INTEGER;

    /**
     * 利用者運営者コードを取得する
     *
     * @return 利用者運営者コード
     */
    public Integer getRiyoushaManagerMasterCode() {
        return riyoushaManagerMasterCode;
    }

    /**
     * 利用者運営者コードを設定する
     *
     * @param riyoushaManagerMasterCode 利用者運営者コード
     */
    public void setRiyoushaManagerMasterCode(final Integer riyoushaManagerMasterCode) {
        this.riyoushaManagerMasterCode = riyoushaManagerMasterCode;
    }

    /** 利用者個人属性id */
    @Column(name = "riyousha_person_property_id")
    private Integer riyoushaPersonPropertyId = INIT_INTEGER;

    /**
     * 利用者個人属性idを取得する
     *
     * @return 利用者個人属性id
     */
    public Integer getRiyoushaPersonPropertyId() {
        return riyoushaPersonPropertyId;
    }

    /**
     * 利用者個人属性idを設定する
     *
     * @param riyoushaPersonPropertyId 利用者個人属性id
     */
    public void setRiyoushaPersonPropertyId(final Integer riyoushaPersonPropertyId) {
        this.riyoushaPersonPropertyId = riyoushaPersonPropertyId;
    }

    /** 利用者個人属性コード */
    @Column(name = "riyousha_person_property_code")
    private Integer riyoushaPersonPropertyCode = INIT_INTEGER;

    /**
     * 利用者個人属性コードを取得する
     *
     * @return 利用者個人属性コード
     */
    public Integer getRiyoushaPersonPropertyCode() {
        return riyoushaPersonPropertyCode;
    }

    /**
     * 利用者個人属性コードを設定する
     *
     * @param riyoushaPersonPropertyCode 利用者個人属性コード
     */
    public void setRiyoushaPersonPropertyCode(final Integer riyoushaPersonPropertyCode) {
        this.riyoushaPersonPropertyCode = riyoushaPersonPropertyCode;
    }

    /** 最新フラグ */
    @Column(name = "is_latest")
    private Boolean isLatest = INIT_BOOLEAN;

    /**
     * 最新フラグを取得する
     *
     * @return 最新フラグ
     */
    @Override
    public Boolean getIsLatest() {
        return isLatest;
    }

    /**
     * 最新フラグを設定する
     *
     * @param isLatest 最新フラグ
     */
    @Override
    public void setIsLatest(final Boolean isLatest) {
        this.isLatest = isLatest;
    }

    /** 姓名 */
    @Column(name = "all_name")
    private String allName = INIT_STRING;

    /**
     * 姓名を取得する
     *
     * @return 姓名
     */
    public String getAllName() {
        return allName;
    }

    /**
     * 姓名を設定する
     *
     * @param allName 姓名
     */
    public void setAllName(final String allName) {
        this.allName = allName;
    }

    /** 姓名かな */
    @Column(name = "all_name_kana")
    private String allNameKana = INIT_STRING;

    /**
     * 姓名かなを取得する
     *
     * @return 姓名かな
     */
    public String getAllNameKana() {
        return allNameKana;
    }

    /**
     * 姓名かなを設定する
     *
     * @param allNameKana 姓名かな
     */
    public void setAllNameKana(final String allNameKana) {
        this.allNameKana = allNameKana;
    }

    /** 住所全体 */
    @Column(name = "address_all")
    private String addressAll = INIT_STRING;

    /**
     * 住所全体を取得する
     *
     * @return 住所全体
     */
    public String getAddressAll() {
        return addressAll;
    }

    /**
     * 住所全体を設定する
     *
     * @param addressAll 住所全体
     */
    public void setAddressAll(final String addressAll) {
        this.addressAll = addressAll;
    }

    /** 全文検索用カラム */
    @Column(name = "search_text")
    private String searchText = INIT_STRING;

    /**
     * 全文検索用カラムを取得する
     *
     * @return 全文検索用カラム
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * 全文検索用カラムを設定する
     *
     * @param searchText 全文検索用カラム
     */
    public void setSearchText(final String searchText) {
        this.searchText = searchText;
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
