
interface RiyoushaCombineOrgTempEntityInterface {

    /** テーブルId */
    riyoushaCombineOrgTempId: number;

    /** 紐づけコード */
    riyoushaCombineOrgTempCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 利用者権限 */
    riyoushaRole: string;

    /** 個人コード */
    personCode: number;

    /** 利用者個人コード */
    personRiyoushaCode: number;

    /** 利用者氏名 */
    personRiyoushaName: string;

    /** 利用者組織コード */
    orgRiyoushaCode: number;

    /** 利用者組織名称 */
    orgName: string;
}

class RiyoushaCombineOrgTempEntity implements RiyoushaCombineOrgTempEntityInterface {

    /** テーブルId */
    riyoushaCombineOrgTempId: number;

    /** 紐づけコード */
    riyoushaCombineOrgTempCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 利用者権限 */
    riyoushaRole: string;

    /** 個人コード */
    personCode: number;

    /** 利用者個人コード */
    personRiyoushaCode: number;

    /** 利用者氏名 */
    personRiyoushaName: string;

    /** 利用者組織コード */
    orgRiyoushaCode: number;

    /** 利用者組織名称 */
    orgName: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.riyoushaCombineOrgTempId = INIT_NUMBER;
        this.riyoushaCombineOrgTempCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.riyoushaRole = INIT_STRING;
        this.personCode = INIT_NUMBER;
        this.personRiyoushaCode = INIT_NUMBER;
        this.personRiyoushaName = INIT_STRING;
        this.orgRiyoushaCode = INIT_NUMBER;
        this.orgName = INIT_STRING;
    }
}

export { type RiyoushaCombineOrgTempEntityInterface, RiyoushaCombineOrgTempEntity }
