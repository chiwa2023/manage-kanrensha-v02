package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputOrgNameDto;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.HoujinShubetsuConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.InputKanrenshaPersonLeastDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
//import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
//import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
//import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SaveKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
//import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.InsertCombineUserKanrenshaLogic;
//import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForKigyouDtUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtPropertyRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForKigyouDtUtil;

/**
 * 関連者企業・団体を新規登録する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class InsertKanrenshaKigyouDtService {

    /** 企業団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private KanrenshaKigyouDtAccessRepository kanrenshaKigyouDtAccessRepository;

    /** 企業団体マスタ住所Repository */
    @Autowired
    private KanrenshaKigyouDtAddressRepository kanrenshaKigyouDtAddressRepository;

    /** 企業団体マスタ属性Repository */
    @Autowired
    private KanrenshaKigyouDtPropertyRepository kanrenshaKigyouDtPropertyRepository;

    /** 関連者企業団体履歴追加Service */
    @Autowired
    private InsertKanrenshaKigyouDtHistoryService insertKanrenshaKigyouDtHistoryService;

//    /** ユーザ関連者紐づけLogic */
//    @Autowired
//    private InsertCombineUserKanrenshaLogic insertCombineUserKanrenshaLogic;

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード企業団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForKigyouDtUtil createDokujiCodeForKigyouDtUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 登録したマスタのID
     */
    @Transactional
    public Integer practice(final SaveKanrenshaKigyouDtCapsuleDto capsuleDto) {

        KanrenshaKigyouDtDto kanrenshaKigyouDtDto = capsuleDto.getKanrenshaKigyouDtDto();

        // マスタ本体設定
        KanrenshaKigyouDtMasterEntity kigyouDtEntity = new KanrenshaKigyouDtMasterEntity();
        InputOrgNameDto inputOrgNameDto = kanrenshaKigyouDtDto.getInputOrgNameDto();
        kigyouDtEntity.setKanrenshaName(inputOrgNameDto.getOrgName());
        kigyouDtEntity.setAllAddress(kanrenshaKigyouDtDto.getInputAddressDto().getAddressAll());
        kigyouDtEntity.setHoujinNo(kanrenshaKigyouDtDto.getHoujinNo());
        kigyouDtEntity
                .setKigyouDtKanrenshaCode(createDokujiCodeForKigyouDtUtil.practice(kanrenshaKigyouDtDto.getHoujinNo()));
        kigyouDtEntity.setKigyouDtDelegate(kanrenshaKigyouDtDto.getOrgDelegateLeastDto().getPersonName());
        kigyouDtEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(kigyouDtEntity.getKanrenshaName()));

        LeastUserDto userDto = capsuleDto.getUserDto();
        setTableDataHistoryUtil.practiceInsert(userDto, kigyouDtEntity);

        // 登録
        KanrenshaKigyouDtMasterEntity savedEntity = kanrenshaKigyouDtMasterRepository.save(kigyouDtEntity);
        final String newCode = savedEntity.getKigyouDtKanrenshaCode();
        final Integer newId = savedEntity.getKanrenshaKigyouDtMasterId();

        // 住所
        KanrenshaKigyouDtAddressEntity addressEntity = new KanrenshaKigyouDtAddressEntity();
        BeanUtils.copyProperties(kanrenshaKigyouDtDto.getInputAddressDto(), addressEntity);

        addressEntity.setKigyouDtKanrenshaCode(newCode);
        addressEntity.setKanrenshaName(kigyouDtEntity.getKanrenshaName());
        addressEntity.setKanrenshaKigyouDtId(newId);
        addressEntity.setKanrenshaKigyouDtAddressId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
        kanrenshaKigyouDtAddressRepository.save(addressEntity);

        // 連絡先
        KanrenshaKigyouDtAccessEntity accessEntity = new KanrenshaKigyouDtAccessEntity();
        BeanUtils.copyProperties(kanrenshaKigyouDtDto.getInputAccessDto(), accessEntity);

        accessEntity.setKigyouDtKanrenshaCode(newCode);
        accessEntity.setKanrenshaName(kigyouDtEntity.getKanrenshaName());
        accessEntity.setKanrenshaKigyouDtId(newId);
        accessEntity.setKanrenshaKigyouDtAccessId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
        kanrenshaKigyouDtAccessRepository.save(accessEntity);

        // 属性
        KanrenshaKigyouDtPropertyEntity propertyEntity = new KanrenshaKigyouDtPropertyEntity();
        propertyEntity.setKigyouDtKanrenshaCode(newCode);
        propertyEntity.setKanrenshaName(kigyouDtEntity.getKanrenshaName());
        propertyEntity.setOrgNameKana(inputOrgNameDto.getOrgNameKana());
        propertyEntity.setIsShiten(kanrenshaKigyouDtDto.getIsShiten());
        propertyEntity.setHoujinSbts(kanrenshaKigyouDtDto.getHoujinSbts());
        propertyEntity.setOrgDelegateCode(kanrenshaKigyouDtDto.getOrgDelegateLeastDto().getPersonKanrenshaCode());
        propertyEntity.setIsForeign(HoujinShubetsuConstants.GAIKOKU_KAISHA.equals(propertyEntity.getHoujinSbts()));

        propertyEntity.setKanrenshaKigyouDtId(newId);
        propertyEntity.setKanrenshaKigyouDtPropertyId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
        kanrenshaKigyouDtPropertyRepository.save(propertyEntity);

        // 履歴を追加
        KanrenshaKigyouDtHistoryBaseEntity historyEntity = new KanrenshaKigyouDtHistoryBaseEntity();
        historyEntity.setAllName(kigyouDtEntity.getKanrenshaName());
        historyEntity.setAllAddress(kigyouDtEntity.getAllAddress());
        InputKanrenshaPersonLeastDto delegateDto = kanrenshaKigyouDtDto.getOrgDelegateLeastDto();
        historyEntity.setOrgDelegateCode(delegateDto.getPersonKanrenshaCode());
        historyEntity.setOrgDelegateName(delegateDto.getPersonName());
        historyEntity.setKigyouDtKanrenshaCode(newCode);

        insertKanrenshaKigyouDtHistoryService.practice(userDto, historyEntity);

//        // 運営者以上が他人のデータを追加していない場合は、操作者ユーザと登録した関連者を紐づける
//        if (kanrenshaKigyouDtDto.getIsCombineUser()) {
//            insertCombineUserKanrenshaLogic.practcie(userDto.getUserPersonCode(), KanrenshaKbnConstants.CORP, newCode,
//                    userDto);
//        }
//        return savedEntity.getMasterKigyouDtId();

        return newId;

    }

}