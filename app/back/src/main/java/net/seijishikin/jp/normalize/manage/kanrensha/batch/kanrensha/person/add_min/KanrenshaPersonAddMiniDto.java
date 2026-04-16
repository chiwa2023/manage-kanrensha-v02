package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.add_min;

import java.io.Serializable;

/**
 * 関連者個人履歴Dto
 */
public class KanrenshaPersonAddMiniDto implements Serializable { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 個人名 */
    private String kanrenshaName = INIT_String;

    /**
     * 個人名を取得する
     *
     * @return 個人名
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 個人名を設定する
     *
     * @param kanrenshaName 個人名
     */
    public void setKanrenshaName(final String kanrenshaName) {
        this.kanrenshaName = kanrenshaName;
    }

    /** 個人全住所 */
    private String allAddress = INIT_String;

    /**
     * 個人全住所を取得する
     *
     * @return 個人全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 個人全住所を設定する
     *
     * @param allAddress 個人全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 個人職業 */
    private String personShokugyou = INIT_String;

    /**
     * 個人職業を取得する
     *
     * @return 個人職業
     */
    public String getPersonShokugyou() {
        return personShokugyou;
    }

    /**
     * 個人職業を設定する
     *
     * @param personShokugyou 個人職業
     */
    public void setPersonShokugyou(final String personShokugyou) {
        this.personShokugyou = personShokugyou;
    }

}
