import { FrameworkMessageAndResultDto, KanrenshaKigyouDtMasterEntity, KanrenshaPersonMasterEntity, KanrenshaSeijidantaiMasterEntity, type FrameworkMessageAndResultDtoInterface, type KanrenshaKigyouDtMasterEntityInterface, type KanrenshaPersonMasterEntityInterface, type KanrenshaSeijidantaiMasterEntityInterface } from "seijishikin-jp-normalize_common-tool";

interface GetKanrenshaMasterResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 関連者個人マスタEntity */
    masterPersonEntity: KanrenshaPersonMasterEntityInterface;

    /** 関連者企業団体マスタEntity */
    masterKigyouDtEntity: KanrenshaKigyouDtMasterEntityInterface;

    /** 関連者政治団体マスタEntity */
    masterSeijidantaiEntity: KanrenshaSeijidantaiMasterEntityInterface;
}

class GetKanrenshaMasterResultDto extends FrameworkMessageAndResultDto
    implements GetKanrenshaMasterResultDtoInterface {

    /** 関連者個人マスタEntity */
    masterPersonEntity: KanrenshaPersonMasterEntityInterface;

    /** 関連者企業団体マスタEntity */
    masterKigyouDtEntity: KanrenshaKigyouDtMasterEntityInterface;

    /** 関連者政治団体マスタEntity */
    masterSeijidantaiEntity: KanrenshaSeijidantaiMasterEntityInterface;

    constructor() {
        super();

        this.masterPersonEntity = new KanrenshaPersonMasterEntity();
        this.masterKigyouDtEntity = new KanrenshaKigyouDtMasterEntity();
        this.masterSeijidantaiEntity = new KanrenshaSeijidantaiMasterEntity();
    }
}

export { type GetKanrenshaMasterResultDtoInterface, GetKanrenshaMasterResultDto }
