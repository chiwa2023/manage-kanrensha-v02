export default interface RiyoushaManagerInterface {

}



export default class RiyoushaManagerEntity implements RiyoushaManagerInterface {

    /** 利用者運営者Id */
    riyoushaManagerId: number;

    /** 利用者運営者コード */
    riyoushaManagerCode: number;

    /** 利用者運営者名称 */
    riyoushaManagerName: string;

    /** 最新該否 */
    isLatest: boolean;

    
    /** 組織非該当 */
    isNotOrg:boolean;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.riyoushaManagerId = INIT_NUMBER;
        this.riyoushaManagerCode = INIT_NUMBER;
        this.riyoushaManagerName = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.isNotOrg = INIT_BOOLEAN;

    }

}