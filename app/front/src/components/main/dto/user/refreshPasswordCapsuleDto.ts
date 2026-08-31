import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface RefreshPasswordCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 新パスワード */
    newPassword: string;

    /** 旧パスワード */
    oldPassword: string;

}

class RefreshPasswordCapsuleDto extends FrameworkCapsuleDto implements RefreshPasswordCapsuleDtoInterface {

    /** 新パスワード */
    newPassword: string;

    /** 旧パスワード */
    oldPassword: string;

    constructor() {
        super();
        const INIT_STRING: string = "";

        this.newPassword = INIT_STRING;
        this.oldPassword = INIT_STRING;
    }
}

export { type RefreshPasswordCapsuleDtoInterface, RefreshPasswordCapsuleDto }