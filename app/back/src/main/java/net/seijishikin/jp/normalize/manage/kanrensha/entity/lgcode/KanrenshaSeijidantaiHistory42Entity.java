package net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode;

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
 * kanrensha_seijidantai_history_42接続用Entity
 */
@Entity
@Table(name = KanrenshaSeijidantaiTableConstants.KANRENSHA_HISTORY_TABLE_SEIJIDANTAII_42)
public class KanrenshaSeijidantaiHistory42Entity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_seijidantai_history_id")
    private Integer kanrenshaSeijidantaiHistoryId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaSeijidantaiHistoryId() {
        return kanrenshaSeijidantaiHistoryId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaSeijidantaiHistoryId テーブルId
     */
    public void setKanrenshaSeijidantaiHistoryId(final Integer kanrenshaSeijidantaiHistoryId) {
        this.kanrenshaSeijidantaiHistoryId = kanrenshaSeijidantaiHistoryId;
    }

    /** 政治団体関連者コード */
    @Column(name = "seijidantai_kanrensha_code")
    private String seijidantaiKanrenshaCode = INIT_STRING;

    /**
     * 政治団体関連者コードを取得する
     *
     * @return 政治団体関連者コード
     */
    public String getSeijidantaiKanrenshaCode() {
        return seijidantaiKanrenshaCode;
    }

    /**
     * 政治団体関連者コードを設定する
     *
     * @param seijidantaiKanrenshaCode 政治団体関連者コード
     */
    public void setSeijidantaiKanrenshaCode(final String seijidantaiKanrenshaCode) {
        this.seijidantaiKanrenshaCode = seijidantaiKanrenshaCode;
    }

    /** 最新フラグ */
    @Column(name = "is_latest")
    private Boolean isLatest = INIT_BOOLEAN;

    /**
     * 最新フラグを取得する
     *
     * @return 最新フラグ
     */
    public Boolean getIsLatest() {
        return isLatest;
    }

    /**
     * 最新フラグを設定する
     *
     * @param isLatest 最新フラグ
     */
    public void setIsLatest(final Boolean isLatest) {
        this.isLatest = isLatest;
    }

    /** 関連者政治団体名 */
    @Column(name = "all_name")
    private String allName = INIT_STRING;

    /**
     * 関連者政治団体名を取得する
     *
     * @return 関連者政治団体名
     */
    public String getAllName() {
        return allName;
    }

    /**
     * 関連者政治団体名を設定する
     *
     * @param allName 関連者政治団体名
     */
    public void setAllName(final String allName) {
        this.allName = allName;
    }

    /** 関連者政治団体全住所 */
    @Column(name = "all_address")
    private String allAddress = INIT_STRING;

    /**
     * 関連者政治団体全住所を取得する
     *
     * @return 関連者政治団体全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 関連者政治団体全住所を設定する
     *
     * @param allAddress 関連者政治団体全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 団体代表者氏名 */
    @Column(name = "org_delegate_name")
    private String orgDelegateName = INIT_STRING;

    /**
     * 団体代表者氏名を取得する
     *
     * @return 団体代表者氏名
     */
    public String getOrgDelegateName() {
        return orgDelegateName;
    }

    /**
     * 団体代表者氏名を設定する
     *
     * @param orgDelegateName 団体代表者氏名
     */
    public void setOrgDelegateName(final String orgDelegateName) {
        this.orgDelegateName = orgDelegateName;
    }

    /** 団体代表者関連者個人コード */
    @Column(name = "org_delegate_code")
    private String orgDelegateCode = INIT_STRING;

    /**
     * 団体代表者関連者個人コードを取得する
     *
     * @return 団体代表者関連者個人コード
     */
    public String getOrgDelegateCode() {
        return orgDelegateCode;
    }

    /**
     * 団体代表者関連者個人コードを設定する
     *
     * @param orgDelegateCode 団体代表者関連者個人コード
     */
    public void setOrgDelegateCode(final String orgDelegateCode) {
        this.orgDelegateCode = orgDelegateCode;
    }

    /** 検索テキスト */
    @Column(name = "search_text")
    private String searchText = INIT_STRING;

    /**
     * 検索テキストを取得する
     *
     * @return 検索テキスト
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * 検索テキストを設定する
     *
     * @param searchText 検索テキスト
     */
    public void setSearchText(final String searchText) {
        this.searchText = searchText;
    }

    /** 挿入ユーザーId */
    @Column(name = "insert_user_id")
    private Integer insertUserId = INIT_INTEGER;

    /**
     * 挿入ユーザーIdを取得する
     *
     * @return 挿入ユーザーId
     */
    public Integer getInsertUserId() {
        return insertUserId;
    }

    /**
     * 挿入ユーザーIdを設定する
     *
     * @param insertUserId 挿入ユーザーId
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

    /** 挿入ユーザ名 */
    @Column(name = "insert_user_name")
    private String insertUserName = INIT_STRING;

    /**
     * 挿入ユーザ名を取得する
     *
     * @return 挿入ユーザ名
     */
    public String getInsertUserName() {
        return insertUserName;
    }

    /**
     * 挿入ユーザ名を設定する
     *
     * @param insertUserName 挿入ユーザ名
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

    /** 削除ユーザId */
    @Column(name = "delete_user_id")
    private Integer deleteUserId = INIT_INTEGER;

    /**
     * 削除ユーザIdを取得する
     *
     * @return 削除ユーザId
     */
    public Integer getDeleteUserId() {
        return deleteUserId;
    }

    /**
     * 削除ユーザIdを設定する
     *
     * @param deleteUserId 削除ユーザId
     */
    public void setDeleteUserId(final Integer deleteUserId) {
        this.deleteUserId = deleteUserId;
    }

    /** 削除ユーザコード */
    @Column(name = "delete_user_code")
    private Integer deleteUserCode = INIT_INTEGER;

    /**
     * 削除ユーザコードを取得する
     *
     * @return 削除ユーザコード
     */
    public Integer getDeleteUserCode() {
        return deleteUserCode;
    }

    /**
     * 削除ユーザコードを設定する
     *
     * @param deleteUserCode 削除ユーザコード
     */
    public void setDeleteUserCode(final Integer deleteUserCode) {
        this.deleteUserCode = deleteUserCode;
    }

    /** 削除ユーザ名 */
    @Column(name = "delete_user_name")
    private String deleteUserName = INIT_STRING;

    /**
     * 削除ユーザ名を取得する
     *
     * @return 削除ユーザ名
     */
    public String getDeleteUserName() {
        return deleteUserName;
    }

    /**
     * 削除ユーザ名を設定する
     *
     * @param deleteUserName 削除ユーザ名
     */
    public void setDeleteUserName(final String deleteUserName) {
        this.deleteUserName = deleteUserName;
    }

    /** 削除日時 */
    @Column(name = "delete_timestamp")
    private LocalDateTime deleteTimestamp = INIT_TIMESTAMP;

    /**
     * 削除日時を取得する
     *
     * @return 削除日時
     */
    public LocalDateTime getDeleteTimestamp() {
        return deleteTimestamp;
    }

    /**
     * 削除日時を設定する
     *
     * @param deleteTimestamp 削除日時
     */
    public void setDeleteTimestamp(final LocalDateTime deleteTimestamp) {
        this.deleteTimestamp = deleteTimestamp;
    }

}
