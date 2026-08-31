package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;

/**
 * 関連者政治団体Dtoからマスタ政治団体連絡先Entity変換Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class ConvertKanrenshaSeijidantaiDtoToAccessEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaSeijidantaiDto 関連者政治団体Dto
     * @return 政治団体連絡先マスタEntity
     */
    public KanrenshaSeijidantaiAccessEntity practice(final KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto) {

        KanrenshaSeijidantaiAccessEntity accessEntity = new KanrenshaSeijidantaiAccessEntity();
        BeanUtils.copyProperties(kanrenshaSeijidantaiDto.getInputAccessDto(), accessEntity);

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        accessEntity.setKanrenshaSeijidantaiId(kanrenshaSeijidantaiDto.getMasterId());
        accessEntity.setKanrenshaSeijidantaiAccessId(kanrenshaSeijidantaiDto.getAccessId());
        accessEntity.setKanrenshaName(kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgName());
        accessEntity.setSeijidantaiKanrenshaCode(kanrenshaSeijidantaiDto.getSeijidantaiKanrenshaCode());

        return accessEntity;
    }

}
