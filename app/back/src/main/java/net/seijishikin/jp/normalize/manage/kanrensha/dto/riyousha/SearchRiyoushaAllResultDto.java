package net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * 利用者検索結果Dto
 */
public class SearchRiyoushaAllResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 利用者SE権限検索結果Dto */
    private SearchRiyoushaAdminResultDto searchRiyoushaAdminResultDto = new SearchRiyoushaAdminResultDto();

    /** 利用者運営者検索結果Dto */
    private SearchRiyoushaManagerResultDto searchRiyoushaManagerResultDto = new SearchRiyoushaManagerResultDto();

    /** 利用者APIパートナー検索結果Dto */
    private SearchRiyoushaPartnerApiResultDto searchRiyoushaPartnerApiResultDto = new SearchRiyoushaPartnerApiResultDto();

    /**
     * 利用者SE権限検索結果Dtoを取得する
     * 
     * @return 利用者SE権限検索結果Dto
     */
    public SearchRiyoushaAdminResultDto getSearchRiyoushaAdminResultDto() {
        return searchRiyoushaAdminResultDto;
    }

    /**
     * 利用者SE権限検索結果Dtoを設定する
     * 
     * @param searchRiyoushaAdminResultDto 利用者SE権限検索結果Dto
     */
    public void setSearchRiyoushaAdminResultDto(final SearchRiyoushaAdminResultDto searchRiyoushaAdminResultDto) {
        this.searchRiyoushaAdminResultDto = searchRiyoushaAdminResultDto;
    }

    /**
     * 利用者運営者検索結果Dtoを取得する
     * 
     * @return 利用者運営者検索結果Dto
     */
    public SearchRiyoushaManagerResultDto getSearchRiyoushaManagerResultDto() {
        return searchRiyoushaManagerResultDto;
    }

    /**
     * 利用者運営者検索結果Dtoを設定する
     * 
     * @param searchRiyoushaManagerResultDto 利用者運営者検索結果Dto
     */
    public void setSearchRiyoushaManagerResultDto(final SearchRiyoushaManagerResultDto searchRiyoushaManagerResultDto) {
        this.searchRiyoushaManagerResultDto = searchRiyoushaManagerResultDto;
    }

    /**
     * 利用者APIパートナー検索結果Dtoを取得する
     * 
     * @return 利用者APIパートナー検索結果Dto
     */
    public SearchRiyoushaPartnerApiResultDto getSearchRiyoushaPartnerApiResultDto() {
        return searchRiyoushaPartnerApiResultDto;
    }

    /**
     * 利用者APIパートナー検索結果Dtoを設定する
     * 
     * @param searchRiyoushaPartnerApiResultDto 利用者APIパートナー検索結果Dto
     */
    public void setSearchRiyoushaPartnerApiResultDto(
            final SearchRiyoushaPartnerApiResultDto searchRiyoushaPartnerApiResultDto) {
        this.searchRiyoushaPartnerApiResultDto = searchRiyoushaPartnerApiResultDto;
    }

}
