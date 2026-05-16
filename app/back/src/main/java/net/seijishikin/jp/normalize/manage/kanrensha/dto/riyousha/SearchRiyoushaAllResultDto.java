package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;

/**
 * 利用者検索結果Dto
 */
public class SearchRiyoushaAllResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
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

    /** 全権限者リスト */
    private List<SearchViewCombineAliveRiyoushaResultDto> listAllRiyousha = new ArrayList<>();

    /**
     * 全権限者リストを取得する
     * 
     * @return 全権限者リスト
     */
    public List<SearchViewCombineAliveRiyoushaResultDto> getListAllRiyousha() {
        return listAllRiyousha;
    }

    /**
     * 全権限者リストを設定する
     * 
     * @param listAllRiyousha 全権限者リスト
     */
    public void setListAllRiyousha(final List<SearchViewCombineAliveRiyoushaResultDto> listAllRiyousha) {
        this.listAllRiyousha = listAllRiyousha;
    }

}
