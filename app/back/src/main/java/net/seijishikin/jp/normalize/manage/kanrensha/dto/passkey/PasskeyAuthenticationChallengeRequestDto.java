package net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * パスキー認証Challenge要求Dto
 */
public class PasskeyAuthenticationChallengeRequestDto implements Serializable,DtoEntityInitialValueInterface {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** Pre-Auth Token (1段階目認証で発行された一時トークン) */
    private String preAuthToken = INIT_STRING;

    /**
     * Pre-Auth Tokenを取得する
     * 
     * @return Pre-Auth TokenPre-Auth Token
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
}
