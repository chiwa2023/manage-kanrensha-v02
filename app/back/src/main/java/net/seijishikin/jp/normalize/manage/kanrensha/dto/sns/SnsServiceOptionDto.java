package net.seijishikin.jp.normalize.manage.kanrensha.dto.sns;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionIntegerDto;

/**
 * SNSサービス選択肢項目Dto
 */
public class SnsServiceOptionDto extends SelectOptionIntegerDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** サービスコード */
    private Integer serviceCode;

    /** ポータルURL */
    private String portalUrl;

    /**
     * コンストラクタ
     * 
     * @param value       値
     * @param text        表示テキスト
     * @param serviceCode サービスコード
     * @param portalUrl   ポータルURL
     */
    public SnsServiceOptionDto(final Integer value, final String text, final Integer serviceCode,
            final String portalUrl) {
        super(value, text);
        this.serviceCode = serviceCode;
        this.portalUrl = portalUrl;
    }

    /**
     * サービスコードを取得する
     * 
     * @return サービスコード
     */
    public Integer getServiceCode() {
        return serviceCode;
    }

    /**
     * サービスコードを設定する
     * 
     * @param serviceCode サービスコード
     */
    public void setServiceCode(final Integer serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * ポータルURLを取得する
     * 
     * @return ポータルURL
     */
    public String getPortalUrl() {
        return portalUrl;
    }

    /**
     * ポータルURLを設定する
     * 
     * @param portalUrl ポータルURL
     */
    public void setPortalUrl(final String portalUrl) {
        this.portalUrl = portalUrl;
    }

}
