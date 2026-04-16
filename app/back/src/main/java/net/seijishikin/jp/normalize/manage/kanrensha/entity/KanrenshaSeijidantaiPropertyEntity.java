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
 * kanrensha_seijidantai_property接続用Entity
 */
@Entity
@Table(name = "kanrensha_seijidantai_property")
public class KanrenshaSeijidantaiPropertyEntity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_seijidantai_property_id")
    private Integer kanrenshaSeijidantaiPropertyId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaSeijidantaiPropertyId() {
        return kanrenshaSeijidantaiPropertyId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaSeijidantaiPropertyId テーブルId
     */
    public void setKanrenshaSeijidantaiPropertyId(final Integer kanrenshaSeijidantaiPropertyId) {
        this.kanrenshaSeijidantaiPropertyId = kanrenshaSeijidantaiPropertyId;
    }

    /** 関連者政治団体Id */
    @Column(name = "kanrensha_seijidantai_id")
    private Integer kanrenshaSeijidantaiId = INIT_INTEGER;

    /**
     * 関連者政治団体Idを取得する
     *
     * @return 関連者政治団体Id
     */
    public Integer getKanrenshaSeijidantaiId() {
        return kanrenshaSeijidantaiId;
    }

    /**
     * 関連者政治団体Idを設定する
     *
     * @param kanrenshaSeijidantaiId 関連者政治団体Id
     */
    public void setKanrenshaSeijidantaiId(final Integer kanrenshaSeijidantaiId) {
        this.kanrenshaSeijidantaiId = kanrenshaSeijidantaiId;
    }

    /** 関連者政治団体コード */
    @Column(name = "seijidantai_kanrensha_code")
    private String seijidantaiKanrenshaCode = INIT_STRING;

    /**
     * 関連者政治団体コードを取得する
     *
     * @return 関連者政治団体コード
     */
    public String getSeijidantaiKanrenshaCode() {
        return seijidantaiKanrenshaCode;
    }

    /**
     * 関連者政治団体コードを設定する
     *
     * @param seijidantaiKanrenshaCode 関連者政治団体コード
     */
    public void setSeijidantaiKanrenshaCode(final String seijidantaiKanrenshaCode) {
        this.seijidantaiKanrenshaCode = seijidantaiKanrenshaCode;
    }

    /** 関連者政治団体名称 */
    @Column(name = "kanrensha_name")
    private String kanrenshaName = INIT_STRING;

    /**
     * 関連者政治団体名称を取得する
     *
     * @return 関連者政治団体名称
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 関連者政治団体名称を設定する
     *
     * @param kanrenshaName 関連者政治団体名称
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

    /** 関連者団体名称かな */
    @Column(name = "org_name_kana")
    private String orgNameKana = INIT_STRING;

    /**
     * 関連者団体名称かなを取得する
     *
     * @return 関連者団体名称かな
     */
    public String getOrgNameKana() {
        return orgNameKana;
    }

    /**
     * 関連者団体名称かなを設定する
     *
     * @param orgNameKana 関連者団体名称かな
     */
    public void setOrgNameKana(final String orgNameKana) {
        this.orgNameKana = orgNameKana;
    }

    /** 団体代表者関連者コード */
    @Column(name = "org_delegate_code")
    private String orgDelegateCode = INIT_STRING;

    /**
     * 団体代表者関連者コードを取得する
     *
     * @return 団体代表者関連者コード
     */
    public String getOrgDelegateCode() {
        return orgDelegateCode;
    }

    /**
     * 団体代表者関連者コードを設定する
     *
     * @param orgDelegateCode 団体代表者関連者コード
     */
    public void setOrgDelegateCode(final String orgDelegateCode) {
        this.orgDelegateCode = orgDelegateCode;
    }

    /** 会計責任者関連者個人コード */
    @Column(name = "account_mgr_code")
    private String accountMgrCode = INIT_STRING;

    /**
     * 会計責任者関連者個人コードを取得する
     *
     * @return 会計責任者関連者個人コード
     */
    public String getAccountMgrCode() {
        return accountMgrCode;
    }

    /**
     * 会計責任者関連者個人コードを設定する
     *
     * @param accountMgrCode 会計責任者関連者個人コード
     */
    public void setAccountMgrCode(final String accountMgrCode) {
        this.accountMgrCode = accountMgrCode;
    }

    /** 会計責任者関連者個人氏名 */
    @Column(name = "account_mgr_name")
    private String accountMgrName = INIT_STRING;

    /**
     * 会計責任者関連者個人氏名を取得する
     *
     * @return 会計責任者関連者個人氏名
     */
    public String getAccountMgrName() {
        return accountMgrName;
    }

    /**
     * 会計責任者関連者個人氏名を設定する
     *
     * @param accountMgrName 会計責任者関連者個人氏名
     */
    public void setAccountMgrName(final String accountMgrName) {
        this.accountMgrName = accountMgrName;
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
