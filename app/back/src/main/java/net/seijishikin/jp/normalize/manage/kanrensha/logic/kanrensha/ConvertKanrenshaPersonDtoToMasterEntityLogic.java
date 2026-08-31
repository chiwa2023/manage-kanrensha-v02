package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;


/**
 * 関連者個人Dtoを個人マスタ変換Logic
 */
@Component
public class ConvertKanrenshaPersonDtoToMasterEntityLogic {

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return 個人マスタEntity
     */
    public KanrenshaPersonMasterEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        KanrenshaPersonMasterEntity masterEntity = new KanrenshaPersonMasterEntity();

        masterEntity.setKanrenshaPersonMasterId(kanrenshaPersonDto.getMasterId());
        masterEntity.setPersonKanrenshaCode(kanrenshaPersonDto.getPersonKanrenshaCode());
        masterEntity.setKanrenshaName(kanrenshaPersonDto.getInputPersonNameDto().getAllName());

        masterEntity.setAllAddress(kanrenshaPersonDto.getInputAddressDto().getAddressAll());
        masterEntity.setPersonShokugyou(kanrenshaPersonDto.getInputShokugyouDto().getAllShokugyou());
        masterEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(masterEntity.getKanrenshaName()));

        return masterEntity;
    }

}
