import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { LeastUserDto, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';

interface GetUserDtoResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** ユーザ最小限Dto */
    userDto: LeastUserDtoInterface;

}

class GetUserDtoResultDto extends FrameworkMessageAndResultDto implements GetUserDtoResultDtoInterface {

    /** ユーザ最小限Dto */
    userDto: LeastUserDtoInterface;

    constructor() {
        super();
        this.userDto = new LeastUserDto();
    }
}


export { type GetUserDtoResultDtoInterface, GetUserDtoResultDto }