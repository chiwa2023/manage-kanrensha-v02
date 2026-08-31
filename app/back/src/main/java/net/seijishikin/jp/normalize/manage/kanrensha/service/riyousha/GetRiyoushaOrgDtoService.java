package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetRiyoushaOrgByCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaOrgDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaCombineOrgRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgPropertyRepository;

/**
 * 利用者組織取得Service
 */
@Service
public class GetRiyoushaOrgDtoService {

    /** 利用者組織マスタRepository */
    @Autowired
    private RiyoushaOrgMasterRepository riyoushaOrgMasterRepository;

    /** 利用者組織属性Repository */
    @Autowired
    private RiyoushaOrgPropertyRepository riyoushaOrgPropertyRepository;

    /** 利用者組織個人紐づけRepository */
    @Autowired
    private RiyoushaCombineOrgRepository riyoushaCombineOrgRepository;

    /**
     * コードから処理を行う
     * 
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    public RiyoushaOrgDto practiceByCode(final GetRiyoushaOrgByCodeCapsuleDto capsuleDto) {

        Optional<RiyoushaOrgMasterEntity> optional = riyoushaOrgMasterRepository
                .findByRiyoushaOrgMasterCodeAndIsLatestTrue(capsuleDto.getSelectedCode());

        // 空の場合はControllerで処理をする
        if (optional.isEmpty()) {
            return new RiyoushaOrgDto();
        }

        return this.practice(optional.get());
    }

    /**
     * 処理を行う
     * 
     * @param masterEntity マスタEntity
     * @return 利用者運営者Dto
     */
    public RiyoushaOrgDto practice(final RiyoushaOrgMasterEntity masterEntity) {

        RiyoushaOrgDto orgDto = new RiyoushaOrgDto();

        BeanUtils.copyProperties(masterEntity, orgDto);

        Optional<RiyoushaOrgPropertyEntity> optionalProperty = riyoushaOrgPropertyRepository
                .findById(masterEntity.getRiyoushaOrgPropertyId());

        if (optionalProperty.isEmpty()) {
            return new RiyoushaOrgDto();
        }

        RiyoushaOrgPropertyEntity propertyEntity = optionalProperty.get();

        BeanUtils.copyProperties(propertyEntity, orgDto.getInputOrgNameDto());
        BeanUtils.copyProperties(propertyEntity, orgDto.getInputAddressDto());
        BeanUtils.copyProperties(propertyEntity, orgDto.getInputAccessDto());

        orgDto.getInputOrgNameDto().setOrgName(masterEntity.getAllName());
        orgDto.getInputOrgNameDto().setOrgNameKana(masterEntity.getAllNameKana());
        orgDto.getInputAddressDto().setAddressAll(masterEntity.getAddressAll());

        // 紐づけ組織構成員
        orgDto.setListPersonCombine(riyoushaCombineOrgRepository
                .findByOrgRiyoushaCodeAndIsLatestTrue(masterEntity.getRiyoushaOrgMasterCode()));

        return orgDto;
    }

}
