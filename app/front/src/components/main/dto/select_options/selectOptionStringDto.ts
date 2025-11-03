/**
 * selectbox選択肢項目Interface(値string)
 */
interface SelectOptionStringDtoInterface {

    /** 選択肢値 */
    value: string;

    /** 選択肢表示テキスト */
    text: string;

}

/**
 * selectbox選択肢項目Dto(値string)
 */
class SelectOptionStringDto implements SelectOptionStringDtoInterface {

    /** 選択肢値 */
    value: string;

    /** 選択肢表示テキスト */
    text: string;

    constructor() {
        const INIT_STRING: string = "";
        this.value = INIT_STRING;
        this.text = INIT_STRING;
    }
}

export { SelectOptionStringDto, type SelectOptionStringDtoInterface }