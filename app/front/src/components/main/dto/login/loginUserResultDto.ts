import { LeastUserDto } from "../user/leastUserDto";
import { JwtTokenDto, type JwtTokenDtoInterface } from "./jwtTokenDto";

interface LoginUserResultDtoInterface {

    /** トークンDto  */
    jwtTokenDto: JwtTokenDtoInterface;

    /** ユーザ最低限Dto */
    userDto: LeastUserDto;

}

class LoginUserResultDto implements LoginUserResultDtoInterface {

    /** トークンDto  */
    jwtTokenDto: JwtTokenDtoInterface;

    /** ユーザ最低限Dto */
    userDto: LeastUserDto;

    constructor() {
        this.jwtTokenDto = new JwtTokenDto();
        this.userDto = new LeastUserDto();
    }

}

export { type LoginUserResultDtoInterface, LoginUserResultDto }