package net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * 地方自治体コード検索条件Dto
 */
public class SearchLgCodeCapsuleDto implements Serializable, PagingIntegerDtoInterface { // NOPMD DataClass

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

    /** 県地方自治体コード */
    private String prefCode = INIT_STRING;

    /** 検索語 */
    private String searchWords = INIT_STRING;

    /**
     * 県地方自治体コードを取得する
     * 
     * @return 県地方自治体コード
     */
    public String getPrefCode() {
        return prefCode;
    }

    /**
     * 県地方自治体コードを設定する
     * 
     * @param prefCode 県地方自治体コード
     */
    public void setPrefCode(final String prefCode) {
        this.prefCode = prefCode;
    }

    /**
     * 検索語を取得する
     * 
     * @return 検索語
     */
    public String getSearchWords() {
        return searchWords;
    }

    /**
     * 検索語を設定する
     * 
     * @param searchWords 検索語
     */
    public void setSearchWords(final String searchWords) {
        this.searchWords = searchWords;
    }

}
