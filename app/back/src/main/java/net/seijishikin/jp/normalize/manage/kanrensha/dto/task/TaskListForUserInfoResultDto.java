package net.seijishikin.jp.normalize.manage.kanrensha.dto.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;

/**
 * タスク未処理リスト格納Dto
 */
public class TaskListForUserInfoResultDto // NOPMD DataClass
        implements DtoEntityInitialValueInterface, Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 本年度未処理タスク計画リスト */
    private List<TaskPlanBaseEntity> listThisYear = new ArrayList<>();

    /** 前年度未処理タスク計画リスト */
    private List<TaskPlanBaseEntity> listLastYear = new ArrayList<>();

    /** リスト更新済状態 */
    private Boolean isRefreshed = INIT_BOOLEAN;

    /**
     * 本年度未処理タスク計画リストを取得する
     * 
     * @return 本年度未処理タスク計画リスト
     */
    public List<TaskPlanBaseEntity> getListThisYear() {
        return listThisYear;
    }

    /**
     * 本年度未処理タスク計画リストを設定する
     * 
     * @param listThisYear 本年度未処理タスク計画リスト
     */
    public void setListThisYear(final List<TaskPlanBaseEntity> listThisYear) {
        this.listThisYear = listThisYear;
    }

    /**
     * 前年度未処理タスク計画リストを取得する
     * 
     * @return 前年度未処理タスク計画リスト
     */
    public List<TaskPlanBaseEntity> getListLastYear() {
        return listLastYear;
    }

    /**
     * 前年度未処理タスク計画リストを設定する
     * 
     * @param listLastYear 前年度未処理タスク計画リスト
     */
    public void setListLastYear(final List<TaskPlanBaseEntity> listLastYear) {
        this.listLastYear = listLastYear;
    }

    /**
     * リスト更新済状態を取得する
     * 
     * @return リスト更新済状態
     */
    public Boolean getIsRefreshed() {
        return isRefreshed;
    }

    /**
     * リスト更新済状態を設定する
     * 
     * @param isRefreshed リスト更新済状態
     */
    public void setIsRefreshed(final Boolean isRefreshed) {
        this.isRefreshed = isRefreshed;
    }

}
