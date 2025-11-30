package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaPartnerApiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPersonPropertyRepository;

/**
 * 運営者ユーザDtoを取得する
 */
@Service
public class GetRiyoushaPartnerApiDtoService {

    /** 利用者個人属性Repository */
    @Autowired
    private RiyoushaPersonPropertyRepository riyoushaPersonPropertyRepository;

    /**
     * 処理を行う
     * 
     * @param masterEntity マスタEntity
     * @return 利用者運営者Dto
     */
    public RiyoushaPartnerApiDto practice(final RiyoushaPartnerApiMasterEntity masterEntity) {

        RiyoushaPartnerApiDto partnerApiDto = new RiyoushaPartnerApiDto();

        BeanUtils.copyProperties(masterEntity, partnerApiDto);

        Optional<RiyoushaPersonPropertyEntity> optionalProperty = riyoushaPersonPropertyRepository
                .findById(masterEntity.getRiyoushaPersonPropertyId());
        if (optionalProperty.isEmpty()) {
            return new RiyoushaPartnerApiDto();
        }

        RiyoushaPersonPropertyEntity propertyEntity = optionalProperty.get();

        BeanUtils.copyProperties(propertyEntity, partnerApiDto.getInputPersonNameDto());
        BeanUtils.copyProperties(propertyEntity, partnerApiDto.getInputAddressDto());
        BeanUtils.copyProperties(propertyEntity, partnerApiDto.getInputAccessDto());

        partnerApiDto.getInputPersonNameDto().setAllName(masterEntity.getAllName());
        partnerApiDto.getInputPersonNameDto().setAllNameKana(masterEntity.getAllNameKana());
        partnerApiDto.getInputAddressDto().setAddressAll(masterEntity.getAddressAll());

        return partnerApiDto;
    }

}
