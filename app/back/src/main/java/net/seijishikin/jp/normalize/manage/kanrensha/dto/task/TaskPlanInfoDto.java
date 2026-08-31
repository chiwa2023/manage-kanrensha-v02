package net.seijishikin.jp.normalize.manage.kanrensha.dto.task;

import java.io.Serializable;

import jakarta.persistence.Column;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * タスク計画情報Dto(back用の呼び出し情報受け渡し)
 */
public class TaskPlanInfoDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** タスク予定Id */
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

    /** タスク発生年 */
    private Integer taskYear = INIT_INTEGER;

    /**
     * タスク発生年を取得する
     *
     * @return タスク発生年
     */
    public Integer getTaskYear() {
        return taskYear;
    }

    /**
     * タスク発生年を設定する
     *
     * @param taskYear タスク発生年
     */
    public void setTaskYear(final Integer taskYear) {
        this.taskYear = taskYear;
    }

}
