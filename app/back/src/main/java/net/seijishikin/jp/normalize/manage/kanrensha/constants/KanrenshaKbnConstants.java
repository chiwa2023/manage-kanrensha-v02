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

}
