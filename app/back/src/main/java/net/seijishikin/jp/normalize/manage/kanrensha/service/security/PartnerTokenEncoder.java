package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

/**
 * APIパートナートークンをSHA-256でハッシュ化するためのPasswordEncoder
 */
@Component
public class PartnerTokenEncoder implements PasswordEncoder {

    /**
     * 暗号化を行う
     */
    @Override
    public String encode(final CharSequence rawToken) {
        // SHA-256 を使用してハッシュ化
        return digest("SHA-256", rawToken.toString());
    }

    /**
     * 暗号化された文字列で照合を行う
     */
    @Override
    public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
        // このサービスでは照合は不要なため、未実装
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private String digest(final String algorithm, final String value) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] digest = messageDigest.digest(Utf8.encode(value));
            return toHexString(digest);
        } catch (Exception e) { // NOPMD
            throw new RuntimeException(e); // NOPMD
        }
    }

    private String toHexString(final byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
