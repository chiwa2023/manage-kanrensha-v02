import { type FrameworkCapsuleDtoInterface, FrameworkCapsuleDto } from "seijishikin-jp-normalize_common-tool";

interface ForceDumpCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 企業団体処理該否 */
    isExecuteKigyouDt: boolean;
    /** 個人処理該否 */
    isExecutePerson: boolean;
    /** 政治団体処理該否 */
    isExecuteSeijidantai: boolean;

    /** 開始日時 */
    dateStart: Date;
    /** 終了日時 */
    dateEnd: Date;

}

class ForceDumpCapsuleDto extends FrameworkCapsuleDto implements ForceDumpCapsuleDtoInterface {

    /** 企業団体処理該否 */
    isExecuteKigyouDt: boolean;
    /** 個人処理該否 */
    isExecutePerson: boolean;
    /** 政治団体処理該否 */
    isExecuteSeijidantai: boolean;

    /** 開始日時 */
    dateStart: Date;
    /** 終了日時 */
    dateEnd: Date;

    constructor() {
        super();

        const INIT_BOOLEAN: boolean = true;
        const INIT_DATE: Date = new Date();

        this.isExecuteKigyouDt = INIT_BOOLEAN;
        this.isExecutePerson = INIT_BOOLEAN;
        this.isExecuteSeijidantai = INIT_BOOLEAN;

        this.dateStart = INIT_DATE;
        this.dateEnd = INIT_DATE;
    }
}

export { type ForceDumpCapsuleDtoInterface, ForceDumpCapsuleDto }