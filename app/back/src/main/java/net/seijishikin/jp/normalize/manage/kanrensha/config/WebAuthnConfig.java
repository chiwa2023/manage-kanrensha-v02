package net.seijishikin.jp.normalize.manage.kanrensha.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.webauthn4j.WebAuthnManager;
import com.webauthn4j.converter.util.ObjectConverter;

/**
 * WebAuthn (パスキー) 設定クラス
 */
@Configuration
public class WebAuthnConfig {

    /** Relying Party ID */
    @Value("${app.webauthn.rp-id:localhost}")
    private String rpId;

    /** Relying Party 名称 */
    @Value("${app.webauthn.rp-name:manage-kanrensha}")
    private String rpName;

    /** 許可オリジンリスト */
    @Value("${app.webauthn.allowed-origins:http://localhost:5173,http://localhost:6180}")
    private List<String> allowedOrigins;

    /**
     * WebAuthnManagerのBean定義
     *
     * @param objectConverter ObjectConverter
     * @return WebAuthnManager
     */
    @Bean
    protected WebAuthnManager webAuthnManager(final ObjectConverter objectConverter) {
        return WebAuthnManager.createNonStrictWebAuthnManager(objectConverter);
    }

    /**
     * ObjectConverterのBean定義
     *
     * @return ObjectConverter
     */
    @Bean
    protected ObjectConverter objectConverter() {
        return new ObjectConverter();
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
     * Relying Party 名称を取得する
     *
     * @return Relying Party 名称
     */
    public String getRpName() {
        return rpName;
    }

    /**
     * 許可オリジンリストを取得する
     *
     * @return 許可オリジンリスト
     */
    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    /**
     * 指定されたオリジンが許可されているか判定する
     *
     * @param origin リクエスト元のオリジン
     * @return 許可されている場合true
     */
    public boolean isOriginAllowed(final String origin) {
        if (origin == null || origin.isBlank()) {
            return false;
        }

        // ポートやプロトコルによる柔軟な判定はfalse
        return allowedOrigins != null && allowedOrigins.contains(origin);
    }
}
