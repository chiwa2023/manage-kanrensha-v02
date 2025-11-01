import { PagingDto } from "../dto/paging/pagingDto";

function createPagingData(allCount: number, limit: number, pageNumber: number): PagingDto[] {

    const list: PagingDto[] = [];


    const last: number = Math.trunc(allCount / limit);
    const minus2: number = pageNumber - 2;
    const minus1: number = pageNumber - 1;
    const plus2: number = pageNumber + 2;
    const plus1: number = pageNumber + 1;

    const listCreatePage: number[] = [];
    // 最初のページ
    listCreatePage.push(0);

    // マイナスページと先頭の間に距離があれば省略を示す3点リーダを追加
    if (minus2 - 0 > 1) {
        listCreatePage.push(-1);
    }

    //マイナスページを先頭と重複しなければ追加
    if (0 < minus2) {
        listCreatePage.push(minus2);
    }
    if (0 < minus1) {
        listCreatePage.push(minus1);
    }

    if(!listCreatePage.includes(pageNumber)){
        listCreatePage.push(pageNumber);
    }

    //プラスページを先頭と重複しなければ追加
    if (last > plus1) {
        listCreatePage.push(plus1);
    }
    if (last > plus2) {
        listCreatePage.push(plus2);
    }

    // プラスページと最後の間に距離があれば省略を示す3点リーダを追加
    if (last - plus2 > 1) {
        listCreatePage.push(-1);
    }

    // 最後のページ
    if(!listCreatePage.includes(last)){
    listCreatePage.push(last);
    }
    for (let viewNumber of listCreatePage) {
        list.push(createDto(viewNumber, pageNumber));
    }

    return list;
}

function createDto(viewNumber: number, pageNumber: number): PagingDto {

    const dto: PagingDto = new PagingDto();
    if (viewNumber > -1) {
        dto.pageNumber = viewNumber;
        dto.pageText = String(viewNumber + 1);
        // 表示
        if (viewNumber === pageNumber) {
            dto.viewClass = "active";
        }
        dto.disabled = false;
    } else {
        // 省略を示す3点リーダ用
        dto.pageNumber = viewNumber;
        dto.pageText = "...";
        dto.viewClass = "dead";
        dto.disabled = true;
    }


    return dto;
}


export { createPagingData }