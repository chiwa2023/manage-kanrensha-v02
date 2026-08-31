import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { StorageFileDto, type StorageFileDtoInterface } from "./storageFileDto";

interface RegistDataByCsvFileCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** ストレージ保存ファイルDto */
    storageFileDto: StorageFileDtoInterface;
}

class RegistDataByCsvFileCapsuleDto extends FrameworkCapsuleDto implements RegistDataByCsvFileCapsuleDtoInterface {

    /** ストレージ保存ファイルDto */
    storageFileDto: StorageFileDtoInterface;

    constructor() {
        super();

        this.storageFileDto = new StorageFileDto();
    }

}

export { type RegistDataByCsvFileCapsuleDtoInterface, RegistDataByCsvFileCapsuleDto }
