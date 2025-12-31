import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { StorageFileDto, type StorageFileDtoInterface } from "./storageFileDto";

interface LookAheadPublishXmlResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** ファイル格納Dto */
    storageFileDto: StorageFileDtoInterface;

    /** 作成アプリ名 */
    app: string;

    /** 作成アプリバージョン */
    version: string;

    /** 団体名称 */
    dantaiName: string;

    /** 報告年 */
    houkokuNen: number;

}


class LookAheadPublishXmlResultDto extends FrameworkMessageAndResultDto
    implements LookAheadPublishXmlResultDtoInterface {

    /** ファイル格納Dto */
    storageFileDto: StorageFileDtoInterface;

    /** 作成アプリ名 */
    app: string;

    /** 作成アプリバージョン */
    version: string;

    /** 団体名称 */
    dantaiName: string;

    /** 報告年 */
    houkokuNen: number;


    constructor() {
        super();
        const INIT_STRING: string = "";

        this.storageFileDto = new StorageFileDto();
        this.app = INIT_STRING;
        this.version = INIT_STRING;
        this.dantaiName = INIT_STRING;
        this.houkokuNen = 0;

    }

}

export { type LookAheadPublishXmlResultDtoInterface, LookAheadPublishXmlResultDto }
