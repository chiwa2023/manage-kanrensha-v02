package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Component;

/**
 * IPアドレス・CIDRサブネット検証ユーティリティ
 */
@Component
public class IpAddressMatcherUtil {

    /**
     * クライアントIPが許可IP表現（単一IP、CIDR表記、カンマ区切り複数指定）に一致するか検証する
     *
     * @param allowedIpExpression 許可IP表現（例: "192.168.1.1", "192.168.1.0/24", "127.0.0.1,10.0.0.0/8"）
     * @param clientIp            検証対象のクライアントIPアドレス
     * @return マッチする場合true、それ以外false
     */
    public boolean matches(final String allowedIpExpression, final String clientIp) {

        if (allowedIpExpression == null || allowedIpExpression.isBlank()) {
            return false;
        }

        if (clientIp == null || clientIp.isBlank()) {
            return false;
        }

        String[] expressions = allowedIpExpression.split(",");
        for (String expression : expressions) {
            String trimmed = expression.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            try {
                IpAddressMatcher matcher = new IpAddressMatcher(trimmed); // NOPMD 
                if (matcher.matches(clientIp)) {
                    return true;
                }
            } catch (IllegalArgumentException e) { // NOPMD 不正フォーマットのIP設定値はスキップ
                // フォーマット不正の個別設定は不一致扱いとして次へ
                continue;
            }
        }

        return false;
    }
}
