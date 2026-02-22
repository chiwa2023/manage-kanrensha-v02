package net.seijishikin.jp.normalize.manage.kanrensha.utils;

/**
 * チェックデジット作成Utility
 */
public final class PlusCheckDigitUtil {

    /**
     * インスタンス作成除け
     */
    private PlusCheckDigitUtil() {

    }

    /**
     * 5桁地方自体コードにチェックデジットを付与して6桁にする
     * 
     * @param lgCode 地方自体コード5桁
     * @return チェックデジット不可地方自治体コード6桁
     */
    public static String plusForLgCode(final String lgCode) {
        // CHECKSTYLE:OFF MagicNumber

        try {
            int ans6 = Integer.parseInt(lgCode.substring(0, 1)) * 6;
            int ans5 = Integer.parseInt(lgCode.substring(1, 2)) * 5;
            int ans4 = Integer.parseInt(lgCode.substring(2, 3)) * 4;
            int ans3 = Integer.parseInt(lgCode.substring(3, 4)) * 3;
            int ans2 = Integer.parseInt(lgCode.substring(4, 5)) * 2;
            int ans = ans6 + ans5 + ans4 + ans3 + ans2;

            return lgCode + ((11 - (ans % 11)) % 10);

        } catch (NullPointerException nullPointerException) {// NOPMD どうしても中断したい
            throw new IllegalArgumentException("地方自治体コードがnullです", nullPointerException);
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            throw new IllegalArgumentException("引数が5桁未満です:" + lgCode, stringIndexOutOfBoundsException);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("引数が数字に変換できません:" + lgCode, numberFormatException);
        }

    }

}
