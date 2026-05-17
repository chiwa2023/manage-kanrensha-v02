package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallSeijidantaiAccessEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallSeijidantaiAddressEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallSeijidantaiPropertyEntityLogic;

/**
 * 関連者個人マスタを取得してDTOに変換するService
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class GetKanrenshaSeijidantaiDtoService {

    /** 関連者個人連絡先マスタ取得Logic */
    @Autowired
    private CallSeijidantaiAccessEntityLogic callSeijidantaiAccessEntityLogic;

    /** 関連者個人住所マスタ取得Logic */
    @Autowired
    private CallSeijidantaiAddressEntityLogic callSeijidantaiAddressEntityLogic;

    /** 関連者個人属性マスタ取得Logic */
    @Autowired
    private CallSeijidantaiPropertyEntityLogic callSeijidantaiPropertyEntityLogic;

    /**
     * 処理を行う
     *
     * @param masterSeijidantaiEntity 関連者個人マスタ
     * @return 関連者個人DTO
     */
    public KanrenshaSeijidantaiDto practice(final KanrenshaSeijidantaiMasterEntity masterSeijidantaiEntity) {
        KanrenshaSeijidantaiDto dto = new KanrenshaSeijidantaiDto();
        String kanrenshaCode = masterSeijidantaiEntity.getSeijidantaiKanrenshaCode();

        // 各マスタからエンティティを取得
        // Entity -> DTOへの値複写
        KanrenshaSeijidantaiAccessEntity accessEntity = callSeijidantaiAccessEntityLogic.practice(kanrenshaCode);
        dto.setInputAccessDto(new InputAccessDto());
        BeanUtils.copyProperties(accessEntity, dto.getInputAccessDto());
        dto.setAccessId(accessEntity.getKanrenshaSeijidantaiAccessId());

        KanrenshaSeijidantaiAddressEntity addressEntity = callSeijidantaiAddressEntityLogic.practice(kanrenshaCode);
        dto.setInputAddressDto(new InputAddressDto());
        BeanUtils.copyProperties(addressEntity, dto.getInputAddressDto());
        dto.setAddressId(addressEntity.getKanrenshaSeijidantaiAddressId());

        KanrenshaSeijidantaiPropertyEntity propertyEntity = callSeijidantaiPropertyEntityLogic.practice(kanrenshaCode);
        dto.setPropertyId(propertyEntity.getKanrenshaSeijidantaiPropertyId());
        dto.getInputOrgNameDto().setOrgNameKana(propertyEntity.getOrgNameKana());
        dto.getOrgDelegateLeastDto().setPersonKanrenshaCode(propertyEntity.getOrgDelegateCode());
        dto.getAccounrMgrLeastDto().setPersonKanrenshaCode(propertyEntity.getAccountMgrCode());
        dto.getAccounrMgrLeastDto().setPersonName(propertyEntity.getAccountMgrName());

        // 最後にMasterSeijidantaiEntityの値を複写して上書き
        dto.setMasterId(masterSeijidantaiEntity.getKanrenshaSeijidantaiMasterId());
        dto.setSeijidantaiKanrenshaCode(masterSeijidantaiEntity.getSeijidantaiKanrenshaCode());
        dto.getInputOrgNameDto().setOrgName(masterSeijidantaiEntity.getKanrenshaName());
        dto.getInputAddressDto().setAddressAll(masterSeijidantaiEntity.getAllAddress());
        dto.getOrgDelegateLeastDto().setPersonName(masterSeijidantaiEntity.getSeijidantaiDelegate());
        dto.setDantaiKbn(masterSeijidantaiEntity.getDantaiKbn());
        dto.setPoliOrgNo(masterSeijidantaiEntity.getPoliOrgNo());

        return dto;
    }
}
