package net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * パスワード更新Dto
 */
public class RefleshPasswordCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 新パスワード */
    private String newPassword = INIT_STRING;

    /** 旧パスワード */
    private String oldPassword = INIT_STRING;

    /**
     * 新パスワードを取得する
     *
     * @return 新パスワード
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * 新パスワードを設定する
     *
     * @param newPassword 新パスワード
     */
    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * 旧パスワードを取得する
     *
     * @return 旧パスワード
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * 旧パスワードを設定する
     *
     * @param oldPassword 旧パスワード
     */
    public void setOldPassword(final String oldPassword) {
        this.oldPassword = oldPassword;
    }

}
