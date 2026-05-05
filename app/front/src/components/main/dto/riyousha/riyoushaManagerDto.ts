import { FrameworkMessageAndResultDto, InputAccessDto, InputAddressDto, InputPersonNameDto, type FrameworkMessageAndResultDtoInterface, type InputAccessDtoInterface, type InputAddressDtoInterface, type InputPersonNameDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface RiyoushaManagerDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** テーブルId */
    riyoushaManagerMasterId: number;

    /** 利用者運営者コード */
    riyoushaManagerMasterCode: number;

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


class RiyoushaManagerDto extends FrameworkMessageAndResultDto implements RiyoushaManagerDtoInterface {

    /** テーブルId */
    riyoushaManagerMasterId: number;

    /** 利用者運営者コード */
    riyoushaManagerMasterCode: number;

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

        this.riyoushaManagerMasterId = INIT_NUMBER;
        this.riyoushaManagerMasterCode = INIT_NUMBER;
        this.riyoushaPersonPropertyId = INIT_NUMBER;
        this.riyoushaPersonPropertyCode = INIT_NUMBER;
        this.inputPersonNameDto = new InputPersonNameDto();
        this.inputAddressDto = new InputAddressDto();
        this.inputAccessDto = new InputAccessDto();
        this.isCombineUser = false;

    }

}

export { type RiyoushaManagerDtoInterface, RiyoushaManagerDto }
