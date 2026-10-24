package net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey;

import java.io.Serializable;
import java.time.LocalDateTime;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * 登録済みパスキー一覧表示用サマリーDto
 */
public class PasskeySummaryDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 内部パスキーID */
    private Long passkeyId = INIT_LONG;

    /** クレデンシャルID (Base64URL) */
    private String credentialId = INIT_STRING;

    /** 端末名称 */
    private String deviceName = INIT_STRING;

    /** 登録日時 */
    private LocalDateTime createdAt;

    /** 最終利用日時 */
    private LocalDateTime lastUsedAt;

    /** AAGUID */
    private String aaguid = INIT_STRING;

    /**
     * 内部パスキーIDを取得する
     * 
     * @return 内部パスキーID
     */
    public Long getPasskeyId() {
        return passkeyId;
    }

    /**
     * 内部パスキーIDを設定する
     * 
     * @param passkeyId 内部パスキーID
     */
    public void setPasskeyId(final Long passkeyId) {
        this.passkeyId = passkeyId;
    }

    /**
     * クレデンシャルIDを取得する
     * 
     * @return クレデンシャルID
     */
    public String getCredentialId() {
        return credentialId;
    }

    /**
     * クレデンシャルIDを設定する
     * 
     * @param credentialId クレデンシャルID
     */
    public void setCredentialId(final String credentialId) {
        this.credentialId = credentialId;
    }

    /**
     * 端末名称を取得する
     * 
     * @return 端末名称
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 端末名称を設定する
     * 
     * @param deviceName 端末名称
     */
    public void setDeviceName(final String deviceName) {
        this.deviceName = deviceName;
    }

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

    /**
     * AAGUIDを取得する
     * 
     * @return AAGUID
     */
    public String getAaguid() {
        return aaguid;
    }

    /**
     * AAGUIDを設定する
     * 
     * @param aaguid AAGUID
     */
    public void setAaguid(final String aaguid) {
        this.aaguid = aaguid;
    }
}
