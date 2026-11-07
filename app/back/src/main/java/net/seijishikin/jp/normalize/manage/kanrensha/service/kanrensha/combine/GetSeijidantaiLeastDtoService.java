package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.combine;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.GetKanrenshaCombineLeastResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;

/**
 * 関連者紐づけ用政治団体最小取得Service
 */
@Service
public class GetSeijidantaiLeastDtoService {

    /** 関連者政治団体マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /**
     * 処理を行う
     * 
     * @param kanrenshaCode 関連者コード
     * @return 関連者紐づけ用マスタ最小
     */
    public GetKanrenshaCombineLeastResultDto practice(final String kanrenshaCode) {

        Optional<KanrenshaSeijidantaiMasterEntity> optional = kanrenshaSeijidantaiMasterRepository
                .findFirstBySeijidantaiKanrenshaCodeAndIsLatestTrueOrderByKanrenshaSeijidantaiMasterIdDesc(kanrenshaCode);

        GetKanrenshaCombineLeastResultDto resultDto = new GetKanrenshaCombineLeastResultDto();

        if (!optional.isEmpty()) {
            KanrenshaSeijidantaiMasterEntity entity = optional.get();
            resultDto.setKanrenshaCode(entity.getSeijidantaiKanrenshaCode());
            resultDto.setKanrenshaName(entity.getKanrenshaName());
        }

        return resultDto;
    }

}
