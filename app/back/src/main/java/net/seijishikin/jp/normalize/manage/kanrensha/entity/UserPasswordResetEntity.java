package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * user_password_reset接続用Entity
 */
@Entity
@Table(name = "user_password_reset")
public class UserPasswordResetEntity implements Serializable, DtoEntityInitialValueInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** メールアドレス */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email")
    private String email = INIT_STRING;

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

    /** 認証コード */
    @Column(name = "regist_code")
    private String registCode = INIT_STRING;

    /**
     * 認証コードを取得する
     *
     * @return 認証コード
     */
    public String getRegistCode() {
        return registCode;
    }

    /**
     * 認証コードを設定する
     *
     * @param registCode 認証コード
     */
    public void setRegistCode(final String registCode) {
        this.registCode = registCode;
    }

    /** 認証コード有効期限日時 */
    @Column(name = "limit_datetime")
    private LocalDateTime limitDatetime = INIT_TIMESTAMP;

    /**
     * 認証コード有効期限日時を取得する
     *
     * @return 認証コード有効期限日時
     */
    public LocalDateTime getLimitDatetime() {
        return limitDatetime;
    }

    /**
     * 認証コード有効期限日時を設定する
     *
     * @param limitDatetime 認証コード有効期限日時
     */
    public void setLimitDatetime(final LocalDateTime limitDatetime) {
        this.limitDatetime = limitDatetime;
    }

}
