import { FrameworkMessageAndResultDto, InputAccessDto, InputAddressDto, InputPersonNameDto, type FrameworkMessageAndResultDtoInterface, type InputAccessDtoInterface, type InputAddressDtoInterface, type InputPersonNameDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface RiyoushaAdminDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** テーブルId */
    riyoushaAdminMasterId: number;

    /** 利用者運営者コード */
    riyoushaAdminMasterCode: number;

    /** 利用者個人属性id */
    riyoushaPersonPropertyId: number;

    /** 利用者個人属性コード */
    riyoushaPersonPropertyCode: number;

    /** 個人姓名入力 */
    inputPersonNameDto: InputPersonNameDtoInterface;

    /** 住所入力 */
    inputAddressDto: InputAddressDtoInterface;

    /** 連絡先入力 */
    inputAccessDto: InputAccessDtoInterface;

    /** 関連者ユーザ紐づけ該否 */
    isCombineUser: boolean;

}


class RiyoushaAdminDto extends FrameworkMessageAndResultDto implements RiyoushaAdminDtoInterface {

    /** テーブルId */
    riyoushaAdminMasterId: number;

    /** 利用者運営者コード */
    riyoushaAdminMasterCode: number;

    /** 利用者個人属性id */
    riyoushaPersonPropertyId: number;

    /** 利用者個人属性コード */
    riyoushaPersonPropertyCode: number;

    /** 個人姓名入力 */
    inputPersonNameDto: InputPersonNameDtoInterface;

    /** 住所入力 */
    inputAddressDto: InputAddressDtoInterface;

    /** 連絡先入力 */
    inputAccessDto: InputAccessDtoInterface;

    /** 関連者ユーザ紐づけ該否 */
    isCombineUser: boolean;

    constructor() {

        super();

        const INIT_NUMBER: number = 0;

        this.riyoushaAdminMasterId = INIT_NUMBER;
        this.riyoushaAdminMasterCode = INIT_NUMBER;
        this.riyoushaPersonPropertyId = INIT_NUMBER;
        this.riyoushaPersonPropertyCode = INIT_NUMBER;
        this.inputPersonNameDto = new InputPersonNameDto();
        this.inputAddressDto = new InputAddressDto();
        this.inputAccessDto = new InputAccessDto();
        this.isCombineUser = false;
    }

}

export { type RiyoushaAdminDtoInterface, RiyoushaAdminDto }
