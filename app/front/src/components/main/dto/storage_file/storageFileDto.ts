interface StorageFileDtoInterface {

    /** 保存ディレクトリ */
    savedDir: string;

    /** ファイル名 */
    fileName: string;

}

class StorageFileDto implements StorageFileDtoInterface {

    /** 保存ディレクトリ */
    savedDir: string;

    /** ファイル名 */
    fileName: string;

    constructor() {
        const INIT_STRING: string = "";

        this.savedDir = INIT_STRING;
        this.fileName = INIT_STRING;
    }

}

export { type StorageFileDtoInterface, StorageFileDto }
