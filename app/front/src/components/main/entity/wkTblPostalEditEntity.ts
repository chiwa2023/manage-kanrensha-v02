interface WkTblPostalEditEntityInterface {

    /** テーブルId */
    wkTblPostalEditId: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 編集フラグ */
    flgEdit: string;

    /** 修正可否 */
    isRepair: boolean;

    /** 地方自治体コード */
    lgCode: string;

    /** 郵便番号5桁 */
    postalcode5: string;

    /** 郵便番号7桁 */
    postalcode7: string;

    /** 県名カナ */
    prefNameKana: string;

    /** 市区町名カナ */
    cityNameKana: string;

    /** 原文書名カナ */
    orgNameKana: string;

    /** 県名 */
    prefName: string;

    /** 市区町名 */
    cityName: string;

    /** 原文書名 */
    orgName: string;

    /** 属性1フラグ */
    flgProp1: string;

    /** 属性2フラグ */
    flgProp2: string;

    /** 属性3フラグ */
    flgProp3: string;

    /** 属性4フラグ */
    flgProp4: string;

    /** 更新表示フラグ */
    flgKoushin: string;

    /** 変更理由フラグ */
    flgHenkouRiyu: string;

    /** 正規テーブルId */
    addressPostalId: number;

    /** 行政区データ該否 */
    isGyoseikuData: boolean;

    /** 不規則Id */
    addressPostalIrregularId: number;

    /** 編集内容説明 */
    worksText: string;

}

class WkTblPostalEditEntity implements WkTblPostalEditEntityInterface {

    /** テーブルId */
    wkTblPostalEditId: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 編集フラグ */
    flgEdit: string;

    /** 修正可否 */
    isRepair: boolean;

    /** 地方自治体コード */
    lgCode: string;

    /** 郵便番号5桁 */
    postalcode5: string;

    /** 郵便番号7桁 */
    postalcode7: string;

    /** 県名カナ */
    prefNameKana: string;

    /** 市区町名カナ */
    cityNameKana: string;

    /** 原文書名カナ */
    orgNameKana: string;

    /** 県名 */
    prefName: string;

    /** 市区町名 */
    cityName: string;

    /** 原文書名 */
    orgName: string;

    /** 属性1フラグ */
    flgProp1: string;

    /** 属性2フラグ */
    flgProp2: string;

    /** 属性3フラグ */
    flgProp3: string;

    /** 属性4フラグ */
    flgProp4: string;

    /** 更新表示フラグ */
    flgKoushin: string;

    /** 変更理由フラグ */
    flgHenkouRiyu: string;

    /** 正規テーブルId */
    addressPostalId: number;

    /** 行政区データ該否 */
    isGyoseikuData: boolean;

    /** 不規則Id */
    addressPostalIrregularId: number;

    /** 編集内容説明 */
    worksText: string;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.wkTblPostalEditId = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.flgEdit = INIT_STRING;
        this.isRepair = INIT_BOOLEAN;
        this.lgCode = INIT_STRING;
        this.postalcode5 = INIT_STRING;
        this.postalcode7 = INIT_STRING;
        this.prefNameKana = INIT_STRING;
        this.cityNameKana = INIT_STRING;
        this.orgNameKana = INIT_STRING;
        this.prefName = INIT_STRING;
        this.cityName = INIT_STRING;
        this.orgName = INIT_STRING;
        this.flgProp1 = INIT_STRING;
        this.flgProp2 = INIT_STRING;
        this.flgProp3 = INIT_STRING;
        this.flgProp4 = INIT_STRING;
        this.flgKoushin = INIT_STRING;
        this.flgHenkouRiyu = INIT_STRING;
        this.addressPostalId = INIT_NUMBER;
        this.isGyoseikuData = INIT_BOOLEAN;
        this.addressPostalIrregularId = INIT_NUMBER;
        this.worksText = INIT_STRING;
    }

}

export { type WkTblPostalEditEntityInterface, WkTblPostalEditEntity }
