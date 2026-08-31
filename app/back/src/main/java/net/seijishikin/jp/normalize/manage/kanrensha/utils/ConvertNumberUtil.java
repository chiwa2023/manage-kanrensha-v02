package net.seijishikin.jp.normalize.manage.kanrensha.utils;

/**
 * 全角数字半角変換Util
 */
public final class ConvertNumberUtil {

    /**
     * コンストラクタ(インスタンス作成除け)
     */
    private ConvertNumberUtil() {

    }

    /**
     * 処理を行う
     * 
     * @param data 全角数字の文字列
     * @return 半角数字
     */
    public static String practice(final String data) {

        String dt0 = data.replaceAll("０", "0");
        String dt1 = dt0.replaceAll("１", "1");
        String dt2 = dt1.replaceAll("２", "2");
        String dt3 = dt2.replaceAll("３", "3");
        String dt4 = dt3.replaceAll("４", "4");
        String dt5 = dt4.replaceAll("５", "5");
        String dt6 = dt5.replaceAll("６", "6");
        String dt7 = dt6.replaceAll("７", "7");
        String dt8 = dt7.replaceAll("８", "8");

        return dt8.replaceAll("９", "9");

    }
}
