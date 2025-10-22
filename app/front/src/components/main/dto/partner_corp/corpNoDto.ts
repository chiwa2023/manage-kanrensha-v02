import type InputAccessInterface from "../input_access/inputAccessDto";
import InputAccessDto from "../input_access/inputAccessDto";
import InputAddressDto from "../Input_address/inputAddressDto";
import type InputKanrenshaPersonLeastInterface from "../input_person_name/inputKanrenshaPersonLeastDto";
import InputKanrenshaPersonLeastDto from "../input_person_name/inputKanrenshaPersonLeastDto";
import InputOrgNameDto from "../input_org_name/inputOrgNameDto";
import InputOrgNameInterface from "../input_org_name/inputOrgNameDto";

export default interface CorpNoInterface {

}

export default class CorpNoDto implements CorpNoInterface {

    /** 関連者企業団体マスタテーブルId */
    masterId: number;

    /** 関連者企業団体連絡先テーブルId */
    accessId: number;

    /** 関連者企業団体住所テーブルId */
    addressId: number;

    /** 関連者企業団体基本テーブルId */
    baseId: number;

    /** 関連者企業団体属性テーブルId */
    propertyId: number;

    /** 関連者企業・団体コード */
    corpKanrenshaCode: string;

    /** 法人番号 */
    houjinNo: string;

    /** 支店該当 */
    isShiten: boolean;

    /** 法人種別 */
    houjinSbts: string;

    /** 政治団体名称 */
    inputOrgNameDto: InputOrgNameInterface;

    /** 住所詳細Dto */
    inputAddressDto: InputAddressDto;

    /** 連絡先Dto */
    inputAccessDto:InputAccessInterface;

    /** 団体代表者関連者最低限Dto */
    orgDelegateLeastDto: InputKanrenshaPersonLeastInterface;

        /** 関連者ユーザ紐づけ該否 */
        isCombineUser:boolean;

    constructor() {

        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;
        const INIT_BOOLEAN: boolean = false;

        this.corpKanrenshaCode = INIT_STRING;
        this.houjinNo = INIT_STRING;
        this.isShiten = false;
        this.houjinSbts = INIT_STRING;
        this.inputOrgNameDto = new InputOrgNameDto();
        this.inputAddressDto = new InputAddressDto();
        this.inputAccessDto = new InputAccessDto();
        this.orgDelegateLeastDto = new InputKanrenshaPersonLeastDto();

        this.masterId = INIT_NUMBER;
        this.accessId = INIT_NUMBER;
        this.addressId = INIT_NUMBER;
        this.baseId = INIT_NUMBER;
        this.propertyId = INIT_NUMBER;

        this.isCombineUser = INIT_BOOLEAN;
    }
}