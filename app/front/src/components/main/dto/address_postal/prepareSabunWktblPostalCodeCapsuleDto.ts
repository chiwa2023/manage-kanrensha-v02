import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { StorageFileDto, type StorageFileDtoInterface } from "../storage_file/storageFileDto";

interface PrepareSabunWktblPostalCodeCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 差分追加ファイルDto */
    addFileDto: StorageFileDtoInterface;

    /** 差分削除ファイルDto */
    deleteFileDto: StorageFileDtoInterface;
}


class PrepareSabunWktblPostalCodeCapsuleDto extends FrameworkCapsuleDto
    implements PrepareSabunWktblPostalCodeCapsuleDtoInterface {

    /** 差分追加ファイルDto */
    addFileDto: StorageFileDtoInterface;

    /** 差分削除ファイルDto */
    deleteFileDto: StorageFileDtoInterface;

    constructor() {
        super();

        this.addFileDto = new StorageFileDto();
        this.deleteFileDto = new StorageFileDto();
    }

}

export { type PrepareSabunWktblPostalCodeCapsuleDtoInterface, PrepareSabunWktblPostalCodeCapsuleDto }