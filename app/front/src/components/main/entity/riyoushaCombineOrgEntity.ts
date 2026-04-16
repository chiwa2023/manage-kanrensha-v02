interface RiyoushaCombineOrgEntityInterface {

        /** テーブルId */
    riyoushaCombineOrgId: number;

    /** 紐づけコード */
    riyoushaCombineOrgCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 紐づけ関連者区分 */
    riyoushaKbn: number;

    /** 個人関連者コード */
    personRiyoushaCode: number;

    /** 個人氏名 */
    personName: string;

    /** 団体関連者コード */
    orgRiyoushaCode: number;

}


class RiyoushaCombineOrgEntity implements RiyoushaCombineOrgEntityInterface {

    /** テーブルId */
    riyoushaCombineOrgId: number;

    /** 紐づけコード */
    riyoushaCombineOrgCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 紐づけ関連者区分 */
    riyoushaKbn: number;

    /** 個人関連者コード */
    personRiyoushaCode: number;

    /** 個人氏名 */
    personName: string;

    /** 団体関連者コード */
    orgRiyoushaCode: number;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.riyoushaCombineOrgId = INIT_NUMBER;
        this.riyoushaCombineOrgCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.riyoushaKbn = INIT_NUMBER;
        this.personRiyoushaCode = INIT_NUMBER;
        this.personName = INIT_STRING;
        this.orgRiyoushaCode = INIT_NUMBER;
    }
}


export { type RiyoushaCombineOrgEntityInterface, RiyoushaCombineOrgEntity }