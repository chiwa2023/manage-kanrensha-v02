package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SaveKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.InsertCombineUserKanrenshaLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForSeijidantaiUtil;


/**
 * 関連者政治団体を新規登録する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class InsertKanrenshaSeijidantaiService {

//    /** 政治団体マスタRepository */
//    @Autowired
//    private MasterSeijidantaiRepository masterSeijidantaiRepository;
//
//    /** 政治団体マスタ連絡先Repository */
//    @Autowired
//    private MasterSeijidantaiAccessRepository masterSeijidantaiAccessRepository;
//
//    /** 政治団体マスタ住所Repository */
//    @Autowired
//    private MasterSeijidantaiAddressRepository masterSeijidantaiAddressRepository;
//
//    /** 政治団体マスタ基本Repository */
//    @Autowired
//    private MasterSeijidantaiBaseRepository masterSeijidantaiBaseRepository;
//
//    /** 政治団体マスタ属性Repository */
//    @Autowired
//    private MasterSeijidantaiPropertyRepository masterSeijidantaiPropertyRepository;
//
//    /** 関連者政治団体Dtoマスタ住所Entity変換Logic */
//    @Autowired
//    private ConvertKanrenshaSeijidantaiDtoToMasterSeijidantaiAddressEntityLogic convertKanrenshaSeijidantaiDtoToMasterSeijidantaiAddressEntityLogic;
//
//    /** 関連者政治団体Dtoマスタ連絡先Entity変換Logic */
//    @Autowired
//    private ConvertKanrenshaSeijidantaiDtoToMasterSeijidantaiAccessEntityLogic convertKanrenshaSeijidantaiDtoToMasterSeijidantaiAccessEntityLogic;
//
//    /** 関連者政治団体Dtoマスタ基本Entity変換Logic */
//    @Autowired
//    private ConvertKanrenshaSeijidantaiDtoToMasterSeijidantaiBaseEntityLogic convertKanrenshaSeijidantaiDtoToMasterSeijidantaiBaseEntityLogic;
//
//    /** 関連者政治団体Dtoマスタ属性Entity変換Logic */
//    @Autowired
//    private ConvertKanrenshaSeijidantaiDtoToMasterSeijidantaiPropertyEntityLogic convertKanrenshaSeijidantaiDtoToMasterSeijidantaiPropertyEntityLogic;

    /** 関連者政治団体履歴追加Service */
    @Autowired
    private InsertKanrenshaSeijidantaiHistoryService insertKanrenshaSeijidantaiHistoryService;

    /** ユーザ関連者紐づけLogic */
    @Autowired
    private InsertCombineUserKanrenshaLogic insertCombineUserKanrenshaLogic;

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

//        KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto = capsuleDto.getKanrenshaSeijidantaiDto();
//
//        // マスタ本体設定
//        MasterSeijidantaiEntity seijidantaiEntity = new MasterSeijidantaiEntity();
//        seijidantaiEntity.setKanrenshaName(kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgName());
//        seijidantaiEntity.setAllAddress(kanrenshaSeijidantaiDto.getInputAddressDto().getAddressAll());
//        seijidantaiEntity.setSeijidantaiDelegate(kanrenshaSeijidantaiDto.getOrgDelegateLeastDto().getPersonName());
//        seijidantaiEntity.setSeijidantaiKanrenshaCode(createDokujiCodeForSeijidantaiUtil.practice(""));
//        seijidantaiEntity.setDantaiKbn(kanrenshaSeijidantaiDto.getDantaiKbn());
//        seijidantaiEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(seijidantaiEntity.getKanrenshaName()));
//
//        LeastUserDto userDto = capsuleDto.getLeastUserDto();
//        setTableDataHistoryUtil.practiceInsert(userDto, seijidantaiEntity);
//
//        // 登録
//        MasterSeijidantaiEntity savedEntity = masterSeijidantaiRepository.save(seijidantaiEntity);
//        String newCode = savedEntity.getSeijidantaiKanrenshaCode();
//
//        // 住所
//        MasterSeijidantaiAddressEntity addressEntity = convertKanrenshaSeijidantaiDtoToMasterSeijidantaiAddressEntityLogic
//                .practice(kanrenshaSeijidantaiDto);
//        addressEntity.setSeijidantaiKanrenshaCode(newCode);
//        addressEntity.setKanrenshaName(seijidantaiEntity.getKanrenshaName());
//        addressEntity.setMasterSeijidantaiAddressId(0); // auto_increment明示
//        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
//        masterSeijidantaiAddressRepository.save(addressEntity);
//
//        // 連絡先
//        MasterSeijidantaiAccessEntity accessEntity = convertKanrenshaSeijidantaiDtoToMasterSeijidantaiAccessEntityLogic
//                .practice(kanrenshaSeijidantaiDto);
//        accessEntity.setSeijidantaiKanrenshaCode(newCode);
//        accessEntity.setKanrenshaName(seijidantaiEntity.getKanrenshaName());
//        accessEntity.setMasterSeijidantaiAccessId(0); // auto_increment明示
//        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
//        masterSeijidantaiAccessRepository.save(accessEntity);
//
//        // 基本
//        MasterSeijidantaiBaseEntity baseEntity = convertKanrenshaSeijidantaiDtoToMasterSeijidantaiBaseEntityLogic
//                .practice(kanrenshaSeijidantaiDto);
//        baseEntity.setSeijidantaiKanrenshaCode(newCode);
//        baseEntity.setKanrenshaName(seijidantaiEntity.getKanrenshaName());
//        baseEntity.setMasterSeijidantaiBaseId(0); // auto_increment明示
//        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);
//        masterSeijidantaiBaseRepository.save(baseEntity);
//
//        // 属性
//        MasterSeijidantaiPropertyEntity propertyEntity = convertKanrenshaSeijidantaiDtoToMasterSeijidantaiPropertyEntityLogic
//                .practice(kanrenshaSeijidantaiDto);
//        propertyEntity.setSeijidantaiKanrenshaCode(newCode);
//        propertyEntity.setKanrenshaName(seijidantaiEntity.getKanrenshaName());
//        propertyEntity.setMasterSeijidantaiId(0); // auto_increment明示
//        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
//        masterSeijidantaiPropertyRepository.save(propertyEntity);
//
//        // 履歴を追加
//        KanrenshaSeijidantaiHistoryBaseEntity historyEntity = new KanrenshaSeijidantaiHistoryBaseEntity();
//        historyEntity.setKanrenshaName(seijidantaiEntity.getKanrenshaName());
//        historyEntity.setAllAddress(seijidantaiEntity.getAllAddress());
//        historyEntity.setSeijidantaiKanrenshaCode(newCode);
//
//        insertKanrenshaSeijidantaiHistoryService.practice(userDto, historyEntity);
//
//        // 運営者以上が他人のデータを追加している以外の場合は操作者ユーザと登録した関連者を紐づける
//        if (kanrenshaSeijidantaiDto.getIsCombineUser()) {
//            insertCombineUserKanrenshaLogic.practcie(userDto.getUserPersonCode(), KanrenshaKbnConstants.POLI_ORG, newCode,
//                    userDto);
//        }
//        return savedEntity.getMasterSeijidantaiId();
        
        return null;
    }

}