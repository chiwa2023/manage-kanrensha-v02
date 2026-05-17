package net.seijishikin.jp.normalize.manage.kanrensha.constants;

/**
 * タスク情報定数
 */
public class TaskInfoConstants { // NOPMD DataClass

    /** 郵便番号差分CSV保存 */
    public static final int SAVE_POSTAL_REPAIR_CSV = 101;

    /** 地方自治体コード更新 */
    public static final int SAVE_ADDRESS_BASE_CSV = 151;

    /** 関連者XML読み取りバッチ */
    public static final int WKTBL_KANRENSHA_XML = 301;
    /** 関連者XML読み取り再処理 */
    public static final int RETRY_KANRENSHA_XML = 302;

    /** 関連者標準ダンプ個人 */
    public static final int DUMP_STD_PERSON = 311;
    /** 関連者標準ダンプ企業 */
    public static final int DUMP_STD_KIGYOU = 312;
    /** 関連者標準ダンプ政治団体 */
    public static final int DUMP_STD_SEIJIDANTAI = 313;
    /** 関連者最小ダンプ個人 */
    public static final int DUMP_MIN_PERSON = 314;
    /** 関連者最小ダンプ企業 */
    public static final int DUMP_MIN_KIGYOU = 315;
    /** 関連者最小ダンプ政治団体 */
    public static final int DUMP_MIN_SEIJIDANTAI = 316;
    /** 関連者履歴ダンプ個人 */
    public static final int DUMP_HISTORY_PERSON = 317;
    /** 関連者履歴ダンプ企業 */
    public static final int DUMP_HISTORY_KIGYOU = 318;
    /** 関連者履歴ダンプ政治団体 */
    public static final int DUMP_HISTORY_SEIJIDANTAI = 319;

    /** 関連者標準ダンプ差分個人 */
    public static final int DUMP_STD_SABUN_PERSON = 321;
    /** 関連者標準ダンプ差分企業 */
    public static final int DUMP_STD_SABUN_KIGYOU = 322;
    /** 関連者標準ダンプ差分政治団体 */
    public static final int DUMP_STD_SABUN_SEIJIDANTAI = 323;
    /** 関連者最小ダンプ差分個人 */
    public static final int DUMP_MIN_SABUN_PERSON = 324;
    /** 関連者最小ダンプ差分企業 */
    public static final int DUMP_MIN_SABUN_KIGYOU = 325;
    /** 関連者最小ダンプ差分政治団体 */
    public static final int DUMP_MIN_SABUN_SEIJIDANTAI = 326;
    /** 関連者履歴ダンプ差分個人 */
    public static final int DUMP_HISTORY_SABUN_PERSON = 327;
    /** 関連者履歴ダンプ差分企業 */
    public static final int DUMP_HISTORY_SABUN_KIGYOU = 328;
    /** 関連者履歴ダンプ差分政治団体 */
    public static final int DUMP_HISTORY_SABUN_SEIJIDANTAI = 329;

    /** 関連者個人標準ファイル登録 */
    public static final int FILE_PERSON_STD = 351;
    /** 関連者企業団体標準再分類 */
    public static final int FILE_KIGYOU_STD = 352;
    /** 関連者政治団体標準再分類 */
    public static final int FILE_SEIJIDANTAI_STD = 353;
    /** 関連者個人最小再分類 */
    public static final int FILE_PERSON_MIN = 354;
    /** 関連者企業団体最小再分類 */
    public static final int FILE_KIGYOU_MIN = 355;
    /** 関連者政治団体最小再分類 */
    public static final int FILE_SEIJIDANTAI_MIN = 356;
    /** 関連者個人履歴再分類 */
    public static final int FILE_PERSON_HISTORY = 357;
    /** 関連者企業団体履歴再分類 */
    public static final int FILE_KIGYOU_HISTORY = 358;
    /** 関連者政治団体履歴再分類 */
    public static final int FILE_SEIJIDANTAI_HISTORY = 359;

    /** 関連者個人標準再分類 */
    public static final int RETRY_PERSON_STD = 361;
    /** 関連者企業団体標準再分類 */
    public static final int RETRY_KIGYOU_STD = 362;
    /** 関連者政治団体標準再分類 */
    public static final int RETRY_SEIJIDANTAI_STD = 363;
    /** 関連者個人最小再分類 */
    public static final int RETRY_PERSON_MIN = 364;
    /** 関連者企業団体最小再分類 */
    public static final int RETRY_KIGYOU_MIN = 365;
    /** 関連者政治団体最小再分類 */
    public static final int RETRY_SEIJIDANTAI_MIN = 366;
    /** 関連者個人履歴再分類 */
    public static final int RETRY_PERSON_HISTORY = 367;
    /** 関連者企業団体履歴再分類 */
    public static final int RETRY_KIGYOU_HISTORY = 368;
    /** 関連者政治団体履歴再分類 */
    public static final int RETRY_SEIJIDANTAI_HISTORY = 369;

    /** 関連者紐づけ企業 */
    public static final int COMBINE_KIGYOU = 370;
    /** 関連者紐づけ政治団体 */
    public static final int COMBINE_SEIJIDANTAI = 371;
    /** 関連者紐づけ再処理 */
    public static final int COMBINE_RETRY = 372;

    
    /** 関連者他者紐づけ */
    public static final int ORG_COMBINE_PERSON_KANRENSHA = 801;
    
    /** 利用者他者紐づけ */
    public static final int ORG_COMBINE_PERSON_RIYOUSHA = 802;
    
    /** SE権限推薦 */
    public static final int PROMOTE_ADMIN = 901;

}
