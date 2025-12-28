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
import net.seijishikin.jp.normalize.common_tool.dto.input.InputPersonNameDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputShokugyouDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAddressEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonPropertyEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.lgcode.KanrenshaPersonHistory45Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallPersonAccessEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallPersonAddressEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.CallPersonPropertyEntityLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.lgcode.KanrenshaPersonHistory45Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertKanrenshaPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertKanrenshaPersonServiceTest.sql")
class InsertKanrenshaPersonServiceTest {

    /** テスト対象 */
    @Autowired
    private InsertKanrenshaPersonService insertKanrenshaPersonService;

    /** 関連者個人マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    /** 企業団体連絡先呼び出しLogic */
    @Autowired
    private CallPersonAccessEntityLogic callPersonAccessEntityLogic;

    /** 企業団体住所呼び出しLogic */
    @Autowired
    private CallPersonAddressEntityLogic callPersonAddressEntityLogic;

    /** 企業団体属性呼び出しLogic */
    @Autowired
    private CallPersonPropertyEntityLogic callPersonPropertyEntityLogic;

    /** 個人ん履歴Repository(45) */
    @Autowired
    private KanrenshaPersonHistory45Repository kanrenshaPersonHistory45Repository;

    @Test
    @Tag("TableTruncate") // NOPMD
    void test() {

        final KanrenshaPersonDto kanrenshaPersonDto = new KanrenshaPersonDto();

        InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();
        inputPersonNameDto.setAllName("迂回献金　ミカエル太郎");
        inputPersonNameDto.setAllNameKana("うかいけんきん　みかえるたろう");
        inputPersonNameDto.setLastName("太郎");
        inputPersonNameDto.setFirstName("迂回献金");
        inputPersonNameDto.setMiddleName("ミカエル");
        inputPersonNameDto.setLastNameKana("たろう");
        inputPersonNameDto.setFirstNameKana("うかいけんきん");
        inputPersonNameDto.setMiddleNameKana("みかえる");
        kanrenshaPersonDto.setInputPersonNameDto(inputPersonNameDto);

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

        kanrenshaPersonDto.setInputAddressDto(inputAddressDto);

        InputAccessDto inputAccessDto = new InputAccessDto();
        inputAccessDto.setPhon1("0985");
        inputAccessDto.setPhon2("26");
        inputAccessDto.setPhon3("7132");
        inputAccessDto.setEmail("test@example.com");
        inputAccessDto.setMyPortalUrl("https://my-portal/index.html");
        inputAccessDto.setSnsServiceName("弱小SNS");
        inputAccessDto.setSnsPortalUrl("https://jyakusho-sns/");
        inputAccessDto.setSnsAccount("@taro123456");
        kanrenshaPersonDto.setInputAccessDto(inputAccessDto);

        InputShokugyouDto inputShokugyouDto = new InputShokugyouDto();
        inputShokugyouDto.setAllShokugyou("素浪人");
        inputShokugyouDto.setGyoushu("農林業");
        inputShokugyouDto.setYakushoku("部長");
        inputShokugyouDto.setShokugyouUserWrite("農作業");
        inputShokugyouDto.setHoujinNo("1-2345");
        inputShokugyouDto.setHoujinAddress("山梨県実在市湖畔町");
        inputShokugyouDto.setHoujinName("ほったらかし農園");

        kanrenshaPersonDto.setInputShokugyouDto(inputShokugyouDto);

        kanrenshaPersonDto.setIsForeign(true);

        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setKanrenshaPersonDto(kanrenshaPersonDto);
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        Integer newId = insertKanrenshaPersonService.practice(capsuleDto);
        assertNotEquals(0, newId);

        // --- 検証 ---

        // マスタ
        List<KanrenshaPersonMasterEntity> listMaster = kanrenshaPersonMasterRepository.findAll();
        assertEquals(1, listMaster.size());
        KanrenshaPersonMasterEntity masterEntity = listMaster.get(0);
        assertEquals(inputPersonNameDto.getAllName(), masterEntity.getKanrenshaName());
        assertEquals(inputAddressDto.getAddressAll(), masterEntity.getAllAddress());
        assertEquals(inputShokugyouDto.getAllShokugyou(), masterEntity.getPersonShokugyou());
        String newCode = masterEntity.getPersonKanrenshaCode();

        // 住所
        KanrenshaPersonAddressEntity addressEntity = callPersonAddressEntityLogic.practice(newCode);
        assertEquals(newCode, addressEntity.getPersonKanrenshaCode());
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
        KanrenshaPersonAccessEntity accessEntity = callPersonAccessEntityLogic.practice(newCode);
        assertEquals(newCode, accessEntity.getPersonKanrenshaCode());
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
        KanrenshaPersonPropertyEntity propertyEntity = callPersonPropertyEntityLogic.practice(newCode);
        assertEquals(newCode, propertyEntity.getPersonKanrenshaCode());
        assertEquals(inputPersonNameDto.getLastName(), propertyEntity.getLastName());
        assertEquals(inputPersonNameDto.getFirstName(), propertyEntity.getFirstName());
        assertEquals(inputPersonNameDto.getMiddleName(), propertyEntity.getMiddleName());
        assertEquals(inputPersonNameDto.getLastNameKana(), propertyEntity.getLastNameKana());
        assertEquals(inputPersonNameDto.getFirstNameKana(), propertyEntity.getFirstNameKana());
        assertEquals(inputPersonNameDto.getMiddleNameKana(), propertyEntity.getMiddleNameKana());
        assertEquals(inputShokugyouDto.getGyoushu(), propertyEntity.getGyoushu());
        assertEquals(inputShokugyouDto.getYakushoku(), propertyEntity.getYakushoku());
        assertEquals(inputShokugyouDto.getShokugyouUserWrite(), propertyEntity.getShokugyouUserWrite());
        assertEquals(inputShokugyouDto.getHoujinNo(), propertyEntity.getKigyouDtNo());
        assertEquals(inputShokugyouDto.getHoujinName(), propertyEntity.getKigyouDtName());
        assertEquals(inputShokugyouDto.getHoujinAddress(), propertyEntity.getKigyouDtAddress());
        assertEquals(true, propertyEntity.getIsShokyouEdit());
        assertEquals(false, propertyEntity.getIsShokyouAccept());
        assertEquals(kanrenshaPersonDto.getIsForeign(), propertyEntity.getIsForeign());

        // 履歴
        // PartnerCorpHistory45Entity (宮崎県のlgCodeのため)
        List<KanrenshaPersonHistory45Entity> listHistory = kanrenshaPersonHistory45Repository.findAll();
        assertEquals(1, listHistory.size());
        KanrenshaPersonHistory45Entity historyEntity = listHistory.get(0);
        assertEquals(newCode, historyEntity.getPersonKanrenshaCode());
        assertEquals(masterEntity.getKanrenshaName(), historyEntity.getAllName());
        assertEquals(masterEntity.getAllAddress(), historyEntity.getAllAddress());
        assertEquals(masterEntity.getPersonShokugyou(), historyEntity.getPersonShokugyou());

        // TODO ユーザ自身の追加と他人の追加で処理が異なる

        fail("Not yet implemented");
    }

}
