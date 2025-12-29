import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "../frameworkCapsuleDto";
import { StorageFileDto, type StorageFileDtoInterface } from "../storage_file/storageFileDto";

interface RegistDataByXmlCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** ストレージ保存ファイルDto */
    storageFileDto: StorageFileDtoInterface;

    /** 備考1項目解析該否 */
    isNotBiko: boolean;

    /** 名前住所2項目解析該否 */
    isNotNameAddress: boolean;

}

class RegistDataByXmlCapsuleDto extends FrameworkCapsuleDto implements RegistDataByXmlCapsuleDtoInterface {

    /** ストレージ保存ファイルDto */
    storageFileDto: StorageFileDtoInterface;

    /** 備考1項目解析該否 */
    isNotBiko: boolean;

    /** 名前住所2項目解析該否 */
    isNotNameAddress: boolean;

    constructor() {
        super();
        const INI_BOOLEAN: boolean = false;

        this.storageFileDto = new StorageFileDto();
        this.isNotBiko = INI_BOOLEAN;
        this.isNotNameAddress = INI_BOOLEAN;
    }

}

export { type RegistDataByXmlCapsuleDtoInterface, RegistDataByXmlCapsuleDto }
