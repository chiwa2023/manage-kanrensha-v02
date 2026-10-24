package net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * パスキー認証検証リクエストDto
 */
public class PasskeyAuthenticationVerifyRequestDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** Pre-Auth Token (1段階目で取得した一時トークン) */
    private String preAuthToken = INIT_STRING;

    /** クレデンシャルID (Base64URL) */
    private String credentialId = INIT_STRING;

    /** rawId (Base64URL) */
    private String rawId = INIT_STRING;

    /** clientDataJSON (Base64URL) */
    private String clientDataJSON = INIT_STRING;

    /** authenticatorData (Base64URL) */
    private String authenticatorData = INIT_STRING;

    /** signature (Base64URL) */
    private String signature = INIT_STRING;

    /** userHandle (Base64URL, 任意) */
    private String userHandle = INIT_STRING;

    /**
     * Pre-Auth Tokenを取得する
     * 
     * @return Pre-Auth Token
     */
    public String getPreAuthToken() {
        return preAuthToken;
    }

    /**
     * Pre-Auth Tokenを設定する
     * 
     * @param preAuthToken Pre-Auth Token
     */
    public void setPreAuthToken(final String preAuthToken) {
        this.preAuthToken = preAuthToken;
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
     * rawIdを取得する
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
     * clientDataJSON を設定する
     * 
     * @param clientDataJSON clientDataJSON
     */
    public void setClientDataJSON(final String clientDataJSON) {
        this.clientDataJSON = clientDataJSON;
    }

    /**
     * authenticatorDataを取得する
     * 
     * @return authenticatorData
     */
    public String getAuthenticatorData() {
        return authenticatorData;
    }

    /**
     * authenticatorDataを設定する
     * 
     * @param authenticatorData authenticatorData
     */
    public void setAuthenticatorData(final String authenticatorData) {
        this.authenticatorData = authenticatorData;
    }

    /**
     * signatureを取得する
     * 
     * @return signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * signatureを設定する
     * 
     * @param signature signature
     */
    public void setSignature(final String signature) {
        this.signature = signature;
    }

    /**
     * userHandleを取得する
     * 
     * @return userHandle
     */
    public String getUserHandle() {
        return userHandle;
    }

    /**
     * userHandleを設定する
     * 
     * @param userHandle userHandle
     */
    public void setUserHandle(final String userHandle) {
        this.userHandle = userHandle;
    }
}
