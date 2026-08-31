package net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblKanrenshaPersonAddMinEntity;

/**
 * 個人一括登録マスタ最小ワークテーブル検索ページングDto
 */
public class SearchWkTblMinPersonPagingResultDto // NOPMD DataClass
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
     * 全件数
     */
    @Override
    public Integer getAllCount() {
        return allCount;
    }

    /**
     * 全件数
     */
    @Override
    public void setAllCount(final Integer allCount) {
        this.allCount = allCount;
    }

    /**
     * ページ内件数
     */
    @Override
    public Integer getLimit() {
        return limit;
    }

    /**
     * ページ内件数
     */
    @Override
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    /**
     * ページ番号
     */
    @Override
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * ページ番号
     */
    @Override
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /** 個人登録候補リスト */
    private List<WkTblKanrenshaPersonAddMinEntity> listWktblPerson = new ArrayList<>();

    /**
     * 個人登録候補リストを取得する
     * 
     * @return 個人登録候補リスト
     */
    public List<WkTblKanrenshaPersonAddMinEntity> getListWktblPerson() {
        return listWktblPerson;
    }

    /**
     * 個人登録候補リストを設定する
     * 
     * @param listWktblPerson 個人登録候補リスト
     */
    public void setListWktblPerson(final List<WkTblKanrenshaPersonAddMinEntity> listWktblPerson) {
        this.listWktblPerson = listWktblPerson;
    }

}
