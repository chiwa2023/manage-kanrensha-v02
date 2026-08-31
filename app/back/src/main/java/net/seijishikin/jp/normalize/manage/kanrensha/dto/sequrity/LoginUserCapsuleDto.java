package net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * ユーザログインDto
 */
public class LoginUserCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ユーザId */
    private String userId = INIT_STRING;

    /** パスワード */
    private String password = INIT_STRING;

    /**
     * ユーザIdを取得する
     *
     * @return ユーザId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIdを設定する
     *
     * @param userId ユーザId
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * パスワードを取得する
     * 
     *
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定する
     *
     * @param password パスワード
     */
    public void setPassword(final String password) {
        this.password = password;
    }

}
