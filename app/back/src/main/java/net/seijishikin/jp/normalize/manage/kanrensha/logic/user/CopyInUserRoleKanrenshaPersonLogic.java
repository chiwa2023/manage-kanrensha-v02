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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaManagerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaPartnerApiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaManagerMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserRoleEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaManagerMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPartnerApiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.InsertKanrenshaPersonService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.GetRiyoushaManagerDtoService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha.GetRiyoushaPartnerApiDtoService;

/**
 * 関連者運営者へその他権限から詳細複写Logic
 */
@Component
public class CopyInUserRoleKanrenshaPersonLogic {

    /** 関連者個人挿入Service */
    @Autowired
    private InsertKanrenshaPersonService insertKanrenshaPersonService;

    /** 利用者運営者マスタRepository */
    @Autowired
    private RiyoushaManagerMasterRepository riyoushaManagerMasterRepository;

    /** 利用者運営者マスタRepository */
    @Autowired
    private RiyoushaPartnerApiMasterRepository riyoushaPartnerApiMasterRepository;

    /** 関連者運営者マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 関連者運営者マスタ取得Service */
    @Autowired
    private GetRiyoushaManagerDtoService getRiyoushaManagerDtoService;

    /** 関連者運営者マスタ取得Service */
    @Autowired
    private GetRiyoushaPartnerApiDtoService getRiyoushaPartnerApiDtoService;

    /**
     * 処理を行う
     * 
     * @param rolesWithRiyoushaCode 利用者紐づけ権限リスト
     * @param mapRole               変更前ユーザ権限Map
     * @param userDto               ユーザ最小限Dto
     * @return 更新コード
     */
    public String practice(final List<String> rolesWithRiyoushaCode, final Map<String, UserRoleEntity> mapRole,
            final LeastUserDto userDto) {

        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();

        if (rolesWithRiyoushaCode.contains(UserRoleConstants.MANAGER)) {
            RiyoushaManagerDto resultDto = this.getManagerDto(mapRole);
            kanrenshaPersonDto = this.createInsertDto(resultDto.getInputAddressDto(), resultDto.getInputAccessDto(),
                    resultDto.getInputPersonNameDto());
        }
        if (rolesWithRiyoushaCode.contains(UserRoleConstants.PARTNER_API)) {
            RiyoushaPartnerApiDto resultDto = this.getPartnerDto(mapRole);
            kanrenshaPersonDto = this.createInsertDto(resultDto.getInputAddressDto(), resultDto.getInputAccessDto(),
                    resultDto.getInputPersonNameDto());

        }

        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setUserDto(userDto);
        capsuleDto.setKanrenshaPersonDto(kanrenshaPersonDto);

        Integer newId = insertKanrenshaPersonService.practice(capsuleDto);
        if (0 != newId) {
            return kanrenshaPersonMasterRepository.findById(newId).get().getPersonKanrenshaCode();
        }

        throw new EmptyResultDataAccessException("複写できる対象が存在しません", 1);
    }

    private KanrenshaPersonDto createInsertDto(final InputAddressDto inputAddressDto,
            final InputAccessDto inputAccessDto, final InputPersonNameDto inputPersonNameDto) {

        KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();
        kanrenshaPersonDto.setInputAddressDto(inputAddressDto);
        kanrenshaPersonDto.setInputAccessDto(inputAccessDto);
        kanrenshaPersonDto.setInputPersonNameDto(inputPersonNameDto);
        kanrenshaPersonDto.setIsCombineUser(false); // 紐づけはするけどuserデータが後

        return kanrenshaPersonDto;
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

    private RiyoushaManagerDto getManagerDto(final Map<String, UserRoleEntity> mapRole) {

        if (!mapRole.containsKey(UserRoleConstants.MANAGER)) {
            throw new IllegalCallerException("ユーザ権限処理に以上が発生しています");
        }

        Optional<RiyoushaManagerMasterEntity> optional = riyoushaManagerMasterRepository
                .findFirstByRiyoushaManagerMasterCodeAndIsLatestTrueOrderByRiyoushaManagerMasterIdDesc(
                        mapRole.get(UserRoleConstants.MANAGER).getRiyoushaCode());

        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("複写元の権限に紐づくデータが存在しません", 1);
        }

        return getRiyoushaManagerDtoService.practice(optional.get());
    }

}
