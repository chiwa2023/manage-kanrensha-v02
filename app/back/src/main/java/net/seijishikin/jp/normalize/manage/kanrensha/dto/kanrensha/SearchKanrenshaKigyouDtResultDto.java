package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;

/**
 * 関連者企業団体検索結果Dto
 */
public class SearchKanrenshaKigyouDtResultDto // NOPMD DataClass
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

    /** 関連者企業団体マスタリスト */
    private List<KanrenshaKigyouDtMasterEntity> listMasterKigyouDt = new ArrayList<>();

    /**
     * 関連者企業団体マスタリストを取得
     *
     * @return 関連者企業団体マスタリスト
     */
    public List<KanrenshaKigyouDtMasterEntity> getListMasterKigyouDt() {
        return listMasterKigyouDt;
    }

    /**
     * 関連者企業団体マスタリストを設定する
     *
     * @param listMasterKigyouDt 関連者企業団体マスタリスト
     */
    public void setListMasterKigyouDt(final List<KanrenshaKigyouDtMasterEntity> listMasterKigyouDt) {
        this.listMasterKigyouDt = listMasterKigyouDt;
    }

}
