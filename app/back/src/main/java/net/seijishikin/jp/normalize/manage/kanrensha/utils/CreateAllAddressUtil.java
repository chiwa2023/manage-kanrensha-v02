package net.seijishikin.jp.normalize.manage.kanrensha.utils;

import java.util.Objects;

/**
 * 全住所を作成する
 */
public final class CreateAllAddressUtil {

    /**
     * コンストラクタ(インスタンス生成除け)
     */
    private CreateAllAddressUtil() {

    }

    /**
     * 全住所を作成する
     * 
     * @param postal   住所郵便番号まで
     * @param block    住所番地まで
     * @param building 住所建物
     * @return 全住所
     */
    public static String practice(final String postal, final String block, final String building) {

        return convertNullToBlank(postal) + convertNullToBlank(block) + "　" + convertNullToBlank(building);
    }

    /**
     * 全住所を作成する(X年から施行される番地を出力しない対応)
     * 
     * @param postal   住所郵便番号まで
     * @param block    住所番地まで
     * @param building 住所建物
     * @return 全住所
     */
    public static String practiceTanshuku(final String postal, final String block, final String building) {

        return convertNullToBlank(postal);
    }

    /**
     * nullを空白に変換する
     * 
     * @param data データ
     * @return 変換後文字列
     */
    private static String convertNullToBlank(final String data) {

        if (Objects.isNull(data)) {
            return "";
        } else {
            return data;
        }

    }

}
