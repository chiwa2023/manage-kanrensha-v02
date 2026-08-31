import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { KanrenshaSeijidantaiDto, type KanrenshaSeijidantaiDtoInterface } from "./kanrenshaSeijidantaiDto";

interface SaveKanrenshaSeijidantaiCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 関連者政治団体Dto */
    kanrenshaSeijidantaiDto: KanrenshaSeijidantaiDtoInterface;
}



class SaveKanrenshaSeijidantaiCapsuleDto extends FrameworkCapsuleDto
    implements SaveKanrenshaSeijidantaiCapsuleDtoInterface {

    /** 関連者政治団体Dto */
    kanrenshaSeijidantaiDto: KanrenshaSeijidantaiDtoInterface;

    constructor() {
        super();

        this.kanrenshaSeijidantaiDto = new KanrenshaSeijidantaiDto();
    }
}

export { type SaveKanrenshaSeijidantaiCapsuleDtoInterface, SaveKanrenshaSeijidantaiCapsuleDto }
