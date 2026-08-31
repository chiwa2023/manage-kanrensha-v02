package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * partner_access_history_2026接続用Entity
 */
@Entity
public class PartnerAccessHistoryBaseEntity implements Serializable, DtoEntityInitialValueInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_access_history_id")
    private Integer partnerAccessHistoryId = INIT_INTEGER;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getPartnerAccessHistoryId() {
        return partnerAccessHistoryId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param partnerAccessHistoryId テーブルId
     */
    public void setPartnerAccessHistoryId(final Integer partnerAccessHistoryId) {
        this.partnerAccessHistoryId = partnerAccessHistoryId;
    }

    /** ユーザコード */
    @Column(name = "user_code")
    private Integer userCode = INIT_INTEGER;

    /**
     * ユーザコードを取得する
     *
     * @return ユーザコード
     */
    public Integer getUserCode() {
        return userCode;
    }

    /**
     * ユーザコードを設定する
     *
     * @param userCode ユーザコード
     */
    public void setUserCode(final Integer userCode) {
        this.userCode = userCode;
    }

    /** ユーザ名 */
    @Column(name = "user_name")
    private String userName = INIT_STRING;

    /**
     * ユーザ名を取得する
     *
     * @return ユーザ名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ユーザ名を設定する
     *
     * @param userName ユーザ名
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /** 接続Url */
    @Column(name = "access_url")
    private String accessUrl = INIT_STRING;

    /**
     * 接続Urlを取得する
     *
     * @return 接続Url
     */
    public String getAccessUrl() {
        return accessUrl;
    }

    /**
     * 接続Urlを設定する
     *
     * @param accessUrl 接続Url
     */
    public void setAccessUrl(final String accessUrl) {
        this.accessUrl = accessUrl;
    }

    /** IPアドレス */
    @Column(name = "ip_address")
    private String ipAddress = INIT_STRING;

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

    /** ユーザエージェント */
    @Column(name = "user_agent")
    private String userAgent = INIT_STRING;

    /**
     * ユーザエージェントを取得する
     *
     * @return ユーザエージェント
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * ユーザエージェントを設定する
     *
     * @param userAgent ユーザエージェント
     */
    public void setUserAgent(final String userAgent) {
        this.userAgent = userAgent;
    }

    /** 接続日時 */
    @Column(name = "attempt_time")
    private LocalDateTime attemptTime = INIT_TIMESTAMP;

    /**
     * 接続日時を取得する
     *
     * @return 接続日時
     */
    public LocalDateTime getAttemptTime() {
        return attemptTime;
    }

    /**
     * 接続日時を設定する
     *
     * @param attemptTime 接続日時
     */
    public void setAttemptTime(final LocalDateTime attemptTime) {
        this.attemptTime = attemptTime;
    }

    /** 接続成功フラグ */
    @Column(name = "is_success")
    private Boolean isSuccess = INIT_BOOLEAN;

    /**
     * 接続成功フラグを取得する
     *
     * @return 接続成功フラグ
     */
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    /**
     * 接続成功フラグを設定する
     *
     * @param isSuccess 接続成功フラグ
     */
    public void setIsSuccess(final Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

}
