interface SnsServiceEntityInterface {

    /** テーブルId */
    snsServiceId: number;

    /** SNSサービスコード */
    snsServiceCode: number;

    /** SNSサービス名称 */
    snsServiceName: string;

    /** 最新フラグ */
    isLatest: boolean;

    /** ポータルURL */
    snsPortalUrl: string;

    /** 全文検索用カラム */
    searchText: string;

}

class SnsServiceEntity
    implements SnsServiceEntityInterface {

    /** テーブルId */
    snsServiceId: number;

    /** SNSサービスコード */
    snsServiceCode: number;

    /** SNSサービス名称 */
    snsServiceName: string;

    /** 最新フラグ */
    isLatest: boolean;

    /** ポータルURL */
    snsPortalUrl: string;

    /** 全文検索用カラム */
    searchText: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.snsServiceId = INIT_NUMBER;
        this.snsServiceCode = INIT_NUMBER;
        this.snsServiceName = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.snsPortalUrl = INIT_STRING;
        this.searchText = INIT_STRING;
    }

}

export { type SnsServiceEntityInterface, SnsServiceEntity }
