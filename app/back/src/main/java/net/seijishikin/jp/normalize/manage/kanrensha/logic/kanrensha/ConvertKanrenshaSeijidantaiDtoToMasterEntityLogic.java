package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;


/**
 * 関連者政治団体Dtoを政治団体マスタ変換Logic
 */
@Component
public class ConvertKanrenshaSeijidantaiDtoToMasterEntityLogic {

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 処理を行う
     *
     * @param kanrenshaSeijidantaiDto 関連者政治団体Dto
     * @return 政治団体マスタEntity
     */
    public KanrenshaSeijidantaiMasterEntity practice(final KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto) {

        KanrenshaSeijidantaiMasterEntity masterEntity = new KanrenshaSeijidantaiMasterEntity();

        masterEntity.setKanrenshaSeijidantaiMasterId(kanrenshaSeijidantaiDto.getMasterId());
        masterEntity.setSeijidantaiKanrenshaCode(kanrenshaSeijidantaiDto.getSeijidantaiKanrenshaCode());
        masterEntity.setKanrenshaName(kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgName());
        
        
        masterEntity.setAllAddress(kanrenshaSeijidantaiDto.getInputAddressDto().getAddressAll());
        masterEntity.setSeijidantaiDelegate(kanrenshaSeijidantaiDto.getOrgDelegateLeastDto().getPersonName());
        masterEntity.setDantaiKbn(kanrenshaSeijidantaiDto.getDantaiKbn());
        masterEntity.setPoliOrgNo(kanrenshaSeijidantaiDto.getPoliOrgNo());
        masterEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(masterEntity.getKanrenshaName()));

        return masterEntity;
    }

}
