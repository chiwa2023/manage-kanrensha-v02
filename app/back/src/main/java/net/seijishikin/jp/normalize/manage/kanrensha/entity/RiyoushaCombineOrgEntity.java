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
 * riyousha_combine_org接続用Entity
 */
@Entity
@Table(name = "riyousha_combine_org")
public class RiyoushaCombineOrgEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "riyousha_combine_org_id")
    private Integer riyoushaCombineOrgId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getRiyoushaCombineOrgId() {
        return riyoushaCombineOrgId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param riyoushaCombineOrgId テーブルId
     */
    public void setRiyoushaCombineOrgId(final Integer riyoushaCombineOrgId) {
        this.riyoushaCombineOrgId = riyoushaCombineOrgId;
    }

    /** 紐づけコード */
    @Column(name = "riyousha_combine_org_code")
    private Integer riyoushaCombineOrgCode = INIT_INTEGER;

    /**
     * 紐づけコードを取得する
     *
     * @return 紐づけコード
     */
    public Integer getRiyoushaCombineOrgCode() {
        return riyoushaCombineOrgCode;
    }

    /**
     * 紐づけコードを設定する
     *
     * @param riyoushaCombineOrgCode 紐づけコード
     */
    public void setRiyoushaCombineOrgCode(final Integer riyoushaCombineOrgCode) {
        this.riyoushaCombineOrgCode = riyoushaCombineOrgCode;
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

    /** 紐づけ関連者区分 */
    @Column(name = "riyousha_kbn")
    private Short riyoushaKbn = INIT_SHORT;

    /**
     * 紐づけ関連者区分を取得する
     *
     * @return 紐づけ関連者区分
     */
    public Short getRiyoushaKbn() {
        return riyoushaKbn;
    }

    /**
     * 紐づけ関連者区分を設定する
     *
     * @param riyoushaKbn 紐づけ関連者区分
     */
    public void setRiyoushaKbn(final Short riyoushaKbn) {
        this.riyoushaKbn = riyoushaKbn;
    }

    /** 紐づけコード */
    @Column(name = "person_riyousha_code")
    private Integer personRiyoushaCode = INIT_INTEGER;

    /**
     * 紐づけコードを取得する
     *
     * @return 紐づけコード
     */
    public Integer getPersonRiyoushaCode() {
        return personRiyoushaCode;
    }

    /**
     * 紐づけコードを設定する
     *
     * @param personRiyoushaCode 紐づけコード
     */
    public void setPersonRiyoushaCode(final Integer personRiyoushaCode) {
        this.personRiyoushaCode = personRiyoushaCode;
    }

    /** 個人氏名 */
    @Column(name = "person_name")
    private String personName = INIT_STRING;

    /**
     * 個人氏名を取得する
     *
     * @return 個人氏名
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 個人氏名を設定する
     *
     * @param personName 個人氏名
     */
    public void setPersonName(final String personName) {
        this.personName = personName;
    }

    /** 紐づけコード */
    @Column(name = "org_riyousha_code")
    private Integer orgRiyoushaCode = INIT_INTEGER;

    /**
     * 紐づけコードを取得する
     *
     * @return 紐づけコード
     */
    public Integer getOrgRiyoushaCode() {
        return orgRiyoushaCode;
    }

    /**
     * 紐づけコードを設定する
     *
     * @param orgRiyoushaCode 紐づけコード
     */
    public void setOrgRiyoushaCode(final Integer orgRiyoushaCode) {
        this.orgRiyoushaCode = orgRiyoushaCode;
    }

    /** 団体代表者名称 */
    @Column(name = "org_name")
    private String orgName = INIT_STRING;

    /**
     * 団体代表者名称を取得する
     *
     * @return 団体代表者名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 団体代表者名称を設定する
     *
     * @param orgName 団体代表者名称
     */
    public void setOrgName(final String orgName) {
        this.orgName = orgName;
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
