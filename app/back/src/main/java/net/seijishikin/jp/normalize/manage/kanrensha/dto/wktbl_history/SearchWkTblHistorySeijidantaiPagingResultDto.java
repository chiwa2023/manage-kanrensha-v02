package net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaSeijidantaiHistoryEntity;

/**
 * 政治団体一括登録履歴ワークテーブル検索ページングDto
 */
public class SearchWkTblHistorySeijidantaiPagingResultDto // NOPMD DataClass
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

    /** 政治団体登録候補リスト */
    private List<WkTblKanrenshaSeijidantaiHistoryEntity> listWktblSeijidantai = new ArrayList<>();

    /**
     * 政治団体登録候補リストを取得する
     * 
     * @return 政治団体登録候補リスト
     */
    public List<WkTblKanrenshaSeijidantaiHistoryEntity> getListWktblSeijidantai() {
        return listWktblSeijidantai;
    }

    /**
     * 政治団体登録候補リストを設定する
     * 
     * @param listWktblSeijidantai 政治団体登録候補リスト
     */
    public void setListWktblSeijidantai(final List<WkTblKanrenshaSeijidantaiHistoryEntity> listWktblSeijidantai) {
        this.listWktblSeijidantai = listWktblSeijidantai;
    }

}
