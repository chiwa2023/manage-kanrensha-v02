import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface MovePostalCodeCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 地方自治体コード */
    lgCode: string;

    /** 郵便番号1旧 */
    postalOld1: string;

    /** 郵便番号2旧 */
    postalOld2: string;

    /** 郵便番号1新 */
    postalNew1: string;

    /** 郵便番号2新 */
    postalNew2: string;
}

class MovePostalCodeCapsuleDto extends FrameworkCapsuleDto implements MovePostalCodeCapsuleDtoInterface {

    /** 地方自治体コード */
    lgCode: string;

    /** 郵便番号1旧 */
    postalOld1: string;

    /** 郵便番号2旧 */
    postalOld2: string;

    /** 郵便番号1新 */
    postalNew1: string;

    /** 郵便番号2新 */
    postalNew2: string;

    constructor() {
        super();

        const INIT_STRING: string = "";

        /** 地方自治体コード */
        this.lgCode = INIT_STRING;

        /** 郵便番号1旧 */
        this.postalOld1 = INIT_STRING;

        /** 郵便番号2旧 */
        this.postalOld2 = INIT_STRING;

        /** 郵便番号1新 */
        this.postalNew1 = INIT_STRING;

        /** 郵便番号2新 */
        this.postalNew2 = INIT_STRING;

    }
}

export { type MovePostalCodeCapsuleDtoInterface, MovePostalCodeCapsuleDto }
