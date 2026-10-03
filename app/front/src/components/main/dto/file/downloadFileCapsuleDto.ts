import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface DownloadFileCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** ファイル保存年 */
    storageYear: number;

    /** ファイル保存Id */
    storageId: number;

    /** 関連者コード */
    kanrenshaCode: string;

    /** 関連者コード申請者 */
    kanrenshaKbn: number;
}

class DownloadFileCapsuleDto extends FrameworkCapsuleDto
    implements DownloadFileCapsuleDtoInterface {

    /** ファイル保存年 */
    storageYear: number;

    /** ファイル保存Id */
    storageId: number;

    /** 関連者コード */
    kanrenshaCode: string;

    /** 関連者コード申請者 */
    kanrenshaKbn: number;

    constructor() {
        super();

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.storageYear = INIT_NUMBER;
        this.storageId = INIT_NUMBER;
        this.kanrenshaCode = INIT_STRING;
        this.kanrenshaKbn = INIT_NUMBER;
    }
}

export { type DownloadFileCapsuleDtoInterface, DownloadFileCapsuleDto }
