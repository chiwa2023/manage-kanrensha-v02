package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallKigyouDtAccessEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallKigyouDtAddressEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallKigyouDtPropertyEntityLogic;

/**
 * 関連者個人マスタを取得してDTOに変換するService
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class GetKanrenshaKigyouDtDtoService {

    /** 関連者個人連絡先マスタ取得Logic */
    @Autowired
    private CallKigyouDtAccessEntityLogic callKigyouDtAccessEntityLogic;

    /** 関連者個人住所マスタ取得Logic */
    @Autowired
    private CallKigyouDtAddressEntityLogic callKigyouDtAddressEntityLogic;

    /** 関連者個人属性マスタ取得Logic */
    @Autowired
    private CallKigyouDtPropertyEntityLogic callKigyouDtPropertyEntityLogic;

    /**
     * 処理を行う
     *
     * @param masterKigyouDtEntity 関連者企業団体マスタ
     * @return 関連者個人DTO
     */
    public KanrenshaKigyouDtDto practice(final KanrenshaKigyouDtMasterEntity masterKigyouDtEntity) {
        KanrenshaKigyouDtDto dto = new KanrenshaKigyouDtDto();
        String kanrenshaCode = masterKigyouDtEntity.getKigyouDtKanrenshaCode();

        // 各マスタからエンティティを取得
        // Entity -> DTOへの値複写
        KanrenshaKigyouDtAccessEntity accessEntity = callKigyouDtAccessEntityLogic.practice(kanrenshaCode);
        dto.setInputAccessDto(new InputAccessDto());
        BeanUtils.copyProperties(accessEntity, dto.getInputAccessDto());
        dto.setAccessId(accessEntity.getKanrenshaKigyouDtAccessId());
        
        KanrenshaKigyouDtAddressEntity addressEntity = callKigyouDtAddressEntityLogic.practice(kanrenshaCode);
        dto.setInputAddressDto(new InputAddressDto());
        BeanUtils.copyProperties(addressEntity, dto.getInputAddressDto());
        dto.setAddressId(addressEntity.getKanrenshaKigyouDtAddressId());
        
        KanrenshaKigyouDtPropertyEntity propertyEntity = callKigyouDtPropertyEntityLogic.practice(kanrenshaCode);
        BeanUtils.copyProperties(propertyEntity, dto);
        dto.setPropertyId(propertyEntity.getKanrenshaKigyouDtPropertyId());
        
        // 最後にMasterEntityを設定する
        dto.setMasterId(masterKigyouDtEntity.getKanrenshaKigyouDtMasterId());
        dto.getInputOrgNameDto().setOrgName(masterKigyouDtEntity.getKanrenshaName());
        dto.getInputOrgNameDto().setOrgNameKana(propertyEntity.getOrgNameKana());
        dto.getInputAddressDto().setAddressAll(masterKigyouDtEntity.getAllAddress());
        dto.getOrgDelegateLeastDto().setPersonName(masterKigyouDtEntity.getKigyouDtDelegate());
        dto.getOrgDelegateLeastDto().setPersonKanrenshaCode(propertyEntity.getOrgDelegateCode());
        dto.setHoujinNo(masterKigyouDtEntity.getHoujinNo());

        return dto;
    }
}
