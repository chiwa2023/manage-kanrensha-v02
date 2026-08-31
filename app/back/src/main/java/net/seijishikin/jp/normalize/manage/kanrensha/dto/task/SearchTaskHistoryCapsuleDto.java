package net.seijishikin.jp.normalize.manage.kanrensha.dto.task;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * タスク計画履歴検索結果Dto
 */
public class SearchTaskHistoryCapsuleDto implements Serializable,DtoEntityInitialValueInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** タスク計画年 */
    private Integer taskYear = INIT_INTEGER;

    /** タスク計画コード */
    private Integer taskPlanCode = INIT_INTEGER;

    /**
     * タスク計画年を取得する
     *
     * @return タスク計画年
     */
    public Integer getTaskYear() {
        return taskYear;
    }

    /**
     * タスク計画年を設定する
     *
     * @param taskYear タスク計画年
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

}
