import FrameworkCapsuleInterface from "../frameworkCapsuleDto";
import CorpNoInterface from "./corpNoDto";
import CorpNoDto from "./corpNoDto";

export default interface SaveKanrenshaCorpCapsuleInterface {

}



export default class SaveKanrenshaCorpCapsuleDto extends FrameworkCapsuleInterface
    implements SaveKanrenshaCorpCapsuleInterface {

    /** 関連者企業団体Dto */
    kanrenshaCorpDto: CorpNoInterface;

    constructor() {
        super();
        this.kanrenshaCorpDto = new CorpNoDto();
    }
}