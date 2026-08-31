import { InputAccessDto, InputAddressDto, InputPersonNameDto, InputShokugyouDto, type InputAccessDtoInterface, type InputAddressDtoInterface, type InputPersonNameDtoInterface, type InputShokugyouDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface KanrenshaPersonDtoInterface {
    
    /** 個人氏名入力Dto */
    inputPersonNameDto: InputPersonNameDtoInterface;

    /** 住所入力Dto */
    inputAddressDto: InputAddressDtoInterface;

    /** 連絡先入力Dto */
    inputAccessDto: InputAccessDtoInterface;

    /** 職業入力Dto */
    inputShokugyouDto: InputShokugyouDtoInterface;

    /** 外国籍該否 */
    isForeign: boolean;

    /** 関連者個人マスタテーブルId */
    masterId: number;

    /** 関連者個人連絡先テーブルId */
    accessId: number;

    /** 関連者個人住所テーブルId */
    addressId: number;

    /** 関連者個人属性テーブルId */
    propertyId: number;

    /** 関連者個人コード */
    personKanrenshaCode: string;

    /** 関連者ユーザ紐づけ該否 */
    isCombineUser: boolean;
}

class KanrenshaPersonDto implements KanrenshaPersonDtoInterface {

    /** 個人氏名入力Dto */
    inputPersonNameDto: InputPersonNameDtoInterface;

    /** 住所入力Dto */
    inputAddressDto: InputAddressDtoInterface;

    /** 連絡先入力Dto */
    inputAccessDto: InputAccessDtoInterface;

    /** 職業入力Dto */
    inputShokugyouDto: InputShokugyouDtoInterface;

    /** 外国籍該否 */
    isForeign: boolean;

    /** 関連者個人マスタテーブルId */
    masterId: number;

    /** 関連者個人連絡先テーブルId */
    accessId: number;

    /** 関連者個人住所テーブルId */
    addressId: number;

    /** 関連者個人属性テーブルId */
    propertyId: number;

    /** 関連者個人コード */
    personKanrenshaCode: string;

    /** 関連者ユーザ紐づけ該否 */
    isCombineUser: boolean;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.inputPersonNameDto = new InputPersonNameDto();
        this.inputAddressDto = new InputAddressDto();
        this.inputAccessDto = new InputAccessDto();
        this.inputShokugyouDto = new InputShokugyouDto();
        this.isForeign = INIT_BOOLEAN;
        this.masterId = INIT_NUMBER;
        this.accessId = INIT_NUMBER;
        this.addressId = INIT_NUMBER;
        this.propertyId = INIT_NUMBER;
        this.personKanrenshaCode = INIT_STRING;
        this.isCombineUser = INIT_BOOLEAN;
    }

}

export { type KanrenshaPersonDtoInterface, KanrenshaPersonDto }
