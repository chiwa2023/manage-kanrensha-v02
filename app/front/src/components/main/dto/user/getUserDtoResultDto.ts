import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { LeastUserDto, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';

interface GetUserDtoResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** ユーザ最小限Dto */
    userDto: LeastUserDtoInterface;

    /** タスク開始通知有無 */
    isAlertTaskStart: boolean;

    /** タスク終了通知有無 */
    isAlertTaskEnd: boolean;
}

class GetUserDtoResultDto extends FrameworkMessageAndResultDto implements GetUserDtoResultDtoInterface {

    /** ユーザ最小限Dto */
    userDto: LeastUserDtoInterface;

    /** タスク開始通知有無 */
    isAlertTaskStart: boolean;

    /** タスク終了通知有無 */
    isAlertTaskEnd: boolean;

    constructor() {
        super();

        const INIT_BOOLEAN: boolean = false;

        this.userDto = new LeastUserDto();
        this.isAlertTaskStart = INIT_BOOLEAN;
        this.isAlertTaskEnd = INIT_BOOLEAN;
    }
}


export { type GetUserDtoResultDtoInterface, GetUserDtoResultDto }