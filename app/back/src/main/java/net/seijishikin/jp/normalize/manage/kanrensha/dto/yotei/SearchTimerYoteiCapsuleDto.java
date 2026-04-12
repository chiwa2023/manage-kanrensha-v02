package net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * 予定実行検索条件Dto
 */
public class SearchTimerYoteiCapsuleDto // NOPMD DaytaClass
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

    /** 予定区分リスト */
    private List<Short> listYoteiKbn = new ArrayList<>();

    /** 検索開始日時 */
    private LocalDateTime startDateTime = INIT_TIMESTAMP;

    /** 検索終了日時 */
    private LocalDateTime endDateTime = INIT_TIMESTAMP;

    /**
     * 予定区分リストを取得する
     * 
     * @return 予定区分リスト
     */
    public List<Short> getListYoteiKbn() {
        return listYoteiKbn;
    }

    /**
     * 予定区分リストを設定する
     * 
     * @param listYoteiKbn 予定区分リスト
     */
    public void setListYoteiKbn(final List<Short> listYoteiKbn) {
        this.listYoteiKbn = listYoteiKbn;
    }

    /**
     * 検索開始日時を取得する
     * 
     * @return 検索開始日時
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * 検索開始日時を設定する
     * 
     * @param startDateTime 検索開始日時
     */
    public void setStartDateTime(final LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * 検索終了日時を取得する
     * 
     * @return 検索終了日時
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * 検索終了日時を設定する
     * 
     * @param endDateTime 検索終了日時
     */
    public void setEndDateTime(final LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /** 検索日時有無 */
    private Boolean isPeriodSearch = INIT_BOOLEAN;

    /**
     * 検索日時有無を取得する
     * 
     * @return 検索日時有無
     */
    public Boolean getIsPeriodSearch() {
        return isPeriodSearch;
    }

    /**
     * 検索日時有無を設定する
     * 
     * @param isPeriodSearch 検索日時有無
     */
    public void setIsPeriodSearch(final Boolean isPeriodSearch) {
        this.isPeriodSearch = isPeriodSearch;
    }

}
