package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml;

import java.io.Serializable;

import jakarta.persistence.Column;

/**
 * 備考1項目で複数行抽出用Dto
 */
public class XmlBikouUniquekeyDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 備考 */
    @Column(name = "bikou")
    private String bikou;

    /**
     * コンストラクタ
     *
     * @param bikou 備考
     */
    public XmlBikouUniquekeyDto(final String bikou) {
        this.bikou = bikou;
    }

    /**
     * 備考を取得する
     *
     * @return 備考
     */
    public String getBikou() {
        return bikou;
    }

    /**
     * 備考を設定する
     *
     * @param bikou 備考
     */
    public void setBikou(final String bikou) {
        this.bikou = bikou;
    }

}
