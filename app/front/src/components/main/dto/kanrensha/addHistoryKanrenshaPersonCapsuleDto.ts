import { FrameworkCapsuleDto, KanrenshaTableHistoryPersonDto, type FrameworkCapsuleDtoInterface, type KanrenshaTableHistoryPersonDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface AddHistoryKanrenshaPersonCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 関連者個人履歴テーブル */
    editDto: KanrenshaTableHistoryPersonDtoInterface;

}

class AddHistoryKanrenshaPersonCapsuleDto extends FrameworkCapsuleDto
    implements AddHistoryKanrenshaPersonCapsuleDtoInterface {

    /** 関連者個人履歴テーブル */
    editDto: KanrenshaTableHistoryPersonDtoInterface;

    constructor() {
        super();

        this.editDto = new KanrenshaTableHistoryPersonDto();
    }
}

export { type AddHistoryKanrenshaPersonCapsuleDtoInterface, AddHistoryKanrenshaPersonCapsuleDto }
