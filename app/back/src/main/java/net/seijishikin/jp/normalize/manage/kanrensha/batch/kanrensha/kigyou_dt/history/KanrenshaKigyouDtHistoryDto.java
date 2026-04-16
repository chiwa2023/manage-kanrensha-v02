package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.kigyou_dt.history;

import java.io.Serializable;

/**
 * 関連者企業・団体履歴Dto
 */
public class KanrenshaKigyouDtHistoryDto implements Serializable { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 企業・団体名 */
    private String kanrenshaName = INIT_String;

    /**
     * 企業・団体名を取得する
     *
     * @return 企業・団体名
     */
    public String getKanrenshaName() {
        return kanrenshaName;
    }

    /**
     * 企業・団体名を設定する
     *
     * @param kanrenshaName 企業・団体名
     */
    public void setKanrenshaName(final String kanrenshaName) {
        this.kanrenshaName = kanrenshaName;
    }

    /** 企業・団体全住所 */
    private String allAddress = INIT_String;

    /**
     * 企業・団体全住所を取得する
     *
     * @return 企業・団体全住所
     */
    public String getAllAddress() {
        return allAddress;
    }

    /**
     * 企業・団体全住所を設定する
     *
     * @param allAddress 企業・団体全住所
     */
    public void setAllAddress(final String allAddress) {
        this.allAddress = allAddress;
    }

    /** 企業・団体代表者 */
    private String KigyouDtDelegate = INIT_String;

    /**
     * 企業・団体代表者を取得する
     *
     * @return 企業・団体代表者
     */
    public String getKigyouDtDelegate() {
        return KigyouDtDelegate;
    }

    /**
     * 企業・団体代表者を設定する
     *
     * @param KigyouDtDelegate 企業・団体代表者
     */
    public void setKigyouDtDelegate(final String KigyouDtDelegate) {
        this.KigyouDtDelegate = KigyouDtDelegate;
    }

    /** 企業・団体関連者コード */
    private String KigyouDtKanrenshaCode = INIT_String;

    /**
     * 企業・団体関連者コードを取得する
     *
     * @return 企業・団体関連者コード
     */
    public String getKigyouDtKanrenshaCode() {
        return KigyouDtKanrenshaCode;
    }

    /**
     * 企業・団体関連者コードを設定する
     *
     * @param KigyouDtKanrenshaCode 企業・団体関連者コード
     */
    public void setKigyouDtKanrenshaCode(final String KigyouDtKanrenshaCode) {
        this.KigyouDtKanrenshaCode = KigyouDtKanrenshaCode;
    }

    /** 団体代表者関連者コード */
    private String orgDelegateCode = INIT_String;

    /**
     * 団体代表者関連者コードを取得する
     *
     * @return 団体代表者関連者コード
     */
    public String getOrgDelegateCode() {
        return orgDelegateCode;
    }

    /**
     * 団体代表者関連者コードを設定する
     *
     * @param orgDelegateCode 団体代表者関連者コード
     */
    public void setOrgDelegateCode(final String orgDelegateCode) {
        this.orgDelegateCode = orgDelegateCode;
    }

}
