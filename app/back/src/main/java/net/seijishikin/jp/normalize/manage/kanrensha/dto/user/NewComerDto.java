package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * 新規ユーザDto
 */
public class NewComerDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** メールアドレス */
    private String mailAddress = INIT_STRING;

    /** 登録名 */
    private String nickName = INIT_STRING;

    /** パスワード */
    private String password = INIT_STRING;

    /** 登録用コード */
    private String registCode = INIT_STRING;

    /** ユーザ区分(ロール・権限) */
    private String role;

    /** 有効期限 */
    private LocalDateTime limitDateTime = INIT_TIMESTAMP;

    /** URLダイレクトアクセス用認証トークン */
    private String verifyToken = INIT_STRING;

    /**
     * メールアドレスを取得する
     *
     * @return メールアドレス
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * メールアドレスを設定する
     *
     * @param mailAddress メールアドレス
     */
    public void setMailAddress(final String mailAddress) {
        this.mailAddress = mailAddress;
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

    /**
     * 登録用コードを取得する
     *
     * @return 登録用コード
     */
    public String getRegistCode() {
        return registCode;
    }

    /**
     * 登録用コードを設定する
     *
     * @param registCode 登録用コード
     */
    public void setRegistCode(final String registCode) {
        this.registCode = registCode;
    }

    /**
     * ユーザ区分(ロール・権限)を取得する
     *
     * @return ユーザ区分(ロール・権限)
     */
    public String getRole() {
        return role;
    }

    /**
     * ユーザ区分(ロール・権限)を設定する
     *
     * @param role ユーザ区分(ロール・権限)
     */
    public void setRole(final String role) {
        this.role = role;
    }

    /**
     * 有効期限を取得する
     *
     * @return 有効期限
     */
    public LocalDateTime getLimitDateTime() {
        return limitDateTime;
    }

    /**
     * 有効期限を設定する
     *
     * @param limitDateTime 有効期限
     */
    public void setLimitDateTime(final LocalDateTime limitDateTime) {
        this.limitDateTime = limitDateTime;
    }

    /**
     * 登録名を取得する
     *
     * @return 登録名
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 登録名を設定する
     *
     * @param nickName 登録名
     */
    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

    /**
     * URLダイレクトアクセス用認証トークンを取得する
     *
     * @return URLダイレクトアクセス用認証トークン
     */
    public String getVerifyToken() {
        return verifyToken;
    }

    /**
     * URLダイレクトアクセス用認証トークンを設定する
     *
     * @param verifyToken URLダイレクトアクセス用認証トークン
     */
    public void setVerifyToken(final String verifyToken) {
        this.verifyToken = verifyToken;
    }

}
