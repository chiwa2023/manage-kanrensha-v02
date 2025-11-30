package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaManagerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaManagerMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPersonPropertyRepository;

/**
 * 運営者ユーザDtoを取得する
 */
@Service
public class GetRiyoushaManagerDtoService {

    /** 利用者個人属性Repository */
    @Autowired
    private RiyoushaPersonPropertyRepository riyoushaPersonPropertyRepository;

    /**
     * 処理を行う
     * 
     * @param masterEntity マスタEntity
     * @return 利用者運営者Dto
     */
    public RiyoushaManagerDto practice(final RiyoushaManagerMasterEntity masterEntity) {

        RiyoushaManagerDto managerDto = new RiyoushaManagerDto();

        BeanUtils.copyProperties(masterEntity, managerDto);

        Optional<RiyoushaPersonPropertyEntity> optionalProperty = riyoushaPersonPropertyRepository
                .findById(masterEntity.getRiyoushaPersonPropertyId());

        if (optionalProperty.isEmpty()) {
            return new RiyoushaManagerDto();
        }

        RiyoushaPersonPropertyEntity propertyEntity = optionalProperty.get();

        BeanUtils.copyProperties(propertyEntity, managerDto.getInputPersonNameDto());
        BeanUtils.copyProperties(propertyEntity, managerDto.getInputAddressDto());
        BeanUtils.copyProperties(propertyEntity, managerDto.getInputAccessDto());

        managerDto.getInputPersonNameDto().setAllName(masterEntity.getAllName());
        managerDto.getInputPersonNameDto().setAllNameKana(masterEntity.getAllNameKana());
        managerDto.getInputAddressDto().setAddressAll(masterEntity.getAddressAll());

        return managerDto;
    }

}
