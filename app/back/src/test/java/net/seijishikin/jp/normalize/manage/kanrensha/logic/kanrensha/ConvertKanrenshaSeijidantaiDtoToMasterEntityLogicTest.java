package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputOrgNameDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.InputKanrenshaPersonLeastDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;

/**
 * ConvertKanrenshaSeijidantaiDtoToMasterEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertKanrenshaSeijidantaiDtoToMasterEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ConvertKanrenshaSeijidantaiDtoToMasterEntityLogic convertKanrenshaSeijidantaiDtoToMasterEntityLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto = this.createSeijiDantaiDto();

        KanrenshaSeijidantaiMasterEntity masterEntity = convertKanrenshaSeijidantaiDtoToMasterEntityLogic
                .practice(kanrenshaSeijidantaiDto);

        assertEquals(kanrenshaSeijidantaiDto.getMasterId(), masterEntity.getKanrenshaSeijidantaiMasterId());
        assertEquals(kanrenshaSeijidantaiDto.getSeijidantaiKanrenshaCode(), masterEntity.getSeijidantaiKanrenshaCode());
        assertEquals(kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgName(), masterEntity.getKanrenshaName());

        assertEquals(kanrenshaSeijidantaiDto.getInputAddressDto().getAddressAll(), masterEntity.getAllAddress());
        assertEquals(kanrenshaSeijidantaiDto.getOrgDelegateLeastDto().getPersonName(),
                masterEntity.getSeijidantaiDelegate());
        assertEquals(kanrenshaSeijidantaiDto.getPoliOrgNo(), masterEntity.getPoliOrgNo());
        assertEquals(kanrenshaSeijidantaiDto.getDantaiKbn(), masterEntity.getDantaiKbn());
        assertEquals("ちゃらんぽらん政治団体q", masterEntity.getCompareNameText());

    }

    private KanrenshaSeijidantaiDto createSeijiDantaiDto() {

        KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto = new KanrenshaSeijidantaiDto();

        // DTOの準備
        InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();
        inputOrgNameDto.setOrgName("ちゃらんぽらん政治団体Ｑ");
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

        // 共通Id類
        kanrenshaSeijidantaiDto.setMasterId(328);
        kanrenshaSeijidantaiDto.setAccessId(295);
        kanrenshaSeijidantaiDto.setAddressId(44);
        kanrenshaSeijidantaiDto.setPropertyId(22);
        kanrenshaSeijidantaiDto.setSeijidantaiKanrenshaCode("919193");

        return kanrenshaSeijidantaiDto;
    }

}
