package net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * パスキー登録用ChallengeレスポンスDto
 */
public class PasskeyRegistrationChallengeDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** WebAuthn Challenge (Base64URL) */
    private String challenge = INIT_STRING;

    /** Relying Party ID */
    private String rpId = INIT_STRING;

    /** Relying Party 名称 */
    private String rpName = INIT_STRING;

    /** ユーザ人物コード */
    private Integer userPersonCode = INIT_INTEGER;

    /** ユーザ名 (ログインID/メールアドレス) */
    private String userName = INIT_STRING;

    /** ユーザ表示名 */
    private String userDisplayName = INIT_STRING;

    /** 登録済みクレデンシャルID除外リスト (二重登録防止) */
    private List<String> excludeCredentialIds = new ArrayList<>();

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
     * Relying Party IDを取得する
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
     * Relying Party 名称を取得する
     * 
     * @return Relying Party 名称
     */
    public String getRpName() {
        return rpName;
    }

    /**
     * Relying Party 名称を設定する
     * 
     * @param rpName Relying Party 名称
     */
    public void setRpName(final String rpName) {
        this.rpName = rpName;
    }

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

    /**
     * ユーザ名 (メールアドレス)を取得する
     * 
     * @return ユーザ名 (メールアドレス)
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ユーザ名 (メールアドレス)を設定する
     * 
     * @param userName ユーザ名 (メールアドレス)
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * ユーザ表示名を取得する
     * 
     * @return ユーザ表示名ユーザ表示名
     */
    public String getUserDisplayName() {
        return userDisplayName;
    }

    /**
     * ユーザ表示名を設定する
     * 
     * @param userDisplayName ユーザ表示名
     */
    public void setUserDisplayName(final String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    /**
     * 登録済みクレデンシャルID除外リストを取得する
     * 
     * @return 登録済みクレデンシャルID除外リスト
     */
    public List<String> getExcludeCredentialIds() {
        return excludeCredentialIds;
    }

    /**
     * 登録済みクレデンシャルID除外リストを設定する
     * 
     * @param excludeCredentialIds 登録済みクレデンシャルID除外リスト
     */
    public void setExcludeCredentialIds(final List<String> excludeCredentialIds) {
        this.excludeCredentialIds = excludeCredentialIds;
    }
}
