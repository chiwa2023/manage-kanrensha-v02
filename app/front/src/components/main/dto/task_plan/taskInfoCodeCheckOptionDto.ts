
interface TaskInfoCodeCheckOptionDtoInterface {

    /** checkboxの値 */
    isChecked: boolean;

    /** タスク情報コード */
    codeValue: number;

    /** コード名称 */
    codeName: string;
}


class TaskInfoCodeCheckOptionDto implements TaskInfoCodeCheckOptionDtoInterface {

    /** checkboxの値 */
    isChecked: boolean;

    /** タスク情報コード */
    codeValue: number;

    /** コード名称 */
    codeName: string;

    constructor() {

        this.isChecked = true; // 表示上true:検索するがdefault
        this.codeValue = 0;
        this.codeName = "";
    }
}

export { type TaskInfoCodeCheckOptionDtoInterface, TaskInfoCodeCheckOptionDto }