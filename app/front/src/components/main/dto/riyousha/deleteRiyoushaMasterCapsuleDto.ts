import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface DeleteRiyoushaMasterCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 利用者コード */
    riyoushaCode: number;

    /** 利用者権限 */
    riyoushaRole: string;
}

class DeleteRiyoushaMasterCapsuleDto extends FrameworkCapsuleDto
    implements DeleteRiyoushaMasterCapsuleDtoInterface {

    /** 利用者コード */
    riyoushaCode: number;

    /** 利用者権限 */
    riyoushaRole: string;

    constructor() {
        super();

        /** 利用者コード */
        this.riyoushaCode = 0;

        /** 利用者権限 */
        this.riyoushaRole = "";

    }

}

export { type DeleteRiyoushaMasterCapsuleDtoInterface, DeleteRiyoushaMasterCapsuleDto }
