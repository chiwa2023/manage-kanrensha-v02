package net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * APIパートナー長期トークン属性Dto
 */
public class PartnerAccessTokenStateDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルID */
    private Integer partnerAccessTokenId = INIT_INTEGER;

    /**
     * テーブルIDを取得する
     *
     * @return テーブルID
     */
    public Integer getPartnerAccessTokenId() {
        return partnerAccessTokenId;
    }

    /**
     * テーブルIDを設定する
     *
     * @param partnerAccessTokenId テーブルID
     */
    public void setPartnerAccessTokenId(final Integer partnerAccessTokenId) {
        this.partnerAccessTokenId = partnerAccessTokenId;
    }

    /** ユーザーID */
    @Column(name = "user_id")
    private Integer userCode = INIT_INTEGER;

    /**
     * ユーザーコードを取得する
     *
     * @return ユーザーコード
     */
    public Integer getUserCode() {
        return userCode;
    }

    /**
     * ユーザーコードを設定する
     *
     * @param userCode ユーザーコード
     */
    public void setUserCode(final Integer userCode) {
        this.userCode = userCode;
    }

    /** ユーザ名称 */
    private String userName = INIT_STRING;

    /**
     * ユーザ名称を取得する
     *
     * @return ユーザ名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ユーザ名称を設定する
     *
     * @param userName ユーザ名称
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /** 有効期限 */
    private LocalDateTime expiresAt = INIT_TIMESTAMP;

    /**
     * 有効期限を取得する
     *
     * @return 有効期限
     */
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    /**
     * 有効期限を設定する
     *
     * @param expiresAt 有効期限
     */
    public void setExpiresAt(final LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    /** 発行日時 */
    private LocalDateTime createdAt = INIT_TIMESTAMP;

    /**
     * 発行日時を取得する
     *
     * @return 発行日時
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * 発行日時を設定する
     *
     * @param createdAt 発行日時
     */
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /** 最終利用日時 */
    private LocalDateTime lastUsedAt = INIT_TIMESTAMP;

    /**
     * 最終利用日時を取得する
     *
     * @return 最終利用日時
     */
    public LocalDateTime getLastUsedAt() {
        return lastUsedAt;
    }

    /**
     * 最終利用日時を設定する
     *
     * @param lastUsedAt 最終利用日時
     */
    public void setLastUsedAt(final LocalDateTime lastUsedAt) {
        this.lastUsedAt = lastUsedAt;
    }

    /** 失効日時 */
    private LocalDateTime revokedAt = INIT_TIMESTAMP;

    /**
     * 失効日時を取得する
     *
     * @return 失効日時
     */
    public LocalDateTime getRevokedAt() {
        return revokedAt;
    }

    /**
     * 失効日時を設定する
     *
     * @param revokedAt 失効日時
     */
    public void setRevokedAt(final LocalDateTime revokedAt) {
        this.revokedAt = revokedAt;
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

}
