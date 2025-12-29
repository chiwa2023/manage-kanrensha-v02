import { FrameworkResultDto, type FrameworkResultDtoInterface } from "../frameworkResultDto";
import { StorageFileDto, type StorageFileDtoInterface } from "./storageFileDto";

interface LookAheadCsvResultDtoInterface extends FrameworkResultDtoInterface {

    /** ファイル格納Dto */
    storageFileDto: StorageFileDtoInterface;

    /** 頭出しcsvデータ */
    tableData: string[][];
}


class LookAheadCsvResultDto extends FrameworkResultDto
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
