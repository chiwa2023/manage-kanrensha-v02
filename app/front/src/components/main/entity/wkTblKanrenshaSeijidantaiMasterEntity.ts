interface WkTblKanrenshaSeijidantaiMasterEntityInterface {

    /** テーブルId */
    wkTblKanrenshaSeijidantaiMasterId: number;

    /** ワークテーブルコード */
    wkTblKanrenshaSeijidantaiMasterCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 政治団体名 */
    kanrenshaName: string;

    /** 政治団体全住所 */
    allAddress: string;

    /** 政治団体代表者 */
    seijidantaiDelegate: string;

    /** 政治団体区分 */
    dantaiKbn: string;

    /** 政治団体番号 */
    poliOrgNo: string;

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

    /** 関連者団体名称かな */
    orgNameKana: string;

    /** 団体代表者関連者コード */
    orgDelegateCode: string;

    /** 会計責任者関連者個人コード */
    accountMgrCode: string;

    /** 会計責任者関連者個人氏名 */
    accountMgrName: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;

    /** 住所整形済 */
    isJhushoFormat: boolean;

}



class WkTblKanrenshaSeijidantaiMasterEntity implements WkTblKanrenshaSeijidantaiMasterEntityInterface {

    /** テーブルId */
    wkTblKanrenshaSeijidantaiMasterId: number;

    /** ワークテーブルコード */
    wkTblKanrenshaSeijidantaiMasterCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 政治団体名 */
    kanrenshaName: string;

    /** 政治団体全住所 */
    allAddress: string;

    /** 政治団体代表者 */
    seijidantaiDelegate: string;

    /** 政治団体区分 */
    dantaiKbn: string;

    /** 政治団体番号 */
    poliOrgNo: string;

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

    /** 関連者団体名称かな */
    orgNameKana: string;

    /** 団体代表者関連者コード */
    orgDelegateCode: string;

    /** 会計責任者関連者個人コード */
    accountMgrCode: string;

    /** 会計責任者関連者個人氏名 */
    accountMgrName: string;

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

        this.wkTblKanrenshaSeijidantaiMasterId = INIT_NUMBER;
        this.wkTblKanrenshaSeijidantaiMasterCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.isFinish = INIT_BOOLEAN;
        this.kanrenshaName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.seijidantaiDelegate = INIT_STRING;
        this.dantaiKbn = INIT_STRING;
        this.poliOrgNo = INIT_STRING;
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
        this.orgNameKana = INIT_STRING;
        this.orgDelegateCode = INIT_STRING;
        this.accountMgrCode = INIT_STRING;
        this.accountMgrName = INIT_STRING;
        this.isAffected = INIT_BOOLEAN;
        this.judgeReason = INIT_STRING;
        this.isJhushoFormat = INIT_BOOLEAN;
    }

}

export { type WkTblKanrenshaSeijidantaiMasterEntityInterface, WkTblKanrenshaSeijidantaiMasterEntity }
