package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * ユーザ削除条件Dto
 */
public class DeleteUserCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 退会理由 */
    private String withdrawReason = INIT_STRING;

    /**
     * 退会理由を取得する
     * 
     * @return 退会理由
     */
    public String getWithdrawReason() {
        return withdrawReason;
    }

    /**
     * 退会理由を設定する
     * 
     * @param withdrawReason 退会理由
     */
    public void setWithdrawReason(final String withdrawReason) {
        this.withdrawReason = withdrawReason;
    }

}
