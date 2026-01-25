package net.seijishikin.jp.normalize.manage.kanrensha.dto.task;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * タスク計画挿入結果Dto
 */
public class InsertTaskPlanResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** タスク計画Id */
    private Integer taskPlanId = INIT_INTEGER;

    /**
     * タスク計画Idを取得する
     *
     * @return タスク計画Id
     */
    public Integer getTaskPlanId() {
        return taskPlanId;
    }

    /**
     * タスク計画Idを設定する
     *
     * @param taskPlanId タスク計画Id
     */
    public void setTaskPlanId(final Integer taskPlanId) {
        this.taskPlanId = taskPlanId;
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
