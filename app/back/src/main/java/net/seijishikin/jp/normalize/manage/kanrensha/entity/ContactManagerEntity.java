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
 * contact_manager接続用Entity
 */
@Entity
@Table(name = "contact_manager")
public class ContactManagerEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_manager_id")
    private Integer contactManagerId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getContactManagerId() {
        return contactManagerId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param contactManagerId テーブルId
     */
    public void setContactManagerId(final Integer contactManagerId) {
        this.contactManagerId = contactManagerId;
    }

    /** 運営者問い合わせコード */
    @Column(name = "contact_manager_code")
    private Integer contactManagerCode = INIT_INTEGER;

    /**
     * 運営者問い合わせコードを取得する
     *
     * @return 運営者問い合わせコード
     */
    public Integer getContactManagerCode() {
        return contactManagerCode;
    }

    /**
     * 運営者問い合わせコードを設定する
     *
     * @param contactManagerCode 運営者問い合わせコード
     */
    public void setContactManagerCode(final Integer contactManagerCode) {
        this.contactManagerCode = contactManagerCode;
    }

    /** 最新該非 */
    @Column(name = "is_latest")
    private Boolean isLatest = INIT_BOOLEAN;

    /**
     * 最新該非を取得する
     *
     * @return 最新該非
     */
    @Override
    public Boolean getIsLatest() {
        return isLatest;
    }

    /**
     * 最新該非を設定する
     *
     * @param isLatest 最新該非
     */
    @Override
    public void setIsLatest(final Boolean isLatest) {
        this.isLatest = isLatest;
    }

    /** 初回問い合わせ日時 */
    @Column(name = "first_timestamp")
    private LocalDateTime firstTimestamp = INIT_TIMESTAMP;

    /**
     * 初回問い合わせ日時を取得する
     *
     * @return 初回問い合わせ日時
     */
    public LocalDateTime getFirstTimestamp() {
        return firstTimestamp;
    }

    /**
     * 初回問い合わせ日時を設定する
     *
     * @param firstTimestamp 初回問い合わせ日時
     */
    public void setFirstTimestamp(final LocalDateTime firstTimestamp) {
        this.firstTimestamp = firstTimestamp;
    }

    /** 問い合わせクローズ該非 */
    @Column(name = "is_closed")
    private Boolean isClosed = INIT_BOOLEAN;

    /**
     * 問い合わせクローズ該非を取得する
     * 
     * @return 問い合わせクローズ該非
     */
    public Boolean getIsClosed() {
        return isClosed;
    }

    /**
     * 問い合わせクローズ該非を設定する
     * 
     * @param isClosed 問い合わせクローズ該非
     */
    public void setIsClosed(final Boolean isClosed) {
        this.isClosed = isClosed;
    }

    /** クローズ日時 */
    @Column(name = "close_timestamp")
    private LocalDateTime closeTimestamp = INIT_TIMESTAMP;

    /**
     * クローズ日時を取得する
     *
     * @return クローズ日時
     */
    public LocalDateTime getCloseTimestamp() {
        return closeTimestamp;
    }

    /**
     * クローズ日時を設定する
     *
     * @param closeTimestamp クローズ日時
     */
    public void setCloseTimestamp(final LocalDateTime closeTimestamp) {
        this.closeTimestamp = closeTimestamp;
    }

    /** 問い合わせユーザId */
    @Column(name = "inquire_user_id")
    private Integer inquireUserId = INIT_INTEGER;

    /**
     * 問い合わせユーザIdを取得する
     *
     * @return 問い合わせユーザId
     */
    public Integer getInquireUserId() {
        return inquireUserId;
    }

    /**
     * 問い合わせユーザIdを設定する
     *
     * @param inquireUserId 問い合わせユーザId
     */
    public void setInquireUserId(final Integer inquireUserId) {
        this.inquireUserId = inquireUserId;
    }

    /** 問い合わせユーザコード */
    @Column(name = "inquire_user_code")
    private Integer inquireUserCode = INIT_INTEGER;

    /**
     * 問い合わせユーザコードを取得する
     *
     * @return 問い合わせユーザコード
     */
    public Integer getInquireUserCode() {
        return inquireUserCode;
    }

    /**
     * 問い合わせユーザコードを設定する
     *
     * @param inquireUserCode 問い合わせユーザコード
     */
    public void setInquireUserCode(final Integer inquireUserCode) {
        this.inquireUserCode = inquireUserCode;
    }

    /** 問い合わせユーザ名称 */
    @Column(name = "inquire_user_name")
    private String inquireUserName = INIT_STRING;

    /**
     * 問い合わせユーザ名称を取得する
     *
     * @return 問い合わせユーザ名称
     */
    public String getInquireUserName() {
        return inquireUserName;
    }

    /**
     * 問い合わせユーザ名称を設定する
     *
     * @param inquireUserName 問い合わせユーザ名称
     */
    public void setInquireUserName(final String inquireUserName) {
        this.inquireUserName = inquireUserName;
    }

    /** 問い合わせタイトル */
    @Column(name = "inquire_title")
    private String inquireTitle = INIT_STRING;

    /**
     * 問い合わせタイトルを取得する
     *
     * @return 問い合わせタイトル
     */
    public String getInquireTitle() {
        return inquireTitle;
    }

    /**
     * 問い合わせタイトルを設定する
     *
     * @param inquireTitle 問い合わせタイトル
     */
    public void setInquireTitle(final String inquireTitle) {
        this.inquireTitle = inquireTitle;
    }

    /** 問い合わせ内容 */
    @Column(name = "inquire_content")
    private String inquireContent = INIT_STRING;

    /**
     * 問い合わせ内容を取得する
     *
     * @return 問い合わせ内容
     */
    public String getInquireContent() {
        return inquireContent;
    }

    /**
     * 問い合わせ内容を設定する
     *
     * @param inquireContent 問い合わせ内容
     */
    public void setInquireContent(final String inquireContent) {
        this.inquireContent = inquireContent;
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
