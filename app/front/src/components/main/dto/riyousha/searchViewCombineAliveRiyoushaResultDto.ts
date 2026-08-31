import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchViewCombineAliveRiyoushaResultDtoInterface extends FrameworkPagingDtoInterface {


    /** 利用者Id */
    riyoushaId: number;

    /** 利用者コード */
    riyoushaCode: number;

    /** 権限基礎 */
    roleBase: string;

    /** 権限保持 */
    roleHas: string;

    /** 氏名 */
    allName: string;

}

class SearchViewCombineAliveRiyoushaResultDto extends FrameworkPagingDto implements SearchViewCombineAliveRiyoushaResultDtoInterface {

    /** 利用者Id */
    riyoushaId: number;

    /** 利用者コード */
    riyoushaCode: number;

    /** 権限基礎 */
    roleBase: string;

    /** 権限保持 */
    roleHas: string;

    /** 氏名 */
    allName: string;

    constructor() {
        super();

        // 初期データ
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;

        this.riyoushaId = INIT_NUMBER;
        this.riyoushaCode = INIT_NUMBER;
        this.roleBase = INIT_STRING;
        this.roleHas = INIT_STRING;
        this.allName = INIT_STRING;
    }
}

export { type SearchViewCombineAliveRiyoushaResultDtoInterface, SearchViewCombineAliveRiyoushaResultDto }
