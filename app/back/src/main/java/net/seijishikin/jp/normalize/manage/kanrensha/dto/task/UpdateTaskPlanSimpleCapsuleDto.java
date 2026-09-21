package net.seijishikin.jp.normalize.manage.kanrensha.dto.task;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * タスク計画単純更新Dto
 */
public class UpdateTaskPlanSimpleCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
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
