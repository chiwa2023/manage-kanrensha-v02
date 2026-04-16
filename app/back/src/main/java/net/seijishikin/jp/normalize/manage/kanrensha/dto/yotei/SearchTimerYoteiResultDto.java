package net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;

/**
 * 予定実行検索条件Dto
 */
public class SearchTimerYoteiResultDto // NOPMD DataClass
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

    /** 予約実行リスト */
    private List<TimerYoteiEntity> listEntity = new ArrayList<>();

    /**
     * 予約実行リストを取得する
     * 
     * @return 予約実行リスト
     */
    public List<TimerYoteiEntity> getListEntity() {
        return listEntity;
    }

    /**
     * 予約実行リストを設定する
     * 
     * @param listEntity 予約実行リスト
     */
    public void setListEntity(final List<TimerYoteiEntity> listEntity) {
        this.listEntity = listEntity;
    }

}
