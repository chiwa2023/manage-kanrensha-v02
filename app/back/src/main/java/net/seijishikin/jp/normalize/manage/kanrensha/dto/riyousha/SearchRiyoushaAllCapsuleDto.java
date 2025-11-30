package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.NaturalTextSearchPagingCapsuleDto;

/**
 * 利用者検索条件Dto
 */
public class SearchRiyoushaAllCapsuleDto extends NaturalTextSearchPagingCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** APIユーザ検索フラグ */
    private Boolean isPartnerApiSearch = INIT_BOOLEAN;

    /** 運営者検索フラグ */
    private Boolean isManagerSearch = INIT_BOOLEAN;

    /** 管理者検索フラグ */
    private Boolean isAdminSearch = INIT_BOOLEAN;

    /**
     * APIユーザ検索実行フラグを取得する
     *
     * @return APIユーザ検索
     */
    public Boolean getIsPartnerApiSearch() {
        return isPartnerApiSearch;
    }

    /**
     * APIユーザ実行フラグ検索を設定する
     *
     * @param isPartnerApiSearch APIユーザ検索
     */
    public void setIsPartnerApiSearch(final Boolean isPartnerApiSearch) {
        this.isPartnerApiSearch = isPartnerApiSearch;
    }

    /**
     * 運営者検索実行フラグを取得する
     *
     * @return 運営者検索実行フラグ
     */
    public Boolean getIsManagerSearch() {
        return isManagerSearch;
    }

    /**
     * 運営者検索実行フラグを設定する
     *
     * @param isManagerSearch 運営者検索実行フラグ
     */
    public void setIsManagerSearch(final Boolean isManagerSearch) {
        this.isManagerSearch = isManagerSearch;
    }

    /**
     * SE権限検索実行フラグを取得する
     *
     * @return SE権限検索実行フラグ
     */
    public Boolean getIsAdminSearch() {
        return isAdminSearch;
    }

    /**
     * SE権限検索実行フラグを設定する
     *
     * @param isAdminSearch SE権限検索実行フラグ
     */
    public void setIsAdminSearch(final Boolean isAdminSearch) {
        this.isAdminSearch = isAdminSearch;
    }

    /** 運営者検索条件Dto */
    private SearchRiyoushaManagerCapsuleDto searchRiyoushaManagerCapsuleDto = new SearchRiyoushaManagerCapsuleDto();

    /** SE権限検索条件Dto */
    private SearchRiyoushaAdminCapsuleDto searchRiyoushaAdminCapsuleDto = new SearchRiyoushaAdminCapsuleDto();

    /** APIパートナー検索条件Dto */
    private SearchRiyoushaPartnerApiCapsuleDto searchRiyoushaPartnerApiCapsuleDto = new SearchRiyoushaPartnerApiCapsuleDto();

    /**
     * 運営者検索条件Dtoを取得する
     * 
     * @return 運営者検索条件Dto
     */
    public SearchRiyoushaManagerCapsuleDto getSearchRiyoushaManagerCapsuleDto() {
        return searchRiyoushaManagerCapsuleDto;
    }

    /**
     * 運営者検索条件Dtoを設定する
     * 
     * @param searchRiyoushaManagerCapsuleDto 運営者検索条件Dto
     */
    public void setSearchRiyoushaManagerCapsuleDto(
            final SearchRiyoushaManagerCapsuleDto searchRiyoushaManagerCapsuleDto) {
        this.searchRiyoushaManagerCapsuleDto = searchRiyoushaManagerCapsuleDto;
    }

    /**
     * SE権限検索条件Dtoを取得する
     * 
     * @return SE権限検索条件Dto
     */
    public SearchRiyoushaAdminCapsuleDto getSearchRiyoushaAdminCapsuleDto() {
        return searchRiyoushaAdminCapsuleDto;
    }

    /**
     * SE権限検索条件Dtoを設定する
     * 
     * @param searchRiyoushaAdminCapsuleDto SE権限検索条件Dto
     */
    public void setSearchRiyoushaAdminCapsuleDto(final SearchRiyoushaAdminCapsuleDto searchRiyoushaAdminCapsuleDto) {
        this.searchRiyoushaAdminCapsuleDto = searchRiyoushaAdminCapsuleDto;
    }

    /**
     * APIパートナー検索条件Dtoを取得する
     * 
     * @return APIパートナー検索条件Dto
     */
    public SearchRiyoushaPartnerApiCapsuleDto getSearchRiyoushaPartnerApiCapsuleDto() {
        return searchRiyoushaPartnerApiCapsuleDto;
    }

    /**
     * APIパートナー検索条件Dtoを設定する
     * 
     * @param searchRiyoushaPartnerApiCapsuleDto APIパートナー検索条件Dto
     */
    public void setSearchRiyoushaPartnerApiCapsuleDto(
            final SearchRiyoushaPartnerApiCapsuleDto searchRiyoushaPartnerApiCapsuleDto) {
        this.searchRiyoushaPartnerApiCapsuleDto = searchRiyoushaPartnerApiCapsuleDto;
    }

}
