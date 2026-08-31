interface RiyoushaCombineOrgEntityInterface {

    /** テーブルId */
    riyoushaCombineOrgId: number;

    /** 紐づけコード */
    riyoushaCombineOrgCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 紐づけ関連者区分 */
    riyoushaRole: string;

    /** 個人関連者コード */
    personCode: number;

    /** 利用者個人コード */
    personRiyoushaCode: number;

    /** 個人氏名 */
    personRiyoushaName: string;

    /** 団体関連者コード */
    orgRiyoushaCode: number;

    /** 団体関連者名称 */
    orgName: string;

}


class RiyoushaCombineOrgEntity implements RiyoushaCombineOrgEntityInterface {

    /** テーブルId */
    riyoushaCombineOrgId: number;

    /** 紐づけコード */
    riyoushaCombineOrgCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 紐づけ関連者区分 */
    riyoushaRole: string;

    /** 個人関連者コード */
    personCode: number;

    /** 利用者個人コード */
    personRiyoushaCode: number;

    /** 個人氏名 */
    personRiyoushaName: string;

    /** 団体関連者コード */
    orgRiyoushaCode: number;

    /** 団体関連者名称 */
    orgName: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.riyoushaCombineOrgId = INIT_NUMBER;
        this.riyoushaCombineOrgCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.riyoushaRole = INIT_STRING;
        this.personCode = INIT_NUMBER;
        this.personRiyoushaCode = INIT_NUMBER;
        this.personRiyoushaName = INIT_STRING;
        this.orgRiyoushaCode = INIT_NUMBER;
        this.orgName = INIT_STRING;
    }
}


export { type RiyoushaCombineOrgEntityInterface, RiyoushaCombineOrgEntity }