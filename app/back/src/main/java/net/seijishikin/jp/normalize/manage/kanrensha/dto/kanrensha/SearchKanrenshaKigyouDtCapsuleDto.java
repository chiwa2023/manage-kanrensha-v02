package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * 関連者企業団体検索結果Dto
 */
public class SearchKanrenshaKigyouDtCapsuleDto // NOPMD DataClass
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

    /** 法人番号 */
    private String houjinNo = INIT_STRING;

    /** 名称 */
    private String name = INIT_STRING;

    /** 住所 */
    private String address = INIT_STRING;

    /** 代表者 */
    private String delegate = INIT_STRING;

    /**
     * 法人番号を取得する
     * 
     * @return 法人番号
     */
    public String getHoujinNo() {
        return houjinNo;
    }

    /**
     * 法人番号を設定する
     * 
     * @param houjinNo 法人番号
     */
    public void setHoujinNo(final String houjinNo) {
        this.houjinNo = houjinNo;
    }

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
     * 代表者を取得する
     * 
     * @return 代表者
     */
    public String getDelegate() {
        return delegate;
    }

    /**
     * 代表者を設定する
     * 
     * @param delegate 代表者
     */
    public void setDelegate(final String delegate) {
        this.delegate = delegate;
    }
}
