package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaAdminMasterEntity;

/**
 * 利用者管理者検索結果Dto
 */
public class SearchRiyoushaAdminResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable, PagingIntegerDtoInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 全件数 */
    private Integer allCount = INIT_Integer;

    /** ページ内件数 */
    private Integer limit = INIT_Integer;

    /** ページ番号 */
    private Integer pageNumber = INIT_Integer;

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

    /** 利用者運営者リスト */
    private List<RiyoushaAdminMasterEntity> listRiyoushaAdmin = new ArrayList<>();

    /**
     * 利用者運営者リスト
     *
     * @return 利用者運営者リスト
     */
    public List<RiyoushaAdminMasterEntity> getListRiyoushaAdmin() {
        return listRiyoushaAdmin;
    }

    /**
     * 利用者運営者リスト
     *
     * @param listRiyoushaAdmin 利用者運営者リスト
     */
    public void setListRiyoushaAdmin(final List<RiyoushaAdminMasterEntity> listRiyoushaAdmin) {
        this.listRiyoushaAdmin = listRiyoushaAdmin;
    }

}
