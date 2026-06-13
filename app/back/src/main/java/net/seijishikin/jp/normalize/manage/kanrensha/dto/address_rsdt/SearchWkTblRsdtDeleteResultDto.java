package net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.paging.PagingIntegerDtoInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtDeleteEntity;

/**
 * 住所差分ワークテーブル検索結果Dto
 */
public class SearchWkTblRsdtDeleteResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable, PagingIntegerDtoInterface {

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
     *
     * @return 全件数
     */
    @Override
    public Integer getAllCount() {
        return allCount;
    }

    /**
     * 全件数を設定する
     *
     * @param allCount 全件数全件数
     */
    @Override
    public void setAllCount(final Integer allCount) {
        this.allCount = allCount;
    }

    /**
     * 抽出件数を取得する
     *
     * @return 抽出件数
     */
    @Override
    public Integer getLimit() {
        return limit;
    }

    /**
     * 抽出件数を設定する
     *
     * @param limit 抽出件数
     */
    @Override
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    /**
     * ページ番号を取得する
     *
     * @return ページ番号
     */
    @Override
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * ページ番号を設定する
     *
     * @param pageNumber ページ番号
     */
    @Override
    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /** ワークテーブル住所差分削除リスト */
    private List<WkTblAddressRsdtDeleteEntity> listEntity = new ArrayList<>();

    /**
     * ワークテーブル住所差分削除リストを取得する
     * 
     * @return ワークテーブル住所差分削除リスト
     */
    public List<WkTblAddressRsdtDeleteEntity> getListEntity() {
        return listEntity;
    }

    /**
     * ワークテーブル住所差分削除リストを設定する
     * 
     * @param listEntity ワークテーブル住所差分削除リスト
     */
    public void setListEntity(final List<WkTblAddressRsdtDeleteEntity> listEntity) {
        this.listEntity = listEntity;
    }

}
