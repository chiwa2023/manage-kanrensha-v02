package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetRiyoushaMasterCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetRiyoushaMasterResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaManagerMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaManagerMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPartnerApiMasterRepository;

/**
 * 利用者マスタ取得Service
 */
@Service
public class GetRiyoushaMasterEntityService {

    /** 利用者運営者マスタRespoitory */
    @Autowired
    private RiyoushaManagerMasterRepository riyoushaManagerMasterRepository;

    /** 利用者運営者マスタRespoitory */
    @Autowired
    private RiyoushaPartnerApiMasterRepository riyoushaPartnerApiMasterRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 利用者マスタEntity取得条件Dto
     * @return 利用者マスタ取得Dto
     */
    public GetRiyoushaMasterResultDto practice(final GetRiyoushaMasterCapsuleDto capsuleDto) {

        GetRiyoushaMasterResultDto resultDto = new GetRiyoushaMasterResultDto();
        Integer code = capsuleDto.getRiyoushaCode();

        switch (capsuleDto.getRiyoushaRole()) {
            case UserRoleConstants.MANAGER, UserRoleConstants.ADMIN:
                Optional<RiyoushaManagerMasterEntity> optionalManager = riyoushaManagerMasterRepository
                        .findFirstByRiyoushaManagerMasterCodeAndIsLatestTrueOrderByRiyoushaManagerMasterIdDesc(code);
                if (optionalManager.isEmpty()) {
                    resultDto.setIsFailure(true);
                    resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);

                } else {
                    resultDto.setManagerMasterEntity(optionalManager.get());
                }
                break;

            case UserRoleConstants.PARTNER_API:
                Optional<RiyoushaPartnerApiMasterEntity> optionalPartner = riyoushaPartnerApiMasterRepository
                        .findFirstByRiyoushaPartnerApiMasterCodeAndIsLatestTrueOrderByRiyoushaPartnerApiMasterIdDesc(code);
                if (optionalPartner.isEmpty()) {
                    resultDto.setIsFailure(true);
                    resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);

                } else {
                    resultDto.setMaPartnerApiMasterEntity(optionalPartner.get());
                }
                break;

            default:
                throw new IllegalArgumentException("Unexpected value: " + capsuleDto.getRiyoushaRole());
        }

        return resultDto;
    }

}
