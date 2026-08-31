package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;

/**
 * 関連者企業団体Dtoからマスタ企業団体住所Entity変換Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class ConvertKanrenshaKigyouDtDtoToAddressEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaKigyouDtDto 関連者企業団体Dto
     * @return 企業団体住所マスタEntity
     */
    public KanrenshaKigyouDtAddressEntity practice(final KanrenshaKigyouDtDto kanrenshaKigyouDtDto) {

        KanrenshaKigyouDtAddressEntity addressEntity = new KanrenshaKigyouDtAddressEntity();
        BeanUtils.copyProperties(kanrenshaKigyouDtDto.getInputAddressDto(), addressEntity);

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        addressEntity.setKanrenshaKigyouDtAddressId(kanrenshaKigyouDtDto.getAddressId());
        addressEntity.setKanrenshaKigyouDtId(kanrenshaKigyouDtDto.getMasterId());
        addressEntity.setKanrenshaName(kanrenshaKigyouDtDto.getInputOrgNameDto().getOrgName());
        addressEntity.setKigyouDtKanrenshaCode(kanrenshaKigyouDtDto.getKigyouDtKanrenshaCode());

        return addressEntity;
    }

}
