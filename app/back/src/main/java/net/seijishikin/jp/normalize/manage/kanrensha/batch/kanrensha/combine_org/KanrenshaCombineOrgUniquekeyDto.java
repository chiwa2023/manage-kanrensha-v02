package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.combine_org;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

/**
 * 個人団体紐づけ一意キー取得Dto
 */
public class KanrenshaCombineOrgUniquekeyDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     *
     * @param kanrenshaKbn        関連者区分
     * @param personKanrenshaCode 関連者コード個人
     * @param orgKanrenshaCode    関連者コード団体
     * @param yearArrayText       登録年配列
     */
    public KanrenshaCombineOrgUniquekeyDto(final Short kanrenshaKbn, final String personKanrenshaCode,
            final String orgKanrenshaCode, final String yearArrayText) {
        super();
        this.kanrenshaKbn = kanrenshaKbn; // 業務的に0,1,2,3のみ
        this.personKanrenshaCode = personKanrenshaCode;
        this.orgKanrenshaCode = orgKanrenshaCode;
        this.yearArrayText = yearArrayText;
    }

    /** 紐づけ関連者区分 */
    @Id
    @Column(name = "kanrensha_kbn")
    private Short kanrenshaKbn;

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
    @Id
    @Column(name = "person_kanrensha_code")
    private String personKanrenshaCode;

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

    /** 団体関連者コード */
    @Id
    @Column(name = "org_kanrensha_code")
    private String orgKanrenshaCode;

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

    /** 登録年配列 */
    @Id
    @Column(name = "year_array_text")
    private String yearArrayText;

    /**
     * 登録年配列を取得する
     *
     * @return 登録年配列
     */
    public String getYearArrayText() {
        return yearArrayText;
    }

    /**
     * 登録年配列を設定する
     *
     * @param yearArrayText 登録年配列
     */
    public void setYearArrayText(final String yearArrayText) {
        this.yearArrayText = yearArrayText;
    }

}
