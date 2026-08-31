package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

/**
 * 編集ユーザ取得Dto
 */
public class GetUserDtoResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ユーザ最小限Dto */
    private LeastUserDto userDto = new LeastUserDto();

    /**
     * ユーザ最小限Dtoを取得する
     * 
     * @return ユーザ最小限Dto
     */
    public LeastUserDto getUserDto() {
        return userDto;
    }

    /**
     * ユーザ最小限Dtoを設定する
     * 
     * @param userDto ユーザ最小限Dto
     */
    public void setUserDto(final LeastUserDto userDto) {
        this.userDto = userDto;
    }

    /** タスク開始通知有無 */
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
