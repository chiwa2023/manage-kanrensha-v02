package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * 関連者個人検索結果Dto
 */
public class SearchKanrenshaPersonCapsuleDto // NOPMD DataClass
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

    /** 名称 */
    private String name = INIT_STRING;

    /** 住所 */
    private String address = INIT_STRING;

    /** 職業 */
    private String shokugyou = INIT_STRING;

    /**
     * 名称を取得する
     * 
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称を設定する
     * 
     * @param name 名称
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 住所を取得する
     * 
     * @return 住所
     */
    public String getAddress() {
        return address;
    }

    /**
     * 住所を設定する
     * 
     * @param address 住所
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * 職業を取得する
     * 
     * @return 職業
     */
    public String getShokugyou() {
        return shokugyou;
    }

    /**
     * 職業を設定する
     * 
     * @param shokugyou 職業
     */
    public void setShokugyou(final String shokugyou) {
        this.shokugyou = shokugyou;
    }

}
