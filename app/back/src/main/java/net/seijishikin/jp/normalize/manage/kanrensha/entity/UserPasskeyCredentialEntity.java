package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * user_passkey_credential接続用Entity
 */
@Entity
@Table(name = "user_passkey_credential")
public class UserPasskeyCredentialEntity implements Serializable, DtoEntityInitialValueInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 内部ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passkey_id")
    private Long passkeyId = INIT_LONG;

    /**
     * 内部IDを取得する
     *
     * @return 内部ID
     */
    public Long getPasskeyId() {
        return passkeyId;
    }

    /**
     * 内部IDを設定する
     *
     * @param passkeyId 内部ID
     */
    public void setPasskeyId(final Long passkeyId) {
        this.passkeyId = passkeyId;
    }

    /** ユーザ人物コード */
    @Column(name = "user_person_code")
    private Integer userPersonCode = INIT_INTEGER;

    /**
     * ユーザ人物コードを取得する
     *
     * @return ユーザ人物コード
     */
    public Integer getUserPersonCode() {
        return userPersonCode;
    }

    /**
     * ユーザ人物コードを設定する
     *
     * @param userPersonCode ユーザ人物コード
     */
    public void setUserPersonCode(final Integer userPersonCode) {
        this.userPersonCode = userPersonCode;
    }

    /** WebAuthnクレデンシャルID (Base64URL) */
    @Column(name = "credential_id")
    private String credentialId = INIT_STRING;

    /**
     * WebAuthnクレデンシャルID (Base64URL)を取得する
     *
     * @return WebAuthnクレデンシャルID (Base64URL)
     */
    public String getCredentialId() {
        return credentialId;
    }

    /**
     * WebAuthnクレデンシャルID (Base64URL)を設定する
     *
     * @param credentialId WebAuthnクレデンシャルID (Base64URL)
     */
    public void setCredentialId(final String credentialId) {
        this.credentialId = credentialId;
    }

    /** 公開鍵 (COSEフォーマット) */
    @Lob
    @Column(name = "public_key_cose")
    private byte[] publicKeyCose;

    /**
     * 公開鍵 (COSEフォーマット)を取得する
     *
     * @return 公開鍵 (COSEフォーマット)
     */
    public byte[] getPublicKeyCose() {
        return publicKeyCose; // NOPMD InternalArray
    }

    /**
     * 公開鍵 (COSEフォーマット)を設定する
     *
     * @param publicKeyCose 公開鍵 (COSEフォーマット)
     */
    public void setPublicKeyCose(final byte[] publicKeyCose) {
        this.publicKeyCose = publicKeyCose; // NOPMD ArrayStoredDirect
    }

    /** 署名カウンタ (クローン検知用) */
    @Column(name = "sign_count")
    private Long signCount = INIT_LONG;

    /**
     * 署名カウンタ (クローン検知用)を取得する
     *
     * @return 署名カウンタ (クローン検知用)
     */
    public Long getSignCount() {
        return signCount;
    }

    /**
     * 署名カウンタ (クローン検知用)を設定する
     *
     * @param signCount 署名カウンタ (クローン検知用)
     */
    public void setSignCount(final Long signCount) {
        this.signCount = signCount;
    }

    /** 認証器GUID */
    @Column(name = "aaguid")
    private String aaguid = INIT_STRING;

    /**
     * 認証器GUIDを取得する
     *
     * @return 認証器GUID
     */
    public String getAaguid() {
        return aaguid;
    }

    /**
     * 認証器GUIDを設定する
     *
     * @param aaguid 認証器GUID
     */
    public void setAaguid(final String aaguid) {
        this.aaguid = aaguid;
    }

    /** 端末名称 (例: iPhone TouchID、・。・ PC Windows Hello) */
    @Column(name = "device_name")
    private String deviceName = INIT_STRING;

    /**
     * 端末名称 (例: iPhone TouchID、・。・ PC Windows Hello)を取得する
     *
     * @return 端末名称 (例: iPhone TouchID、・。・ PC Windows Hello)
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 端末名称 (例: iPhone TouchID、・。・ PC Windows Hello)を設定する
     *
     * @param deviceName 端末名称 (例: iPhone TouchID、・。・ PC Windows Hello)
     */
    public void setDeviceName(final String deviceName) {
        this.deviceName = deviceName;
    }

    /** 登録日時 */
    @Column(name = "created_at")
    private LocalDateTime createdAt = INIT_TIMESTAMP;

    /**
     * 登録日時を取得する
     *
     * @return 登録日時
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * 登録日時を設定する
     *
     * @param createdAt 登録日時
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

}
