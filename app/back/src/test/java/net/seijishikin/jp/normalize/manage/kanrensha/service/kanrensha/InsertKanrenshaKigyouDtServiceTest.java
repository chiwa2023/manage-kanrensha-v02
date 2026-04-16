package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD highNumberImports

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputOrgNameDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.HoujinShubetsuConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.InputKanrenshaPersonLeastDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SaveKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaKigyouDtHistory45Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallKigyouDtAccessEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallKigyouDtAddressEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallKigyouDtPropertyEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaKigyouDtHistory45Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertKanrenshaKigyouDtService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertKanrenshaKigyouDtServiceTest.sql")
class InsertKanrenshaKigyouDtServiceTest {

    /** テスト対象 */
    @Autowired
    private InsertKanrenshaKigyouDtService insertKanrenshaKigyouDtService;

    /** 企業団体マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    /** 企業団体連絡先呼び出しLogic */
    @Autowired
    private CallKigyouDtAccessEntityLogic callKigyouDtAccessEntityLogic;

    /** 企業団体住所呼び出しLogic */
    @Autowired
    private CallKigyouDtAddressEntityLogic callKigyouDtAddressEntityLogic;

    /** 企業団体属性呼び出しLogic */
    @Autowired
    private CallKigyouDtPropertyEntityLogic callKigyouDtPropertyEntityLogic;

    /** 企業団体履歴Repository(45) */
    @Autowired
    private KanrenshaKigyouDtHistory45Repository kanrenshaKigyouDtHistory45Repository;

    @Test
    @Tag("TableTruncate") // NOPMD
    void test() throws Exception {

        KanrenshaKigyouDtDto kanrenshaKigyouDtDto = new KanrenshaKigyouDtDto();

        // DTOの準備
        kanrenshaKigyouDtDto.setHoujinNo("1234567");
        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("テスト株式会社");
        inputOrgNameDto.setOrgNameKana("テストカブシキガイシャ");
        kanrenshaKigyouDtDto.setInputOrgNameDto(inputOrgNameDto);

        InputAddressDto inputAddressDto = new InputAddressDto();
        inputAddressDto.setPostalcode1("880");
        inputAddressDto.setPostalcode2("8501");
        inputAddressDto.setAddressAll("宮崎県架空市橘通東２丁目１０−１");
        inputAddressDto.setAddressPostal("宮崎県架空市橘通東");
        inputAddressDto.setAddressBlock("２丁目１０−１");
        inputAddressDto.setAddressBuilding("宮崎県庁");
        inputAddressDto.setLgCode("131016");
        inputAddressDto.setMachiazaId("324");
        inputAddressDto.setBlkId("131");
        inputAddressDto.setPrcId("249");
        inputAddressDto.setRsdtId("136");
        inputAddressDto.setRsdt2Id("978");
        inputAddressDto.setIsPostalEdit(true);
        inputAddressDto.setIsBlockEdit(true);
        inputAddressDto.setIsBuildingEdit(true);

        kanrenshaKigyouDtDto.setInputAddressDto(inputAddressDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("test@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");
        kanrenshaKigyouDtDto.setInputAccessDto(inputAccessDto);

        InputKanrenshaPersonLeastDto orgDelegateLeastDto = new InputKanrenshaPersonLeastDto();
        orgDelegateLeastDto.setPersonKanrenshaCode("P12345");
        orgDelegateLeastDto.setPersonName("代表者　太郎");
        kanrenshaKigyouDtDto.setOrgDelegateLeastDto(orgDelegateLeastDto);

        kanrenshaKigyouDtDto.setHoujinSbts(HoujinShubetsuConstants.GAIKOKU_KAISHA); // 外国籍
        kanrenshaKigyouDtDto.setIsShiten(true);

        SaveKanrenshaKigyouDtCapsuleDto capsuleDto = new SaveKanrenshaKigyouDtCapsuleDto();
        capsuleDto.setKanrenshaKigyouDtDto(kanrenshaKigyouDtDto);
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        Integer newId = insertKanrenshaKigyouDtService.practice(capsuleDto);
        assertNotEquals(0, newId);

        // --- 検証 ---

        // マスタ
        List<KanrenshaKigyouDtMasterEntity> listMaster = kanrenshaKigyouDtMasterRepository.findAll();
        assertEquals(1, listMaster.size());

        KanrenshaKigyouDtMasterEntity masterEntity = listMaster.get(0);
        assertEquals(inputOrgNameDto.getOrgName(), masterEntity.getKanrenshaName());
        assertEquals(inputAddressDto.getAddressAll(), masterEntity.getAllAddress());
        assertEquals(kanrenshaKigyouDtDto.getHoujinNo(), masterEntity.getHoujinNo());
        assertEquals(kanrenshaKigyouDtDto.getOrgDelegateLeastDto().getPersonName(), masterEntity.getKigyouDtDelegate());
        String newCode = masterEntity.getKigyouDtKanrenshaCode();

        // 住所
        KanrenshaKigyouDtAddressEntity addressEntity = callKigyouDtAddressEntityLogic.practice(newCode);
        assertEquals(newCode, addressEntity.getKigyouDtKanrenshaCode());
        assertEquals(inputAddressDto.getAddressPostal(), addressEntity.getAddressPostal());
        assertEquals(inputAddressDto.getAddressBlock(), addressEntity.getAddressBlock());
        assertEquals(inputAddressDto.getAddressBuilding(), addressEntity.getAddressBuilding());
        assertEquals(inputAddressDto.getPostalcode1(), addressEntity.getPostalcode1());
        assertEquals(inputAddressDto.getPostalcode2(), addressEntity.getPostalcode2());
        assertEquals(inputAddressDto.getLgCode(), addressEntity.getLgCode());
        assertEquals(inputAddressDto.getMachiazaId(), addressEntity.getMachiazaId());
        assertEquals(inputAddressDto.getBlkId(), addressEntity.getBlkId());
        assertEquals(inputAddressDto.getPrcId(), addressEntity.getPrcId());
        assertEquals(inputAddressDto.getRsdtId(), addressEntity.getRsdtId());
        assertEquals(inputAddressDto.getRsdt2Id(), addressEntity.getRsdt2Id());
        assertEquals(true, addressEntity.getIsPostalEdit());
        assertEquals(true, addressEntity.getIsBlockEdit());
        assertEquals(true, addressEntity.getIsBuildingEdit());
        assertEquals(false, addressEntity.getIsPostalAccept());
        assertEquals(false, addressEntity.getIsBlockAccept());
        assertEquals(false, addressEntity.getIsBuildingAccept());

        // 連絡先
        KanrenshaKigyouDtAccessEntity accessEntity = callKigyouDtAccessEntityLogic.practice(newCode);
        assertEquals(newCode, accessEntity.getKigyouDtKanrenshaCode());
        assertEquals(inputAccessDto.getPhon1(), accessEntity.getPhon1());
        assertEquals(inputAccessDto.getPhon2(), accessEntity.getPhon2());
        assertEquals(inputAccessDto.getPhon3(), accessEntity.getPhon3());
        assertEquals(inputAccessDto.getEmail(), accessEntity.getEmail());
        assertEquals(inputAccessDto.getMyPortalUrl(), accessEntity.getMyPortalUrl());
        assertEquals(inputAccessDto.getSnsServiceId(), accessEntity.getSnsServiceId());
        assertEquals(inputAccessDto.getSnsServiceCode(), accessEntity.getSnsServiceCode());
        assertEquals(inputAccessDto.getSnsServiceName(), accessEntity.getSnsServiceName());
        assertEquals(inputAccessDto.getSnsPortalUrl(), accessEntity.getSnsPortalUrl());
        assertEquals(inputAccessDto.getSnsAccount(), accessEntity.getSnsAccount());

        // 属性
        KanrenshaKigyouDtPropertyEntity propertyEntity = callKigyouDtPropertyEntityLogic.practice(newCode);
        assertEquals(true, propertyEntity.getIsShiten());
        assertEquals(orgDelegateLeastDto.getPersonKanrenshaCode(), propertyEntity.getOrgDelegateCode());
        assertEquals(inputOrgNameDto.getOrgNameKana(), propertyEntity.getOrgNameKana());
        assertEquals(kanrenshaKigyouDtDto.getHoujinSbts(), propertyEntity.getHoujinSbts());
        assertEquals(true, propertyEntity.getIsForeign());

        // PartnerCorpHistory45Entity (宮崎県のlgCodeのため)
        List<KanrenshaKigyouDtHistory45Entity> listHistory = kanrenshaKigyouDtHistory45Repository.findAll();
        assertEquals(1, listHistory.size());
        KanrenshaKigyouDtHistory45Entity historyEntity = listHistory.get(0);
        assertEquals(newCode, historyEntity.getKigyouDtKanrenshaCode());
        assertEquals(masterEntity.getKanrenshaName(), historyEntity.getAllName());
        assertEquals(masterEntity.getAllAddress(), historyEntity.getAllAddress());
        assertEquals(kanrenshaKigyouDtDto.getOrgDelegateLeastDto().getPersonName(), historyEntity.getOrgDelegateName());
        assertEquals(kanrenshaKigyouDtDto.getOrgDelegateLeastDto().getPersonKanrenshaCode(),
                historyEntity.getOrgDelegateCode());

        // TODO ユーザ自身の追加と他人の追加で処理が異なる
        
        fail("Not yet implemented");
    }

}
