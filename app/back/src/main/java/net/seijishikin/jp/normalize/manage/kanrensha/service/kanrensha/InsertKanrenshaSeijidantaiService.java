package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.InputKanrenshaPersonLeastDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SaveKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiPropertyRepository;
//import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.InsertCombineUserKanrenshaLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForSeijidantaiUtil;

/**
 * 関連者政治団体を新規登録する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class InsertKanrenshaSeijidantaiService {

    /** 政治団体マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 政治団体マスタ連絡先Repository */
    @Autowired
    private KanrenshaSeijidantaiAccessRepository kanrenshaSeijidantaiAccessRepository;

    /** 政治団体マスタ住所Repository */
    @Autowired
    private KanrenshaSeijidantaiAddressRepository kanrenshaSeijidantaiAddressRepository;

    /** 政治団体マスタ属性Repository */
    @Autowired
    private KanrenshaSeijidantaiPropertyRepository kanrenshaSeijidantaiPropertyRepository;

    /** 関連者政治団体履歴追加Service */
    @Autowired
    private InsertKanrenshaSeijidantaiHistoryService insertKanrenshaSeijidantaiHistoryService;

//    /** ユーザ関連者紐づけLogic */
//    @Autowired
//    private InsertCombineUserKanrenshaLogic insertCombineUserKanrenshaLogic;

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード政治団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForSeijidantaiUtil createDokujiCodeForSeijidantaiUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 登録したマスタのID
     */
    @Transactional
    public Integer practice(final SaveKanrenshaSeijidantaiCapsuleDto capsuleDto) {

        KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto = capsuleDto.getKanrenshaSeijidantaiDto();

        // マスタ本体設定
        KanrenshaSeijidantaiMasterEntity seijidantaiEntity = new KanrenshaSeijidantaiMasterEntity();
        InputKanrenshaPersonLeastDto orgDelegateDto = kanrenshaSeijidantaiDto.getOrgDelegateLeastDto();
        seijidantaiEntity.setKanrenshaName(kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgName());
        seijidantaiEntity.setAllAddress(kanrenshaSeijidantaiDto.getInputAddressDto().getAddressAll());
        seijidantaiEntity.setSeijidantaiDelegate(orgDelegateDto.getPersonName());
        seijidantaiEntity.setSeijidantaiKanrenshaCode(
                createDokujiCodeForSeijidantaiUtil.practice(kanrenshaSeijidantaiDto.getPoliOrgNo()));
        seijidantaiEntity.setDantaiKbn(kanrenshaSeijidantaiDto.getDantaiKbn());
        seijidantaiEntity.setPoliOrgNo(kanrenshaSeijidantaiDto.getPoliOrgNo());
        seijidantaiEntity
                .setCompareNameText(formatNaturalSearchTextUtil.practice(seijidantaiEntity.getKanrenshaName()));

        LeastUserDto userDto = capsuleDto.getUserDto();
        setTableDataHistoryUtil.practiceInsert(userDto, seijidantaiEntity);

        // 登録
        KanrenshaSeijidantaiMasterEntity savedEntity = kanrenshaSeijidantaiMasterRepository.save(seijidantaiEntity);
        String newCode = savedEntity.getSeijidantaiKanrenshaCode();
        Integer newId = savedEntity.getKanrenshaSeijidantaiMasterId();

        // 住所
        KanrenshaSeijidantaiAddressEntity addressEntity = new KanrenshaSeijidantaiAddressEntity();
        BeanUtils.copyProperties(kanrenshaSeijidantaiDto.getInputAddressDto(), addressEntity);
        addressEntity.setSeijidantaiKanrenshaCode(newCode);
        addressEntity.setKanrenshaSeijidantaiId(newId);
        addressEntity.setKanrenshaName(seijidantaiEntity.getKanrenshaName());
        addressEntity.setKanrenshaSeijidantaiAddressId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
        kanrenshaSeijidantaiAddressRepository.save(addressEntity);

        // 連絡先
        KanrenshaSeijidantaiAccessEntity accessEntity = new KanrenshaSeijidantaiAccessEntity();
        BeanUtils.copyProperties(kanrenshaSeijidantaiDto.getInputAccessDto(), accessEntity);
        accessEntity.setSeijidantaiKanrenshaCode(newCode);
        accessEntity.setKanrenshaSeijidantaiId(newId);
        accessEntity.setKanrenshaName(seijidantaiEntity.getKanrenshaName());
        accessEntity.setKanrenshaSeijidantaiAccessId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
        kanrenshaSeijidantaiAccessRepository.save(accessEntity);

        // 属性
        KanrenshaSeijidantaiPropertyEntity propertyEntity = new KanrenshaSeijidantaiPropertyEntity();
        propertyEntity.setSeijidantaiKanrenshaCode(newCode);
        propertyEntity.setKanrenshaSeijidantaiId(newId);
        propertyEntity.setKanrenshaName(seijidantaiEntity.getKanrenshaName());
        propertyEntity.setOrgNameKana(kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgNameKana());
        propertyEntity.setOrgDelegateCode(orgDelegateDto.getPersonKanrenshaCode());

        InputKanrenshaPersonLeastDto orgAccountMgrDto = kanrenshaSeijidantaiDto.getAccounrMgrLeastDto();
        propertyEntity.setAccountMgrCode(orgAccountMgrDto.getPersonKanrenshaCode());
        propertyEntity.setAccountMgrName(orgAccountMgrDto.getPersonName());

        propertyEntity.setKanrenshaSeijidantaiPropertyId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
        kanrenshaSeijidantaiPropertyRepository.save(propertyEntity);

        // 履歴を追加
        KanrenshaSeijidantaiHistoryBaseEntity historyEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
        historyEntity.setAllName(seijidantaiEntity.getKanrenshaName());
        historyEntity.setAllAddress(seijidantaiEntity.getAllAddress());
        historyEntity.setSeijidantaiKanrenshaCode(newCode);
        historyEntity.setOrgDelegateCode(orgDelegateDto.getPersonKanrenshaCode());
        historyEntity.setOrgDelegateName(orgDelegateDto.getPersonName());

        insertKanrenshaSeijidantaiHistoryService.practice(userDto, historyEntity);

//        // 運営者以上が他人のデータを追加している以外の場合は操作者ユーザと登録した関連者を紐づける
//        if (kanrenshaSeijidantaiDto.getIsCombineUser()) {
//            insertCombineUserKanrenshaLogic.practcie(userDto.getUserPersonCode(), KanrenshaKbnConstants.POLI_ORG, newCode,
//                    userDto);
//        }

        return newId;
    }

}