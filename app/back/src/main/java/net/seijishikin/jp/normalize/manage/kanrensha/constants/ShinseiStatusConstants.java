package net.seijishikin.jp.normalize.manage.kanrensha.constants;

/**
 * 関連者区分定数
 */
public final class ShinseiStatusConstants { // NOPMD DataClass

    /**
     * コンストラクタ
     */
    private ShinseiStatusConstants() {

    }

    /** 未定 */
    public static final short MITEI = 0;

    /** 申請中 */
    public static final short PROMOTE = 1;

    /** 却下 */
    public static final short REJECT = 2;

    /** 承認 */
    public static final short ACCEPT = 3;

    /** 追加調査 */
    public static final short RESEARCH = 4;

}
