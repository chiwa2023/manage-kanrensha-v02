package net.seijishikin.jp.normalize.manage.kanrensha.dto.task_info;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskInfoEntity;

/**
 * タスク情報編集Dto
 */
public class EditTaskInfoCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集Entity */
    private TaskInfoEntity taskInfoEntity = new TaskInfoEntity();

    /**
     * 編集Entityを取得する
     * 
     * @return 編集Entity
     */
    public TaskInfoEntity getTaskInfoEntity() {
        return taskInfoEntity;
    }

    /**
     * 編集Entityを設定する
     * 
     * @param taskInfoEntity 編集Entity
     */
    public void setTaskInfoEntity(final TaskInfoEntity taskInfoEntity) {
        this.taskInfoEntity = taskInfoEntity;
    }

}
