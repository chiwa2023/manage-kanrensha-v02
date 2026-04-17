interface OneFileBlobDtoInterface {
    /** ファイル名 */
    fileName: string;

    /** ファイル内容バイナリ */
    fileContentBase64: string;
}

class OneFileBlobDto implements OneFileBlobDtoInterface {
    /** ファイル名 */
    fileName: string;

    /** ファイル内容バイナリ */
    fileContentBase64: string;

    constructor() {
        const INIT_STRING: string = "";
        this.fileName = INIT_STRING;
        this.fileContentBase64 = INIT_STRING;
    }
}

export { type OneFileBlobDtoInterface, OneFileBlobDto }