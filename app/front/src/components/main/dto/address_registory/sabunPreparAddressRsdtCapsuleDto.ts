import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { StorageFileDto, type StorageFileDtoInterface } from "../storage_file/storageFileDto";

interface SabunPreparAddressRsdtCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 地番追加ファイルDto */
    parcelFileDto: StorageFileDtoInterface;

    /** 住居ファイルDto */
    rsdtFileDto: StorageFileDtoInterface;

    /** 地方自治体コード */
    lgCode: string;
}

class SabunPreparAddressRsdtCapsuleDto extends FrameworkCapsuleDto implements SabunPreparAddressRsdtCapsuleDtoInterface {

    /** 地番追加ファイルDto */
    parcelFileDto: StorageFileDtoInterface;

    /** 住居ファイルDto */
    rsdtFileDto: StorageFileDtoInterface;

    /** 地方自治体コード */
    lgCode: string;

    constructor() {
        super();

        this.lgCode = "";
        this.parcelFileDto = new StorageFileDto();
        this.rsdtFileDto = new StorageFileDto();
    }
}

export { type SabunPreparAddressRsdtCapsuleDtoInterface, SabunPreparAddressRsdtCapsuleDto }
