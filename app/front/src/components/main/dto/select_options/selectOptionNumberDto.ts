/**
 * selectbox選択肢項目Interface(値number)
 */
interface SelectOptionNumberDtoInterface {

    /** 選択肢値 */
    value: number;

    /** 選択肢表示テキスト */
    text: string;

}

/**
 * selectbox選択肢項目Dto(値number)
 */
class SelectOptionNumberDto implements SelectOptionNumberDtoInterface {

    /** 選択肢値 */
    value: number;

    /** 選択肢表示テキスト */
    text: string;

    constructor() {
        this.value = 0;
        this.text = "";
    }
}

export { SelectOptionNumberDto, type SelectOptionNumberDtoInterface }