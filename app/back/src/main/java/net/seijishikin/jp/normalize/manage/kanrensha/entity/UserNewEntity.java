package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * user_new接続用Entity
 */
@Entity
@Table(name = "user_new")
public class UserNewEntity implements Serializable, DtoEntityInitialValueInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** email */
    @Id
    @Column(name = "email")
    private String email = INIT_STRING;

    /**
     * emailを取得する
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * emailを設定する
     *
     * @param email email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /** email */
    @Column(name = "regist_code")
    private String registCode = INIT_STRING;

    /**
     * emailを取得する
     *
     * @return email
     */
    public String getRegistCode() {
        return registCode;
    }

    /**
     * emailを設定する
     *
     * @param registCode email
     */
    public void setRegistCode(final String registCode) {
        this.registCode = registCode;
    }

    /** 有効期限日時 */
    @Column(name = "limit_datetime")
    private LocalDateTime limitDatetime = INIT_TIMESTAMP;

    /**
     * 有効期限日時を取得する
     *
     * @return 有効期限日時
     */
    public LocalDateTime getLimitDatetime() {
        return limitDatetime;
    }

    /**
     * 有効期限日時を設定する
     *
     * @param limitDatetime 有効期限日時
     */
    public void setLimitDatetime(final LocalDateTime limitDatetime) {
        this.limitDatetime = limitDatetime;
    }

    /** URLダイレクトアクセス用認証トークン */
    @Column(name = "verify_token")
    private String verifyToken = INIT_STRING;

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

    /** URLダイレクトアクセス用認証トークン有効期限日時 */
    @Column(name = "verify_limit_date_time")
    private LocalDateTime verifyLimitDateTime = INIT_TIMESTAMP;

    /**
     * URLダイレクトアクセス用認証トークン有効期限日時を取得する
     * 
     * @return URLダイレクトアクセス用認証トークン有効期限日時
     */
    public LocalDateTime getVerifyLimitDateTime() {
        return verifyLimitDateTime;
    }

    /**
     * URLダイレクトアクセス用認証トークン有効期限日時を設定する
     * 
     * @param verifyLimitDateTime URLダイレクトアクセス用認証トークン有効期限日時
     */
    public void setVerifyLimitDateTime(final LocalDateTime verifyLimitDateTime) {
        this.verifyLimitDateTime = verifyLimitDateTime;
    }
}
