package net.seijishikin.jp.normalize.manage.kanrensha.dto.postal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.select_options.SelectOptionIntegerDto;

/**
 * 住所建物選択肢Dto
 */
public class PostalCodeBuildingResultDto implements Serializable, DtoEntityInitialValueInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 地方自治体コード */
    private String lgCode = INIT_STRING;

    /** 建物選択肢リスト */
    private List<SelectOptionIntegerDto> listOptions = new ArrayList<>();

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
    public List<SelectOptionIntegerDto> getListOptions() {
        return listOptions;
    }

    /**
     * 建物選択肢リストを設定する
     *
     * @param listOptions 建物選択肢リスト
     */
    public void setListOptions(final List<SelectOptionIntegerDto> listOptions) {
        this.listOptions = listOptions;
    }

}
