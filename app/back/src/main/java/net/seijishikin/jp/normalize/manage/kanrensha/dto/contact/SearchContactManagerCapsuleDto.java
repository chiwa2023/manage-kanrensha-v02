package net.seijishikin.jp.normalize.manage.kanrensha.dto.contact;

import java.io.Serializable;
import java.time.LocalDate;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * 運営者に連絡検索条件Dto
 */
public class SearchContactManagerCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
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

    /** 運営者問い合わせコード */
    private Integer contactManagerCode = INIT_INTEGER;

    /**
     * 運営者問い合わせコードを取得する
     *
     * @return 運営者問い合わせコード
     */
    public Integer getContactManagerCode() {
        return contactManagerCode;
    }

    /**
     * 運営者問い合わせコードを設定する
     *
     * @param contactManagerCode 運営者問い合わせコード
     */
    public void setContactManagerCode(final Integer contactManagerCode) {
        this.contactManagerCode = contactManagerCode;
    }

    /** 検索条件開始日付 */
    private LocalDate startDate = INIT_DATE;

    /** 検索条件終了日付 */
    private LocalDate endDate = INIT_DATE;

    /** 検索条件クローズ検索該当 */
    private Boolean isSearchClose = INIT_BOOLEAN;

    /**
     * 検索条件開始日付を取得する
     * 
     * @return 検索条件開始日付
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * 検索条件開始日付を設定する
     * 
     * @param startDate 検索条件開始日付
     */
    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * 検索条件終了日付を取得する
     * 
     * @return 検索条件終了日付
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * 検索条件終了日付を設定する
     * 
     * @param endDate 検索条件終了日付
     */
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * 検索条件クローズ検索該当を取得する
     * 
     * @return 検索条件クローズ検索該当
     */
    public Boolean getIsSearchClose() {
        return isSearchClose;
    }

    /**
     * 検索条件クローズ検索該当を設定する
     * 
     * @param isSearchClose 検索条件クローズ検索該当
     */
    public void setIsSearchClose(final Boolean isSearchClose) {
        this.isSearchClose = isSearchClose;
    }

}
