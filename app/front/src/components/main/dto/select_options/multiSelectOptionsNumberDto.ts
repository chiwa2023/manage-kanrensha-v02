/**
 * selectbox選択肢項目Interface(値number)
 */
interface MultiSelectOptionNumberDtoInterface {

    /** 選択肢値 */
    value: number;

    /** 選択肢表示テキスト */
    text: string;
    
    /** 選択肢チェック */
    isSelected: boolean;

}

/**
 * selectbox選択肢項目Dto(値number)
 */
class MultiSelectOptionNumberDto implements MultiSelectOptionNumberDtoInterface {

    /** 選択肢値 */
    value: number;

    /** 選択肢表示テキスト */
    text: string;

    /** 選択肢チェック */
    isSelected: boolean;

    constructor() {
        this.value = 0;
        this.text = "";
        this.isSelected = false;
    }
}

export { MultiSelectOptionNumberDto, type MultiSelectOptionNumberDtoInterface }