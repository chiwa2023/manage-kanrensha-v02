package net.seijishikin.jp.normalize.manage.kanrensha.dto.postal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionStringDto;


/**
 * 住所番地まで選択肢Dto
 */
public class PostalCodeBlockResultDto implements Serializable,DtoEntityInitialValueInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 行政区検索 */
    private Boolean isGyouseikuData = INIT_BOOLEAN;

    /** 地方自治体コード */
    private String lgCode = INIT_STRING;

    /** 住所番地まで選択肢 */
    private List<SelectOptionStringDto> listOptions = new ArrayList<>();

    /**
     * 検索行政区該否を取得する
     *
     * @return 検索行政区該否
     */
    public Boolean getIsGyouseikuData() {
        return isGyouseikuData;
    }

    /**
     * 検索行政区該否を設定する
     *
     * @param isGyouseikuData 検索行政区該否
     */
    public void setIsGyouseikuData(final Boolean isGyouseikuData) {
        this.isGyouseikuData = isGyouseikuData;
    }

    /**
     * 地方自治体コードを取得する
     *
     * @return 地方自治体コード
     */
    public String getLgCode() {
        return lgCode;
    }

    /**
     * 地方自治体コードを設定する
     *
     * @param lgCode 地方自治体コード
     */
    public void setLgCode(final String lgCode) {
        this.lgCode = lgCode;
    }

    /**
     * 建物選択肢リストを取得する
     *
     * @return 建物選択肢リスト
     */
    public List<SelectOptionStringDto> getListOptions() {
        return listOptions;
    }

    /**
     * 建物選択肢リストを設定する
     *
     * @param listOptions 建物選択肢リスト
     */
    public void setListOptions(final List<SelectOptionStringDto> listOptions) {
        this.listOptions = listOptions;
    }

}
