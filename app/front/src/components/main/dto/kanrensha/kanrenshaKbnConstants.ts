export default class KanrenshaKbnConstants {

    /** 0 無選択 */
    static readonly NO_SELECT: number = 0;
    static readonly NO_SELECT_TEXT: string = " ";

    /** 1 個人 */
    static readonly PERSON: number = 1;
    static readonly PERSON_TEXT: string = "個人";

    /** 2 企業／団体 */
    static readonly KIGYOU_DT: number = 2;
    static readonly KIGYOU_DT_TEXT: string = "企業／団体";

    /** 3 政治団体 */
    static readonly SEIJIDANTAI: number = 3;
    static readonly SEIJIDANTAI_ORG_TEXT: string = "政治団体";

    static getLabel(value: number): string {

        switch (value) {

            // 0 無選択  
            case this.NO_SELECT:
                return this.NO_SELECT_TEXT;

            // 1 個人
            case this.PERSON:
                return this.PERSON_TEXT;

            // 2 企業／団体 
            case this.KIGYOU_DT:
                return this.KIGYOU_DT_TEXT;

            // 3 政治団体
            case this.SEIJIDANTAI:
                return this.SEIJIDANTAI_ORG_TEXT;

            default:
                return "";
        }
    }

}