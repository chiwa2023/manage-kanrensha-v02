package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.HoujinShubetsuConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;


/**
 * 関連者個人Dtoマスタ政治団体属性Entity変換Logic
 */
@Component
public class ConvertKanrenshaKigyouDtDtoToPropertyEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaKigyouDtDto 関連者企業団体Dto
     * @return マスタ政治団体属性Entity
     */
    public KanrenshaKigyouDtPropertyEntity practice(final KanrenshaKigyouDtDto kanrenshaKigyouDtDto) {

        KanrenshaKigyouDtPropertyEntity propertyEntity = new KanrenshaKigyouDtPropertyEntity();
        BeanUtils.copyProperties(kanrenshaKigyouDtDto, propertyEntity);

        // 法人種別が外国会社401であれば外国籍フラグを立てる
        propertyEntity.setIsForeign(HoujinShubetsuConstants.GAIKOKU_KAISHA.equals(propertyEntity.getHoujinSbts()));
        propertyEntity.setKanrenshaName(kanrenshaKigyouDtDto.getInputOrgNameDto().getOrgName());
        propertyEntity.setOrgDelegateCode(kanrenshaKigyouDtDto.getOrgDelegateLeastDto().getPersonKanrenshaCode());
        propertyEntity.setOrgNameKana(kanrenshaKigyouDtDto.getInputOrgNameDto().getOrgNameKana());
        
        propertyEntity.setKanrenshaKigyouDtPropertyId(kanrenshaKigyouDtDto.getPropertyId());
        propertyEntity.setKanrenshaKigyouDtId(kanrenshaKigyouDtDto.getMasterId());

        return propertyEntity;
    }
}
