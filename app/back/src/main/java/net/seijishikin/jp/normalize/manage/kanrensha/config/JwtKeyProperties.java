package net.seijishikin.jp.normalize.manage.kanrensha.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JWT鍵設定(リソースから鍵を取得する)
 */
@Component
public class JwtKeyProperties { // NOPMD DataClass

    /** 公開鍵 */
    @Value("${jwt.key.publickey}")
    private RSAPublicKey publicKey;

    /** 秘密鍵 */
    @Value("${jwt.key.privatekey}")
    private RSAPrivateKey privateKey;

    /**
     * RSA公開鍵を取得する
     *
     * @return 公開鍵
     */
    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * RSA公開鍵を設定する
     *
     * @param publicKey 公開鍵
     */
    public void setPublicKey(final RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * RSA秘密鍵を取得する
     *
     * @return RSA秘密鍵
     */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * RSA秘密鍵を設定する
     *
     * @param privateKey RSA秘密鍵
     */
    public void setPrivateKey(final RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }

}