import { LeastUserDto, type LeastUserDtoInterface } from "../user/leastUserDto";
import { StorageFileDto, type StorageFileDtoInterface } from "./storageFileDto";

interface RegistDataByCsvFileCapsuleDtoInterface {

    /** ユーザ最低限Dto */
    userDto: LeastUserDtoInterface;

    /** ストレージ保存ファイルDto */
    storageFileDto: StorageFileDtoInterface;
}

class RegistDataByCsvFileCapsuleDto implements RegistDataByCsvFileCapsuleDtoInterface {

    /** ユーザ最低限Dto */
    userDto: LeastUserDtoInterface;

    /** ストレージ保存ファイルDto */
    storageFileDto: StorageFileDtoInterface;

    constructor() {

        this.userDto = new LeastUserDto();
        this.storageFileDto = new StorageFileDto();

    }

}

export { type RegistDataByCsvFileCapsuleDtoInterface, RegistDataByCsvFileCapsuleDto }
