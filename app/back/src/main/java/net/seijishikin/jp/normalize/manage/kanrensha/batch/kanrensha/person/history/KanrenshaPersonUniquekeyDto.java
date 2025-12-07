package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.person.history;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

/**
 * 関連者個人の一意キー取得Dto
 */
public class KanrenshaPersonUniquekeyDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     *
     * @param kanrenshaName         個人姓名
     * @param allAddress          全住所
     * @param personShokugyou     個人職業
     * @param personKanrenshaCode 関連者コード個人
     */
    public KanrenshaPersonUniquekeyDto(final String kanrenshaName, final String allAddress, final String personShokugyou,
            final String personKanrenshaCode) {
        super();
        this.kanrenshaName = kanrenshaName;
        this.allAddress = allAddress;
        this.personShokugyou = personShokugyou;
        this.personKanrenshaCode = personKanrenshaCode;
    }

    /** 個人名 */
    @Id
    @Column(name = "kanrensha_name")
    private String kanrenshaName;

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
    @Id
    @Column(name = "all_address")
    private String allAddress;

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
    @Id
    @Column(name = "person_shokugyou")
    private String personShokugyou;

    /** 個人関連者コード */
    @Id
    @Column(name = "person_kanrensha_code")
    private String personKanrenshaCode;

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

    /**
     * 個人関連者コードを取得する
     *
     * @return 個人関連者コード
     */
    public String getPersonKanrenshaCode() {
        return personKanrenshaCode;
    }

    /**
     * 個人関連者コード設定する
     *
     * @param personKanrenshaCode 個人関連者コード
     */
    public void setPersonKanrenshaCode(final String personKanrenshaCode) {
        this.personKanrenshaCode = personKanrenshaCode;
    }

}
