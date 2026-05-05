package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallPersonAccessEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallPersonAddressEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallPersonPropertyEntityLogic;

/**
 * 関連者個人マスタを取得してDTOに変換するService
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class GetKanrenshaPersonDtoService {

    /** 関連者個人連絡先マスタ取得Logic */
    @Autowired
    private CallPersonAccessEntityLogic callPersonAccessEntityLogic;

    /** 関連者個人住所マスタ取得Logic */
    @Autowired
    private CallPersonAddressEntityLogic callPersonAddressEntityLogic;

    /** 関連者個人属性マスタ取得Logic */
    @Autowired
    private CallPersonPropertyEntityLogic callPersonPropertyEntityLogic;

    /**
     * 処理を行う
     *
     * @param masterPersonEntity 関連者個人マスタ
     * @return 関連者個人DTO
     */
    public KanrenshaPersonDto practice(final KanrenshaPersonMasterEntity masterPersonEntity) {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        String kanrenshaCode = masterPersonEntity.getPersonKanrenshaCode();

        // 各マスタからエンティティを取得
        // Entity -> DTOへの値複写
        KanrenshaPersonAccessEntity accessEntity = callPersonAccessEntityLogic.practice(kanrenshaCode);
        dto.setInputAccessDto(new InputAccessDto());
        BeanUtils.copyProperties(accessEntity, dto.getInputAccessDto());
        dto.setAccessId(accessEntity.getKanrenshaPersonAccessId());

        KanrenshaPersonAddressEntity addressEntity = callPersonAddressEntityLogic.practice(kanrenshaCode);
        dto.setInputAddressDto(new InputAddressDto());
        BeanUtils.copyProperties(addressEntity, dto.getInputAddressDto());
        dto.setAddressId(addressEntity.getKanrenshaPersonAddressId());

        KanrenshaPersonPropertyEntity propertyEntity = callPersonPropertyEntityLogic.practice(kanrenshaCode);
        BeanUtils.copyProperties(propertyEntity, dto.getInputPersonNameDto());
        BeanUtils.copyProperties(propertyEntity, dto.getInputShokugyouDto());
        dto.setPropertyId(propertyEntity.getKanrenshaPersonPropertyId());
        dto.getInputShokugyouDto().setHoujinNo(propertyEntity.getKigyouDtNo());
        dto.getInputShokugyouDto().setHoujinName(propertyEntity.getKigyouDtName());
        dto.getInputShokugyouDto().setHoujinAddress(propertyEntity.getKigyouDtAddress());
        dto.setIsForeign(propertyEntity.getIsForeign());

        // 最後にMasterPersonEntityの値を複写して上書き
        dto.setMasterId(masterPersonEntity.getKanrenshaPersonMasterId());
        dto.setPersonKanrenshaCode(masterPersonEntity.getPersonKanrenshaCode());
        dto.getInputPersonNameDto().setAllName(masterPersonEntity.getKanrenshaName());
        dto.getInputAddressDto().setAddressAll(masterPersonEntity.getAllAddress());
        dto.getInputShokugyouDto().setAllShokugyou(masterPersonEntity.getPersonShokugyou());

        return dto;
    }
}
