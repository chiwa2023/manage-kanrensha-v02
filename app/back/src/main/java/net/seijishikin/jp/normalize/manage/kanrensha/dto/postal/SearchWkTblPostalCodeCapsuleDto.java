package net.seijishikin.jp.normalize.manage.kanrensha.dto.postal;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * 郵便番号差分ワークテーブル検索条件Dto
 */
public class SearchWkTblPostalCodeCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
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

    /** 履歴検索該否 */
    private Boolean isSearchHistory = INIT_BOOLEAN;

    /**
     * 履歴検索該否
     * 
     * @return 履歴検索該否
     */
    public Boolean getIsSearchHistory() {
        return isSearchHistory;
    }

    /**
     * 履歴検索該否
     * 
     * @param isSearchHistory 履歴検索該否
     */
    public void setIsSearchHistory(final Boolean isSearchHistory) {
        this.isSearchHistory = isSearchHistory;
    }


    /** 自動修復検索該否 */
    private Boolean isSearchRepair = INIT_BOOLEAN;

    /**
     * 自動修復検索該否を取得する
     * 
     * @return 自動修復検索該否
     */
    public Boolean getIsSearchRepair() {
        return isSearchRepair;
    }

    /**
     * 自動修復検索該否を設定する
     * 
     * @param isSearchRepair 自動修復検索該否
     */
    public void setIsSearchRepair(final Boolean isSearchRepair) {
        this.isSearchRepair = isSearchRepair;
    }

}
