package net.seijishikin.jp.normalize.manage.kanrensha.constants;

/**
 * ダンプファイル名定数
 */
public class MasterCsvFileNameConstants {

    /** マスタ出力フォルダ */
    public static final String FOLDER_MASTER = "/dump_master";

    /** マスタ差分出力フォルダ */
    public static final String FOLDER_MASTER_SABUN = "/dump_master_sabun";

    /**
     * マスタ最小ファイル名定数
     */
    public class MasterMin { // NOPMD DataCalss
        /** マスタ最小一括Zip */
        public static final String MASTER_MIN_ZIP = "master_min_all.zip";

        /** マスタ最小企業／団体(ファイル) */
        public static final String MASTER_MIN_KIGYOU = "master_kigyou_dt_min.csv";
        /** マスタ最小個人(ファイル) */
        public static final String MASTER_MIN_PERSON = "master_person_min.csv";
        /** マスタ最小政治団体(ファイル) */
        public static final String MASTER_MIN_SEIJIDANTAI = "master_seijidantai_min.csv";
    }

    /**
     * マスタ標準ファイル名定数
     */
    public class MasterStd { // NOPMD DataCalss
        /** マスタ最小一括Zip */
        public static final String MASTER_STD_ZIP = "master_std_all.zip";

        /** マスタ最小企業／団体(ファイル) */
        public static final String MASTER_STD_KIGYOU = "master_kigyou_dt_std.csv";
        /** マスタ最小個人(ファイル) */
        public static final String MASTER_STD_PERSON = "master_person_std.csv";
        /** マスタ最小政治団体(ファイル) */
        public static final String MASTER_STD_SEIJIDANTAI = "master_seijidantai_std.csv";
    }

    /**
     * 差分マスタ最小ファイル名定数
     */
    public class SabunMasterMin { // NOPMD DataCalss
        /** マスタ最小差分一括Zip */
        public static final String SABUN_MIN_ZIP = "sabun_master_min_all.zip";

        /** 差分マスタ最小企業／団体(ファイル) */
        public static final String SABUN_MIN_KIGYOU = "sabun_master_kigyou_dt_min.csv";
        /** 差分マスタ最小個人(ファイル) */
        public static final String SABUN_MIN_PERSON = "sabun_master_person_min.csv";
        /** 差分マスタ最小政治団体(ファイル) */
        public static final String SABUN_MIN_SEIJIDANTAI = "sabun_master_seijidantai_min.csv";
    }

    /**
     * マスタ標準ファイル名定数
     */
    public class SabunMasterStd { // NOPMD DataCalss
        /** マスタ標準差分一括Zip */
        public static final String SABUN_STD_ZIP = "sabun_master_std_all.zip";

        /** 差分マスタ標準企業／団体(ファイル) */
        public static final String SABUN_STD_KIGYOU = "sabun_master_kigyou_dt_std.csv";
        /** 差分マスタ標準個人(ファイル) */
        public static final String SABUN_STD_PERSON = "sabun_master_person_std.csv";
        /** 差分マスタ標準政治団体(ファイル) */
        public static final String SABUN_STD_SEIJIDANTAI = "sabun_master_seijidantai_std.csv";
    }

}
