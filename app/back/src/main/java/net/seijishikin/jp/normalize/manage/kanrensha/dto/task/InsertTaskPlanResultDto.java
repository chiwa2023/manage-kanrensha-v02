package net.seijishikin.jp.normalize.manage.kanrensha.dto.task;

import java.io.Serializable;

import jakarta.persistence.Column;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * タスク計画挿入結果Dto
 */
public class InsertTaskPlanResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

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

    /** 遷移時引数 */
    @Column(name = "param_query")
    private String paramQuery = INIT_STRING;

    /**
     * 遷移時引数
     *
     * @return 遷移時引数
     */
    public String getParamQuery() {
        return paramQuery;
    }

    /**
     * 遷移時引数
     *
     * @param paramQuery 遷移時引数
     */
    public void setParamQuery(final String paramQuery) {
        this.paramQuery = paramQuery;
    }


}
