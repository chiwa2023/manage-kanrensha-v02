interface KanrenshaCodeMoveEntityInterface {

    /** テーブルId */
    kanrenshaCodeMoveId: number;

    /** 関連者コード移動コード */
    kanrenshaCodeMoveCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 承認フラグ */
    moveStatus: number;

    /** 関連者区分 */
    kanrenshaKbn: number;

    /** 併合先コード */
    originKanrenshaCode: string;

    /** 併合先コード名称 */
    originName: string;

    /** 廃止コード */
    abolishKanrenshaCode: string;

    /** 廃止コード名称 */
    abolishKanrenshaName: string;

    /** 廃止コード最新該当 */
    isAbolishLast: boolean;

    /** 発生年 */
    taskYear: number;

    /** ストレージId */
    saveFileStorageId: number;
}


class KanrenshaCodeMoveEntity implements KanrenshaCodeMoveEntityInterface {

    /** テーブルId */
    kanrenshaCodeMoveId: number;

    /** 関連者コード移動コード */
    kanrenshaCodeMoveCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 承認フラグ */
    moveStatus: number;

    /** 関連者区分 */
    kanrenshaKbn: number;

    /** 併合先コード */
    originKanrenshaCode: string;

    /** 併合先コード名称 */
    originName: string;

    /** 廃止コード */
    abolishKanrenshaCode: string;

    /** 廃止コード名称 */
    abolishKanrenshaName: string;

    /** 廃止コード最新該当 */
    isAbolishLast: boolean;

    /** 発生年 */
    taskYear: number;

    /** ストレージId */
    saveFileStorageId: number;


    constructor() {

        const INIT_INTEGER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.kanrenshaCodeMoveId = INIT_INTEGER;
        this.kanrenshaCodeMoveCode = INIT_INTEGER;
        this.isLatest = INIT_BOOLEAN;
        this.moveStatus = INIT_INTEGER;
        this.kanrenshaKbn = INIT_INTEGER;
        this.originKanrenshaCode = INIT_STRING;
        this.originName = INIT_STRING;
        this.abolishKanrenshaCode = INIT_STRING;
        this.abolishKanrenshaName = INIT_STRING;
        this.isAbolishLast = INIT_BOOLEAN;
        this.taskYear = INIT_INTEGER;
        this.saveFileStorageId = INIT_INTEGER;
    }
}

export { type KanrenshaCodeMoveEntityInterface, KanrenshaCodeMoveEntity }
