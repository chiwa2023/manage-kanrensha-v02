package net.seijishikin.jp.normalize.manage.kanrensha.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * レートリミット設定
 */
@Component
public class RateLimitConfig { // NOPMD DataClass

    /** レートリミット有効化フラグ */
    @Value("${app.rate-limit.enabled:true}")
    private boolean enabled;

    /** バケット最大容量 (リクエスト数) */
    @Value("${app.rate-limit.capacity:60}")
    private long capacity;

    /** 補充トークン数 */
    @Value("${app.rate-limit.refill-tokens:60}")
    private long refillTokens;

    /** 補充間隔秒数 */
    @Value("${app.rate-limit.refill-duration-seconds:60}")
    private long refillDurationSeconds;

    /**
     * レートリミット有効化フラグを取得する
     *
     * @return 有効化フラグ
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * レートリミット有効化フラグを設定する
     *
     * @param enabled 有効化フラグ
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * バケット最大容量を取得する
     *
     * @return 最大容量
     */
    public long getCapacity() {
        return capacity;
    }

    /**
     * バケット最大容量を設定する
     *
     * @param capacity 最大容量
     */
    public void setCapacity(final long capacity) {
        this.capacity = capacity;
    }

    /**
     * 補充トークン数を取得する
     *
     * @return 補充トークン数
     */
    public long getRefillTokens() {
        return refillTokens;
    }

    /**
     * 補充トークン数を設定する
     *
     * @param refillTokens 補充トークン数
     */
    public void setRefillTokens(final long refillTokens) {
        this.refillTokens = refillTokens;
    }

    /**
     * 補充間隔秒数を取得する
     *
     * @return 補充間隔秒数
     */
    public long getRefillDurationSeconds() {
        return refillDurationSeconds;
    }

    /**
     * 補充間隔秒数を設定する
     *
     * @param refillDurationSeconds 補充間隔秒数
     */
    public void setRefillDurationSeconds(final long refillDurationSeconds) {
        this.refillDurationSeconds = refillDurationSeconds;
    }
}
