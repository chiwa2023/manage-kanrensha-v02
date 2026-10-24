package net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * パスキー登録結果Dto
 */
public class PasskeyRegistrationResultDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 登録成功フラグ */
    private boolean success = INIT_BOOLEAN;

    /** 内部パスキーID */
    private Long passkeyId = INIT_LONG;

    /** クレデンシャルID */
    private String credentialId = INIT_STRING;

    /** 端末名称 */
    private String deviceName = INIT_STRING;

    /** メッセージ */
    private String message = INIT_STRING;

    /**
     * 登録成功フラグを取得する
     * 
     * @return 登録成功フラグ
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 登録成功フラグを設定する
     * 
     * @param success 登録成功フラグ
     */
    public void setSuccess(final boolean success) {
        this.success = success;
    }

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
     * メッセージを取得する
     * 
     * @return メッセージ
     */
    public String getMessage() {
        return message;
    }

    /**
     * メッセージを設定する
     * 
     * @param message メッセージ
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
