package net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

/**
 * ログイン結果Dto
 */
public class LoginUserResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ユーザ最小限Dto */
    private LeastUserDto userDto = new LeastUserDto();

    /** Jwtトークン */
    private JwtTokenDto jwtTokenDto;

    /** パスキー2段階認証要求フラグ */
    private Boolean requiresPasskey = false;

    /** Pre-Auth Token (2段階目認証用一時トークン) */
    private String preAuthToken = "";

    /** WebAuthn Challenge (Base64URL) */
    private String challenge = "";

    /** 許可クレデンシャルIDリスト */
    private java.util.List<String> allowCredentials = new java.util.ArrayList<>();

    /**
     * パスキー2段階認証要求フラグを取得する
     *
     * @return パスキー2段階認証要求フラグ
     */
    public Boolean getRequiresPasskey() {
        return requiresPasskey;
    }

    /**
     * パスキー2段階認証要求フラグを設定する
     *
     * @param requiresPasskey パスキー2段階認証要求フラグ
     */
    public void setRequiresPasskey(final Boolean requiresPasskey) {
        this.requiresPasskey = requiresPasskey;
    }

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
     * 許可クレデンシャルIDリストを取得する
     *
     * @return 許可クレデンシャルIDリスト
     */
    public java.util.List<String> getAllowCredentials() {
        return allowCredentials;
    }

    /**
     * 許可クレデンシャルIDリストを設定する
     *
     * @param allowCredentials 許可クレデンシャルIDリスト
     */
    public void setAllowCredentials(final java.util.List<String> allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    
    /**
     * ユーザ最小限Dtoを取得する
     * 
     * @return ユーザ最小限Dto
     */
    public LeastUserDto getUserDto() {
        return userDto;
    }

    /**
     * ユーザ最小限Dtoを設定する
     * 
     * @param userDto ユーザ最小限Dto
     */
    public void setUserDto(final LeastUserDto userDto) {
        this.userDto = userDto;
    }

    /**
     * Jwtトークンを取得する
     *
     * @return Jwtトークン
     */
    public JwtTokenDto getJwtTokenDto() {
        return jwtTokenDto;
    }

    /**
     * Jwtトークンを設定する
     *
     * @param jwtTokenDto Jwtトークン
     */
    public void setJwtTokenDto(final JwtTokenDto jwtTokenDto) {
        this.jwtTokenDto = jwtTokenDto;
    }

}
