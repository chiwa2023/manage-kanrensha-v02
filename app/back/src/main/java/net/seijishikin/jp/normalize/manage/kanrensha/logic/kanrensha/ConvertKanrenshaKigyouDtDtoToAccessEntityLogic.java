package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;

/**
 * 関連者企業団体Dtoからマスタ企業団体連絡先Entity変換Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class ConvertKanrenshaKigyouDtDtoToAccessEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaKigyouDtDto 関連者企業団体Dto
     * @return 企業団体連絡先マスタEntity
     */
    public KanrenshaKigyouDtAccessEntity practice(final KanrenshaKigyouDtDto kanrenshaKigyouDtDto) {

        KanrenshaKigyouDtAccessEntity accessEntity = new KanrenshaKigyouDtAccessEntity();
        BeanUtils.copyProperties(kanrenshaKigyouDtDto.getInputAccessDto(), accessEntity);

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        accessEntity.setKanrenshaKigyouDtId(kanrenshaKigyouDtDto.getMasterId());
        accessEntity.setKanrenshaKigyouDtAccessId(kanrenshaKigyouDtDto.getAccessId());
        accessEntity.setKigyouDtKanrenshaCode(kanrenshaKigyouDtDto.getKigyouDtKanrenshaCode());
        accessEntity.setKanrenshaName(kanrenshaKigyouDtDto.getInputOrgNameDto().getOrgName());

        return accessEntity;
    }

}
