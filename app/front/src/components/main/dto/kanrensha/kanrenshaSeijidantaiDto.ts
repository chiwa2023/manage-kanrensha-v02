import {
    InputAccessDto, InputAddressDto, InputKanrenshaPersonLeastDto, InputOrgNameDto,
    type InputAccessDtoInterface, type InputAddressDtoInterface, type InputKanrenshaPersonLeastDtoInterface,
    type InputOrgNameDtoInterface
} from "seijishikin-jp-normalize_common-tool";

interface KanrenshaSeijidantaiDtoInterface {
    /** 名称入力Dto */
    inputOrgNameDto: InputOrgNameDtoInterface;

    /** 住所入力Dto */
    inputAddressDto: InputAddressDtoInterface

    /** 連絡先Dto */
    inputAccessDto: InputAccessDtoInterface;

    /** 団体代表者関連者最低限Dto */
    orgDelegateLeastDto: InputKanrenshaPersonLeastDtoInterface;

    /** 団体会計責任者関連者最低限Dto */
    accounrMgrLeastDto: InputKanrenshaPersonLeastDtoInterface;

    /** 政治団体区分 */
    dantaiKbn: string;

    /** 政治団体番号 */
    poliOrgNo: string;

    /** 関連者政治団体マスタテーブルId */
    masterId: number;

    /** 関連者政治団体連絡先テーブルId */
    accessId: number;

    /** 関連者政治団体住所テーブルId */
    addressId: number;

    /** 関連者政治団体属性テーブルId */
    propertyId: number;

    /** 関連者政治団体コード */
    seijidantaiKanrenshaCode: string;

    /** 関連者ユーザ紐づけ該否 */
    isCombineUser: boolean;

}

class KanrenshaSeijidantaiDto implements KanrenshaSeijidantaiDtoInterface {

    /** 名称入力Dto */
    inputOrgNameDto: InputOrgNameDtoInterface;

    /** 住所入力Dto */
    inputAddressDto: InputAddressDtoInterface

    /** 連絡先Dto */
    inputAccessDto: InputAccessDtoInterface;

    /** 団体代表者関連者最低限Dto */
    orgDelegateLeastDto: InputKanrenshaPersonLeastDtoInterface;

    /** 団体会計責任者関連者最低限Dto */
    accounrMgrLeastDto: InputKanrenshaPersonLeastDtoInterface;

    /** 政治団体区分 */
    dantaiKbn: string;

    /** 政治団体番号 */
    poliOrgNo: string;

    /** 関連者政治団体マスタテーブルId */
    masterId: number;

    /** 関連者政治団体連絡先テーブルId */
    accessId: number;

    /** 関連者政治団体住所テーブルId */
    addressId: number;

    /** 関連者政治団体属性テーブルId */
    propertyId: number;

    /** 関連者政治団体コード */
    seijidantaiKanrenshaCode: string;

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
        this.accounrMgrLeastDto = new InputKanrenshaPersonLeastDto();
        this.dantaiKbn = INIT_STRING;
        this.poliOrgNo = INIT_STRING;
        this.masterId = INIT_NUMBER;
        this.accessId = INIT_NUMBER;
        this.addressId = INIT_NUMBER;
        this.propertyId = INIT_NUMBER;
        this.seijidantaiKanrenshaCode = INIT_STRING;
        this.isCombineUser = INIT_BOOLEAN;
    }

}
export { type KanrenshaSeijidantaiDtoInterface, KanrenshaSeijidantaiDto }