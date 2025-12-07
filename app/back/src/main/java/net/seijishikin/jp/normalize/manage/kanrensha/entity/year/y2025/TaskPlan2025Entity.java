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
 * task_plan_2025接続用Entity
 */
@Entity
@Table(name = "task_plan_2025")
public class TaskPlan2025Entity // NOPMD DataClass
        implements Serializable, AllTabeDataHistoryInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** タスク予定Id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_plan_id")
    private Integer taskPlanId = INIT_INTEGER;

    /**
     * タスク予定Idを取得する
     *
     * @return タスク予定Id
     */
    public Integer getTaskPlanId() {
        return taskPlanId;
    }

    /**
     * タスク予定Idを設定する
     *
     * @param taskPlanId タスク予定Id
     */
    public void setTaskPlanId(final Integer taskPlanId) {
        this.taskPlanId = taskPlanId;
    }

    /** タスク予定コード */
    @Column(name = "task_plan_code")
    private Integer taskPlanCode = INIT_INTEGER;

    /**
     * タスク予定コードを取得する
     *
     * @return タスク予定コード
     */
    public Integer getTaskPlanCode() {
        return taskPlanCode;
    }

    /**
     * タスク予定コードを設定する
     *
     * @param taskPlanCode タスク予定コード
     */
    public void setTaskPlanCode(final Integer taskPlanCode) {
        this.taskPlanCode = taskPlanCode;
    }

    /** タスク設定コード */
    @Column(name = "task_info_code")
    private Integer taskInfoCode = INIT_INTEGER;

    /**
     * タスク設定コードを取得する
     *
     * @return タスク設定コード
     */
    public Integer getTaskInfoCode() {
        return taskInfoCode;
    }

    /**
     * タスク設定コードを設定する
     *
     * @param taskInfoCode タスク設定コード
     */
    public void setTaskInfoCode(final Integer taskInfoCode) {
        this.taskInfoCode = taskInfoCode;
    }

    /** タスク予定名称 */
    @Column(name = "task_plan_name")
    private String taskPlanName = INIT_STRING;

    /**
     * タスク予定名称を取得する
     *
     * @return タスク予定名称
     */
    public String getTaskPlanName() {
        return taskPlanName;
    }

    /**
     * タスク予定名称を設定する
     *
     * @param taskPlanName タスク予定名称
     */
    public void setTaskPlanName(final String taskPlanName) {
        this.taskPlanName = taskPlanName;
    }

    /**  */
    @Column(name = "table_year")
    private Integer tableYear = INIT_INTEGER;

    /**
     * 登録年を取得する
     *
     * @return 登録年
     */
    public Integer getTableYear() {
        return tableYear;
    }

    /**
     * 登録年を設定する
     *
     * @param tableYear 登録年
     */
    public void setTableYear(final Integer tableYear) {
        this.tableYear = tableYear;
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

    /** 作業開始フラグ */
    @Column(name = "is_start")
    private Boolean isStart = INIT_BOOLEAN;

    /**
     * 作業開始フラグを取得する
     *
     * @return 作業開始フラグ
     */
    public Boolean getIsStart() {
        return isStart;
    }

    /**
     * 作業開始フラグを設定する
     *
     * @param isStart 作業開始フラグ
     */
    public void setIsStart(final Boolean isStart) {
        this.isStart = isStart;
    }

    /** 作業終了フラグ */
    @Column(name = "is_finished")
    private Boolean isFinished = INIT_BOOLEAN;

    /**
     * 作業終了フラグを取得する
     *
     * @return 作業終了フラグ
     */
    public Boolean getIsFinished() {
        return isFinished;
    }

    /**
     * 作業終了フラグを設定する
     *
     * @param isFinished 作業終了フラグ
     */
    public void setIsFinished(final Boolean isFinished) {
        this.isFinished = isFinished;
    }

    /** タスク中断フラグ */
    @Column(name = "is_suspended")
    private Boolean isSuspended = INIT_BOOLEAN;

    /**
     * タスク中断フラグを取得する
     *
     * @return タスク中断フラグ
     */
    public Boolean getIsSuspended() {
        return isSuspended;
    }

    /**
     * タスク中断フラグを設定する
     *
     * @param isSuspended タスク中断フラグ
     */
    public void setIsSuspended(final Boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    /** 開始日時 */
    @Column(name = "start_datetime")
    private LocalDateTime startDatetime = INIT_TIMESTAMP;

    /**
     * 開始日時を取得する
     *
     * @return 開始日時
     */
    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    /**
     * 開始日時を設定する
     *
     * @param startDatetime 開始日時
     */
    public void setStartDatetime(final LocalDateTime startDatetime) {
        this.startDatetime = startDatetime;
    }

    /** 終了日時 */
    @Column(name = "end_dateimte")
    private LocalDateTime endDateimte = INIT_TIMESTAMP;

    /**
     * 終了日時を取得する
     *
     * @return 終了日時
     */
    public LocalDateTime getEndDateimte() {
        return endDateimte;
    }

    /**
     * 終了日時を設定する
     *
     * @param endDateimte 終了日時
     */
    public void setEndDateimte(final LocalDateTime endDateimte) {
        this.endDateimte = endDateimte;
    }

    /** 対象ユーザリスト */
    @Column(name = "role_list")
    private String roleList = INIT_STRING;

    /**
     * 対象ユーザリストを取得する
     *
     * @return 対象ユーザリスト
     */
    public String getRoleList() {
        return roleList;
    }

    /**
     * 対象ユーザリストを設定する
     *
     * @param roleList 対象ユーザリスト
     */
    public void setRoleList(final String roleList) {
        this.roleList = roleList;
    }

    /** 遷移パス */
    @Column(name = "transfer_pass")
    private String transferPass = INIT_STRING;

    /**
     * 遷移パスを取得する
     *
     * @return 遷移パス
     */
    public String getTransferPass() {
        return transferPass;
    }

    /**
     * 遷移パスを設定する
     *
     * @param transferPass 遷移パス
     */
    public void setTransferPass(final String transferPass) {
        this.transferPass = transferPass;
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
