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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SaveRiyoushaPartnerApiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPartnerApiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPersonPropertyRepository;

/**
 * 利用者仲間エンティティを保存するサービスクラス
 */
@Service
public class SaveRiyoushaPartnerApiEntityService {

    /** テーブル履歴設定utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 利用者個人属性Repository */
    @Autowired
    private RiyoushaPersonPropertyRepository riyoushaPersonPropertyRepository;

    /** 利用者運営者マスタRepository */
    @Autowired
    private RiyoushaPartnerApiMasterRepository riyoushaPartnerApiMasterRepository;

    /** 全文検索用カラム */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 利用者仲間エンティティをDBに新規・変更保存する
     *
     * @param capsuleDto 保存用データを含むカプセルDTO
     * @return 保存した利用者仲間エンティティのテーブルID
     */
    @Transactional
    public Integer practice(final SaveRiyoushaPartnerApiCapsuleDto capsuleDto) {

        if (0 == capsuleDto.getRiyoushaPartnerApiDto().getRiyoushaPartnerApiMasterId()) {
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
    private Integer practiceInsert(final SaveRiyoushaPartnerApiCapsuleDto capsuleDto) {

        // 属性
        RiyoushaPersonPropertyEntity propertyEntity = this.createPropertyEntity(capsuleDto);
        setTableDataHistoryUtil.practiceInsert(capsuleDto.getUserDto(), propertyEntity);

        Integer codeProperty = 1;
        Optional<RiyoushaPersonPropertyEntity> optionalProperty = riyoushaPersonPropertyRepository
                .findFirstByOrderByRiyoushaPersonPropertyCodeDesc();
        if (!optionalProperty.isEmpty()) {
            codeProperty += optionalProperty.get().getRiyoushaPersonPropertyCode();
        }
        propertyEntity.setRiyoushaPersonPropertyCode(codeProperty);

        RiyoushaPersonPropertyEntity savedPropertyEntity = riyoushaPersonPropertyRepository.save(propertyEntity);

        // マスタ
        RiyoushaPartnerApiMasterEntity masterEntity = this.createMasterEntity(capsuleDto, savedPropertyEntity);

        Integer codeMaster = 1;
        Optional<RiyoushaPartnerApiMasterEntity> optionalPartnerApi = riyoushaPartnerApiMasterRepository
                .findFirstByOrderByRiyoushaPartnerApiMasterCodeDesc();
        if (!optionalProperty.isEmpty()) {
            codeMaster += optionalPartnerApi.get().getRiyoushaPartnerApiMasterCode();
        }
        masterEntity.setRiyoushaPartnerApiMasterCode(codeMaster);

        RiyoushaPartnerApiMasterEntity savedMasterEntity = riyoushaPartnerApiMasterRepository.save(masterEntity);

        return savedMasterEntity.getRiyoushaPartnerApiMasterId();
    }

    /**
     * 更新処理を行う
     * 
     * @param capsuleDto 利用者運営者保存Dto
     * @return 登録後Id
     */
    private Integer practiceUpdate(final SaveRiyoushaPartnerApiCapsuleDto capsuleDto) {

        Optional<RiyoushaPersonPropertyEntity> optionalProperty = riyoushaPersonPropertyRepository
                .findById(capsuleDto.getRiyoushaPartnerApiDto().getRiyoushaPersonPropertyId());
        if (optionalProperty.isEmpty()) {
            throw new EmptyResultDataAccessException("利用者個人属性が取得できませんでした", 1);
        }
        RiyoushaPersonPropertyEntity srcPropertyEntity = optionalProperty.get();

        LeastUserDto userDto = capsuleDto.getUserDto();

        setTableDataHistoryUtil.practiceDelete(userDto, srcPropertyEntity);

        RiyoushaPersonPropertyEntity propertryEntity = this.createPropertyEntity(capsuleDto);
        propertryEntity.setRiyoushaPersonPropertyCode(srcPropertyEntity.getRiyoushaPersonPropertyCode());
        propertryEntity.setRiyoushaPersonPropertyId(0); // auto increment明示

        // 属性更新
        riyoushaPersonPropertyRepository.save(srcPropertyEntity);
        RiyoushaPersonPropertyEntity savedPropertryEntity = riyoushaPersonPropertyRepository.save(propertryEntity);

        Optional<RiyoushaPartnerApiMasterEntity> optionalMaster = riyoushaPartnerApiMasterRepository
                .findById(capsuleDto.getRiyoushaPartnerApiDto().getRiyoushaPartnerApiMasterId());
        RiyoushaPartnerApiMasterEntity srcMasterEntity = optionalMaster.get();
        setTableDataHistoryUtil.practiceDelete(userDto, srcMasterEntity);

        RiyoushaPartnerApiMasterEntity masterEntity = this.createMasterEntity(capsuleDto, savedPropertryEntity);
        masterEntity.setRiyoushaPartnerApiMasterCode(srcMasterEntity.getRiyoushaPartnerApiMasterCode());
        masterEntity.setRiyoushaPartnerApiMasterId(0); // auto increment明示

        // マスタ更新
        riyoushaPartnerApiMasterRepository.save(srcMasterEntity);
        RiyoushaPartnerApiMasterEntity savedMasterEntity = riyoushaPartnerApiMasterRepository.save(masterEntity);

        return savedMasterEntity.getRiyoushaPartnerApiMasterId();
    }

    /**
     * 属性Entityを作成する
     * 
     * @param capsuleDto 利用者運営者保存Dto
     * @return 属性Entity
     */
    private RiyoushaPersonPropertyEntity createPropertyEntity(final SaveRiyoushaPartnerApiCapsuleDto capsuleDto) {

        RiyoushaPersonPropertyEntity propertyEntity = new RiyoushaPersonPropertyEntity();

        BeanUtils.copyProperties(capsuleDto.getRiyoushaPartnerApiDto().getInputPersonNameDto(), propertyEntity);
        BeanUtils.copyProperties(capsuleDto.getRiyoushaPartnerApiDto().getInputAddressDto(), propertyEntity);
        BeanUtils.copyProperties(capsuleDto.getRiyoushaPartnerApiDto().getInputAccessDto(), propertyEntity);

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
    private RiyoushaPartnerApiMasterEntity createMasterEntity(final SaveRiyoushaPartnerApiCapsuleDto capsuleDto,
            final RiyoushaPersonPropertyEntity savedPropertyEntity) {

        RiyoushaPartnerApiMasterEntity masterEntity = new RiyoushaPartnerApiMasterEntity();
        masterEntity.setRiyoushaPersonPropertyId(savedPropertyEntity.getRiyoushaPersonPropertyId());
        masterEntity.setRiyoushaPersonPropertyCode(savedPropertyEntity.getRiyoushaPersonPropertyCode());

        masterEntity.setAddressAll(capsuleDto.getRiyoushaPartnerApiDto().getInputAddressDto().getAddressAll());
        masterEntity.setAllName(capsuleDto.getRiyoushaPartnerApiDto().getInputPersonNameDto().getAllName());
        masterEntity.setAllNameKana(capsuleDto.getRiyoushaPartnerApiDto().getInputPersonNameDto().getAllNameKana());

        String data = masterEntity.getAddressAll() + masterEntity.getAllName() + masterEntity.getAllNameKana();
        masterEntity.setSearchText(formatNaturalSearchTextUtil.practice(data));

        setTableDataHistoryUtil.practiceInsert(capsuleDto.getUserDto(), masterEntity);

        return masterEntity;
    }

}
