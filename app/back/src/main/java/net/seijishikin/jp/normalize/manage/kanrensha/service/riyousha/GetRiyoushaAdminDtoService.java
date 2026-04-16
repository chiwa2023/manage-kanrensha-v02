package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.RiyoushaAdminDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaAdminMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPersonPropertyRepository;

/**
 * 運営者ユーザDtoを取得する
 */
@Service
public class GetRiyoushaAdminDtoService {

    /** 利用者個人属性Repository */
    @Autowired
    private RiyoushaPersonPropertyRepository riyoushaPersonPropertyRepository;

    /**
     * 処理を行う
     * 
     * @param masterEntity マスタEntity
     * @return 利用者運営者Dto
     */
    public RiyoushaAdminDto practice(final RiyoushaAdminMasterEntity masterEntity) {

        RiyoushaAdminDto adminDto = new RiyoushaAdminDto();

        BeanUtils.copyProperties(masterEntity, adminDto);

        Optional<RiyoushaPersonPropertyEntity> optionalProperty = riyoushaPersonPropertyRepository
                .findById(masterEntity.getRiyoushaPersonPropertyId());
        if (optionalProperty.isEmpty()) {
            return new RiyoushaAdminDto();
        }

        RiyoushaPersonPropertyEntity propertyEntity = optionalProperty.get();

        BeanUtils.copyProperties(propertyEntity, adminDto.getInputPersonNameDto());
        BeanUtils.copyProperties(propertyEntity, adminDto.getInputAddressDto());
        BeanUtils.copyProperties(propertyEntity, adminDto.getInputAccessDto());

        adminDto.getInputPersonNameDto().setAllName(masterEntity.getAllName());
        adminDto.getInputPersonNameDto().setAllNameKana(masterEntity.getAllNameKana());
        adminDto.getInputAddressDto().setAddressAll(masterEntity.getAddressAll());

        return adminDto;
    }

}
