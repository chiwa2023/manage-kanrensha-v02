package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetTempRiyoushaOrgCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetTempRiyoushaOrgCombineResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgTempEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgTempRepository;

/**
 * 利用者組織招待取得Service
 */
@Service
public class GetTempRiyoushaOrgCombineService {

    /** 利用者組織紐づけ個人Respoitory */
    @Autowired
    private RiyoushaCombineOrgTempRepository riyoushaCombineOrgTempRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 招待取得Dto
     * @return 処理結果
     */
    public GetTempRiyoushaOrgCombineResultDto practice(final GetTempRiyoushaOrgCombineCapsuleDto capsuleDto) {

        List<RiyoushaCombineOrgTempEntity> list = this.getTempList(capsuleDto);

        GetTempRiyoushaOrgCombineResultDto resultDto = new GetTempRiyoushaOrgCombineResultDto();
        if (list.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("紐づけとなるデータが呼び出せませんでした。");
            return resultDto;
        }

        resultDto.setCombineTempEntity(list.get(0));
        return resultDto;
    }

    private List<RiyoushaCombineOrgTempEntity> getTempList(final GetTempRiyoushaOrgCombineCapsuleDto capsuleDto) {

        if (0 == capsuleDto.getPersonCode()) {
            // QueryParamが設定されていないときは自身のユーザを持ってきて1件づつ処理(登録順=古い順)
            return riyoushaCombineOrgTempRepository
                    .findByPersonCodeAndIsLatestTrue(capsuleDto.getUserDto().getUserPersonCode());
        } else {
            // QueryParamが設定されている場合はその情報を呼び出し
            return riyoushaCombineOrgTempRepository.findByPersonRiyoushaCodeAndOrgRiyoushaCodeAndRiyoushaRoleAndIsLatestTrue(
                    capsuleDto.getPersonCode(), capsuleDto.getOrgCode(), capsuleDto.getUserRole());
        }

    }

}
