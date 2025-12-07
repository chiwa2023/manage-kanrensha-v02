package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.seijidantai.add_min;

import java.io.Serializable;

/**
 * 関連者政治団体履歴Dto
 */
public class KanrenshaSeijidantaiAddMiniDto implements Serializable { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 政治団体名 */
    private String kanrenshaName = INIT_String;

    /**
     * 政治団体名を取得する
     *
     * @return 政治団体名
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 政治団体名を設定する
     *
     * @param kanrenshaName 政治団体名
     */
    public void setKanrenshaName(final String kanrenshaName) {
        this.kanrenshaName = kanrenshaName;
    }

    /** 政治団体全住所 */
    private String allAddress = INIT_String;

    /**
     * 政治団体全住所を取得する
     *
     * @return 政治団体全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 政治団体全住所を設定する
     *
     * @param allAddress 政治団体全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 政治団体代表者 */
    private String KigyouDtDelegate = INIT_String;

    /**
     * 政治団体代表者を取得する
     *
     * @return 政治団体代表者
     */
    public String getSeijidantaiDelegate() {
        return KigyouDtDelegate;
    }

    /**
     * 政治団体代表者を設定する
     *
     * @param KigyouDtDelegate 政治団体代表者
     */
    public void setSeijidantaiDelegate(final String KigyouDtDelegate) {
        this.KigyouDtDelegate = KigyouDtDelegate;
    }

    /** 政治団体区分 */
    private String dantaiKbn = INIT_String;

    /**
     * 政治団体区分を取得する
     *
     * @return 政治団体区分
     */
    public String getDantaiKbn() {
        return dantaiKbn;
    }

    /**
     * 政治団体区分を設定する
     *
     * @param dantaiKbn 政治団体区分
     */
    public void setDantaiKbn(final String dantaiKbn) {
        this.dantaiKbn = dantaiKbn;
    }

}
