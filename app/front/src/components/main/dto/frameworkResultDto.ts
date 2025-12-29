interface FrameworkResultDtoInterface {

    /** 出力メッセージ */
    message: string;

    /** 処理失敗フラグ */
    isFailure: boolean;

}

class FrameworkResultDto implements FrameworkResultDtoInterface {

    /** 出力メッセージ */
    message: string;

    /** 処理失敗フラグ */
    isFailure: boolean;

    constructor() {
        this.message = "";
        this.isFailure = false;
    }
}

export { type FrameworkResultDtoInterface, FrameworkResultDto }