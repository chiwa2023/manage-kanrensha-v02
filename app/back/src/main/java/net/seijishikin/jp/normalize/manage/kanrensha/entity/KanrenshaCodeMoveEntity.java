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
 * kanrensha_code_move接続用Entity
 */
@Entity
@Table(name = "kanrensha_code_move")
public class KanrenshaCodeMoveEntity implements Serializable, AllTabeDataHistoryInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_code_move_id")
    private Integer kanrenshaCodeMoveId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaCodeMoveId() {
        return kanrenshaCodeMoveId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaCodeMoveId テーブルId
     */
    public void setKanrenshaCodeMoveId(final Integer kanrenshaCodeMoveId) {
        this.kanrenshaCodeMoveId = kanrenshaCodeMoveId;
    }

    /** 関連者コード移動申請コード */
    @Column(name = "kanrensha_code_move_code")
    private Integer kanrenshaCodeMoveCode = INIT_INTEGER;

    /**
     * 関連者コード移動申請コードを取得する
     *
     * @return 関連者コード移動申請コード
     */
    public Integer getKanrenshaCodeMoveCode() {
        return kanrenshaCodeMoveCode;
    }

    /**
     * 関連者コード移動申請コードを設定する
     *
     * @param kanrenshaCodeMoveCode 関連者コード移動申請コード
     */
    public void setKanrenshaCodeMoveCode(final Integer kanrenshaCodeMoveCode) {
        this.kanrenshaCodeMoveCode = kanrenshaCodeMoveCode;
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

    /** 承認状態 */
    @Column(name = "move_status")
    private Short moveStatus = INIT_SHORT;

    /**
     * 承認状態を取得する
     *
     * @return 承認状態
     */
    public Short getMoveStatus() {
        return moveStatus;
    }

    /**
     * 承認状態を設定する
     *
     * @param moveStatus 承認状態
     */
    public void setMoveStatus(final Short moveStatus) {
        this.moveStatus = moveStatus;
    }

    /** 関連者区分 */
    @Column(name = "kanrensha_kbn")
    private Short kanrenshaKbn = INIT_SHORT;

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

    /** 併合先コード */
    @Column(name = "origin_kanrensha_code")
    private String originKanrenshaCode = INIT_STRING;

    /**
     * 併合先コードを取得する
     *
     * @return 併合先コード
     */
    public String getOriginKanrenshaCode() {
        return originKanrenshaCode;
    }

    /**
     * 併合先コードを設定する
     *
     * @param originKanrenshaCode 併合先コード
     */
    public void setOriginKanrenshaCode(final String originKanrenshaCode) {
        this.originKanrenshaCode = originKanrenshaCode;
    }

    /** 併合先コード名称 */
    @Column(name = "origin_name")
    private String originName = INIT_STRING;

    /**
     * 併合先コード名称を取得する
     *
     * @return 併合先コード名称
     */
    public String getOriginName() {
        return originName;
    }

    /**
     * 併合先コード名称を設定する
     *
     * @param originName 併合先コード名称
     */
    public void setOriginName(final String originName) {
        this.originName = originName;
    }

    /** 廃止コード */
    @Column(name = "abolish_kanrensha_code")
    private String abolishKanrenshaCode = INIT_STRING;

    /**
     * 廃止コードを取得する
     *
     * @return 廃止コード
     */
    public String getAbolishKanrenshaCode() {
        return abolishKanrenshaCode;
    }

    /**
     * 廃止コードを設定する
     *
     * @param abolishKanrenshaCode 廃止コード
     */
    public void setAbolishKanrenshaCode(final String abolishKanrenshaCode) {
        this.abolishKanrenshaCode = abolishKanrenshaCode;
    }

    /** 廃止コード名称 */
    @Column(name = "abolish_kanrensha_name")
    private String abolishKanrenshaName = INIT_STRING;

    /**
     * 廃止コード名称を取得する
     *
     * @return 廃止コード名称
     */
    public String getAbolishKanrenshaName() {
        return abolishKanrenshaName;
    }

    /**
     * 廃止コード名称を設定する
     *
     * @param abolishKanrenshaName 廃止コード名称
     */
    public void setAbolishKanrenshaName(final String abolishKanrenshaName) {
        this.abolishKanrenshaName = abolishKanrenshaName;
    }

    /** 移行理由 */
    @Column(name = "move_reason")
    private String moveReason = INIT_STRING;

    /**
     * 移行理由を取得する
     *
     * @return 移行理由
     */
    public String getMoveReason() {
        return moveReason;
    }

    /**
     * 移行理由を設定する
     *
     * @param moveReason 移行理由
     */
    public void setMoveReason(final String moveReason) {
        this.moveReason = moveReason;
    }

    /** 廃止コード最新該当 */
    @Column(name = "is_abolish_last")
    private Boolean isAbolishLast = INIT_BOOLEAN;

    /**
     * 廃止コード最新該当を取得する
     *
     * @return 廃止コード最新該当
     */
    public Boolean getIsAbolishLast() {
        return isAbolishLast;
    }

    /**
     * 廃止コード最新該当を設定する
     *
     * @param isAbolishLast 廃止コード最新該当
     */
    public void setIsAbolishLast(final Boolean isAbolishLast) {
        this.isAbolishLast = isAbolishLast;
    }

    /** 発生年 */
    @Column(name = "task_year")
    private Integer taskYear = INIT_INTEGER;

    /**
     * 発生年を取得する
     *
     * @return 発生年
     */
    public Integer getTaskYear() {
        return taskYear;
    }

    /**
     * 発生年を設定する
     *
     * @param taskYear 発生年
     */
    public void setTaskYear(final Integer taskYear) {
        this.taskYear = taskYear;
    }

    /** ストレージId */
    @Column(name = "save_file_storage_id")
    private Integer saveFileStorageId = INIT_INTEGER;

    /**
     * ストレージIdを取得する
     *
     * @return ストレージId
     */
    public Integer getSaveFileStorageId() {
        return saveFileStorageId;
    }

    /**
     * ストレージIdを設定する
     *
     * @param saveFileStorageId ストレージId
     */
    public void setSaveFileStorageId(final Integer saveFileStorageId) {
        this.saveFileStorageId = saveFileStorageId;
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
