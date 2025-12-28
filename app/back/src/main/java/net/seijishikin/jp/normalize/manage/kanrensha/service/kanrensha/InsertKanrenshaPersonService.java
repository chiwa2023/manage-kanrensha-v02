package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputShokugyouDto;
import net.seijishikin.jp.normalize.common_tool.utils.FormatNaturalSearchTextUtil;
import net.seijishikin.jp.normalize.common_tool.utils.SetTableDataHistoryUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAccessRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonAddressRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonPropertyRepository;
//import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.InsertCombineUserKanrenshaLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateDokujiCodeForPersonUtil;

/**
 * 関連者個人を編集する
 */
@Service
public class InsertKanrenshaPersonService {

    /** 企業団体マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private KanrenshaPersonAccessRepository kanrenshaPersonAccessRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private KanrenshaPersonAddressRepository kanrenshaPersonAddressRepository;

    /** 企業団体マスタ属性Repository */
    @Autowired
    private KanrenshaPersonPropertyRepository kanrenshaPersonPropertyRepository;

    /** 関連者個人履歴追加Service */
    @Autowired
    private InsertKanrenshaPersonHistoryService insertKanrenshaPersonHistoryService;

//    /** ユーザ関連者紐づけLogic */
//    @Autowired
//    private InsertCombineUserKanrenshaLogic insertCombineUserKanrenshaLogic;

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

        KanrenshaPersonDto kanrenshaPersonDto = capsuleDto.getKanrenshaPersonDto();

        // マスタ本体設定
        KanrenshaPersonMasterEntity personEntity = new KanrenshaPersonMasterEntity();
        personEntity.setAllAddress(kanrenshaPersonDto.getInputAddressDto().getAddressAll());
        personEntity.setKanrenshaName(kanrenshaPersonDto.getInputPersonNameDto().getAllName());
        personEntity.setPersonShokugyou(kanrenshaPersonDto.getInputShokugyouDto().getAllShokugyou());
        personEntity.setPersonKanrenshaCode(createDokujiCodeForPersonUtil.practice(""));
        personEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(personEntity.getKanrenshaName()));

        LeastUserDto userDto = capsuleDto.getUserDto();
        setTableDataHistoryUtil.practiceInsert(userDto, personEntity);

        // 登録コードが連番でないので悲観ロック不要
        KanrenshaPersonMasterEntity savedEntity = kanrenshaPersonMasterRepository.save(personEntity);
        final String newCode = savedEntity.getPersonKanrenshaCode();
        final Integer newId = savedEntity.getKanrenshaPersonMasterId();

        // 住所
        KanrenshaPersonAddressEntity addressEntity = new KanrenshaPersonAddressEntity();
        BeanUtils.copyProperties(kanrenshaPersonDto.getInputAddressDto(), addressEntity);
        addressEntity.setPersonKanrenshaCode(newCode);
        addressEntity.setKanrenshaPersonId(newId);
        addressEntity.setKanrenshaName(personEntity.getKanrenshaName());
        addressEntity.setKanrenshaPersonAddressId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
        kanrenshaPersonAddressRepository.save(addressEntity);

        // 連絡先
        KanrenshaPersonAccessEntity accessEntity = new KanrenshaPersonAccessEntity();
        BeanUtils.copyProperties(kanrenshaPersonDto.getInputAccessDto(), accessEntity);
        accessEntity.setPersonKanrenshaCode(newCode);
        accessEntity.setKanrenshaPersonId(newId); 
        accessEntity.setKanrenshaName(personEntity.getKanrenshaName());
        accessEntity.setKanrenshaPersonAccessId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
        kanrenshaPersonAccessRepository.save(accessEntity);

        // 属性
        KanrenshaPersonPropertyEntity propertyEntity = new KanrenshaPersonPropertyEntity();
        BeanUtils.copyProperties(kanrenshaPersonDto.getInputPersonNameDto(), propertyEntity);
        InputShokugyouDto inputShokugyouDto = kanrenshaPersonDto.getInputShokugyouDto();
        BeanUtils.copyProperties(inputShokugyouDto, propertyEntity);
        propertyEntity.setPersonKanrenshaCode(newCode);
        propertyEntity.setKanrenshaPersonId(newId); 
        propertyEntity.setKanrenshaName(personEntity.getKanrenshaName());
        propertyEntity.setKigyouDtNo(inputShokugyouDto.getHoujinNo());
        propertyEntity.setKigyouDtName(inputShokugyouDto.getHoujinName());
        propertyEntity.setKigyouDtAddress(inputShokugyouDto.getHoujinAddress());
        // ユーザが入力した職業が空欄でないときは自動入力機能が使用されていない
        propertyEntity.setIsShokyouEdit(
                !DtoEntityInitialValueInterface.INIT_STRING.equals(inputShokugyouDto.getShokugyouUserWrite()));
        propertyEntity.setIsForeign(kanrenshaPersonDto.getIsForeign());
        
        propertyEntity.setKanrenshaPersonPropertyId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
        kanrenshaPersonPropertyRepository.save(propertyEntity);

        // 履歴を追加
        KanrenshaPersonHistoryBaseEntity historyEntity = new KanrenshaPersonHistoryBaseEntity();
        historyEntity.setAllName(personEntity.getKanrenshaName());
        historyEntity.setAllAddress(personEntity.getAllAddress());
        historyEntity.setPersonShokugyou(personEntity.getPersonShokugyou());
        historyEntity.setPersonKanrenshaCode(newCode);

        insertKanrenshaPersonHistoryService.practice(userDto, historyEntity);
//
//        // 運営者以上が他人のデータを追加しない場合は操作者ユーザと登録した関連者を紐づける
//        if (kanrenshaPersonDto.getIsCombineUser()) {
//            insertCombineUserKanrenshaLogic.practcie(userDto.getUserPersonCode(), KanrenshaKbnConstants.PERSON, newCode,
//                    userDto);
//        }
//
        return newId;

    }

}
