import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { StorageFileDto, type StorageFileDtoInterface } from "./storageFileDto";

interface UploadFileResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** ファイル格納Dto */
    storageFileDto: StorageFileDtoInterface;

}


class UploadFileResultDto extends FrameworkMessageAndResultDto
    implements UploadFileResultDtoInterface {

    /** ファイル格納Dto */
    storageFileDto: StorageFileDtoInterface;

    constructor() {
        super();
        this.storageFileDto = new StorageFileDto();
    }

}

export { UploadFileResultDto, type UploadFileResultDtoInterface }
