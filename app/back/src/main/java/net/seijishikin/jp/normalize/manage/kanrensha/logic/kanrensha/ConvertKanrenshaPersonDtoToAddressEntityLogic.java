package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;

/**
 * 関連者個人Dtoを個人住所マスタ変換Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class ConvertKanrenshaPersonDtoToAddressEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return 個人住所マスタEntity
     */
    public KanrenshaPersonAddressEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        KanrenshaPersonAddressEntity addressEntity = new KanrenshaPersonAddressEntity();
        BeanUtils.copyProperties(kanrenshaPersonDto.getInputAddressDto(), addressEntity);

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        addressEntity.setKanrenshaPersonAddressId(kanrenshaPersonDto.getAddressId());
        addressEntity.setKanrenshaPersonId(kanrenshaPersonDto.getMasterId());
        addressEntity.setKanrenshaName(kanrenshaPersonDto.getInputPersonNameDto().getAllName());
        addressEntity.setPersonKanrenshaCode(kanrenshaPersonDto.getPersonKanrenshaCode());

        return addressEntity;
    }

}
