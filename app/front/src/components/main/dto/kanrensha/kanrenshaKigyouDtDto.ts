import {
    InputAccessDto, InputAddressDto, InputOrgNameDto, type InputAccessDtoInterface,
    type InputAddressDtoInterface, type InputOrgNameDtoInterface, type InputKanrenshaPersonLeastDtoInterface,
    InputKanrenshaPersonLeastDto
} from "seijishikin-jp-normalize_common-tool";

interface KanrenshaKigyouDtDtoInterface {
    /** 団体名称入力Dto */
    inputOrgNameDto: InputOrgNameDtoInterface;

    /** 住所入力Dto */
    inputAddressDto: InputAddressDtoInterface;

    /** 連絡先Dto */
    inputAccessDto: InputAccessDtoInterface;

    /** 団体代表者関連者最低限Dto */
    orgDelegateLeastDto: InputKanrenshaPersonLeastDtoInterface;

    /** 支店該当 */
    isShiten: boolean;

    /** 法人種別 */
    houjinSbts: string;

    /** 法人番号 */
    houjinNo: string;

    /** 関連者企業団体マスタテーブルId */
    masterId: number;

    /** 関連者企業団体連絡先テーブルId */
    accessId: number;

    /** 関連者企業団体住所テーブルId */
    addressId: number;

    /** 関連者企業団体属性テーブルId */
    propertyId: number;

    /** 関連者企業団体コード */
    kigyouDtKanrenshaCode: string;

    /** 関連者ユーザ紐づけ該否 */
    isCombineUser: boolean;

}

class KanrenshaKigyouDtDto implements KanrenshaKigyouDtDtoInterface {

    /** 団体名称入力Dto */
    inputOrgNameDto: InputOrgNameDtoInterface;

    /** 住所入力Dto */
    inputAddressDto: InputAddressDtoInterface;

    /** 連絡先Dto */
    inputAccessDto: InputAccessDtoInterface;

    /** 団体代表者関連者最低限Dto */
    orgDelegateLeastDto: InputKanrenshaPersonLeastDtoInterface;

    /** 支店該当 */
    isShiten: boolean;

    /** 法人種別 */
    houjinSbts: string;

    /** 法人番号 */
    houjinNo: string;

    /** 関連者企業団体マスタテーブルId */
    masterId: number;

    /** 関連者企業団体連絡先テーブルId */
    accessId: number;

    /** 関連者企業団体住所テーブルId */
    addressId: number;

    /** 関連者企業団体属性テーブルId */
    propertyId: number;

    /** 関連者企業団体コード */
    kigyouDtKanrenshaCode: string;

    /** 関連者ユーザ紐づけ該否 */
    isCombineUser: boolean;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.inputOrgNameDto = new InputOrgNameDto();
        this.inputAddressDto = new InputAddressDto();
        this.inputAccessDto = new InputAccessDto();
        this.orgDelegateLeastDto = new InputKanrenshaPersonLeastDto();
        this.isShiten = INIT_BOOLEAN;
        this.houjinSbts = INIT_STRING;
        this.houjinNo = INIT_STRING;
        this.masterId = INIT_NUMBER;
        this.accessId = INIT_NUMBER;
        this.addressId = INIT_NUMBER;
        this.propertyId = INIT_NUMBER;
        this.kigyouDtKanrenshaCode = INIT_STRING;
        this.isCombineUser = INIT_BOOLEAN;
    }


}

export { type KanrenshaKigyouDtDtoInterface, KanrenshaKigyouDtDto }
