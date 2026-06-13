package net.seijishikin.jp.normalize.manage.kanrensha.dto.postal;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * 郵便番号不規則データ検索条件Dto
 */
public class SearchPostalCodeCapsuleDto // NOPMD DataClass
        implements Serializable, PagingIntegerDtoInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 全件数 */
    private Integer allCount = INIT_INTEGER;

    /** 抽出件数 */
    private Integer limit = INIT_INTEGER;

    /** ページ番号 */
    private Integer pageNumber = INIT_INTEGER;

    /**
     * 全件数を取得する
     *
     * @return 全件数
     */
    @Override
    public Integer getAllCount() {
        return allCount;
    }

    /**
     * 全件数を設定する
     *
     * @param allCount 全件数全件数
     */
    @Override
    public void setAllCount(final Integer allCount) {
        this.allCount = allCount;
    }

    /**
     * 抽出件数を取得する
     *
     * @return 抽出件数
     */
    @Override
    public Integer getLimit() {
        return limit;
    }

    /**
     * 抽出件数を設定する
     *
     * @param limit 抽出件数
     */
    @Override
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    /**
     * ページ番号を取得する
     *
     * @return ページ番号
     */
    @Override
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * ページ番号を設定する
     *
     * @param pageNumber ページ番号
     */
    @Override
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /** 検索条件郵便番号1 */
    private String searchPostalcode1 = INIT_STRING;

    /** 検索条件郵便番号2 */
    private String searchPostalcode2 = INIT_STRING;

    /** 検索条件住所 */
    private String searchAddressName = INIT_STRING;

    /**
     * 検索条件郵便番号1を取得する
     * 
     * @return 検索条件郵便番号1
     */
    public String getSearchPostalcode1() {
        return searchPostalcode1;
    }

    /**
     * 検索条件郵便番号1を設定する
     * 
     * @param searchPostalcode1 検索条件郵便番号1
     */
    public void setSearchPostalcode1(final String searchPostalcode1) {
        this.searchPostalcode1 = searchPostalcode1;
    }

    /**
     * 検索条件郵便番号2を取得する
     * 
     * @return 検索条件郵便番号2
     */
    public String getSearchPostalcode2() {
        return searchPostalcode2;
    }

    /**
     * 検索条件郵便番号2を設定する
     * 
     * @param searchPostalcode2 検索条件郵便番号2
     */
    public void setSearchPostalcode2(final String searchPostalcode2) {
        this.searchPostalcode2 = searchPostalcode2;
    }

    /**
     * 検索条件住所を取得する
     * 
     * @return 検索条件住所
     */
    public String getSearchAddressName() {
        return searchAddressName;
    }

    /**
     * 検索条件住所を設定する
     * 
     * @param searchAddressName 検索条件住所
     */
    public void setSearchAddressName(final String searchAddressName) {
        this.searchAddressName = searchAddressName;
    }

}
