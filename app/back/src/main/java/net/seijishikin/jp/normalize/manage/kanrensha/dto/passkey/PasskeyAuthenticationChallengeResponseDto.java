package net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * パスキー認証Challenge応答Dto
 */
public class PasskeyAuthenticationChallengeResponseDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** WebAuthn Challenge (Base64URL) */
    private String challenge = INIT_STRING;

    /** Relying Party ID */
    private String rpId = INIT_STRING;

    /** 許可クレデンシャルIDリスト (このユーザが登録しているクレデンシャルID群) */
    private List<String> allowCredentials = new ArrayList<>();

    /**
     * WebAuthn Challengeを取得する
     * 
     * @return WebAuthn Challenge
     */
    public String getChallenge() {
        return challenge;
    }

    /**
     * WebAuthn Challengeを設定する
     * 
     * @param challenge WebAuthn Challenge
     */
    public void setChallenge(final String challenge) {
        this.challenge = challenge;
    }

    /**
     * Relying Party ID を取得する
     * 
     * @return Relying Party ID
     */
    public String getRpId() {
        return rpId;
    }

    /**
     * Relying Party IDを設定する
     * 
     * @param rpId Relying Party ID
     */
    public void setRpId(final String rpId) {
        this.rpId = rpId;
    }

    /**
     * 許可クレデンシャルIDリストを取得する
     * 
     * @return 許可クレデンシャルIDリスト
     */
    public List<String> getAllowCredentials() {
        return allowCredentials;
    }

    /**
     * 許可クレデンシャルIDリストを設定する
     * 
     * @param allowCredentials 許可クレデンシャルIDリスト
     */
    public void setAllowCredentials(final List<String> allowCredentials) {
        this.allowCredentials = allowCredentials;
    }
}
