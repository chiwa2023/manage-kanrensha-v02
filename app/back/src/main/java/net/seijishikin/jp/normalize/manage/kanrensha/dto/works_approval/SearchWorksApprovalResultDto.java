package net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval;

import java.io.Serializable;

/**
 * 作業承認検索結果Dto
 */
public class SearchWorksApprovalResultDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 承認作業住所検索結果Dto */
    private SearchApprovalAddressResultDto resultDtoAddress = new SearchApprovalAddressResultDto();

    /** 承認作業職業検索結果Dto */
    private SearchApprovalShokugyouResultDto resultDtoShokugyou = new SearchApprovalShokugyouResultDto();

    /**
     * 承認作業住所検索結果Dtoを取得する
     *
     * @return 承認作業住所検索結果Dto
     */
    public SearchApprovalAddressResultDto getResultDtoAddress() {
        return resultDtoAddress;
    }

    /**
     * 承認作業住所検索結果Dtoを設定する
     *
     * @param resultDtoAddress 承認作業住所検索結果Dto
     */
    public void setResultDtoAddress(final SearchApprovalAddressResultDto resultDtoAddress) {
        this.resultDtoAddress = resultDtoAddress;
    }

    /**
     * 承認作業職業検索結果Dtoを取得する
     *
     * @return 承認作業職業検索結果Dto
     */
    public SearchApprovalShokugyouResultDto getResultDtoShokugyou() {
        return resultDtoShokugyou;
    }

    /**
     * 承認作業職業検索結果Dtoを設定する
     *
     * @param resultDtoShokugyou 承認作業職業検索結果Dto
     */
    public void setResultDtoShokugyou(final SearchApprovalShokugyouResultDto resultDtoShokugyou) {
        this.resultDtoShokugyou = resultDtoShokugyou;
    }

}
