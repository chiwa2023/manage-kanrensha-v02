package net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * パスワードリセット処理入力格納Dto 
 * ログインしていない(できない)想定となる
 */
public class ResetPassswordCapsuleDto // NOPMD DataClass
        implements DtoEntityInitialValueInterface, Serializable {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** メールアドレス */
    private String email = INIT_STRING;

    /** 認証コード */
    private String accessCode = INIT_STRING;

    /** パスワード */
    private String password = INIT_STRING;

    /**
     * メールアドレスを取得する
     * 
     * @return メールアドレス
     */
    public String getEmail() {
        return email;
    }

    /**
     * メールアドレスを設定する
     * 
     * @param email メールアドレス
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * 認証コードを取得する
     * 
     * @return 認証コード
     */
    public String getAccessCode() {
        return accessCode;
    }

    /**
     * 認証コードを設定する
     * 
     * @param accessCode 認証コード
     */
    public void setAccessCode(final String accessCode) {
        this.accessCode = accessCode;
    }

    /**
     * パスワードを取得する
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
