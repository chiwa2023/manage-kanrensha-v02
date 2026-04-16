package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.min;

/**
 * csvダンプカラム定義定数マスタ用
 */
public class DumpMinWriteItemConstants {

    /**
     * 企業団体用カラム定義定数
     */
    public class KigyouDt {

        /** 書き出すカラム列名 */
        public static final String[] NAMES = { "kigyouDtKanrenshaCode", "kanrenshaName", "allAddress",
                "kigyouDtDelegate", "houjinNo", "insertTimestamp" };

        /** 書き出すカラム表示名称 */
        public static final String[] HEADERS = { "\"関連者番号\"", "\"名称\"", "\"全住所\"", "\"代表者名\"", "\"法人番号\"", "\"登録日時\"" };
    }

    /**
     * 個人用カラム定義定数
     */
    public class Person {

        /** 書き出すカラム列名 */
        public static final String[] NAMES = { "personKanrenshaCode", "kanrenshaName", "allAddress", "personShokugyou",
                "insertTimestamp" };

        /** 書き出すカラム表示名称 */
        public static final String[] HEADERS = { "\"関連者番号\"", "\"名称\"", "\"全住所\"", "\"個人職業\"", "\"登録日時\"" };
    }

    /**
     * 政治団体用カラム定義定数
     */
    public class Seijidantai {

        /** 書き出すカラム列名 */
        public static final String[] NAMES = { "seijidantaiKanrenshaCode", "kanrenshaName", "allAddress",
                "seijidantaiDelegate", "dantaiKbn", "poliOrgNo", "insertTimestamp" };

        /** 書き出すカラム表示名称 */
        public static final String[] HEADERS = { "\"関連者番号\"", "\"名称\"", "\"全住所\"", "\"代表者名\"", "\"団体区分\"", "\"政治団体番号\"",
                "\"登録日時\"" };
    }

}
