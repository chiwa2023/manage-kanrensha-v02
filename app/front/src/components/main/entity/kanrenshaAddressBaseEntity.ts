interface KanrenshaAddressBaseEntityInterface {

    /** テーブルId */
    kanrenshaAddressId: number;

    /** 関連者個人Id */
    kanrenshaMasterId: number;

    /** 関連者個人コード */
    kanrenshaCode: string;

    /** 関連者区分 */
    kanrenshaKbn: number;

    /** 関連者企業・団体名称 */
    kanrenshaName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 郵便番号1 */
    postalcode1: string;

    /** 郵便番号2 */
    postalcode2: string;

    /** 住所郵便番号まで */
    addressPostal: string;

    /** 住所番地 */
    addressBlock: string;

    /** 住所建物 */
    addressBuilding: string;

    /** 地方公共団体コード */
    lgCode: string;

    /** 町字Id */
    machiazaId: string;

    /** 街区Id */
    blkId: string;

    /** 地番Id */
    prcId: string;

    /** 住居Id */
    rsdtId: string;

    /** 住居2Id */
    rsdt2Id: string;

    /** 住所郵便番号まで編集有無 */
    isPostalEdit: boolean;

    /** 住所番地編集有無 */
    isBlockEdit: boolean;

    /** 住所建物編集有無 */
    isBuildingEdit: boolean;

    /** 住所郵便番号承認該否 */
    isPostalAccept: boolean;

    /** 住所番地承認該否 */
    isBlockAccept: boolean;

    /** 住所建物承認該否 */
    isBuildingAccept: boolean;

}

class KanrenshaAddressBaseEntity implements KanrenshaAddressBaseEntityInterface {

    /** テーブルId */
    kanrenshaAddressId: number;

    /** 関連者個人Id */
    kanrenshaMasterId: number;

    /** 関連者個人コード */
    kanrenshaCode: string;

    /** 関連者区分 */
    kanrenshaKbn: number;

    /** 関連者企業・団体名称 */
    kanrenshaName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 郵便番号1 */
    postalcode1: string;

    /** 郵便番号2 */
    postalcode2: string;

    /** 住所郵便番号まで */
    addressPostal: string;

    /** 住所番地 */
    addressBlock: string;

    /** 住所建物 */
    addressBuilding: string;

    /** 地方公共団体コード */
    lgCode: string;

    /** 町字Id */
    machiazaId: string;

    /** 街区Id */
    blkId: string;

    /** 地番Id */
    prcId: string;

    /** 住居Id */
    rsdtId: string;

    /** 住居2Id */
    rsdt2Id: string;

    /** 住所郵便番号まで編集有無 */
    isPostalEdit: boolean;

    /** 住所番地編集有無 */
    isBlockEdit: boolean;

    /** 住所建物編集有無 */
    isBuildingEdit: boolean;

    /** 住所郵便番号承認該否 */
    isPostalAccept: boolean;

    /** 住所番地承認該否 */
    isBlockAccept: boolean;

    /** 住所建物承認該否 */
    isBuildingAccept: boolean;


    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.kanrenshaAddressId = INIT_NUMBER;
        this.kanrenshaMasterId = INIT_NUMBER;
        this.kanrenshaCode = INIT_STRING;
        this.kanrenshaKbn = INIT_NUMBER;
        this.kanrenshaName = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.postalcode1 = INIT_STRING;
        this.postalcode2 = INIT_STRING;
        this.addressPostal = INIT_STRING;
        this.addressBlock = INIT_STRING;
        this.addressBuilding = INIT_STRING;
        this.lgCode = INIT_STRING;
        this.machiazaId = INIT_STRING;
        this.blkId = INIT_STRING;
        this.prcId = INIT_STRING;
        this.rsdtId = INIT_STRING;
        this.rsdt2Id = INIT_STRING;
        this.isPostalEdit = INIT_BOOLEAN;
        this.isBlockEdit = INIT_BOOLEAN;
        this.isBuildingEdit = INIT_BOOLEAN;
        this.isPostalAccept = INIT_BOOLEAN;
        this.isBlockAccept = INIT_BOOLEAN;
        this.isBuildingAccept = INIT_BOOLEAN;
    }

}

export { type KanrenshaAddressBaseEntityInterface, KanrenshaAddressBaseEntity }