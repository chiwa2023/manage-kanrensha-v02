package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;

/**
 * 関連者個人Dtoマスタ個人属性Entity変換Logic
 */
@Component
public class ConvertKanrenshaPersonDtoToPropertyEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return マスタ個人属性Entity
     */
    public KanrenshaPersonPropertyEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        KanrenshaPersonPropertyEntity propertyEntity = new KanrenshaPersonPropertyEntity();
        BeanUtils.copyProperties(kanrenshaPersonDto.getInputShokugyouDto(), propertyEntity);
        BeanUtils.copyProperties(kanrenshaPersonDto.getInputPersonNameDto(), propertyEntity);

        propertyEntity.setKigyouDtNo(kanrenshaPersonDto.getInputShokugyouDto().getHoujinNo());
        propertyEntity.setKigyouDtName(kanrenshaPersonDto.getInputShokugyouDto().getHoujinName());
        propertyEntity.setKigyouDtAddress(kanrenshaPersonDto.getInputShokugyouDto().getHoujinAddress());
        propertyEntity.setIsShokyouEdit(!DtoEntityInitialValueInterface.INIT_STRING
                .equals(kanrenshaPersonDto.getInputShokugyouDto().getShokugyouUserWrite()));

        propertyEntity.setIsForeign(kanrenshaPersonDto.getIsForeign());

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        propertyEntity.setKanrenshaPersonPropertyId(kanrenshaPersonDto.getPropertyId());
        propertyEntity.setKanrenshaPersonId(kanrenshaPersonDto.getMasterId());
        propertyEntity.setKanrenshaName(kanrenshaPersonDto.getInputPersonNameDto().getAllName());
        propertyEntity.setPersonKanrenshaCode(kanrenshaPersonDto.getPersonKanrenshaCode());

        return propertyEntity;
    }
}
