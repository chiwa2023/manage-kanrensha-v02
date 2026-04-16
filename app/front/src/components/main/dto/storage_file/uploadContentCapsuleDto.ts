import { LeastUserDto, type LeastUserDtoInterface } from "../user/leastUserDto";
import { UploadFileDto, type UploadFileDtoInterface } from "./uploadFileDto";

interface UploadContentCapsuleDtoInterface {

    /** ユーザ最低限Dto */
    userDto: LeastUserDtoInterface;

    /** アップロードファイルDto */
    uploadFileDto: UploadFileDtoInterface;

}

class UploadContentCapsuleDto implements UploadContentCapsuleDtoInterface {

    /** ユーザ最低限Dto */
    userDto: LeastUserDtoInterface;

    /** アップロードファイルDto */
    uploadFileDto: UploadFileDtoInterface;

    constructor() {

        this.userDto = new LeastUserDto();
        this.uploadFileDto = new UploadFileDto();
    }
}

export { type UploadContentCapsuleDtoInterface, UploadContentCapsuleDto }
