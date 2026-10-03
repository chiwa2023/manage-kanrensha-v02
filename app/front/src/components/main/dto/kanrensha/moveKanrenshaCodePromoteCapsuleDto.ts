import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { StorageFileDto, type StorageFileDtoInterface } from "../storage_file/storageFileDto";

interface MoveKanrenshaCodePromoteCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 関連者区分 */
    kanrenshaKbn: number;

    /** 併合先コード */
    originKanrenshaCode: string;

    /** 併合先コード名称 */
    originName: string;

    /** 廃止コード */
    abolishKanrenshaCode: string;

    /** 廃止コード名称 */
    abolishKanrenshaName: string;

    /** 移動理由 */
    moveReason: string;

    /** 廃止コード最新該当 */
    isAbolishLast: boolean;

    /** 格納一時ファイル */
    storageFileDto: StorageFileDtoInterface;

}

class MoveKanrenshaCodePromoteCapsuleDto extends FrameworkCapsuleDto implements MoveKanrenshaCodePromoteCapsuleDtoInterface {

    /** 関連者区分 */
    kanrenshaKbn: number;

    /** 併合先コード */
    originKanrenshaCode: string;

    /** 併合先コード名称 */
    originName: string;

    /** 廃止コード */
    abolishKanrenshaCode: string;

    /** 廃止コード名称 */
    abolishKanrenshaName: string;

    /** 移動理由 */
    moveReason: string;

    /** 廃止コード最新該当 */
    isAbolishLast: boolean;

    /** 格納一時ファイル */
    storageFileDto: StorageFileDtoInterface;

    constructor() {
        super();

        const INIT_INTEGER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.kanrenshaKbn = INIT_INTEGER;
        this.originKanrenshaCode = INIT_STRING;
        this.originName = INIT_STRING;
        this.abolishKanrenshaCode = INIT_STRING;
        this.abolishKanrenshaName = INIT_STRING;
        this.moveReason = INIT_STRING;
        this.isAbolishLast = INIT_BOOLEAN;
        this.storageFileDto = new StorageFileDto();
    }
}

export { MoveKanrenshaCodePromoteCapsuleDto, type MoveKanrenshaCodePromoteCapsuleDtoInterface }
