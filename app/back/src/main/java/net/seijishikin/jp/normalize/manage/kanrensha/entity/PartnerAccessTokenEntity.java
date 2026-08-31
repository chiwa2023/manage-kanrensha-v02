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
 * partner_access_token接続用Entity
 */
@Entity
@Table(name = "partner_access_token")
public class PartnerAccessTokenEntity // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** テーブルID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_access_token_id")
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

    /** ユーザーコード */
    @Column(name = "user_code")
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

    /** アクセストークンハッシュ値 */
    @Column(name = "access_token_hash")
    private String accessTokenHash = INIT_STRING;

    /**
     * アクセストークンハッシュ値を取得する
     *
     * @return アクセストークンハッシュ値
     */
    public String getAccessTokenHash() {
        return accessTokenHash;
    }

    /**
     * アクセストークンハッシュ値を設定する
     *
     * @param accessTokenHash アクセストークンハッシュ値
     */
    public void setAccessTokenHash(final String accessTokenHash) {
        this.accessTokenHash = accessTokenHash;
    }

    /** ユーザ名 */
    @Column(name = "user_name")
    private String userName = INIT_STRING;

    /**
     * トークン名称または説明を取得する
     *
     * @return トークン名称または説明
     */
    public String getUserName() {
        return userName;
    }

    /**
     * トークン名称または説明を設定する
     *
     * @param userName トークン名称または説明
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /** 有効期限 */
    @Column(name = "expires_at")
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
    @Column(name = "created_at")
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
    @Column(name = "last_used_at")
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
    @Column(name = "revoked_at")
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
