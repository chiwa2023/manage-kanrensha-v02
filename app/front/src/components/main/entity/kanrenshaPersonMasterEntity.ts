interface KanrenshaPersonMasterEntityInterface {

    /** テーブルId */
    kanrenshaPersonMasterId: number;

    /** 個人関連者コード */
    personKanrenshaCode: string;

    /** 個人名 */
    kanrenshaName: string;

    /** 個人全住所 */
    allAddress: string;

    /** 個人職業 */
    personShokugyou: string;

    /** 名称比較用 */
    compareNameText: string;

}

class KanrenshaPersonMasterEntity implements KanrenshaPersonMasterEntityInterface {

    /** テーブルId */
    kanrenshaPersonMasterId: number;

    /** 個人関連者コード */
    personKanrenshaCode: string;

    /** 個人名 */
    kanrenshaName: string;

    /** 個人全住所 */
    allAddress: string;

    /** 個人職業 */
    personShokugyou: string;

    /** 名称比較用 */
    compareNameText: string;

    constructor() {

        const INIT_STRING: string = "";

        this.kanrenshaPersonMasterId = 0;
        this.personKanrenshaCode = INIT_STRING;
        this.kanrenshaName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.personShokugyou = INIT_STRING;
        this.compareNameText = INIT_STRING;
    }

}

export { type KanrenshaPersonMasterEntityInterface, KanrenshaPersonMasterEntity }
