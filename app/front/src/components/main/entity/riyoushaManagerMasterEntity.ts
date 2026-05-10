interface RiyoushaManagerMasterEntityInterface {

    /** テーブルId */
    riyoushaManagerMasterId: number;

    /** 利用者運営者コード */
    riyoushaManagerMasterCode: number;

    /** 利用者個人属性id */
    riyoushaPersonPropertyId: number;

    /** 利用者個人属性コード */
    riyoushaPersonPropertyCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 姓名 */
    allName: string;

    /** 姓名かな */
    allNameKana: string;

    /** 住所全体 */
    addressAll: string;

}



class RiyoushaManagerMasterEntity implements RiyoushaManagerMasterEntityInterface {


    /** テーブルId */
    riyoushaManagerMasterId: number;


    /** 利用者運営者コード */
    riyoushaManagerMasterCode: number;


    /** 利用者個人属性id */
    riyoushaPersonPropertyId: number;

    /** 利用者個人属性コード */
    riyoushaPersonPropertyCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 姓名 */
    allName: string;

    /** 姓名かな */
    allNameKana: string;

    /** 住所全体 */
    addressAll: string;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.riyoushaManagerMasterId = INIT_NUMBER;
        this.riyoushaManagerMasterCode = INIT_NUMBER;
        this.riyoushaPersonPropertyId = INIT_NUMBER;
        this.riyoushaPersonPropertyCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.allName = INIT_STRING;
        this.allNameKana = INIT_STRING;
        this.addressAll = INIT_STRING;
    }
}

export { type RiyoushaManagerMasterEntityInterface, RiyoushaManagerMasterEntity }
