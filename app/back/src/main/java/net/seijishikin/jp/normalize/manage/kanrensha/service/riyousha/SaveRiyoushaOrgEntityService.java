package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SaveRiyoushaOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaCombineOrgEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaOrgPropertyRepository;

/**
 * 利用者組織の追加・編集Service
 */
@Service
public class SaveRiyoushaOrgEntityService {

    /** 利用者組織マスタRepository */
    @Autowired
    private RiyoushaOrgMasterRepository riyoushaOrgMasterRepository;

    /** 利用者組織属性Repository */
    @Autowired
    private RiyoushaOrgPropertyRepository riyoushaOrgPropertyRepository;

    /** 利用者組織属性Repository */
    @Autowired
    private InsertRiyoushaCombinePersonService insertRiyoushaCombinePersonService;

    /** テーブル履歴設定utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 全文検索用カラム */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 利用者組織DTO
     * @return テーブルID
     */
    @Transactional
    public Integer practice(final SaveRiyoushaOrgCapsuleDto capsuleDto) {

        if (0 == capsuleDto.getRiyoushaOrgDto().getRiyoushaOrgMasterId()) {
            // マスタIdに値が入っていないときは新規
            return this.practiceInsert(capsuleDto);

        } else {
            // マスタIdに値が入っているときは更新
            return this.practiceUpdate(capsuleDto);
        }
    }

    /**
     * 新規作成処理を行う
     * 
     * @param capsuleDto 利用者運営者保存Dto
     * @return 登録後Id
     */
    private Integer practiceInsert(final SaveRiyoushaOrgCapsuleDto capsuleDto) {

        LeastUserDto userDto = capsuleDto.getUserDto();

        // 属性
        RiyoushaOrgPropertyEntity propertyEntity = this.createPropertyEntity(capsuleDto);
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);

        Integer codeProperty = 1;
        Optional<RiyoushaOrgPropertyEntity> optionalProperty = riyoushaOrgPropertyRepository
                .findFirstByOrderByRiyoushaOrgPropertyCodeDesc();
        if (!optionalProperty.isEmpty()) {
            codeProperty += optionalProperty.get().getRiyoushaOrgPropertyCode();
        }
        propertyEntity.setRiyoushaOrgPropertyCode(codeProperty);

        RiyoushaOrgPropertyEntity savedPropertyEntity = riyoushaOrgPropertyRepository.save(propertyEntity);

        // マスタ
        RiyoushaOrgMasterEntity masterEntity = this.createMasterEntity(capsuleDto, savedPropertyEntity);

        Integer codeMaster = 1;
        Optional<RiyoushaOrgMasterEntity> optionalAdmin = riyoushaOrgMasterRepository
                .findFirstByOrderByRiyoushaOrgMasterCodeDesc();
        if (!optionalAdmin.isEmpty()) {
            codeMaster += optionalAdmin.get().getRiyoushaOrgMasterCode();
        }
        masterEntity.setRiyoushaOrgMasterCode(codeMaster);

        RiyoushaOrgMasterEntity savedMasterEntity = riyoushaOrgMasterRepository.save(masterEntity);

        // 新規の時は作成者と組織の紐づけを行う
        RiyoushaCombineOrgEntity orgEntity = new RiyoushaCombineOrgEntity();
        orgEntity.setOrgRiyoushaCode(savedMasterEntity.getRiyoushaOrgMasterCode());
        orgEntity.setOrgName(savedMasterEntity.getAllName());
        orgEntity.setPersonCode(userDto.getUserPersonCode());
        orgEntity.setRiyoushaRole(capsuleDto.getRiyoushaRole());
        orgEntity.setPersonRiyoushaCode(capsuleDto.getRiyoushaCode());
        orgEntity.setPersonRiyoushaName(capsuleDto.getRiyoushaName());

        insertRiyoushaCombinePersonService.practice(orgEntity, userDto);

        return savedMasterEntity.getRiyoushaOrgMasterId();
    }

    /**
     * 更新処理を行う
     * 
     * @param capsuleDto 利用者運営者保存Dto
     * @return 登録後Id
     */
    private Integer practiceUpdate(final SaveRiyoushaOrgCapsuleDto capsuleDto) {

        Optional<RiyoushaOrgPropertyEntity> optionalProperty = riyoushaOrgPropertyRepository
                .findById(capsuleDto.getRiyoushaOrgDto().getRiyoushaOrgPropertyId());
        if (optionalProperty.isEmpty()) {
            throw new EmptyResultDataAccessException("利用者個人属性が取得できませんでした", 1);
        }
        RiyoushaOrgPropertyEntity srcPropertyEntity = optionalProperty.get();

        LeastUserDto userDto = capsuleDto.getUserDto();

        setTableDataHistoryUtil.practiceDelete(userDto, srcPropertyEntity);

        RiyoushaOrgPropertyEntity propertryEntity = this.createPropertyEntity(capsuleDto);
        propertryEntity.setRiyoushaOrgPropertyCode(srcPropertyEntity.getRiyoushaOrgPropertyCode());
        propertryEntity.setRiyoushaOrgPropertyId(0); // auto increment明示

        // 属性更新
        riyoushaOrgPropertyRepository.save(srcPropertyEntity);
        RiyoushaOrgPropertyEntity savedPropertryEntity = riyoushaOrgPropertyRepository.save(propertryEntity);

        Optional<RiyoushaOrgMasterEntity> optionalMaster = riyoushaOrgMasterRepository
                .findById(capsuleDto.getRiyoushaOrgDto().getRiyoushaOrgMasterId());
        RiyoushaOrgMasterEntity srcMasterEntity = optionalMaster.get();
        setTableDataHistoryUtil.practiceDelete(userDto, srcMasterEntity);

        RiyoushaOrgMasterEntity masterEntity = this.createMasterEntity(capsuleDto, savedPropertryEntity);
        masterEntity.setRiyoushaOrgMasterCode(srcMasterEntity.getRiyoushaOrgMasterCode());
        masterEntity.setRiyoushaOrgMasterId(0); // auto increment明示

        // マスタ更新
        riyoushaOrgMasterRepository.save(srcMasterEntity);
        RiyoushaOrgMasterEntity savedMasterEntity = riyoushaOrgMasterRepository.save(masterEntity);

        return savedMasterEntity.getRiyoushaOrgMasterId();
    }

    /**
     * 属性Entityを作成する
     * 
     * @param capsuleDto 利用者運営者保存Dto
     * @return 属性Entity
     */
    private RiyoushaOrgPropertyEntity createPropertyEntity(final SaveRiyoushaOrgCapsuleDto capsuleDto) {

        RiyoushaOrgPropertyEntity propertyEntity = new RiyoushaOrgPropertyEntity();

        BeanUtils.copyProperties(capsuleDto.getRiyoushaOrgDto().getInputOrgNameDto(), propertyEntity);
        BeanUtils.copyProperties(capsuleDto.getRiyoushaOrgDto().getInputAddressDto(), propertyEntity);
        BeanUtils.copyProperties(capsuleDto.getRiyoushaOrgDto().getInputAccessDto(), propertyEntity);

        setTableDataHistoryUtil.practiceInsert(capsuleDto.getUserDto(), propertyEntity);

        return propertyEntity;
    }

    /**
     * マスタEntityを作成する
     * 
     * @param capsuleDto          利用者運営者保存Dto
     * @param savedPropertyEntity 連携する運営者個人属性Entity
     * @return マスタEntity
     */
    private RiyoushaOrgMasterEntity createMasterEntity(final SaveRiyoushaOrgCapsuleDto capsuleDto,
            final RiyoushaOrgPropertyEntity savedPropertyEntity) {

        RiyoushaOrgMasterEntity masterEntity = new RiyoushaOrgMasterEntity();
        masterEntity.setRiyoushaOrgPropertyId(savedPropertyEntity.getRiyoushaOrgPropertyId());
        masterEntity.setRiyoushaOrgPropertyCode(savedPropertyEntity.getRiyoushaOrgPropertyCode());

        masterEntity.setAddressAll(capsuleDto.getRiyoushaOrgDto().getInputAddressDto().getAddressAll());
        masterEntity.setAllName(capsuleDto.getRiyoushaOrgDto().getInputOrgNameDto().getOrgName());
        masterEntity.setAllNameKana(capsuleDto.getRiyoushaOrgDto().getInputOrgNameDto().getOrgNameKana());

        String data = masterEntity.getAddressAll() + masterEntity.getAllName() + masterEntity.getAllNameKana();
        masterEntity.setSearchText(formatNaturalSearchTextUtil.practice(data));

        setTableDataHistoryUtil.practiceInsert(capsuleDto.getUserDto(), masterEntity);

        return masterEntity;
    }
}
