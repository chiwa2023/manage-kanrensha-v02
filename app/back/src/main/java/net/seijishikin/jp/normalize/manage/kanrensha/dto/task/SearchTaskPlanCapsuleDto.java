package net.seijishikin.jp.normalize.manage.kanrensha.dto.task;

import java.io.Serializable;
import java.time.LocalDateTime;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.paging.SwitchYearPagingIntegerDatetimeDtoInterface;

/**
 * タスク計画検索条件Dto
 */
public class SearchTaskPlanCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, SwitchYearPagingIntegerDatetimeDtoInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 検索開始日時 */
    private LocalDateTime startDate = INIT_TIMESTAMP;

    /** 検索終了日時 */
    private LocalDateTime endDate = INIT_TIMESTAMP;

    /** タスク検索語 */
    private String searchTaskWord = INIT_STRING;

    /**
     * 検索開始日時を取得する
     */
    @Override
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * 検索開始日時を設定する
     */
    @Override
    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * 検索終了日時を取得する
     */
    @Override
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * 検索終了日時を設定する
     */
    @Override
    public void setEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * タスク検索語
     *
     * @return タスク検索語を取得する
     */
    public String getSearchTaskWord() {
        return searchTaskWord;
    }

    /**
     * タスク検索語を設定する
     *
     * @param searchTaskWord タスク検索語
     */
    public void setSearchTaskWord(final String searchTaskWord) {
        this.searchTaskWord = searchTaskWord;
    }

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

    /** 検索条件変更フラグ */
    private Boolean isChangedCondition = INIT_BOOLEAN;

    /** 前段階表示件数 */
    private Integer preStepViewCount = INIT_INTEGER;

    /**
     * 検索条件変更フラグを取得する
     *
     * @return 検索条件変更フラグ
     */
    @Override
    public Boolean getIsChangedCondition() {
        return isChangedCondition;
    }

    /**
     * 検索条件変更フラグを設定する
     *
     * @param isChangedCondition 検索条件変更フラグ
     */
    @Override
    public void setIsChangedCondition(final Boolean isChangedCondition) {
        this.isChangedCondition = isChangedCondition;
    }

    /**
     * 前段階表示件数を取得する
     *
     * @return 前段階表示件数
     */
    @Override
    public Integer getPreStepViewCount() {
        return preStepViewCount;
    }

    /**
     * 前段階表示件数を設定する
     *
     * @param preStepViewCount 前段階表示件数
     */
    @Override
    public void setPreStepViewCount(final Integer preStepViewCount) {
        this.preStepViewCount = preStepViewCount;
    }

}
