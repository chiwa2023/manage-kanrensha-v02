/**
 * selectbox選択肢項目Interface(値string)
 */
interface SelectOptionsTaskPlanDtoInterface {

    /** 選択肢値 */
    value: string;

    /** 選択肢表示テキスト */
    text: string;

    /** 発生年 */
    taskYear: number;

    /** タスク計画Id */
    taskPlanId: number;
}

/**
 * selectbox選択肢項目Dto(値string)
 */
class SelectOptionsTaskPlanDto implements SelectOptionsTaskPlanDtoInterface {

    /** 選択肢値 */
    value: string;

    /** 選択肢表示テキスト */
    text: string;

    /** 発生年 */
    taskYear: number;

    /** タスク計画Id */
    taskPlanId: number;

    constructor() {
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;

        this.value = INIT_STRING;
        this.text = INIT_STRING;
        this.taskYear = INIT_NUMBER;
        this.taskPlanId = INIT_NUMBER;
    }
}

export { SelectOptionsTaskPlanDto, type SelectOptionsTaskPlanDtoInterface }