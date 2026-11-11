import { FrameworkCapsuleDto, KanrenshaTableHistoryKigyouDtDto, type FrameworkCapsuleDtoInterface, type KanrenshaTableHistoryKigyouDtDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface AddHistoryKanrenshaKigyouDtCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 関連者企業・団体履歴テーブル */
    editDto: KanrenshaTableHistoryKigyouDtDtoInterface;
}

class AddHistoryKanrenshaKigyouDtCapsuleDto extends FrameworkCapsuleDto
    implements AddHistoryKanrenshaKigyouDtCapsuleDtoInterface {

    /** 関連者企業・団体履歴テーブル */
    editDto: KanrenshaTableHistoryKigyouDtDtoInterface;

    constructor() {
        super();
        this.editDto = new KanrenshaTableHistoryKigyouDtDto();
    }
}

export { type AddHistoryKanrenshaKigyouDtCapsuleDtoInterface, AddHistoryKanrenshaKigyouDtCapsuleDto }
