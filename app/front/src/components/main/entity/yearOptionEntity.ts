interface YearOptionEntityInterface {
    /** 選択年 */
    selectedYear: number;

    /** 選択該否 */
    isSelected: boolean;
}

class YearOptionEntity implements YearOptionEntityInterface {

    /** 選択年 */
    selectedYear: number;

    /** 選択該否 */
    isSelected: boolean;

    constructor() {
        this.selectedYear = 0;
        this.isSelected = false;
    }
}

export { type YearOptionEntityInterface, YearOptionEntity }
