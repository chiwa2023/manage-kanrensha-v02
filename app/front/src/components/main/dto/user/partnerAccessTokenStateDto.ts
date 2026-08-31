interface PartnerAccessTokenStateDtoInterface {

    /** テーブルID */
    partnerAccessTokenId: number;

    /** ユーザコード */
    userCode: number;

    /** ユーザ名 */
    userName: string;

    /** 有効期限 */
    expiresAt: Date;

    /** 発行日時 */
    createdAt: Date;

    /** 最終利用日時 */
    lastUsedAt: Date;

    /** 失効日時 */
    revokedAt: Date;

    /** IPアドレス */
    ipAddress: string;
}

class PartnerAccessTokenStateDto implements PartnerAccessTokenStateDtoInterface {

    /** テーブルID */
    partnerAccessTokenId: number;

    /** ユーザコード */
    userCode: number;

    /** ユーザ名 */
    userName: string;

    /** 有効期限 */
    expiresAt: Date;

    /** 発行日時 */
    createdAt: Date;

    /** 最終利用日時 */
    lastUsedAt: Date;

    /** 失効日時 */
    revokedAt: Date;

    /** IPアドレス */
    ipAddress: string;

    constructor() {

        const INIT_INTEGER: number = 0;
        const INIT_STRING: string = "";
        const INIT_TIMESTAMP: Date = new Date(1948, 7, 28, 23, 59, 59);

        this.partnerAccessTokenId = INIT_INTEGER;
        this.userCode = INIT_INTEGER;
        this.userName = INIT_STRING;
        this.expiresAt = INIT_TIMESTAMP;
        this.createdAt = INIT_TIMESTAMP;
        this.lastUsedAt = INIT_TIMESTAMP;
        this.revokedAt = INIT_TIMESTAMP;
        this.ipAddress = INIT_STRING;
    }

}

export { type PartnerAccessTokenStateDtoInterface, PartnerAccessTokenStateDto }