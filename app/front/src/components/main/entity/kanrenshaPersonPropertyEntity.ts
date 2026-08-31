interface KanrenshaPersonPropertyEntityInterface {

    /** テーブルId */
    kanrenshaPersonPropertyId: number;

    /** 関連者個人Id */
    kanrenshaPersonId: number;

    /** 関連者個人コード */
    personKanrenshaCode: string;

    /** 関連者個人名称 */
    kanrenshaName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 外国籍該否 */
    isForeign: boolean;

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

    /** 職業編集該否 */
    isShokyouEdit: boolean;

    /** 職業編集承認該否 */
    isShokyouAccept: boolean;

}

class KanrenshaPersonPropertyEntity implements KanrenshaPersonPropertyEntityInterface {

    /** テーブルId */
    kanrenshaPersonPropertyId: number;

    /** 関連者個人Id */
    kanrenshaPersonId: number;

    /** 関連者個人コード */
    personKanrenshaCode: string;

    /** 関連者個人名称 */
    kanrenshaName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 外国籍該否 */
    isForeign: boolean;

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

    /** 職業編集該否 */
    isShokyouEdit: boolean;

    /** 職業編集承認該否 */
    isShokyouAccept: boolean;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.kanrenshaPersonPropertyId = INIT_NUMBER;
        this.kanrenshaPersonId = INIT_NUMBER;
        this.personKanrenshaCode = INIT_STRING;
        this.kanrenshaName = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.isForeign = INIT_BOOLEAN;
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
        this.isShokyouEdit = INIT_BOOLEAN;
        this.isShokyouAccept = INIT_BOOLEAN;
    }
}

export { type KanrenshaPersonPropertyEntityInterface, KanrenshaPersonPropertyEntity }
