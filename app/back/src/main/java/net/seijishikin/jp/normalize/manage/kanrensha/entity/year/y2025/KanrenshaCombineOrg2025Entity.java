package net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025;

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
 * kanrensha_combine_org_2025接続用Entity
 */
@Entity
@Table(name = "kanrensha_combine_org_2025")
public class KanrenshaCombineOrg2025Entity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_combine_org_id")
    private Integer kanrenshaCombineOrgId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaCombineOrgId() {
        return kanrenshaCombineOrgId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaCombineOrgId テーブルId
     */
    public void setKanrenshaCombineOrgId(final Integer kanrenshaCombineOrgId) {
        this.kanrenshaCombineOrgId = kanrenshaCombineOrgId;
    }

    /** 紐づけコード */
    @Column(name = "kanrensha_combine_org_code")
    private Integer kanrenshaCombineOrgCode = INIT_INTEGER;

    /**
     * 紐づけコードを取得する
     *
     * @return 紐づけコード
     */
    public Integer getKanrenshaCombineOrgCode() {
        return kanrenshaCombineOrgCode;
    }

    /**
     * 紐づけコードを設定する
     *
     * @param kanrenshaCombineOrgCode 紐づけコード
     */
    public void setKanrenshaCombineOrgCode(final Integer kanrenshaCombineOrgCode) {
        this.kanrenshaCombineOrgCode = kanrenshaCombineOrgCode;
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
    @Column(name = "kanrensha_kbn")
    private Short kanrenshaKbn = INIT_SHORT;

    /**
     * 紐づけ関連者区分を取得する
     *
     * @return 紐づけ関連者区分
     */
    public Short getKanrenshaKbn() {
        return kanrenshaKbn;
    }

    /**
     * 紐づけ関連者区分を設定する
     *
     * @param kanrenshaKbn 紐づけ関連者区分
     */
    public void setKanrenshaKbn(final Short kanrenshaKbn) {
        this.kanrenshaKbn = kanrenshaKbn;
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

    /** 団体関連者コード */
    @Column(name = "org_kanrensha_code")
    private String orgKanrenshaCode = INIT_STRING;

    /**
     * 団体関連者コードを取得する
     *
     * @return 団体関連者コード
     */
    public String getOrgKanrenshaCode() {
        return orgKanrenshaCode;
    }

    /**
     * 団体関連者コードを設定する
     *
     * @param orgKanrenshaCode 団体関連者コード
     */
    public void setOrgKanrenshaCode(final String orgKanrenshaCode) {
        this.orgKanrenshaCode = orgKanrenshaCode;
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
