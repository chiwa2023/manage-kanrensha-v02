package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import java.io.Serializable;

/**
 * 個人団体紐づけCsvDto
 */
public class KanrenshaCombineOrgDto implements Serializable { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Short) */
    private static final Short INIT_Short = 0;

    /** 紐づけ関連者区分 */
    private Short kanrenshaKbn = INIT_Short;

    /**
     * 紐づけ関連者区分を取得する
     *
     * @return 紐づけ関連者区分
     */
    public Short getKanrenshaKbn() {
        return kanrenshaKbn;
    }

    /**
     * 紐づけ関連者区分を設定する
     *
     * @param kanrenshaKbn 紐づけ関連者区分
     */
    public void setKanrenshaKbn(final Short kanrenshaKbn) {
        this.kanrenshaKbn = kanrenshaKbn;
    }

    /** 個人関連者コード */
    private String personKanrenshaCode = INIT_String;

    /**
     * 個人関連者コードを取得する
     *
     * @return 個人関連者コード
     */
    public String getPersonKanrenshaCode() {
        return personKanrenshaCode;
    }

    /**
     * 個人関連者コードを設定する
     *
     * @param personKanrenshaCode 個人関連者コード
     */
    public void setPersonKanrenshaCode(final String personKanrenshaCode) {
        this.personKanrenshaCode = personKanrenshaCode;
    }

    /** 個人氏名 */
    private String personName = INIT_String;

    /**
     * 個人氏名を取得する
     *
     * @return 個人氏名
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 個人氏名を設定する
     *
     * @param personName 個人氏名
     */
    public void setPersonName(final String personName) {
        this.personName = personName;
    }

    /** 団体関連者コード */
    private String orgKanrenshaCode = INIT_String;

    /**
     * 団体関連者コードを取得する
     *
     * @return 団体関連者コード
     */
    public String getOrgKanrenshaCode() {
        return orgKanrenshaCode;
    }

    /**
     * 団体関連者コードを設定する
     *
     * @param orgKanrenshaCode 団体関連者コード
     */
    public void setOrgKanrenshaCode(final String orgKanrenshaCode) {
        this.orgKanrenshaCode = orgKanrenshaCode;
    }

    /** 団体代表者名称 */
    private String orgName = INIT_String;

    /**
     * 団体代表者名称を取得する
     *
     * @return 団体代表者名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 団体代表者名称を設定する
     *
     * @param orgName 団体代表者名称
     */
    public void setOrgName(final String orgName) {
        this.orgName = orgName;
    }

    /** 登録開始年 */
    private Short startYear = INIT_Short;

    /**
     * 登録開始年を取得する
     *
     * @return 登録開始年
     */
    public Short getStartYear() {
        return startYear;
    }

    /**
     * 登録開始年を設定する
     *
     * @param startYear 登録開始年
     */
    public void setStartYear(final Short startYear) {
        this.startYear = startYear;
    }

    /** 登録終了年 */
    private Short endYear = INIT_Short;

    /**
     * 登録終了年を取得する
     *
     * @return 登録終了年
     */
    public Short getEndYear() {
        return endYear;
    }

    /**
     * 登録終了年を設定する
     *
     * @param endYear 登録終了年
     */
    public void setEndYear(final Short endYear) {
        this.endYear = endYear;
    }

}
