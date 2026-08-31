package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;

/**
 * 関連者個人Dtoからマスタ個人連作先Entity変換Logic
 */
@Component
public class ConvertKanrenshaPersonDtoToAccessEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return 個人住所マスタEntity
     */
    public KanrenshaPersonAccessEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        KanrenshaPersonAccessEntity accessEntity = new KanrenshaPersonAccessEntity();
        BeanUtils.copyProperties(kanrenshaPersonDto.getInputAccessDto(), accessEntity);

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        accessEntity.setKanrenshaPersonId(kanrenshaPersonDto.getMasterId());
        accessEntity.setKanrenshaPersonAccessId(kanrenshaPersonDto.getAccessId());
        accessEntity.setKanrenshaName(kanrenshaPersonDto.getInputPersonNameDto().getAllName());
        accessEntity.setPersonKanrenshaCode(kanrenshaPersonDto.getPersonKanrenshaCode());

        return accessEntity;
    }

}
