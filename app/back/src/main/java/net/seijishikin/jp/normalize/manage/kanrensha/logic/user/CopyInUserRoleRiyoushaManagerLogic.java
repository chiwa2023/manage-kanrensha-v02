package net.seijishikin.jp.normalize.manage.kanrensha.logic.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputPersonNameDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaManagerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaPartnerApiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SaveRiyoushaManagerCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaManagerMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPartnerApiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaPersonDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.GetRiyoushaPartnerApiDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.SaveRiyoushaManagerEntityService;

/**
 * 利用者運営者へその他権限から詳細複写Logic
 */
@Component
public class CopyInUserRoleRiyoushaManagerLogic {

    /** 利用者運営者保存Service */
    @Autowired
    private SaveRiyoushaManagerEntityService saveRiyoushaManagerEntityService;

    /** 利用者運営者マスタRepository */
    @Autowired
    private RiyoushaPartnerApiMasterRepository riyoushaPartnerApiMasterRepository;

    /** 利用者運営者マスタRepository */
    @Autowired
    private RiyoushaManagerMasterRepository riyoushaManagerMasterRepository;

    /** 関連者APIパートナーマスタ取得Service */
    @Autowired
    private GetRiyoushaPartnerApiDtoService getRiyoushaPartnerApiDtoService;

    /** 関連者運営者マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者運営者マスタ取得Service */
    @Autowired
    private GetKanrenshaPersonDtoService getKanrenshaPersonDtoService;

    /**
     * 処理を行う
     * 
     * @param rolesWithKanrenshaCode 関連者詳細データ紐づき権限リスト
     * @param rolesWithRiyoushaCode  利用者詳細データ紐づき権限リスト
     * @param mapRole                旧権限Map
     * @param userDto                ユーザ最小限 Dto
     * @return 登録コード
     */
    public int practice(final List<String> rolesWithKanrenshaCode, final List<String> rolesWithRiyoushaCode,
            final Map<String, UserRoleEntity> mapRole, final LeastUserDto userDto) {

        RiyoushaManagerDto riyoushaManagerDto = new RiyoushaManagerDto();
        if (rolesWithRiyoushaCode.contains(UserRoleConstants.PARTNER_API)) {

            RiyoushaPartnerApiDto resultDto = this.getPartnerDto(mapRole);
            riyoushaManagerDto = this.createInsertDto(resultDto.getInputAddressDto(), resultDto.getInputAccessDto(),
                    resultDto.getInputPersonNameDto());
        }
        if (rolesWithKanrenshaCode.contains(UserRoleConstants.KANRENSHA_PERSON)) {
            KanrenshaPersonDto resultDto = this.getPersonDto(mapRole);

            riyoushaManagerDto = this.createInsertDto(resultDto.getInputAddressDto(), resultDto.getInputAccessDto(),
                    resultDto.getInputPersonNameDto());
        }

        SaveRiyoushaManagerCapsuleDto capsuleDto = new SaveRiyoushaManagerCapsuleDto();
        capsuleDto.setUserDto(userDto);
        capsuleDto.setRiyoushaManagerDto(riyoushaManagerDto);

        Integer newId = saveRiyoushaManagerEntityService.practice(capsuleDto);
        if (0 != newId) {
            return riyoushaManagerMasterRepository.findById(newId).get().getRiyoushaManagerMasterCode();
        }

        throw new EmptyResultDataAccessException("複写できる対象が存在しません", 1);
    }

    private RiyoushaManagerDto createInsertDto(final InputAddressDto inputAddressDto,
            final InputAccessDto inputAccessDto, final InputPersonNameDto inputPersonNameDto) {

        RiyoushaManagerDto riyoushaManagerDto = new RiyoushaManagerDto();
        riyoushaManagerDto.setInputAddressDto(inputAddressDto);
        riyoushaManagerDto.setInputAccessDto(inputAccessDto);
        riyoushaManagerDto.setInputPersonNameDto(inputPersonNameDto);
        riyoushaManagerDto.setIsCombineUser(false); // 紐づけはするけどuserデータが後

        return riyoushaManagerDto;
    }

    private RiyoushaPartnerApiDto getPartnerDto(final Map<String, UserRoleEntity> mapRole) {

        if (!mapRole.containsKey(UserRoleConstants.PARTNER_API)) {
            throw new IllegalCallerException("ユーザ権限処理に以上が発生しています");
        }

        Optional<RiyoushaPartnerApiMasterEntity> optional = riyoushaPartnerApiMasterRepository
                .findFirstByRiyoushaPartnerApiMasterCodeAndIsLatestTrueOrderByRiyoushaPartnerApiMasterIdDesc(
                        mapRole.get(UserRoleConstants.PARTNER_API).getRiyoushaCode());

        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("複写元の権限に紐づくデータが存在しません", 1);
        }

        return getRiyoushaPartnerApiDtoService.practice(optional.get());
    }

    private KanrenshaPersonDto getPersonDto(final Map<String, UserRoleEntity> mapRole) {

        if (!mapRole.containsKey(UserRoleConstants.KANRENSHA_PERSON)) {
            throw new IllegalCallerException("ユーザ権限処理に以上が発生しています");
        }

        Optional<KanrenshaPersonMasterEntity> optional = kanrenshaPersonMasterRepository
                .findFirstByPersonKanrenshaCodeAndIsLatestTrueOrderByKanrenshaPersonMasterIdDesc(
                        mapRole.get(UserRoleConstants.KANRENSHA_PERSON).getKanrenshaCode());

        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("複写元の権限に紐づくデータが存在しません", 1);
        }

        return getKanrenshaPersonDtoService.practice(optional.get());
    }

}
