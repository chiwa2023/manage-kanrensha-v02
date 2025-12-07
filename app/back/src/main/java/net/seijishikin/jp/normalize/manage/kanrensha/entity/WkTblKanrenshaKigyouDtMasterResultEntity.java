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
 * wk_tbl_kanrensha_kigyou_dt_master_result接続用Entity
 */
@Entity
@Table(name = "wk_tbl_kanrensha_kigyou_dt_master_result")
public class WkTblKanrenshaKigyouDtMasterResultEntity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wk_tbl_kanrensha_kigyou_dt_master_result_id")
    private Integer wkTblKanrenshaKigyouDtMasterResultId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getWkTblKanrenshaKigyouDtMasterResultId() {
        return wkTblKanrenshaKigyouDtMasterResultId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param wkTblKanrenshaKigyouDtMasterResultId テーブルId
     */
    public void setWkTblKanrenshaKigyouDtMasterResultId(final Integer wkTblKanrenshaKigyouDtMasterResultId) {
        this.wkTblKanrenshaKigyouDtMasterResultId = wkTblKanrenshaKigyouDtMasterResultId;
    }

    /** ワークテーブルマスタId */
    @Column(name = "wk_tbl_kanrensha_kigyou_dt_master_id")
    private Integer wkTblKanrenshaKigyouDtMasterId = INIT_INTEGER;

    /**
     * ワークテーブルマスタIdを取得する
     *
     * @return ワークテーブルマスタId
     */
    public Integer getWkTblKanrenshaKigyouDtMasterId() {
        return wkTblKanrenshaKigyouDtMasterId;
    }

    /**
     * ワークテーブルマスタIdを設定する
     *
     * @param wkTblKanrenshaKigyouDtMasterId ワークテーブルマスタId
     */
    public void setWkTblKanrenshaKigyouDtMasterId(final Integer wkTblKanrenshaKigyouDtMasterId) {
        this.wkTblKanrenshaKigyouDtMasterId = wkTblKanrenshaKigyouDtMasterId;
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

}
