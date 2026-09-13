package net.seijishikin.jp.normalize.manage.kanrensha.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

/**
 * kanrensha_code_used接続用Entity
 */
@Entity
@Table(name = "kanrensha_code_used")
public class KanrenshaCodeUsedEntity implements Serializable { // NOPMD DataClass

    /** 初期データ(String) */
    private static final String INIT_STRING = "";

    /** 初期データ(Short) */
    private static final short INIT_SHORT = 0;

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 併合先コード */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kanrensha_code")
    private String kanrenshaCode = INIT_STRING;

    /**
     * 併合先コードを取得する
     *
     * @return 併合先コード
     */
    public String getKanrenshaCode() {
        return kanrenshaCode;
    }

    /**
     * 併合先コードを設定する
     *
     * @param kanrenshaCode 併合先コード
     */
    public void setKanrenshaCode(final String kanrenshaCode) {
        this.kanrenshaCode = kanrenshaCode;
    }

    /** 関連者区分 */
    @Column(name = "kanrensha_kbn")
    private Short kanrenshaKbn = INIT_SHORT;

    /**
     * 関連者区分を取得する
     *
     * @return 関連者区分
     */
    public Short getKanrenshaKbn() {
        return kanrenshaKbn;
    }

    /**
     * 関連者区分を設定する
     *
     * @param kanrenshaKbn 関連者区分
     */
    public void setKanrenshaKbn(final Short kanrenshaKbn) {
        this.kanrenshaKbn = kanrenshaKbn;
    }

}
