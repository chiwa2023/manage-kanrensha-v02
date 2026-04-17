
interface GetUserDtoCapsuleDtoInterface {
    /** 編集ユーザId */
    editUserid: number;
}


class GetUserDtoCapsuleDto implements GetUserDtoCapsuleDtoInterface {
    /** 編集ユーザId */
    editUserid: number;

    constructor() {
        this.editUserid = 0;
    }
}

export { type GetUserDtoCapsuleDtoInterface, GetUserDtoCapsuleDto }