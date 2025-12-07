package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.InsertCombineUserKanrenshaLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForPersonUtil;


/**
 * 関連者個人を編集する
 */
@Service
public class InsertKanrenshaPersonService {

//    /** 企業団体マスタRepository */
//    @Autowired
//    private MasterPersonRepository masterPersonRepository;
//
//    /** 企業団体マスタ連絡先Repository */
//    @Autowired
//    private MasterPersonAccessRepository masterPersonAccessRepository;
//
//    /** 企業団体マスタ連絡先Repository */
//    @Autowired
//    private MasterPersonAddressRepository masterPersonAddressRepository;
//
//    /** 企業団体マスタ連絡先Repository */
//    @Autowired
//    private MasterPersonBaseRepository masterPersonBaseRepository;
//
//    /** 企業団体マスタ属性Repository */
//    @Autowired
//    private MasterPersonPropertyRepository masterPersonPropertyRepository;
//
//    /** 関連者個人Dtoマスタ住所Entity変換Logic */
//    @Autowired
//    private ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogic convertKanrenshaPersonDtoToMasterPersonAddressEntityLogic;
//
//    /** 関連者個人Dtoマスタ住所Entity変換Logic */
//    @Autowired
//    private ConvertKanrenshaPersonDtoToMasterPersonAccessEntityLogic convertKanrenshaPersonDtoToMasterPersonAccessEntityLogic;
//
//    /** 関連者個人Dtoマスタ基本Entity変換Logic */
//    @Autowired
//    private ConvertKanrenshaPersonDtoToMasterPersonBaseEntityLogic convertKanrenshaPersonDtoToMasterPersonBaseEntityLogic;
//
//    /** 関連者個人Dtoマスタ属性Entity変換Logic */
//    @Autowired
//    private ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic convertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic;

    /** 関連者個人履歴追加Service */
    @Autowired
    private InsertKanrenshaPersonHistoryService insertKanrenshaPersonHistoryService;

    /** ユーザ関連者紐づけLogic */
    @Autowired
    private InsertCombineUserKanrenshaLogic insertCombineUserKanrenshaLogic;

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード個人用発行Utility */
    @Autowired
    private CreateDokujiCodeForPersonUtil createDokujiCodeForPersonUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 処理結果
     */
    @Transactional
    public Integer practice(final SaveKanrenshaPersonCapsuleDto capsuleDto) {

//        KanrenshaPersonDto kanrenshaPersonDto = capsuleDto.getKanrenshaPersonDto();
//
//        // マスタ本体設定
//        MasterPersonEntity personEntity = new MasterPersonEntity();
//        personEntity.setAllAddress(kanrenshaPersonDto.getInputAddressDto().getAddressAll());
//        personEntity.setKanrenshaName(kanrenshaPersonDto.getInputPersonNameDto().getAllName());
//        personEntity.setPersonShokugyou(kanrenshaPersonDto.getInputShokugyouDto().getAllShokugyou());
//        personEntity.setPersonKanrenshaCode(createDokujiCodeForPersonUtil.practice(""));
//        personEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(personEntity.getKanrenshaName()));
//
//        LeastUserDto userDto = capsuleDto.getLeastUserDto();
//        setTableDataHistoryUtil.practiceInsert(userDto, personEntity);
//
//        // 登録コードが連番でないので悲観ロック不要
//        MasterPersonEntity savedEntity = masterPersonRepository.save(personEntity);
//        String newCode = savedEntity.getPersonKanrenshaCode();
//
//        // 住所
//        MasterPersonAddressEntity addressEntity = convertKanrenshaPersonDtoToMasterPersonAddressEntityLogic
//                .practice(kanrenshaPersonDto);
//        addressEntity.setPersonKanrenshaCode(newCode);
//        addressEntity.setKanrenshaName(personEntity.getKanrenshaName());
//        addressEntity.setMasterPersonAddressId(0); // auto_increment明示
//        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
//        masterPersonAddressRepository.save(addressEntity);
//
//        // 連絡先
//        MasterPersonAccessEntity accessEntity = convertKanrenshaPersonDtoToMasterPersonAccessEntityLogic
//                .practice(kanrenshaPersonDto);
//        accessEntity.setPersonKanrenshaCode(newCode);
//        accessEntity.setKanrenshaName(personEntity.getKanrenshaName());
//        accessEntity.setMasterPersonAccessId(0); // auto_increment明示
//        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
//        masterPersonAccessRepository.save(accessEntity);
//
//        // 基本
//        MasterPersonBaseEntity baseEntity = convertKanrenshaPersonDtoToMasterPersonBaseEntityLogic
//                .practice(kanrenshaPersonDto);
//        baseEntity.setPersonKanrenshaCode(newCode);
//        baseEntity.setKanrenshaName(personEntity.getKanrenshaName());
//        baseEntity.setMasterPersonBaseId(0); // auto_increment明示
//        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);
//        masterPersonBaseRepository.save(baseEntity);
//
//        // 属性
//        MasterPersonPropertyEntity propertyEntity = convertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic
//                .practice(kanrenshaPersonDto);
//        propertyEntity.setPersonKanrenshaCode(newCode);
//        propertyEntity.setKanrenshaName(personEntity.getKanrenshaName());
//        propertyEntity.setMasterPersonPropertyId(0); // auto_increment明示
//        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
//        masterPersonPropertyRepository.save(propertyEntity);
//
//        // 履歴を追加
//        KanrenshaPersonHistoryBaseEntity historyEntity = new KanrenshaPersonHistoryBaseEntity();
//        historyEntity.setKanrenshaName(personEntity.getKanrenshaName());
//        historyEntity.setAllAddress(personEntity.getAllAddress());
//        historyEntity.setPersonShokugyou(personEntity.getPersonShokugyou());
//        historyEntity.setPersonKanrenshaCode(newCode);
//
//        insertKanrenshaPersonHistoryService.practice(userDto, historyEntity);
//
//        // 運営者以上が他人のデータを追加しない場合は操作者ユーザと登録した関連者を紐づける
//        if (kanrenshaPersonDto.getIsCombineUser()) {
//            insertCombineUserKanrenshaLogic.practcie(userDto.getUserPersonCode(), KanrenshaKbnConstants.PERSON, newCode,
//                    userDto);
//        }
//
//        return savedEntity.getMasterPersonId();
        
        
        return null;

    }

}
