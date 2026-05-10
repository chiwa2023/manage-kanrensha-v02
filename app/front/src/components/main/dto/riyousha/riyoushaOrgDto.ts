import { FrameworkMessageAndResultDto, InputAccessDto, InputAddressDto, InputOrgNameDto, type FrameworkMessageAndResultDtoInterface, type InputAccessDtoInterface, type InputAddressDtoInterface, type InputOrgNameDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { RiyoushaCombineOrgEntityInterface } from "../../entity/riyoushaCombineOrgEntity";
interface RiyoushaOrgDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** テーブルId */
    riyoushaOrgMasterId: number;

    /** 利用者組織コード */
    riyoushaOrgMasterCode: number;

    /** 利用者組織属性id */
    riyoushaOrgPropertyId: number;

    /** 利用者組織属性コード */
    riyoushaOrgPropertyCode: number;

    /** 組織名称入力 */
    inputOrgNameDto: InputOrgNameDtoInterface;

    /** 住所入力 */
    inputAddressDto: InputAddressDtoInterface;

    /** 連絡先入力 */
    inputAccessDto: InputAccessDtoInterface;

    /** 組織構成員リスト */
    listPersonCombine: RiyoushaCombineOrgEntityInterface[];

}


class RiyoushaOrgDto extends FrameworkMessageAndResultDto
    implements RiyoushaOrgDtoInterface {

    /** テーブルId */
    riyoushaOrgMasterId: number;

    /** 利用者組織コード */
    riyoushaOrgMasterCode: number;

    /** 利用者組織属性id */
    riyoushaOrgPropertyId: number;

    /** 利用者組織属性コード */
    riyoushaOrgPropertyCode: number;

    /** 組織名称入力 */
    inputOrgNameDto: InputOrgNameDtoInterface;

    /** 住所入力 */
    inputAddressDto: InputAddressDtoInterface;

    /** 連絡先入力 */
    inputAccessDto: InputAccessDtoInterface;

    /** 組織構成員リスト */
    listPersonCombine: RiyoushaCombineOrgEntityInterface[];

    constructor() {
        super();

        const INIT_NUMBER: number = 0;

        this.riyoushaOrgMasterId = INIT_NUMBER;
        this.riyoushaOrgMasterCode = INIT_NUMBER;
        this.riyoushaOrgPropertyId = INIT_NUMBER;
        this.riyoushaOrgPropertyCode = INIT_NUMBER;
        this.inputOrgNameDto = new InputOrgNameDto();
        this.inputAddressDto = new InputAddressDto();
        this.inputAccessDto = new InputAccessDto();
        this.listPersonCombine = [];
    }
}

export { type RiyoushaOrgDtoInterface, RiyoushaOrgDto }
