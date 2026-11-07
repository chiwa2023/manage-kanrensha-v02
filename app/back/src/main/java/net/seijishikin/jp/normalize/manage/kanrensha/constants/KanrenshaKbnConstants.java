package net.seijishikin.jp.normalize.manage.kanrensha.constants;

/**
 * 関連者区分定数
 */
public final class KanrenshaKbnConstants {

    /**
     * コンストラクタ
     */
    private KanrenshaKbnConstants() {

    }

    /** 個人 */
    public static final short PERSON = 1;
    /** 企業／団体 */
    public static final short KIGYOU_DT = 2;
    /** 政治団体 */
    public static final short SEIJIDANTAI = 3;

    /** 個人名称 */
    public static final String LABEL_PERSON = "個人";
    /** 企業／団体名称 */
    public static final String LABEL_KIGYOU_DT = "企業／団体";
    /** 政治団体名称 */
    public static final String LABEL_SEIJIDANTAI = "政治団体";

    /**
     * ユーザ権限に変換する
     * 
     * @param kanrenshKbn 関連者区分
     * @return 権限
     */
    public static String getUserRole(final Short kanrenshKbn) {

        switch ((short) kanrenshKbn) { // NOPMD
            case PERSON:
                return UserRoleConstants.KANRENSHA_PERSON;

            case KIGYOU_DT:
                return UserRoleConstants.KANRENSHA_KIGYOU_DT;

            case SEIJIDANTAI:
                return UserRoleConstants.KANRENSHA_SEIJIDANTAI;

            default:
                throw new IllegalArgumentException("Unexpected value: " + kanrenshKbn);
        }
    }

    /**
     * 関連者区分名称に変換する
     * 
     * @param kanrenshKbn 関連者区分
     * @return 権限
     */
    public static String getLabel(final Short kanrenshKbn) {

        switch ((short) kanrenshKbn) { // NOPMD
            case PERSON:
                return LABEL_PERSON;

            case KIGYOU_DT:
                return LABEL_KIGYOU_DT;

            case SEIJIDANTAI:
                return LABEL_SEIJIDANTAI;

            default:
                throw new IllegalArgumentException("Unexpected value: " + kanrenshKbn);
        }
    }

}
