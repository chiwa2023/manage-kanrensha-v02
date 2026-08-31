package net.seijishikin.jp.normalize.manage.kanrensha.logic.postal;

import org.springframework.stereotype.Component;

/**
 * 数字から漢数字に変換するUtility
 */
@Component
public class ConvertNumberToKansujiUtil {

    public String practice(final int index) { // SUPPRESS CHECKSTYLE ReturnNumber NOPMD
        // CHECKSTYLE:OFF MagicNumber

        switch (index) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";
            case 10:
                return "十";
            case 11:
                return "十一";
            case 12:
                return "十二";
            case 13:
                return "十三";
            case 14:
                return "十四";
            case 15:
                return "十五";
            case 16:
                return "十六";
            case 17:
                return "十七";
            case 18:
                return "十八";
            case 19:
                return "十九";
            case 20:
                return "二十";

            case 21:
                return "二十一";
            case 22:
                return "二十二";
            case 23:
                return "二十三";
            case 24:
                return "二十四";
            case 25:
                return "二十五";
            case 26:
                return "二十六";
            case 27:
                return "二十七";
            case 28:
                return "二十八";
            case 29:
                return "二十九";
            case 30:
                return "三十";

            case 31:
                return "三十一";
            case 32:
                return "三十二";
            case 33:
                return "三十三";
            case 34:
                return "三十四";
            case 35:
                return "三十五";
            case 36:
                return "三十六";
            case 37:
                return "三十七";
            case 38:
                return "三十八";
            case 39:
                return "三十九";
            case 40:
                return "四十";

            case 41:
                return "四十一";
            case 42:
                return "四十二";
            case 43:
                return "四十三";
            case 44:
                return "四十四";
            case 45:
                return "四十五";
            case 46:
                return "四十六";
            case 47:
                return "四十七";
            case 48:
                return "四十八";
            case 49:
                return "四十九";
            case 50:
                return "五十";
            default:
                throw new IllegalArgumentException("Unexpected value: " + index);
        }

    }

    
}
