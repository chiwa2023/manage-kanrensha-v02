package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgMasterEntity;

/**
 * 利用者API接続者検索結果Dto
 */
public class SearchRiyoushaOrgResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
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

    /** 利用者組織リスト */
    private List<RiyoushaOrgMasterEntity> listRiyoushaOrg = new ArrayList<>();

    /**
     * 利用者組織を取得する
     * 
     * @return 利用者組織
     */
    public List<RiyoushaOrgMasterEntity> getListRiyoushaOrg() {
        return listRiyoushaOrg;
    }

    /**
     * 利用者組織を設定する
     * 
     * @param listRiyoushaOrg 利用者組織
     */
    public void setListRiyoushaOrg(final List<RiyoushaOrgMasterEntity> listRiyoushaOrg) {
        this.listRiyoushaOrg = listRiyoushaOrg;
    }

}
