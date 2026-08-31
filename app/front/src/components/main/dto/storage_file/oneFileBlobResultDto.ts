import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface OneFileBlobResultDtoInterface extends FrameworkMessageAndResultDtoInterface {
    /** ファイル名 */
    fileName: string;

    /** ファイル内容バイナリ */
    fileContentBase64: string;
}

class OneFileBlobResultDto extends FrameworkMessageAndResultDto
    implements OneFileBlobResultDtoInterface {

    /** ファイル名 */
    fileName: string;

    /** ファイル内容バイナリ */
    fileContentBase64: string;

    constructor() {
        super();

        const INIT_STRING: string = "";
        this.fileName = INIT_STRING;
        this.fileContentBase64 = INIT_STRING;
    }
}

export { type OneFileBlobResultDtoInterface, OneFileBlobResultDto }