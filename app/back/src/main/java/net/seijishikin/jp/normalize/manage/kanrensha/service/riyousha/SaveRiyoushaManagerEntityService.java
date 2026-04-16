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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SaveRiyoushaManagerCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaManagerMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaManagerMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.RiyoushaPersonPropertyRepository;

/**
 * 利用者仲間エンティティを保存するサービスクラス
 */
@Service
public class SaveRiyoushaManagerEntityService {

    /** テーブル履歴設定utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 利用者個人属性Repository */
    @Autowired
    private RiyoushaPersonPropertyRepository riyoushaPersonPropertyRepository;

    /** 利用者運営者マスタRepository */
    @Autowired
    private RiyoushaManagerMasterRepository riyoushaManagerMasterRepository;

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
    public Integer practice(final SaveRiyoushaManagerCapsuleDto capsuleDto) {

        if (0 == capsuleDto.getRiyoushaManagerDto().getRiyoushaManagerMasterId()) {
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
    private Integer practiceInsert(final SaveRiyoushaManagerCapsuleDto capsuleDto) {

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
        RiyoushaManagerMasterEntity masterEntity = this.createMasterEntity(capsuleDto, savedPropertyEntity);

        Integer codeMaster = 1;
        Optional<RiyoushaManagerMasterEntity> optionalManager = riyoushaManagerMasterRepository
                .findFirstByOrderByRiyoushaManagerMasterCodeDesc();
        if (!optionalProperty.isEmpty()) {
            codeMaster += optionalManager.get().getRiyoushaManagerMasterCode();
        }
        masterEntity.setRiyoushaManagerMasterCode(codeMaster);

        RiyoushaManagerMasterEntity savedMasterEntity = riyoushaManagerMasterRepository.save(masterEntity);

        return savedMasterEntity.getRiyoushaManagerMasterId();
    }

    /**
     * 更新処理を行う
     * 
     * @param capsuleDto 利用者運営者保存Dto
     * @return 登録後Id
     */
    private Integer practiceUpdate(final SaveRiyoushaManagerCapsuleDto capsuleDto) {

        Optional<RiyoushaPersonPropertyEntity> optionalProperty = riyoushaPersonPropertyRepository
                .findById(capsuleDto.getRiyoushaManagerDto().getRiyoushaPersonPropertyId());
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

        Optional<RiyoushaManagerMasterEntity> optionalMaster = riyoushaManagerMasterRepository
                .findById(capsuleDto.getRiyoushaManagerDto().getRiyoushaManagerMasterId());
        RiyoushaManagerMasterEntity srcMasterEntity = optionalMaster.get();
        setTableDataHistoryUtil.practiceDelete(userDto, srcMasterEntity);

        RiyoushaManagerMasterEntity masterEntity = this.createMasterEntity(capsuleDto, savedPropertryEntity);
        masterEntity.setRiyoushaManagerMasterCode(srcMasterEntity.getRiyoushaManagerMasterCode());
        masterEntity.setRiyoushaManagerMasterId(0); // auto increment明示

        // マスタ更新
        riyoushaManagerMasterRepository.save(srcMasterEntity);
        RiyoushaManagerMasterEntity savedMasterEntity = riyoushaManagerMasterRepository.save(masterEntity);

        return savedMasterEntity.getRiyoushaManagerMasterId();
    }

    /**
     * 属性Entityを作成する
     * 
     * @param capsuleDto 利用者運営者保存Dto
     * @return 属性Entity
     */
    private RiyoushaPersonPropertyEntity createPropertyEntity(final SaveRiyoushaManagerCapsuleDto capsuleDto) {

        RiyoushaPersonPropertyEntity propertyEntity = new RiyoushaPersonPropertyEntity();

        BeanUtils.copyProperties(capsuleDto.getRiyoushaManagerDto().getInputPersonNameDto(), propertyEntity);
        BeanUtils.copyProperties(capsuleDto.getRiyoushaManagerDto().getInputAddressDto(), propertyEntity);
        BeanUtils.copyProperties(capsuleDto.getRiyoushaManagerDto().getInputAccessDto(), propertyEntity);

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
    private RiyoushaManagerMasterEntity createMasterEntity(final SaveRiyoushaManagerCapsuleDto capsuleDto,
            final RiyoushaPersonPropertyEntity savedPropertyEntity) {

        RiyoushaManagerMasterEntity masterEntity = new RiyoushaManagerMasterEntity();
        masterEntity.setRiyoushaPersonPropertyId(savedPropertyEntity.getRiyoushaPersonPropertyId());
        masterEntity.setRiyoushaPersonPropertyCode(savedPropertyEntity.getRiyoushaPersonPropertyCode());

        masterEntity.setAddressAll(capsuleDto.getRiyoushaManagerDto().getInputAddressDto().getAddressAll());
        masterEntity.setAllName(capsuleDto.getRiyoushaManagerDto().getInputPersonNameDto().getAllName());
        masterEntity.setAllNameKana(capsuleDto.getRiyoushaManagerDto().getInputPersonNameDto().getAllNameKana());

        String data = masterEntity.getAddressAll() + masterEntity.getAllName() + masterEntity.getAllNameKana();
        masterEntity.setSearchText(formatNaturalSearchTextUtil.practice(data));

        setTableDataHistoryUtil.practiceInsert(capsuleDto.getUserDto(), masterEntity);

        return masterEntity;
    }

}
