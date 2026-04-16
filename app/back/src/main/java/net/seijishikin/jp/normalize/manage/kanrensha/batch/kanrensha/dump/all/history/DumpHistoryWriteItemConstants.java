package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.history;

/**
 * csvダンプカラム定義定数履歴用
 */
public class DumpHistoryWriteItemConstants {

    /**
     * 企業団体用カラム定義定数
     */
    public class KigyouDt {
        /** 書き出すカラム列名 */
        public static final String[] NAMES = { "kigyouDtKanrenshaCode", "allName", "allAddress", "orgDelegateName",
                "orgDelegateCode", "insertTimestamp" };

        /** 書き出すカラム表示名称 */
        public static final String[] HEADERS = { "\"関連者番号\"", "\"名称\"", "\"全住所\"", "\"代表者名\"", "\"代表者コード\"",
                "\"登録日時\"" };
    }

    /**
     * 個人用カラム定義定数
     */
    public class Person {
        /** 書き出すカラム列名 */
        public static final String[] NAMES = { "personKanrenshaCode", "allName", "allAddress", "personShokugyou", "insertTimestamp" };

        /** 書き出すカラム表示名称 */
        public static final String[] HEADERS = { "\"関連者番号\"", "\"名称\"", "\"全住所\"", "\"個人職業\"", "\"登録日時\"" };
    }

    /**
     * 政治団体用カラム定義定数
     */
    public class Seijidantai {
        /** 書き出すカラム列名 */
        public static final String[] NAMES = { "seijidantaiKanrenshaCode", "allName", "allAddress", "orgDelegateName",
                "orgDelegateCode", "insertTimestamp" };

        /** 書き出すカラム表示名称 */
        public static final String[] HEADERS = { "\"関連者番号\"", "\"名称\"", "\"全住所\"", "\"代表者名\"", "\"代表者コード\"", "\"登録日時\"" };
    }

}
