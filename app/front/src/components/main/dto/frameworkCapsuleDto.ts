import { LeastUserDto } from "./user/leastUserDto";

interface FrameworkCapsuleDtoInterface {

    /** 最小限ユーザDto */
    userDto: LeastUserDto;

}


class FrameworkCapsuleDto implements FrameworkCapsuleDtoInterface {

    /** 最小限ユーザDto */
    userDto: LeastUserDto;

    constructor() {
        this.userDto = new LeastUserDto();
    }
}

export{type FrameworkCapsuleDtoInterface,FrameworkCapsuleDto}