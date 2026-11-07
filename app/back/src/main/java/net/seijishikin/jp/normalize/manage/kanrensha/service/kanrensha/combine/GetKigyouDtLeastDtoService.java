package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.combine;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.GetKanrenshaCombineLeastResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;

/**
 * 関連者紐づけ用記号団体最小取得Service
 */
@Service
public class GetKigyouDtLeastDtoService {

    /** 関連者企業団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /**
     * 処理を行う
     * 
     * @param kanrenshaCode 関連者コード
     * @return 関連者紐づけ用マスタ最小
     */
    public GetKanrenshaCombineLeastResultDto practice(final String kanrenshaCode) {

        Optional<KanrenshaKigyouDtMasterEntity> optional = kanrenshaKigyouDtMasterRepository
                .findFirstByKigyouDtKanrenshaCodeAndIsLatestTrueOrderByKanrenshaKigyouDtMasterIdDesc(kanrenshaCode);

        GetKanrenshaCombineLeastResultDto resultDto = new GetKanrenshaCombineLeastResultDto();

        if (!optional.isEmpty()) {
            KanrenshaKigyouDtMasterEntity entity = optional.get();
            resultDto.setKanrenshaCode(entity.getKigyouDtKanrenshaCode());
            resultDto.setKanrenshaName(entity.getKanrenshaName());
        }

        return resultDto;
    }

}
