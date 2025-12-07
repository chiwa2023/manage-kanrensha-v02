package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SaveKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.InsertCombineUserKanrenshaLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForKigyouDtUtil;


/**
 * 関連者企業・団体を新規登録する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class InsertKanrenshaKigyouDtService {

//    /** 企業団体マスタRepository */
//    @Autowired
//    private MasterKigyouDtRepository masterKigyouDtRepository;
//
//    /** 企業団体マスタ連絡先Repository */
//    @Autowired
//    private MasterKigyouDtAccessRepository masterKigyouDtAccessRepository;
//
//    /** 企業団体マスタ住所Repository */
//    @Autowired
//    private MasterKigyouDtAddressRepository masterKigyouDtAddressRepository;
//
//    /** 企業団体マスタ基本Repository */
//    @Autowired
//    private MasterKigyouDtBaseRepository masterKigyouDtBaseRepository;
//
//    /** 企業団体マスタ属性Repository */
//    @Autowired
//    private MasterKigyouDtPropertyRepository masterKigyouDtPropertyRepository;
//
//    /** 関連者企業団体Dtoマスタ住所Entity変換Logic */
//    @Autowired
//    private ConvertKanrenshaKigyouDtDtoToMasterKigyouDtAddressEntityLogic convertKanrenshaKigyouDtDtoToMasterKigyouDtAddressEntityLogic;
//
//    /** 関連者企業団体Dtoマスタ連絡先Entity変換Logic */
//    @Autowired
//    private ConvertKanrenshaKigyouDtDtoToMasterKigyouDtAccessEntityLogic convertKanrenshaKigyouDtDtoToMasterKigyouDtAccessEntityLogic;
//
//    /** 関連者企業団体Dtoマスタ基本Entity変換Logic */
//    @Autowired
//    private ConvertKanrenshaKigyouDtDtoToMasterKigyouDtBaseEntityLogic convertKanrenshaKigyouDtDtoToMasterKigyouDtBaseEntityLogic;
//
//    /** 関連者企業団体Dtoマスタ属性Entity変換Logic */
//    @Autowired
//    private ConvertKanrenshaKigyouDtDtoToMasterKigyouDtPropertyEntityLogic convertKanrenshaKigyouDtDtoToMasterKigyouDtPropertyEntityLogic;

    /** 関連者企業団体履歴追加Service */
    @Autowired
    private InsertKanrenshaKigyouDtHistoryService insertKanrenshaKigyouDtHistoryService;

    /** ユーザ関連者紐づけLogic */
    @Autowired
    private InsertCombineUserKanrenshaLogic insertCombineUserKanrenshaLogic;

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

//        KanrenshaKigyouDtDto kanrenshaKigyouDtDto = capsuleDto.getKanrenshaKigyouDtDto();
//
//        // マスタ本体設定
//        MasterKigyouDtEntity kigyouDtEntity = new MasterKigyouDtEntity();
//        kigyouDtEntity.setKanrenshaName(kanrenshaKigyouDtDto.getInputOrgNameDto().getOrgName());
//        kigyouDtEntity.setAllAddress(kanrenshaKigyouDtDto.getInputAddressDto().getAddressAll());
//        kigyouDtEntity.setHoujinNo(kanrenshaKigyouDtDto.getHoujinNo());
//        kigyouDtEntity.setKigyouDtKanrenshaCode(createDokujiCodeForKigyouDtUtil.practice(kanrenshaKigyouDtDto.getHoujinNo()));
//        kigyouDtEntity.setKigyouDtDelegate(kanrenshaKigyouDtDto.getOrgDelegateLeastDto().getPersonName());
//        kigyouDtEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(kigyouDtEntity.getKanrenshaName()));
//
//        LeastUserDto userDto = capsuleDto.getLeastUserDto();
//        setTableDataHistoryUtil.practiceInsert(userDto, kigyouDtEntity);
//
//        // 登録
//        MasterKigyouDtEntity savedEntity = masterKigyouDtRepository.save(kigyouDtEntity);
//        String newCode = savedEntity.getKigyouDtKanrenshaCode();
//        Integer newId = savedEntity.getMasterKigyouDtId();
//
//        // 住所
//        MasterKigyouDtAddressEntity addressEntity = convertKanrenshaKigyouDtDtoToMasterKigyouDtAddressEntityLogic
//                .practice(kanrenshaKigyouDtDto);
//        addressEntity.setKigyouDtKanrenshaCode(newCode);
//        addressEntity.setKanrenshaName(kigyouDtEntity.getKanrenshaName());
//        addressEntity.setMasterKigyouDtId(newId);
//        addressEntity.setMasterKigyouDtAddressId(0); // auto_increment明示
//        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
//        masterKigyouDtAddressRepository.save(addressEntity);
//
//        // 連絡先
//        MasterKigyouDtAccessEntity accessEntity = convertKanrenshaKigyouDtDtoToMasterKigyouDtAccessEntityLogic
//                .practice(kanrenshaKigyouDtDto);
//        accessEntity.setKigyouDtKanrenshaCode(newCode);
//        accessEntity.setKanrenshaName(kigyouDtEntity.getKanrenshaName());
//        accessEntity.setMasterKigyouDtAccessId(0); // auto_increment明示
//        accessEntity.setMasterKigyouDtId(newId);
//        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
//        masterKigyouDtAccessRepository.save(accessEntity);
//
//        // 基本
//        MasterKigyouDtBaseEntity baseEntity = convertKanrenshaKigyouDtDtoToMasterKigyouDtBaseEntityLogic
//                .practice(kanrenshaKigyouDtDto);
//        baseEntity.setKigyouDtKanrenshaCode(newCode);
//        baseEntity.setKanrenshaName(kigyouDtEntity.getKanrenshaName());
//        baseEntity.setMasterKigyouDtBaseId(0); // auto_increment明示
//        baseEntity.setMasterKigyouDtId(newId);
//        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);
//        masterKigyouDtBaseRepository.save(baseEntity);
//
//        // 属性
//        MasterKigyouDtPropertyEntity propertyEntity = convertKanrenshaKigyouDtDtoToMasterKigyouDtPropertyEntityLogic
//                .practice(kanrenshaKigyouDtDto);
//        propertyEntity.setKigyouDtKanrenshaCode(newCode);
//        propertyEntity.setKanrenshaName(kigyouDtEntity.getKanrenshaName());
//        propertyEntity.setMasterKigyouDtPropertyId(0); // auto_increment明示
//        propertyEntity.setMasterKigyouDtId(newId);
//        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
//        masterKigyouDtPropertyRepository.save(propertyEntity);
//
//        // 履歴を追加
//        KanrenshaKigyouDtHistoryBaseEntity historyEntity = new KanrenshaKigyouDtHistoryBaseEntity();
//        historyEntity.setKanrenshaName(kigyouDtEntity.getKanrenshaName());
//        historyEntity.setAllAddress(kigyouDtEntity.getAllAddress());
//        historyEntity.setKigyouDtKanrenshaCode(newCode);
//
//        insertKanrenshaKigyouDtHistoryService.practice(userDto, historyEntity);
//
//        // 運営者以上が他人のデータを追加している以外の場合は操作者ユーザと登録した関連者を紐づける
//        if (kanrenshaKigyouDtDto.getIsCombineUser()) {
//            insertCombineUserKanrenshaLogic.practcie(userDto.getUserPersonCode(), KanrenshaKbnConstants.CORP, newCode,
//                    userDto);
//        }
//        return savedEntity.getMasterKigyouDtId();
        
        return null;

    }

}