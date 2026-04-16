package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.dump.all.master;

/**
 * csvダンプカラム定義定数マスタ用
 */
public class DumpMasterWriteItemConstants {

    /**
     * 企業団体用カラム定義定数
     */
    public class KigyouDt {

        /** 書き出すカラム列名 */
        public static final String[] NAMES = { "kigyouDtKanrenshaCode", "kanrenshaName", "orgNameKana", "allAddress",
                "houjinNo", "phon", "myPortalUrl", "orgDelegateCode", "kigyouDtDelegate", "insertTimestamp" };

        /** 書き出すカラム表示名称 */
        public static final String[] HEADERS = { "\"関連者番号\"", "\"名称\"", "\"名称かな\"", "\"全住所\"", "\"法人番号\"", "\"電話番号\"",
                "\"代表URL\"", "\"代表者コード\"", "\"代表者名\"", "\"登録日時\"" };
    }

    /**
     * 個人用カラム定義定数
     */
    public class Person {

        /** 書き出すカラム列名 */
        public static final String[] NAMES = { "personKanrenshaCode", "kanrenshaName", "allAddress", "personShokugyou",
                "Phon", "myPortalUrl", "insertTimestamp" };

        /** 書き出すカラム表示名称 */
        public static final String[] HEADERS = { "\"関連者番号\"", "\"名称\"", "\"全住所\"", "\"個人職業\"", "\"電話番号\"",
                "\"個人サイトURL\"", "\"登録日時\"" };
    }

    /**
     * 政治団体用カラム定義定数
     */
    public class Seijidantai {

        /** 書き出すカラム列名 */
        public static final String[] NAMES = { "seijidantaiKanrenshaCode", "kanrenshaName", "orgNameKana", "allAddress",
                "poliOrgNo", "dantaiKbn", "dantaiKbnLabel", "phon", "myPortalUrl", "orgDelegateCode",
                "seijidantaiDelegate", "accountMgrCode", "accountMgrName", "insertTimestamp" };

        /** 書き出すカラム表示名称 */
        public static final String[] HEADERS = { "\"関連者番号\"", "\"名称\"", "\"名称かな\"", "\"全住所\"", "\"政治団体番号\"", "\"団体区分\"",
                "\"団体区分名称\"", "\"電話番号\"", "\"代表サイトURL\"", "\"代表者番号\"", "\"代表者名\"", "\"会計責任者番号\"", "\"会計責任者氏名\"",
                "\"登録日時\"" };
    }

}
