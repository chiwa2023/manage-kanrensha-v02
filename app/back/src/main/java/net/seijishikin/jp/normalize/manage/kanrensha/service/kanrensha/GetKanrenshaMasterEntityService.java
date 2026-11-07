package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaKigyouDtMasterDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaPersonMasterDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaSeijidantaiMasterDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.GetKanrenshaMasterCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.GetKanrenshaMasterResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;

/**
 * 関連者取得Service
 */
@Service
public class GetKanrenshaMasterEntityService {

    /** 関連者企業団体マスタRespoitory */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 関連者企業団体マスタRespoitory */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者企業団体マスタRespoitory */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /**
     * 処理を行う
     * 
     * @param capsuleDto 関連者マスタEntity取得条件Dto
     * @return 関連者マスタ取得Dto
     */
    public GetKanrenshaMasterResultDto practice(final GetKanrenshaMasterCapsuleDto capsuleDto) {

        GetKanrenshaMasterResultDto resultDto = new GetKanrenshaMasterResultDto();
        String code = capsuleDto.getKanrenshaCode();

        switch (capsuleDto.getKanrenshaRole()) {
            case UserRoleConstants.KANRENSHA_PERSON:
                Optional<KanrenshaPersonMasterEntity> optionalPerson = kanrenshaPersonMasterRepository
                        .findFirstByPersonKanrenshaCodeAndIsLatestTrueOrderByKanrenshaPersonMasterIdDesc(code);
                if (optionalPerson.isEmpty()) {
                    resultDto.setIsFailure(true);
                    resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);

                } else {
                    KanrenshaPersonMasterDto personDto = new KanrenshaPersonMasterDto();
                    BeanUtils.copyProperties(optionalPerson.get(), personDto);
                    resultDto.setMasterPersonEntity(personDto);
                }
                break;

            case UserRoleConstants.KANRENSHA_KIGYOU_DT:
                Optional<KanrenshaKigyouDtMasterEntity> optionalKigyouDt = kanrenshaKigyouDtMasterRepository
                        .findFirstByKigyouDtKanrenshaCodeAndIsLatestTrueOrderByKanrenshaKigyouDtMasterIdDesc(
                                code);
                if (optionalKigyouDt.isEmpty()) {
                    resultDto.setIsFailure(true);
                    resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);

                } else {
                    KanrenshaKigyouDtMasterDto kigyouDtDto = new KanrenshaKigyouDtMasterDto();
                    BeanUtils.copyProperties(optionalKigyouDt.get(), kigyouDtDto);
                    resultDto.setMasterKigyouDtEntity(kigyouDtDto);
                }
                break;

            case UserRoleConstants.KANRENSHA_SEIJIDANTAI:
                Optional<KanrenshaSeijidantaiMasterEntity> optionalSeijidantai = kanrenshaSeijidantaiMasterRepository
                        .findFirstBySeijidantaiKanrenshaCodeAndIsLatestTrueOrderByKanrenshaSeijidantaiMasterIdDesc(
                                code);
                if (optionalSeijidantai.isEmpty()) {
                    resultDto.setIsFailure(true);
                    resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_NO_CONTENT);

                } else {
                    KanrenshaSeijidantaiMasterDto seijidantaiDto = new KanrenshaSeijidantaiMasterDto();
                    BeanUtils.copyProperties(optionalSeijidantai.get(), seijidantaiDto);
                    resultDto.setMasterSeijidantaiEntity(seijidantaiDto);
                }
                break;

            default:
                throw new IllegalArgumentException("Unexpected value: " + capsuleDto.getKanrenshaRole());
        }

        return resultDto;
    }
}
