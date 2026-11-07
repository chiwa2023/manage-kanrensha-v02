package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.combine;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.GetKanrenshaCombineLeastResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;

/**
 * 勘れ者紐づけ個人最小取得Service
 */
@Service
public class GetPersonLeastDtoService {

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /**
     * 処理を行う
     * 
     * @param kanrenshaCode 関連者コード
     * @return 関連者紐づけ用マスタ最小
     */
    public GetKanrenshaCombineLeastResultDto practice(final String kanrenshaCode) {

        Optional<KanrenshaPersonMasterEntity> optional = kanrenshaPersonMasterRepository
                .findFirstByPersonKanrenshaCodeAndIsLatestTrueOrderByKanrenshaPersonMasterIdDesc(kanrenshaCode);

        GetKanrenshaCombineLeastResultDto resultDto = new GetKanrenshaCombineLeastResultDto();

        if (!optional.isEmpty()) {
            KanrenshaPersonMasterEntity entity = optional.get();
            resultDto.setKanrenshaCode(entity.getPersonKanrenshaCode());
            resultDto.setKanrenshaName(entity.getKanrenshaName());
        }

        return resultDto;
    }

}
