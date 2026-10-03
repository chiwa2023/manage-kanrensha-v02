package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * UTCをローカル(東京)変換Util
 */
public final class ConvertDatetimeToLocalUtil {

    /**
     * コンストラクタ(インスタンス生成除け)
     */
    private ConvertDatetimeToLocalUtil() {

    }

    /**
     * 処理を行う
     * 
     * @param localDateTime グリニッジ標準時
     * @return ローカル日時(東京)
     */
    public static LocalDateTime practice(final LocalDateTime localDateTime) {

        return localDateTime.plusHours(9L); // SUPPRESS CHECKSTYLE MagicNumber
    }

    /**
     * 処理を行う(日付)
     * 
     * @param localDate グリニッジ標準時
     * @return ローカル(東京)日付
     */
    public static LocalDate practice(final LocalDate localDate) {

        LocalDateTime utc = LocalDateTime.now(ZoneId.of("GMT"));

        LocalDateTime local = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));

        if (utc.getDayOfMonth() + 1 == local.getDayOfMonth()) {
            return localDate.plusDays(1L);
        } else {
            return localDate;
        }

    }

}
