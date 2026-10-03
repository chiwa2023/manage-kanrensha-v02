import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface AddContactMessageCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 運営者問い合わせコード */
    contactManagerCode: number;

    /** 問い合わせクローズ該非 */
    isColsed: boolean;

    /** 問い合わせタイトル */
    inquireTitle: string;

    /** 問い合わせ内容 */
    inquireContent: string;

}


class AddContactMessageCapsuleDto extends FrameworkCapsuleDto
    implements AddContactMessageCapsuleDtoInterface {

    /** 運営者問い合わせコード */
    contactManagerCode: number;

    /** 問い合わせクローズ該非 */
    isColsed: boolean;

    /** 問い合わせタイトル */
    inquireTitle: string;

    /** 問い合わせ内容 */
    inquireContent: string;

    constructor() {
        super();

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.contactManagerCode = INIT_NUMBER;
        this.isColsed = INIT_BOOLEAN;
        this.inquireTitle = INIT_STRING;
        this.inquireContent = INIT_STRING;
    }
}

export { type AddContactMessageCapsuleDtoInterface, AddContactMessageCapsuleDto }
