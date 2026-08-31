package net.seijishikin.jp.normalize.manage.kanrensha.dto.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;

/**
 * タスク計画履歴検索結果Dto
 */
public class SearchTaskHistoryResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** タスク計画リスト */
    private List<TaskPlanBaseEntity> listTaskHistory = new ArrayList<>();

    /**
     * タスク計画リストを取得する
     *
     * @return タスク計画リスト
     */
    public List<TaskPlanBaseEntity> getListTaskHistory() {
        return listTaskHistory;
    }

    /**
     * タスク計画リストを設定する
     *
     * @param listTaskHistory タスク計画リスト
     */
    public void setListTaskHistory(final List<TaskPlanBaseEntity> listTaskHistory) {
        this.listTaskHistory = listTaskHistory;
    }

}
