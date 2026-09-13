package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;

/**
 * 関連者コード移動承認Dto
 */
public class MoveKanrenshaCodeAcceptCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 関連者コード移動申請Entity */
    private KanrenshaCodeMoveEntity kanrenshaCodeMoveEntity;

    /**
     * 関連者コード移動申請Entityを指定する
     * 
     * @return 関連者コード移動申請Entity
     */
    public KanrenshaCodeMoveEntity getKanrenshaCodeMoveEntity() {
        return kanrenshaCodeMoveEntity;
    }

    /**
     * 関連者コード移動申請Entityを設定する
     * 
     * @param kanrenshaCodeMoveEntity 関連者コード移動申請Entity
     */
    public void setKanrenshaCodeMoveEntity(final KanrenshaCodeMoveEntity kanrenshaCodeMoveEntity) {
        this.kanrenshaCodeMoveEntity = kanrenshaCodeMoveEntity;
    }

    /** タスク計画Id */
    private Integer taskPlanId;

    /** タスク計画発生年 */
    private Integer taskYear;

    /**
     * タスク計画Idを指定する
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

    /**
     * タスク計画発生年を指定する
     * 
     * @return タスク計画発生年
     */
    public Integer getTaskYear() {
        return taskYear;
    }

    /**
     * タスク計画発生年を設定する
     * 
     * @param taskYear タスク計画発生年
     */
    public void setTaskYear(final Integer taskYear) {
        this.taskYear = taskYear;
    }
}
