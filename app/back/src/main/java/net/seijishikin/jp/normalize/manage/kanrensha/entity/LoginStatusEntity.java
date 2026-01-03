package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * login_status接続用Entity
 */
@Entity
@Table(name = "login_status")
public class LoginStatusEntity // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** メールアドレス */
    @Id
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

    /** パスワード */
    @Column(name = "password")
    private String password = INIT_STRING;

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

    /** ログイン可否 */
    @Column(name = "is_success")
    private Boolean isSuccess = INIT_BOOLEAN;

    /**
     * ログイン可否を取得する
     *
     * @return ログイン可否
     */
    public Boolean getIsSuccess() { // NOPMD
        return isSuccess;
    }

    /**
     * ログイン可否を設定する
     *
     * @param isSuccess ログイン可否
     */
    public void setIsSuccess(final Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /** ログイン失敗理由 */
    @Column(name = "fail_reason")
    private String failReason = INIT_STRING;

    /**
     * ログイン失敗理由を取得する
     *
     * @return ログイン失敗理由
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * ログイン失敗理由を設定する
     *
     * @param failReason ログイン失敗理由
     */
    public void setFailReason(final String failReason) {
        this.failReason = failReason;
    }

    /** 無効状態 */
    @Column(name = "disabled")
    private Boolean disabled = INIT_BOOLEAN;

    /**
     * 無効状態を取得する
     *
     * @return 無効状態
     */
    public Boolean getDisabled() { // NOPMD
        return disabled;
    }

    /**
     * 無効状態を設定する
     *
     * @param disabled 無効状態
     */
    public void setDisabled(final Boolean disabled) {
        this.disabled = disabled;
    }

    /** 無効状態理由 */
    @Column(name = "diabled_reason")
    private String diabledReason = INIT_STRING;

    /**
     * 無効状態理由を取得する
     *
     * @return 無効状態理由
     */
    public String getDiabledReason() {
        return diabledReason;
    }

    /**
     * 無効状態理由を設定する
     *
     * @param diabledReason 無効状態理由
     */
    public void setDiabledReason(final String diabledReason) {
        this.diabledReason = diabledReason;
    }

    /** ログイン日時 */
    @Column(name = "login_time")
    private LocalDateTime loginTime = INIT_TIMESTAMP;

    /**
     * ログイン日時を取得する
     *
     * @return ログイン日時
     */
    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    /**
     * ログイン日時を設定する
     *
     * @param loginTime ログイン日時
     */
    public void setLoginTime(final LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    /** パスワード変更時間 */
    @Column(name = "pass_change_time")
    private LocalDateTime passChangeTime = INIT_TIMESTAMP;

    /**
     * パスワード変更時間を取得する
     *
     * @return パスワード変更時間
     */
    public LocalDateTime getPassChangeTime() {
        return passChangeTime;
    }

    /**
     * パスワード変更時間を設定する
     *
     * @param passChangeTime パスワード変更時間
     */
    public void setPassChangeTime(final LocalDateTime passChangeTime) {
        this.passChangeTime = passChangeTime;
    }

}
