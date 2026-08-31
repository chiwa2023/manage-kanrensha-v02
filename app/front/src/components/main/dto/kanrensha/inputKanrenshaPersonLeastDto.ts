interface InputKanrenshaPersonLeastDtoInterface {

    /** 関連者個人姓名 */
    personName: string;

    /** 関連者個人コード */
    personKanrenshaCode: string;

}

class InputKanrenshaPersonLeastDto implements InputKanrenshaPersonLeastDtoInterface {

    /** 関連者個人姓名 */
    personName: string;

    /** 関連者個人コード */
    personKanrenshaCode: string;

    constructor() {
        const INIT_STRING: string = "";

        this.personName = INIT_STRING;
        this.personKanrenshaCode = INIT_STRING;
    }

}

export { type InputKanrenshaPersonLeastDtoInterface, InputKanrenshaPersonLeastDto }
