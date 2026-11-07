package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * 関連者紐づけ検索検索条件Dto
 */
public class SearchKanrenshaCombineCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
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

    /** 登録年 */
    private Integer tableYear = INIT_INTEGER;

    /** 関連者個人コード */
    private String personCode = INIT_STRING;

    /** 関連者組織コード */
    private String orgCode = INIT_STRING;

    /**
     * 関連者個人コードを取得する
     * 
     * @return 関連者個人コード
     */
    public String getPersonCode() {
        return personCode;
    }

    /**
     * 関連者個人コードを設定する
     * 
     * @param personCode 関連者個人コード
     */
    public void setPersonCode(final String personCode) {
        this.personCode = personCode;
    }

    /**
     * 関連者組織コードを取得する
     * 
     * @return 関連者組織コード
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 関連者組織コードを設定する
     * 
     * @param orgCode 関連者組織コード
     */
    public void setOrgCode(final String orgCode) {
        this.orgCode = orgCode;
    }

    /** 紐づけ関連者区分 */
    private Short kanrenshaKbn = INIT_SHORT;

    /**
     * 紐づけ関連者区分を取得する
     *
     * @return 紐づけ関連者区分
     */
    public Short getKanrenshaKbn() {
        return kanrenshaKbn;
    }

    /**
     * 紐づけ関連者区分を設定する
     *
     * @param kanrenshaKbn 紐づけ関連者区分
     */
    public void setKanrenshaKbn(final Short kanrenshaKbn) {
        this.kanrenshaKbn = kanrenshaKbn;
    }

    /**
     * 登録年を取得
     * 
     * @return 登録年
     */
    public Integer getTableYear() {
        return tableYear;
    }

    /**
     * 登録年を設定する
     * 
     * @param tableYear 登録年
     */
    public void setTableYear(final Integer tableYear) {
        this.tableYear = tableYear;
    }

}