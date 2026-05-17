package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import java.io.Serializable;

import jakarta.persistence.Column;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * ユーザ情報編集Dto
 */
public class EditUserPersonCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** タスク開始通知有無 */
    @Column(name = "is_alert_task_start")
    private Boolean isAlertTaskStart = INIT_BOOLEAN;

    /**
     * タスク開始通知有無を取得する
     *
     * @return タスク開始通知有無
     */
    public Boolean getIsAlertTaskStart() {
        return isAlertTaskStart;
    }

    /**
     * タスク開始通知有無を設定する
     *
     * @param isAlertTaskStart タスク開始通知有無
     */
    public void setIsAlertTaskStart(final Boolean isAlertTaskStart) {
        this.isAlertTaskStart = isAlertTaskStart;
    }

    /** タスク終了通知有無 */
    @Column(name = "is_alert_task_end")
    private Boolean isAlertTaskEnd = INIT_BOOLEAN;

    /**
     * タスク終了通知有無を取得する
     *
     * @return タスク終了通知有無
     */
    public Boolean getIsAlertTaskEnd() {
        return isAlertTaskEnd;
    }

    /**
     * タスク終了通知有無を設定する
     *
     * @param isAlertTaskEnd タスク終了通知有無
     */
    public void setIsAlertTaskEnd(final Boolean isAlertTaskEnd) {
        this.isAlertTaskEnd = isAlertTaskEnd;
    }

}
