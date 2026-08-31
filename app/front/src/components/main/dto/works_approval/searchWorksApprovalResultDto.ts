import { SearchApprovalAddressResultDto, type SearchApprovalAddressResultDtoInterface } from "./searchApprovalAddressResultDto"
import { SearchApprovalShokugyouResultDto, type SearchApprovalShokugyouResultDtoInterface } from "./searchApprovalShokugyouResultDto"

interface SearchWorksApprovalResultDtoInterface {

    /** 承認作業住所検索結果Dto */
    resultDtoAddress: SearchApprovalAddressResultDtoInterface;

    /** 承認作業職業検索結果Dto */
    resultDtoShokugyou: SearchApprovalShokugyouResultDtoInterface;

}

class SearchWorksApprovalResultDto {

    /** 承認作業住所検索結果Dto */
    resultDtoAddress: SearchApprovalAddressResultDtoInterface;

    /** 承認作業職業検索結果Dto */
    resultDtoShokugyou: SearchApprovalShokugyouResultDtoInterface;

    constructor() {
        this.resultDtoAddress = new SearchApprovalAddressResultDto();
        this.resultDtoShokugyou = new SearchApprovalShokugyouResultDto();
    }

}

export { type SearchWorksApprovalResultDtoInterface, SearchWorksApprovalResultDto }