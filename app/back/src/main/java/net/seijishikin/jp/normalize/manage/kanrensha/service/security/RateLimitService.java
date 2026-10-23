package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import net.seijishikin.jp.normalize.manage.kanrensha.config.RateLimitConfig;

/**
 * Bucket4jを用いたレートリミット管理Service
 */
@Service
public class RateLimitService {

    /** レートリミット設定 */
    @Autowired
    private RateLimitConfig rateLimitConfig;

    /** キーごとのバケットキャッシュ */
    private final Map<String, Bucket> bucketCache = new ConcurrentHashMap<>();

    /**
     * 指定されたキー（IPまたはトークン等）に対してトークンを1つ消費できるか試行する
     *
     * @param key クライアント識別キー
     * @return リクエスト許可の場合true、制限超過の場合false
     */
    public boolean tryConsume(final String key) {
        return this.tryConsume(key, 1L);
    }

    /**
     * 指定されたキーに対して指定トークン数を消費できるか試行する
     *
     * @param key       クライアント識別キー
     * @param numTokens 消費トークン数
     * @return リクエスト許可の場合true、制限超過の場合false
     */
    public boolean tryConsume(final String key, final long numTokens) {

        if (!rateLimitConfig.isEnabled()) {
            return true;
        }

        if (key == null || key.isBlank()) {
            return true;
        }

        Bucket bucket = bucketCache.computeIfAbsent(key, _ -> this.createNewBucket());
        return bucket.tryConsume(numTokens);
    }

    /**
     * 新しいバケットインスタンスを生成する
     *
     * @return Bucket
     */
    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.builder()
                .capacity(rateLimitConfig.getCapacity())
                .refillGreedy(rateLimitConfig.getRefillTokens(),
                        Duration.ofSeconds(rateLimitConfig.getRefillDurationSeconds()))
                .build();

        return Bucket.builder().addLimit(limit).build();
    }

    /**
     * キャッシュを初期化する（テスト等で使用）
     */
    public void clearCache() {
        bucketCache.clear();
    }
}
