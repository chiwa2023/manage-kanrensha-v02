import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { StorageFileDto, type StorageFileDtoInterface } from "./storageFileDto";

interface LookAheadCsvResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** ファイル格納Dto */
    storageFileDto: StorageFileDtoInterface;

    /** 頭出しcsvデータ */
    tableData: string[][];
}


class LookAheadCsvResultDto extends FrameworkMessageAndResultDto
    implements LookAheadCsvResultDtoInterface {

    /** ファイル格納Dto */
    storageFileDto: StorageFileDtoInterface;

    /** 頭出しcsvデータ */
    tableData: string[][];

    constructor() {
        super();
        this.storageFileDto = new StorageFileDto();
        this.tableData = [[]];
    }
}

export { type LookAheadCsvResultDtoInterface, LookAheadCsvResultDto }
