/**
 * ユーザ最小限DtoInterface
 */
interface LeastUserDtoInterface {

    /** ユーザId  */
    userPersonId: number;

    /** ユーザコード */
    userPersonCode: number;

    /** ユーザ姓名 */
    userPersonName: string;

    /** 権限リスト */
    listRoles: string[];

}

/**
 * ユーザ最小限Dto
 */
class LeastUserDto implements LeastUserDtoInterface {

    /** ユーザId  */
    userPersonId: number;

    /** ユーザコード */
    userPersonCode: number;

    /** ユーザ姓名 */
    userPersonName: string;

    /** 権限リスト */
    listRoles: string[];

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.userPersonId = INIT_NUMBER;
        this.userPersonCode = INIT_NUMBER;
        this.userPersonName = INIT_STRING;
        this.listRoles = [];
    }

}

export { LeastUserDto, type LeastUserDtoInterface }
