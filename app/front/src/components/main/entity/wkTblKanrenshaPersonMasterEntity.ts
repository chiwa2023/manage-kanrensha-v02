interface WkTblKanrenshaPersonMasterEntityInterface {

    /** テーブルId */
    wkTblKanrenshaPersonMasterId: number;

    /** ワークテーブルコード */
    wkTblKanrenshaPersonMasterCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 個人名 */
    kanrenshaName: string;

    /** 個人全住所 */
    allAddress: string;

    /** 個人職業 */
    personShokugyou: string;

    /** 住所郵便番号 */
    addressPostal: string;

    /** 住所番地 */
    addressBlock: string;

    /** 住所建物 */
    addressBuilding: string;

    /** 郵便番号1 */
    postalcode1: string;

    /** 郵便番号2 */
    postalcode2: string;

    /** 地方自治体コード */
    lgCode: string;

    /** 町字コード */
    machiazaId: string;

    /** 街区コード */
    blkId: string;

    /** 地番コード */
    prcId: string;

    /** 住居コード */
    rsdtId: string;

    /** 住居2コード */
    rsdt2Id: string;

    /** 電話番号1 */
    phon1: string;

    /** 電話番号2 */
    phon2: string;

    /** 電話番号3 */
    phon3: string;

    /** 電子メール */
    email: string;

    /** 代表(公式)サイトurl */
    myPortalUrl: string;

    /** SNSサービス名称 */
    snsServiceName: string;

    /** SNSサービスアカウント */
    snsAccount: string;

    /** 姓名の姓 */
    lastName: string;

    /** 姓名の名 */
    firstName: string;

    /** 姓名のミドルネーム */
    middleName: string;

    /** 姓名の姓かな */
    lastNameKana: string;

    /** 姓名の名かな */
    firstNameKana: string;

    /** 姓名のミドルネームかな */
    middleNameKana: string;

    /** 職業の業種 */
    gyoushu: string;

    /** 職業の役職 */
    yakushoku: string;

    /** ユーザ記述の職業 */
    shokugyouUserWrite: string;

    /** 企業番号 */
    kigyouDtNo: string;

    /** 企業所在地 */
    kigyouDtAddress: string;

    /** 企業名 */
    kigyouDtName: string;

    /** 外国籍該否 */
    isForeign: boolean;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;

    /** 住所整形済 */
    isJhushoFormat: boolean;

}



class WkTblKanrenshaPersonMasterEntity implements WkTblKanrenshaPersonMasterEntityInterface {

    /** テーブルId */
    wkTblKanrenshaPersonMasterId: number;

    /** ワークテーブルコード */
    wkTblKanrenshaPersonMasterCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 個人名 */
    kanrenshaName: string;

    /** 個人全住所 */
    allAddress: string;

    /** 個人職業 */
    personShokugyou: string;

    /** 住所郵便番号 */
    addressPostal: string;

    /** 住所番地 */
    addressBlock: string;

    /** 住所建物 */
    addressBuilding: string;

    /** 郵便番号1 */
    postalcode1: string;

    /** 郵便番号2 */
    postalcode2: string;

    /** 地方自治体コード */
    lgCode: string;

    /** 町字コード */
    machiazaId: string;

    /** 街区コード */
    blkId: string;

    /** 地番コード */
    prcId: string;

    /** 住居コード */
    rsdtId: string;

    /** 住居2コード */
    rsdt2Id: string;

    /** 電話番号1 */
    phon1: string;

    /** 電話番号2 */
    phon2: string;

    /** 電話番号3 */
    phon3: string;

    /** 電子メール */
    email: string;

    /** 代表(公式)サイトurl */
    myPortalUrl: string;

    /** SNSサービス名称 */
    snsServiceName: string;

    /** SNSサービスアカウント */
    snsAccount: string;

    /** 姓名の姓 */
    lastName: string;

    /** 姓名の名 */
    firstName: string;

    /** 姓名のミドルネーム */
    middleName: string;

    /** 姓名の姓かな */
    lastNameKana: string;

    /** 姓名の名かな */
    firstNameKana: string;

    /** 姓名のミドルネームかな */
    middleNameKana: string;

    /** 職業の業種 */
    gyoushu: string;

    /** 職業の役職 */
    yakushoku: string;

    /** ユーザ記述の職業 */
    shokugyouUserWrite: string;

    /** 企業番号 */
    kigyouDtNo: string;

    /** 企業所在地 */
    kigyouDtAddress: string;

    /** 企業名 */
    kigyouDtName: string;

    /** 外国籍該否 */
    isForeign: boolean;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;

    /** 住所整形済 */
    isJhushoFormat: boolean;


    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.wkTblKanrenshaPersonMasterId = INIT_NUMBER;
        this.wkTblKanrenshaPersonMasterCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.isFinish = INIT_BOOLEAN;
        this.kanrenshaName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.personShokugyou = INIT_STRING;
        this.addressPostal = INIT_STRING;
        this.addressBlock = INIT_STRING;
        this.addressBuilding = INIT_STRING;
        this.postalcode1 = INIT_STRING;
        this.postalcode2 = INIT_STRING;
        this.lgCode = INIT_STRING;
        this.machiazaId = INIT_STRING;
        this.blkId = INIT_STRING;
        this.prcId = INIT_STRING;
        this.rsdtId = INIT_STRING;
        this.rsdt2Id = INIT_STRING;
        this.phon1 = INIT_STRING;
        this.phon2 = INIT_STRING;
        this.phon3 = INIT_STRING;
        this.email = INIT_STRING;
        this.myPortalUrl = INIT_STRING;
        this.snsServiceName = INIT_STRING;
        this.snsAccount = INIT_STRING;
        this.lastName = INIT_STRING;
        this.firstName = INIT_STRING;
        this.middleName = INIT_STRING;
        this.lastNameKana = INIT_STRING;
        this.firstNameKana = INIT_STRING;
        this.middleNameKana = INIT_STRING;
        this.gyoushu = INIT_STRING;
        this.yakushoku = INIT_STRING;
        this.shokugyouUserWrite = INIT_STRING;
        this.kigyouDtNo = INIT_STRING;
        this.kigyouDtAddress = INIT_STRING;
        this.kigyouDtName = INIT_STRING;
        this.isForeign = INIT_BOOLEAN;
        this.isAffected = INIT_BOOLEAN;
        this.judgeReason = INIT_STRING;
        this.isJhushoFormat = INIT_BOOLEAN;
    }

}

export { type WkTblKanrenshaPersonMasterEntityInterface, WkTblKanrenshaPersonMasterEntity }
