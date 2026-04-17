import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { JwtTokenDto, type JwtTokenDtoInterface } from "./jwtTokenDto";

interface JwtTokenResultDtoInterface extends FrameworkMessageAndResultDtoInterface {
    /** JwtTokenDto */
    jwtTokenDto: JwtTokenDtoInterface;
}

class JwtTokenResultDto extends FrameworkMessageAndResultDto
    implements JwtTokenResultDtoInterface {

    /** JwtTokenDto */
    jwtTokenDto: JwtTokenDtoInterface;

    constructor() {
        super();
        this.jwtTokenDto = new JwtTokenDto();
    }
}

export { type JwtTokenResultDtoInterface, JwtTokenResultDto }