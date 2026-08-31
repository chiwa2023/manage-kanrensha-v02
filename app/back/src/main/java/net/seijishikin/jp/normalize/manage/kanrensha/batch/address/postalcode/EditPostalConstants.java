package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

/**
 * 郵便番号編集定数
 */
public class EditPostalConstants {

    /** 追加ファイルデータ */
    public static final String ADD = "1";

    /** 削除ファイルデータ */
    public static final String DELETTE = "2";

    /**
     * 郵便番号差分ファイル編集理由
     */
    public class HenkouRiyu { // NOPMD DataClass

        /** 市政・区政・町政・分区・政令指定都市施行 */
        public static final String SEIREI_SHITEITOSHI = "1";

        /** 住居表示の実施 */
        public static final String JUUKYO_HYOUJI = "2";

        /** 区画整理 */
        public static final String KUKAU_SEIRI = "3";

        /** 郵便区調整等 */
        public static final String YUUBIN_CHOUSEI = "4";

        /** 訂正 */
        public static final String TEISEI = "5";

        /** 削除 */
        public static final String DELETE = "6";

    }
}
