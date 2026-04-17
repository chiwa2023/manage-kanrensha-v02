package net.seijishikin.jp.normalize.manage.kanrensha.dto.user;

import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;

/**
 * ユーザ検索結果Dto
 */
public class SearchUserEntityResultDto implements PagingIntegerDtoInterface { // NOPMD DataClass

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
     * 抽出件数を取得する
     */
    @Override
    public Integer getLimit() {
        return limit;
    }

    /**
     * 抽出件数を設定する
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

    /** 検索結果リスト */
    private List<UserPersonEntity> listPersonEntity = new ArrayList<>();

    /**
     * 検索結果リストを取得する
     * 
     * @return 検索結果リスト
     */
    public List<UserPersonEntity> getListPersonEntity() {
        return listPersonEntity;
    }

    /**
     * 検索結果リストを設定する
     * 
     * @param listPersonEntity 検索結果リスト
     */
    public void setListPersonEntity(final List<UserPersonEntity> listPersonEntity) {
        this.listPersonEntity = listPersonEntity;
    }

}
