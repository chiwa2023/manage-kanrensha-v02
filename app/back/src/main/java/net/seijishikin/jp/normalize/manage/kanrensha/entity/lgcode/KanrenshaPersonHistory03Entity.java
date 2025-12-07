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
 * kanrensha_person_history_03接続用Entity
 */
@Entity
@Table(name = KanrenshaPersonTableConstants.KANRENSHA_HISTORY_TABLE_PERSON_03)
public class KanrenshaPersonHistory03Entity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルIdd */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_person_history_id")
    private Integer kanrenshaPersonHistoryId = INIT_INTEGER;

    /**
     * テーブルIddを取得する
     *
     * @return テーブルIdd
     */
    public Integer getKanrenshaPersonHistoryId() {
        return kanrenshaPersonHistoryId;
    }

    /**
     * テーブルIddを設定する
     *
     * @param kanrenshaPersonHistoryId テーブルIdd
     */
    public void setKanrenshaPersonHistoryId(final Integer kanrenshaPersonHistoryId) {
        this.kanrenshaPersonHistoryId = kanrenshaPersonHistoryId;
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

    /** 関連者個人名 */
    @Column(name = "all_name")
    private String allName = INIT_STRING;

    /**
     * 関連者個人名を取得する
     *
     * @return 関連者個人名
     */
    public String getAllName() {
        return allName;
    }

    /**
     * 関連者個人名を設定する
     *
     * @param allName 関連者個人名
     */
    public void setAllName(final String allName) {
        this.allName = allName;
    }

    /** 関連者個人全住所 */
    @Column(name = "all_address")
    private String allAddress = INIT_STRING;

    /**
     * 関連者個人全住所を取得する
     *
     * @return 関連者個人全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 関連者個人全住所を設定する
     *
     * @param allAddress 関連者個人全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 関連者個人職業 */
    @Column(name = "person_shokugyou")
    private String personShokugyou = INIT_STRING;

    /**
     * 関連者個人職業を取得する
     *
     * @return 関連者個人職業
     */
    public String getPersonShokugyou() {
        return personShokugyou;
    }

    /**
     * 関連者個人職業を設定する
     *
     * @param personShokugyou 関連者個人職業
     */
    public void setPersonShokugyou(final String personShokugyou) {
        this.personShokugyou = personShokugyou;
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
