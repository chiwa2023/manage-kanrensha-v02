package net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * パスキー登録検証リクエストDto
 */
public class PasskeyRegistrationVerifyRequestDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** クレデンシャルID (Base64URL) */
    private String credentialId = INIT_STRING;

    /** rawId (Base64URL) */
    private String rawId = INIT_STRING;

    /** clientDataJSON (Base64URL) */
    private String clientDataJSON = INIT_STRING;

    /** attestationObject (Base64URL) */
    private String attestationObject = INIT_STRING;

    /** 端末名称 (例: iPhone TouchID, PC Windows Hello) */
    private String deviceName = INIT_STRING;

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
     * rawId を取得する
     * 
     * @return rawId
     */
    public String getRawId() {
        return rawId;
    }

    /**
     * rawIdを設定する
     * 
     * @param rawId rawId
     */
    public void setRawId(final String rawId) {
        this.rawId = rawId;
    }

    /**
     * clientDataJSONを取得する
     * 
     * @return clientDataJSON
     */
    public String getClientDataJSON() {
        return clientDataJSON;
    }

    /**
     * clientDataJSONを設定する
     * 
     * @param clientDataJSON clientDataJSON
     */
    public void setClientDataJSON(final String clientDataJSON) {
        this.clientDataJSON = clientDataJSON;
    }

    /**
     * attestationObjectを取得する
     * 
     * @return attestationObject
     */
    public String getAttestationObject() {
        return attestationObject;
    }

    /**
     * attestationObjectを設定する
     * 
     * @param attestationObject attestationObject
     */
    public void setAttestationObject(final String attestationObject) {
        this.attestationObject = attestationObject;
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
}
