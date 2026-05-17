package net.seijishikin.jp.normalize.manage.kanrensha.dto.task;


import java.io.Serializable;
import java.time.LocalDate;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;


/**
 * StackTrace取得条件Dto
 */
public class GetTaskStackTraceCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable,DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** タスク計画登録年 */
    private Integer taskYear = INIT_INTEGER;

    /** タスク計画コード */
    private Integer taskPlanCode = INIT_INTEGER;

    /**
     * タスク計画登録年を取得する
     *
     * @return タスク計画登録年
     */
    public Integer getTaskYear() {
        return taskYear;
    }

    /**
     * タスク計画登録年を設定する
     *
     * @param taskYear タスク計画登録年
     */
    public void setTaskYear(final Integer taskYear) {
        this.taskYear = taskYear;
    }

    /**
     * タスク計画コードを取得する
     *
     * @return タスク計画コード
     */
    public Integer getTaskPlanCode() {
        return taskPlanCode;
    }

    /**
     * タスク計画コードを設定する
     *
     * @param taskPlanCode タスク計画コード
     */
    public void setTaskPlanCode(final Integer taskPlanCode) {
        this.taskPlanCode = taskPlanCode;
    }

    /** 指定日 */
    private LocalDate pointedDate = INIT_DATE;

    /**
     * 指定日を取得する
     *
     * @return 指定日
     */
    public LocalDate getPointedDate() {
        return pointedDate;
    }

    /**
     * 指定日を設定する
     *
     * @param pointedDate 指定日
     */
    public void setPointedDate(final LocalDate pointedDate) {
        this.pointedDate = pointedDate;
    }

}
