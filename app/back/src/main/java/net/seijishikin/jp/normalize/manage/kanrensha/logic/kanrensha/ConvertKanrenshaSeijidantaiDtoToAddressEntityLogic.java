package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;

/**
 * 関連者政治団体Dtoからマスタ政治団体住所Entity変換Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class ConvertKanrenshaSeijidantaiDtoToAddressEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaSeijidantaiDto 関連者政治団体Dto
     * @return 政治団体住所マスタEntity
     */
    public KanrenshaSeijidantaiAddressEntity practice(final KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto) {

        KanrenshaSeijidantaiAddressEntity addressEntity = new KanrenshaSeijidantaiAddressEntity();
        BeanUtils.copyProperties(kanrenshaSeijidantaiDto.getInputAddressDto(), addressEntity);

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        addressEntity.setKanrenshaSeijidantaiAddressId(kanrenshaSeijidantaiDto.getAddressId());
        addressEntity.setKanrenshaSeijidantaiId(kanrenshaSeijidantaiDto.getMasterId());
        addressEntity.setKanrenshaName(kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgName());
        addressEntity.setSeijidantaiKanrenshaCode(kanrenshaSeijidantaiDto.getSeijidantaiKanrenshaCode());

        return addressEntity;
    }

}
