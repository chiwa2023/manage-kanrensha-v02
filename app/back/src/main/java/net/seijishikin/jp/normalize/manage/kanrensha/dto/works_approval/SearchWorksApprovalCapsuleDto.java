package net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval;

import java.io.Serializable;
import java.time.LocalDate;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * 作業承認リスト検索Dto
 */
public class SearchWorksApprovalCapsuleDto // NOPMD DataClass
        implements PagingIntegerDtoInterface ,Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 全件数 */
    private Integer allCount = INIT_Integer;

    /** ページ内件数 */
    private Integer limit = INIT_Integer;

    /** ページ番号 */
    private Integer pageNumber = INIT_Integer;

    /** 初期データ(LoclaDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

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

    /** 検索開始日 */
    private LocalDate startDate = INIT_LocalDate;

    /** 検索終了日 */
    private LocalDate endDate = INIT_LocalDate;

    /** 終了タスク除外フラグ */
    private Boolean isExcludeFinishedTask = INIT_Boolean;

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

    /**
     * 終了タスク除外フラグを取得する
     *
     * @return 終了タスク除外フラグ
     */
    public Boolean getIsExcludeFinishedTask() {
        return isExcludeFinishedTask;
    }

    /**
     * 終了タスク除外フラグを設定する
     *
     * @param isExcludeFinishedTask 終了タスク除外フラグ
     */
    public void setIsExcludeFinishedTask(final Boolean isExcludeFinishedTask) {
        this.isExcludeFinishedTask = isExcludeFinishedTask;
    }

}
