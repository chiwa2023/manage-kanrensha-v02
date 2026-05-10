package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * 利用者組織紐づけ承諾処理Dto
 */
public class AcceptRiyoushaCombineCapsuleDto extends FrameworkCapsuleDto // NOPMD DataCalss
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** タスク計画Id */
    private Integer taskPlanId = INIT_INTEGER;

    /** 発生年 */
    private Integer taskYear = INIT_INTEGER;

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
     * @param taskPlanId タスク計画Idタスク計画Id
     */
    public void setTaskPlanId(final Integer taskPlanId) {
        this.taskPlanId = taskPlanId;
    }

    /**
     * 発生年を取得する
     * 
     * @return 発生年
     */
    public Integer getTaskYear() {
        return taskYear;
    }

    /**
     * 発生年を設定する
     * 
     * @param taskYear 発生年
     */
    public void setTaskYear(final Integer taskYear) {
        this.taskYear = taskYear;
    }

    /** 承諾状態 */
    private Boolean isAsscept = INIT_BOOLEAN;

    /**
     * 承諾状態を取得する
     * 
     * @return 承諾状態
     */
    public Boolean getIsAsscept() {
        return isAsscept;
    }

    /**
     * 承諾状態を設定する
     * 
     * @param isAsscept 承諾状態
     */
    public void setIsAsscept(final Boolean isAsscept) {
        this.isAsscept = isAsscept;
    }

    /** 仮紐づけId */
    private Integer orgTempId = INIT_INTEGER;

    /**
     * 仮紐づけIdを取得する
     * 
     * @return 仮紐づけId
     */
    public Integer getOrgTempId() {
        return orgTempId;
    }

    /**
     * 仮紐づけIdを設定する
     * 
     * @param orgTempId 仮紐づけId
     */
    public void setOrgTempId(final Integer orgTempId) {
        this.orgTempId = orgTempId;
    }

}
