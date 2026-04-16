interface WkTblMasterAllByXmlEntityInterface {

    /** テーブルId */
    wkTblMasterAllByXmlId: number;

    /** 紐づけコード */
    wkTblMasterAllByXmlCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 関連者区分 */
    kanrenshaKbn: number;

    /** 備考 */
    bikou: string;

    /** 関連者名称 */
    kanrenshaName: string;

    /** 全住所 */
    allAddress: string;

    /** 団体代表者 */
    orgDelegate: string;

    /** 個人職業 */
    personShokugyou: string;

    /** 法人番号 */
    houjinNo: string;

    /** 団体区分 */
    dantaiKbn: string;

    /** 政治団体番号 */
    poliOrgNo: string;

    /** 様式区分 */
    youshikiKbn: number;

    /** 様式枝区分項目 */
    youshikiEdaKbn: number;

    /** 複写元名称 */
    inputSrcName: string;

    /** 複写元住所 */
    inputSrcAddress: string;

    /** 複写元認識キー */
    inputSrcKey: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 編集対象該否 */
    isDisabled: boolean;

    /** 判定理由 */
    judgeReason: string;

    /** 使用可否(frontのみ) */
    isNotUse: boolean;

}

class WkTblMasterAllByXmlEntity implements WkTblMasterAllByXmlEntityInterface {

    /** テーブルId */
    wkTblMasterAllByXmlId: number;

    /** 紐づけコード */
    wkTblMasterAllByXmlCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 関連者区分 */
    kanrenshaKbn: number;

    /** 備考 */
    bikou: string;

    /** 関連者名称 */
    kanrenshaName: string;

    /** 全住所 */
    allAddress: string;

    /** 団体代表者 */
    orgDelegate: string;

    /** 個人職業 */
    personShokugyou: string;

    /** 法人番号 */
    houjinNo: string;

    /** 団体区分 */
    dantaiKbn: string;

    /** 政治団体番号 */
    poliOrgNo: string;

    /** 様式区分 */
    youshikiKbn: number;

    /** 様式枝区分項目 */
    youshikiEdaKbn: number;

    /** 複写元名称 */
    inputSrcName: string;

    /** 複写元住所 */
    inputSrcAddress: string;

    /** 複写元認識キー */
    inputSrcKey: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 編集対象該否 */
    isDisabled: boolean;

    /** 判定理由 */
    judgeReason: string;

    /** 使用可否(frontのみ) */
    isNotUse: boolean;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;


        this.wkTblMasterAllByXmlId = INIT_NUMBER;
        this.wkTblMasterAllByXmlCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.isFinish = INIT_BOOLEAN;
        this.kanrenshaKbn = INIT_NUMBER;
        this.bikou = INIT_STRING;
        this.kanrenshaName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.orgDelegate = INIT_STRING;
        this.personShokugyou = INIT_STRING;
        this.houjinNo = INIT_STRING;
        this.dantaiKbn = INIT_STRING;
        this.poliOrgNo = INIT_STRING;
        this.youshikiKbn = INIT_NUMBER;
        this.youshikiEdaKbn = INIT_NUMBER;
        this.inputSrcName = INIT_STRING;
        this.inputSrcAddress = INIT_STRING;
        this.inputSrcKey = INIT_STRING;
        this.isAffected = INIT_BOOLEAN;
        this.isDisabled = INIT_BOOLEAN;
        this.judgeReason = INIT_STRING;
        this.isNotUse = INIT_BOOLEAN;
    }
}

export { type WkTblMasterAllByXmlEntityInterface, WkTblMasterAllByXmlEntity }

