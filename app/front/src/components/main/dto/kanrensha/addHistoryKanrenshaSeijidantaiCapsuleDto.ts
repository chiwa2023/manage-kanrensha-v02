import { FrameworkCapsuleDto, KanrenshaTableHistorySeijidantaiDto, type FrameworkCapsuleDtoInterface, type KanrenshaTableHistorySeijidantaiDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface AddHistoryKanrenshaSeijidantaiCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 関連者政治団体履歴テーブル */
    editDto: KanrenshaTableHistorySeijidantaiDtoInterface;

}


class AddHistoryKanrenshaSeijidantaiCapsuleDto extends FrameworkCapsuleDto
    implements AddHistoryKanrenshaSeijidantaiCapsuleDtoInterface {

    /** 関連者政治団体履歴テーブル */
    editDto: KanrenshaTableHistorySeijidantaiDtoInterface;

    constructor() {
        super();
        this.editDto = new KanrenshaTableHistorySeijidantaiDto();
    }
}

export { type AddHistoryKanrenshaSeijidantaiCapsuleDtoInterface, AddHistoryKanrenshaSeijidantaiCapsuleDto }
