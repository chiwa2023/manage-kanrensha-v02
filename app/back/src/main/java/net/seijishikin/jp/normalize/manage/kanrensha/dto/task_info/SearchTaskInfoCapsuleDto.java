package net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.NaturalTextSearchPagingCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * タスク情報検索条件Dto
 */
public class SearchTaskInfoCapsuleDto extends NaturalTextSearchPagingCapsuleDto
        implements PagingIntegerDtoInterface, Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** タスク種類 */
    private String taskType = INIT_STRING;

    /**
     * タスク種類を取得する
     * 
     * @return タスク種類
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * タスク種類を設定する
     * 
     * @param taskType タスク種類
     */
    public void setTaskType(final String taskType) {
        this.taskType = taskType;
    }

}
