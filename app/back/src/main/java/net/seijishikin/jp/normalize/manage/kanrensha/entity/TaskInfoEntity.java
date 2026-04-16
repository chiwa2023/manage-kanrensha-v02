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
 * task_info接続用Entity
 */
@Entity
@Table(name = "task_info")
public class TaskInfoEntity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** タスク情報Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_info_id")
    private Integer taskInfoId = INIT_INTEGER;

    /**
     * タスク情報Idを取得する
     *
     * @return タスク情報Id
     */
    public Integer getTaskInfoId() {
        return taskInfoId;
    }

    /**
     * タスク情報Idを設定する
     *
     * @param taskInfoId タスク情報Id
     */
    public void setTaskInfoId(final Integer taskInfoId) {
        this.taskInfoId = taskInfoId;
    }

    /** タスク情報コード */
    @Column(name = "task_info_code")
    private Integer taskInfoCode = INIT_INTEGER;

    /**
     * タスク情報コードを取得する
     *
     * @return タスク情報コード
     */
    public Integer getTaskInfoCode() {
        return taskInfoCode;
    }

    /**
     * タスク情報コードを設定する
     *
     * @param taskInfoCode タスク情報コード
     */
    public void setTaskInfoCode(final Integer taskInfoCode) {
        this.taskInfoCode = taskInfoCode;
    }

    /** タスク情報名称 */
    @Column(name = "task_info_name")
    private String taskInfoName = INIT_STRING;

    /**
     * タスク情報名称を取得する
     *
     * @return タスク情報名称
     */
    public String getTaskInfoName() {
        return taskInfoName;
    }

    /**
     * タスク情報名称を設定する
     *
     * @param taskInfoName タスク情報名称
     */
    public void setTaskInfoName(final String taskInfoName) {
        this.taskInfoName = taskInfoName;
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

    /** 権限区分 */
    @Column(name = "role_list")
    private String roleList = INIT_STRING;

    /**
     * 権限区分を取得する
     *
     * @return 権限区分
     */
    public String getRoleList() {
        return roleList;
    }

    /**
     * 権限区分を設定する
     *
     * @param roleList 権限区分
     */
    public void setRoleList(final String roleList) {
        this.roleList = roleList;
    }

    /** メッセージテンプレート */
    @Column(name = "message_template")
    private String messageTemplate = INIT_STRING;

    /**
     * メッセージテンプレートを取得する
     *
     * @return メッセージテンプレート
     */
    public String getMessageTemplate() {
        return messageTemplate;
    }

    /**
     * メッセージテンプレートを設定する
     *
     * @param messageTemplate メッセージテンプレート
     */
    public void setMessageTemplate(final String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    /** 遷移パス(URL) */
    @Column(name = "transfer_pass")
    private String transferPass = INIT_STRING;

    /**
     * 遷移パス(URL)を取得する
     *
     * @return 遷移パス(URL)
     */
    public String getTransferPass() {
        return transferPass;
    }

    /**
     * 遷移パス(URL)を設定する
     *
     * @param transferPass 遷移パス(URL)
     */
    public void setTransferPass(final String transferPass) {
        this.transferPass = transferPass;
    }

    /** SNS同一識別コード */
    @Column(name = "param_query")
    private String paramQuery = INIT_STRING;

    /**
     * SNS同一識別コードを取得する
     *
     * @return SNS同一識別コード
     */
    public String getParamQuery() {
        return paramQuery;
    }

    /**
     * SNS同一識別コードを設定する
     *
     * @param paramQuery SNS同一識別コード
     */
    public void setParamQuery(final String paramQuery) {
        this.paramQuery = paramQuery;
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
