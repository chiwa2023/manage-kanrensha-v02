package net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.NaturalTextSearchPagingCapsuleDto;

/**
 * 住所検索条件Dto
 */
public class SearchAddressRsdtCapsuleDto extends NaturalTextSearchPagingCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 検索条件地方自治体コード */
    private String searchLgCode;

    /**
     * 検索条件地方自治体コードを取得する
     * 
     * @return 検索条件地方自治体コード
     */
    public String getSearchLgCode() {
        return searchLgCode;
    }

    /**
     * 検索条件地方自治体コードを設定する
     * 
     * @param searchLgCode 検索条件地方自治体コード
     */
    public void setSearchLgCode(final String searchLgCode) {
        this.searchLgCode = searchLgCode;
    }

}
