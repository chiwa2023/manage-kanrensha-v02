interface RiyoushaOrgMasterEntityInterface {

    /** 利用者テーブルI */
    riyoushaOrgMasterId: number;

    /** 利用者組織コード */
    riyoushaOrgMasterCode: number;

    /** 利用者組織属性id */
    riyoushaPersonPropertyId: number;

    /** 利用者組織属性コード */
    riyoushaPersonPropertyCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 姓名 */
    allName: string;

    /** 姓名かな */
    allNameKana: string;

    /** 住所全体 */
    addressAll: string;

    /** 全文検索用カラム */
    searchText: string;

}


class RiyoushaOrgMasterEntity implements RiyoushaOrgMasterEntityInterface {

    /** 利用者テーブルI */
    riyoushaOrgMasterId: number;

    /** 利用者組織コード */
    riyoushaOrgMasterCode: number;

    /** 利用者組織属性id */
    riyoushaPersonPropertyId: number;

    /** 利用者組織属性コード */
    riyoushaPersonPropertyCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 姓名 */
    allName: string;

    /** 姓名かな */
    allNameKana: string;

    /** 住所全体 */
    addressAll: string;

    /** 全文検索用カラム */
    searchText: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.riyoushaOrgMasterId = INIT_NUMBER;
        this.riyoushaOrgMasterCode = INIT_NUMBER;
        this.riyoushaPersonPropertyId = INIT_NUMBER;
        this.riyoushaPersonPropertyCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.allName = INIT_STRING;
        this.allNameKana = INIT_STRING;
        this.addressAll = INIT_STRING;
        this.searchText = INIT_STRING;
    }
}

export { type RiyoushaOrgMasterEntityInterface, RiyoushaOrgMasterEntity }
