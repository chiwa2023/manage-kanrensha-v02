import { FrameworkPagingDto, LeastUserDto, type FrameworkCapsuleDtoInterface, type FrameworkPagingDtoInterface, type LeastUserDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface GetTaskStackTraceCapsuleDtoInterface extends FrameworkPagingDtoInterface, FrameworkCapsuleDtoInterface {

    /** ユーザ最小限Dto */
    userDto: LeastUserDtoInterface;

    /** タスク計画登録年 */
    taskYear: number;

    /** タスク計画Dto */
    taskPlanCode: number;

}


class GetTaskStackTraceCapsuleDto extends FrameworkPagingDto
    implements GetTaskStackTraceCapsuleDtoInterface {

    /** ユーザ最小限Dto */
    userDto: LeastUserDtoInterface;

    /** タスク計画登録年 */
    taskYear: number;

    /** タスク計画Dto */
    taskPlanCode: number;

    constructor() {
        super();

        const INIT_NUMBER: number = 0;

        this.userDto = new LeastUserDto();
        this.taskPlanCode = INIT_NUMBER;
        this.taskYear = INIT_NUMBER;
    }
}

export { type GetTaskStackTraceCapsuleDtoInterface, GetTaskStackTraceCapsuleDto }