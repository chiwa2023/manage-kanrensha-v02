package net.seijishikin.jp.normalize.manage.kanrensha.batch.kanrensha.xml;

import java.io.Serializable;

import jakarta.persistence.Column;

/**
 * 名称と住所で重複キーを格納するDto
 */
public class XmlNameAddressUniquekeyDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     *
     * @param inputSrcName    入力名称
     * @param inputSrcAddress 入力住所
     */
    public XmlNameAddressUniquekeyDto(final String inputSrcName, final String inputSrcAddress) {
        this.inputSrcName = inputSrcName;
        this.inputSrcAddress = inputSrcAddress;
    }

    /** 複写元名称 */
    @Column(name = "input_src_name")
    private String inputSrcName;

    /**
     * 複写元名称を取得する
     *
     * @return 複写元名称
     */
    public String getInputSrcName() {
        return inputSrcName;
    }

    /**
     * 複写元名称を設定する
     *
     * @param inputSrcName 複写元名称
     */
    public void setInputSrcName(final String inputSrcName) {
        this.inputSrcName = inputSrcName;
    }

    /** 複写元住所 */
    @Column(name = "input_src_address")
    private String inputSrcAddress;

    /**
     * 複写元住所を取得する
     *
     * @return 複写元住所
     */
    public String getInputSrcAddress() {
        return inputSrcAddress;
    }

    /**
     * 複写元住所を設定する
     *
     * @param inputSrcAddress 複写元住所
     */
    public void setInputSrcAddress(final String inputSrcAddress) {
        this.inputSrcAddress = inputSrcAddress;
    }
}
