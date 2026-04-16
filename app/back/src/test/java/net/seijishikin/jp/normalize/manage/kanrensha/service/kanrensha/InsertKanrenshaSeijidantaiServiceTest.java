package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha; // NOPMD HighNumberImports

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.InputKanrenshaPersonLeastDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SaveKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaSeijidantaiHistory45Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallSeijidantaiAccessEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallSeijidantaiAddressEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallSeijidantaiPropertyEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaSeijidantaiHistory45Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertKanrenshaSeijidantaiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertKanrenshaSeijidantaiServiceTest.sql")
class InsertKanrenshaSeijidantaiServiceTest {

    /** テスト対象 */
    @Autowired
    private InsertKanrenshaSeijidantaiService insertKanrenshaSeijidantaiService;

    /** 企業団体マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    /** 企業団体連絡先呼び出しLogic */
    @Autowired
    private CallSeijidantaiAccessEntityLogic callSeijidantaiAccessEntityLogic;

    /** 企業団体住所呼び出しLogic */
    @Autowired
    private CallSeijidantaiAddressEntityLogic callSeijidantaiAddressEntityLogic;

    /** 企業団体属性呼び出しLogic */
    @Autowired
    private CallSeijidantaiPropertyEntityLogic callSeijidantaiPropertyEntityLogic;

    /** 企業団体履歴Repository(45) */
    @Autowired
    private KanrenshaSeijidantaiHistory45Repository kanrenshaSeijidantaiHistory45Repository;

    @Test
    @Tag("TableTruncate") // NOPMD
    void test() throws Exception {

        KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto = new KanrenshaSeijidantaiDto();

        // DTOの準備
        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("ちゃらんぽらん政治団体");
        inputOrgNameDto.setOrgNameKana("ちゃらんぽらんせいじだんたい");
        kanrenshaSeijidantaiDto.setInputOrgNameDto(inputOrgNameDto);

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
        kanrenshaSeijidantaiDto.setInputAddressDto(inputAddressDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("test@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");
        kanrenshaSeijidantaiDto.setInputAccessDto(inputAccessDto);

        InputKanrenshaPersonLeastDto orgDelegateLeastDto = new InputKanrenshaPersonLeastDto();
        orgDelegateLeastDto.setPersonKanrenshaCode("P12345");
        orgDelegateLeastDto.setPersonName("代表者　太郎");
        kanrenshaSeijidantaiDto.setOrgDelegateLeastDto(orgDelegateLeastDto);

        InputKanrenshaPersonLeastDto orgAccountMgrLeastDto = new InputKanrenshaPersonLeastDto();
        orgAccountMgrLeastDto.setPersonKanrenshaCode("P98765");
        orgAccountMgrLeastDto.setPersonName("会計責任者　花子");
        kanrenshaSeijidantaiDto.setAccounrMgrLeastDto(orgAccountMgrLeastDto);

        kanrenshaSeijidantaiDto.setDantaiKbn("03");
        kanrenshaSeijidantaiDto.setPoliOrgNo("98-4321");

        SaveKanrenshaSeijidantaiCapsuleDto capsuleDto = new SaveKanrenshaSeijidantaiCapsuleDto();
        capsuleDto.setKanrenshaSeijidantaiDto(kanrenshaSeijidantaiDto);
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        Integer newId = insertKanrenshaSeijidantaiService.practice(capsuleDto);
        assertNotEquals(0, newId);

        // --- 検証 ---

        // マスタ
        List<KanrenshaSeijidantaiMasterEntity> listMaster = kanrenshaSeijidantaiMasterRepository.findAll();
        assertEquals(1, listMaster.size());

        KanrenshaSeijidantaiMasterEntity masterEntity = listMaster.get(0);
        assertEquals(inputOrgNameDto.getOrgName(), masterEntity.getKanrenshaName());
        assertEquals(inputAddressDto.getAddressAll(), masterEntity.getAllAddress());
        assertEquals(orgDelegateLeastDto.getPersonName(), masterEntity.getSeijidantaiDelegate());
        assertEquals(kanrenshaSeijidantaiDto.getPoliOrgNo(), masterEntity.getPoliOrgNo());
        assertEquals(kanrenshaSeijidantaiDto.getDantaiKbn(), masterEntity.getDantaiKbn());
        String newCode = masterEntity.getSeijidantaiKanrenshaCode();

        // 住所
        KanrenshaSeijidantaiAddressEntity addressEntity = callSeijidantaiAddressEntityLogic.practice(newCode);
        assertEquals(newCode, addressEntity.getSeijidantaiKanrenshaCode());
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
        KanrenshaSeijidantaiAccessEntity accessEntity = callSeijidantaiAccessEntityLogic.practice(newCode);
        assertEquals(newCode, accessEntity.getSeijidantaiKanrenshaCode());
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
        KanrenshaSeijidantaiPropertyEntity propertyEntity = callSeijidantaiPropertyEntityLogic.practice(newCode);
        assertEquals(orgDelegateLeastDto.getPersonKanrenshaCode(), propertyEntity.getOrgDelegateCode());
        assertEquals(inputOrgNameDto.getOrgNameKana(), propertyEntity.getOrgNameKana());
        assertEquals(orgAccountMgrLeastDto.getPersonKanrenshaCode(), propertyEntity.getAccountMgrCode());
        assertEquals(orgAccountMgrLeastDto.getPersonName(), propertyEntity.getAccountMgrName());

        // 履歴
        List<KanrenshaSeijidantaiHistory45Entity> listHistory = kanrenshaSeijidantaiHistory45Repository.findAll();
        assertEquals(1, listHistory.size());
        KanrenshaSeijidantaiHistory45Entity historyEntity = listHistory.get(0);
        assertEquals(newCode, historyEntity.getSeijidantaiKanrenshaCode());
        assertEquals(masterEntity.getKanrenshaName(), historyEntity.getAllName());
        assertEquals(masterEntity.getAllAddress(), historyEntity.getAllAddress());
        assertEquals(kanrenshaSeijidantaiDto.getOrgDelegateLeastDto().getPersonName(),
                historyEntity.getOrgDelegateName());
        assertEquals(kanrenshaSeijidantaiDto.getOrgDelegateLeastDto().getPersonKanrenshaCode(),
                historyEntity.getOrgDelegateCode());

        // TODO ユーザ自身の追加と他人の追加で処理が異なる

        fail("Not yet implemented");
    }

}
