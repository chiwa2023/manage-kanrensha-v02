package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.InputKanrenshaPersonLeastDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;

/**
 * 関連者個人Dtoマスタ個人属性Entity変換Logic
 */
@Component
public class ConvertKanrenshaSeijidantaiDtoToPropertyEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaSeijidantaiDto 関連者政治団体Dto
     * @return マスタ政治団体属性Entity
     */
    public KanrenshaSeijidantaiPropertyEntity practice(final KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto) {

        KanrenshaSeijidantaiPropertyEntity propertyEntity = new KanrenshaSeijidantaiPropertyEntity();

        propertyEntity.setOrgNameKana(kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgNameKana());

        propertyEntity.setOrgDelegateCode(kanrenshaSeijidantaiDto.getOrgDelegateLeastDto().getPersonKanrenshaCode());
        InputKanrenshaPersonLeastDto accounrMgrLeastDto = kanrenshaSeijidantaiDto.getAccounrMgrLeastDto();
        propertyEntity.setAccountMgrName(accounrMgrLeastDto.getPersonName());
        propertyEntity.setAccountMgrCode(accounrMgrLeastDto.getPersonKanrenshaCode());

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        propertyEntity.setKanrenshaSeijidantaiPropertyId(kanrenshaSeijidantaiDto.getPropertyId());
        propertyEntity.setKanrenshaSeijidantaiId(kanrenshaSeijidantaiDto.getMasterId());
        propertyEntity.setKanrenshaName(kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgName());
        propertyEntity.setSeijidantaiKanrenshaCode(kanrenshaSeijidantaiDto.getSeijidantaiKanrenshaCode());

        return propertyEntity;
    }
}
