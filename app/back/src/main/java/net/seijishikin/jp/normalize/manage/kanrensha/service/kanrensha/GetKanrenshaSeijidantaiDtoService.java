package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaSeijidantaiMasterDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
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
     * @param masterSeijidantaiDto 関連者個人マスタ
     * @return 関連者個人DTO
     */
    public KanrenshaSeijidantaiDto practice(final KanrenshaSeijidantaiMasterDto masterSeijidantaiDto) {
        KanrenshaSeijidantaiDto dto = new KanrenshaSeijidantaiDto();
        String kanrenshaCode = masterSeijidantaiDto.getSeijidantaiKanrenshaCode();

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
        dto.setMasterId(masterSeijidantaiDto.getKanrenshaSeijidantaiMasterId());
        dto.setSeijidantaiKanrenshaCode(masterSeijidantaiDto.getSeijidantaiKanrenshaCode());
        dto.getInputOrgNameDto().setOrgName(masterSeijidantaiDto.getKanrenshaName());
        dto.getInputAddressDto().setAddressAll(masterSeijidantaiDto.getAllAddress());
        dto.getOrgDelegateLeastDto().setPersonName(masterSeijidantaiDto.getSeijidantaiDelegate());
        dto.setDantaiKbn(masterSeijidantaiDto.getDantaiKbn());
        dto.setPoliOrgNo(masterSeijidantaiDto.getPoliOrgNo());

        return dto;
    }
}
