/**
 * ページングDto
 * API間の伝達Dtoとして使用しないのでInterface不要
 */
class PagingDto {

    /** ページ番号  */
    pageNumber: number;

    /** ページ番号表示  */
    pageText: string;

    /** 表示クラス  */
    viewClass: string;

    /** 使用フラグ  */
    disabled: boolean;

    constructor() {
        this.pageNumber = 0;
        this.pageText = "";
        this.viewClass = "";
        this.disabled = false;
    }
}

export { PagingDto }