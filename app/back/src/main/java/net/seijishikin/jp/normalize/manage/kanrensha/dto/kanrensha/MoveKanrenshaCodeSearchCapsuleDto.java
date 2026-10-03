package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;
import java.time.LocalDate;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * 関連者コード移動申請検索条件Dto
 */
public class MoveKanrenshaCodeSearchCapsuleDto // NOPMD DataClass
        implements Serializable, PagingIntegerDtoInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 全件数 */
    private Integer allCount = INIT_INTEGER;

    /** ページ内件数 */
    private Integer limit = INIT_INTEGER;

    /** ページ番号 */
    private Integer pageNumber = INIT_INTEGER;

    /**
     * 全件数を取得する
     */
    @Override
    public Integer getAllCount() {
        return allCount;
    }

    /**
     * 全件数を設定する
     */
    @Override
    public void setAllCount(final Integer allCount) {
        this.allCount = allCount;
    }

    /**
     * ページ内件数を取得する
     */
    @Override
    public Integer getLimit() {
        return limit;
    }

    /**
     * ページ内件数を設定する
     */
    @Override
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    /**
     * ページ番号を取得する
     */
    @Override
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * ページ番号を設定する
     */
    @Override
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /** 申請中検索該当 */
    private Boolean isPromoteSearch;

    /** 却下検索該当 */
    private Boolean isRejectSearch;

    /** 承認検索該当 */
    private Boolean isAcceptSearch;

    /** 追加調査検索該当 */
    private Boolean isResearchSearch;

    /** 検索開始日 */
    private LocalDate startDate;

    /** 検索終了日 */
    private LocalDate endDate;

    /**
     * 申請中検索該当を取得する
     * 
     * @return 申請中検索該当
     */
    public Boolean getIsPromoteSearch() {
        return isPromoteSearch;
    }

    /**
     * 申請中検索該当を設定する
     * 
     * @param isPromoteSearch 申請中検索該当
     */
    public void setIsPromoteSearch(final Boolean isPromoteSearch) {
        this.isPromoteSearch = isPromoteSearch;
    }

    /**
     * 却下検索該当を取得する
     * 
     * @return 却下検索該当
     */
    public Boolean getIsRejectSearch() {
        return isRejectSearch;
    }

    /**
     * 却下検索該当を設定する
     * 
     * @param isRejectSearch 却下検索該当
     */
    public void setIsRejectSearch(final Boolean isRejectSearch) {
        this.isRejectSearch = isRejectSearch;
    }

    /**
     * 承認検索該当を取得する
     * 
     * @return 承認検索該当
     */
    public Boolean getIsAcceptSearch() {
        return isAcceptSearch;
    }

    /**
     * 承認検索該当を設定する
     * 
     * @param isAcceptSearch 承認検索該当
     */
    public void setIsAcceptSearch(final Boolean isAcceptSearch) {
        this.isAcceptSearch = isAcceptSearch;
    }

    /**
     * 追加調査検索該当を取得する
     * 
     * @return 追加調査検索該当
     */
    public Boolean getIsResearchSearch() {
        return isResearchSearch;
    }

    /**
     * 追加調査検索該当を設定する
     * 
     * @param isResearchSearch 追加調査検索該当
     */
    public void setIsResearchSearch(final Boolean isResearchSearch) {
        this.isResearchSearch = isResearchSearch;
    }

    /**
     * 検索開始日を取得する
     * 
     * @return 検索開始日
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * 検索開始日を設定する
     * 
     * @param startDate 検索開始日
     */
    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * 検索終了日を取得する
     * 
     * @return 検索終了日
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * 検索終了日を設定する
     * 
     * @param endDate 検索終了日
     */
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }
}
