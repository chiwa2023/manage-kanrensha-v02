package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * kanrensha_combine_org_2026接続用Entity
 */
public class KanrenshaCombineOrgDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * 
     * @param kanrenshaCombineOrgId   kanrenshaCombineOrgId
     * @param kanrenshaCombineOrgCode kanrenshaCombineOrgCode
     * @param tableYear               tableYear
     * @param kanrenshaKbn            kanrenshaKbn
     * @param personKanrenshaCode     personKanrenshaCode
     * @param personName              personName
     * @param orgKanrenshaCode        orgKanrenshaCode
     * @param orgName                 orgName
     */
    public KanrenshaCombineOrgDto(final Integer kanrenshaCombineOrgId, final Integer kanrenshaCombineOrgCode,
            final Long tableYear, final Short kanrenshaKbn, final String personKanrenshaCode, final String personName,
            final String orgKanrenshaCode, final String orgName) {
        super();
        this.kanrenshaCombineOrgId = kanrenshaCombineOrgId;
        this.tableYear = Math.toIntExact(tableYear);
        this.kanrenshaCombineOrgCode = kanrenshaCombineOrgCode;
        this.kanrenshaKbn = kanrenshaKbn;
        this.personKanrenshaCode = personKanrenshaCode;
        this.personName = personName;
        this.orgKanrenshaCode = orgKanrenshaCode;
        this.orgName = orgName;
    }

    /** テーブルId */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_combine_org_id")
    private Integer kanrenshaCombineOrgId;

    /**
     * テーブルIdを取得する
     *
     * @return テーブルId
     */
    public Integer getKanrenshaCombineOrgId() {
        return kanrenshaCombineOrgId;
    }

    /**
     * テーブルIdを設定する
     *
     * @param kanrenshaCombineOrgId テーブルId
     */
    public void setKanrenshaCombineOrgId(final Integer kanrenshaCombineOrgId) {
        this.kanrenshaCombineOrgId = kanrenshaCombineOrgId;
    }

    /** 紐づけコード */
    @Column(name = "kanrensha_combine_org_code")
    private Integer kanrenshaCombineOrgCode;

    /**
     * 紐づけコードを取得する
     *
     * @return 紐づけコード
     */
    public Integer getKanrenshaCombineOrgCode() {
        return kanrenshaCombineOrgCode;
    }

    /** 発生年 */
    @Column(name = "table_year")
    private Integer tableYear;

    /**
     * 発生年を取得する
     * 
     * @return 発生年
     */
    public Integer getTableYear() {
        return tableYear;
    }

    /**
     * 発生年を設定する
     * 
     * @param tableYear 発生年
     */
    public void setTableYear(final Integer tableYear) {
        this.tableYear = tableYear;
    }

    /**
     * 紐づけコードを設定する
     *
     * @param kanrenshaCombineOrgCode 紐づけコード
     */
    public void setKanrenshaCombineOrgCode(final Integer kanrenshaCombineOrgCode) {
        this.kanrenshaCombineOrgCode = kanrenshaCombineOrgCode;
    }

    /** 紐づけ関連者区分 */
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

    /** 個人氏名 */
    @Column(name = "person_name")
    private String personName;

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

    /** 団体代表者名称 */
    @Column(name = "org_name")
    private String orgName;

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

}
