package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.GetDetailPostalIllegularCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.GetDetailPostalIllegularResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * 同一建物取得Service
 */
@Service
public class SearchPostalIrregularBuildingAllFloorService {

    /** 郵便番号不規則Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public GetDetailPostalIllegularResultDto practice(final GetDetailPostalIllegularCapsuleDto capsuleDto) {

        GetDetailPostalIllegularResultDto resultDto = new GetDetailPostalIllegularResultDto();
        // 150階建てのような件数が100件を超えるような建物が存在しないのでページングの必要性を感じない
        resultDto.setListIrregular(
                addressPostalIrregularRepository.findByAddressNameAndIsLatestTrue(capsuleDto.getAddressWords()));

        return resultDto;
    }

}
