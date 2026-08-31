package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;

/**
 * 関連者企業団体Dtoを企業団体マスタ変換Logic
 */
@Component
public class ConvertKanrenshaKigyouDtDtoToMasterEntityLogic {

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 処理を行う
     *
     * @param kanrenshaKigyouDtDto 関連者企業団体Dto
     * @return 企業団体マスタEntity
     */
    public KanrenshaKigyouDtMasterEntity practice(final KanrenshaKigyouDtDto kanrenshaKigyouDtDto) {

        KanrenshaKigyouDtMasterEntity masterEntity = new KanrenshaKigyouDtMasterEntity();

        masterEntity.setKanrenshaKigyouDtMasterId(kanrenshaKigyouDtDto.getMasterId());
        masterEntity.setKigyouDtKanrenshaCode(kanrenshaKigyouDtDto.getKigyouDtKanrenshaCode());
        masterEntity.setKanrenshaName(kanrenshaKigyouDtDto.getInputOrgNameDto().getOrgName());

        masterEntity.setHoujinNo(kanrenshaKigyouDtDto.getHoujinNo());
        masterEntity.setAllAddress(kanrenshaKigyouDtDto.getInputAddressDto().getAddressAll());
        masterEntity.setKigyouDtDelegate(kanrenshaKigyouDtDto.getOrgDelegateLeastDto().getPersonName());
        masterEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(masterEntity.getKanrenshaName()));

        return masterEntity;
    }

}
