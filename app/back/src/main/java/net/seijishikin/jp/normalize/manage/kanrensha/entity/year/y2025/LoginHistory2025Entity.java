package net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025;

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
 * login_history_2025接続用Entity
 */
@Entity
@Table(name = "login_history_2025")
public class LoginHistory2025Entity // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルid */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_history_id")
    private Integer loginHistoryId = INIT_INTEGER;

    /** ログイン成否 */
    @Column(name = "is_success")
    private Boolean isSuccess = INIT_BOOLEAN;

    /** メールアドレス */
    @Column(name = "email")
    private String email = INIT_STRING;

    /** IPアドレス */
    @Column(name = "ip_address")
    private String ipAddress = INIT_STRING;

    /**
     * テーブルidを取得する
     *
     * @return テーブルid
     */
    public Integer getLoginHistoryId() {
        return loginHistoryId;
    }

    /**
     * テーブルidを設定する
     *
     * @param loginHistoryId テーブルid
     */
    public void setLoginHistoryId(final Integer loginHistoryId) {
        this.loginHistoryId = loginHistoryId;
    }

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
     * IPアドレスを取得する
     *
     * @return IPアドレス
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * IPアドレスを設定する
     *
     * @param ipAddress IPアドレス
     */
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /** user-agent */
    @Column(name = "user_agent")
    private String userAgent = INIT_STRING;

    /**
     * user-agentを取得する
     *
     * @return user-agent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * user-agentを設定する
     *
     * @param userAgent user-agent
     */
    public void setUserAgent(final String userAgent) {
        this.userAgent = userAgent;
    }

    /** 試行日時 */
    @Column(name = "attempt_time")
    private LocalDateTime attemptTime = INIT_TIMESTAMP;

    /**
     * 試行日時を取得する
     *
     * @return 試行日時
     */
    public LocalDateTime getAttemptTime() {
        return attemptTime;
    }

    /**
     * 試行日時を設定する
     *
     * @param attemptTime 試行日時
     */
    public void setAttemptTime(final LocalDateTime attemptTime) {
        this.attemptTime = attemptTime;
    }

    /**
     * ログイン成否を取得する
     *
     * @return ログイン成否
     */
    public Boolean getIsSuccess() { // NOPMD
        return isSuccess;
    }

    /**
     * ログイン成否を設定する
     *
     * @param isSuccess ログイン成否
     */
    public void setIsSuccess(final Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

}
