package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputOrgNameDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.InputKanrenshaPersonLeastDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiAccessEntity;

/**
 * ConvertKanrenshaSeijidantaiDtoToAccessEntityLogic単体テスト
 */
class ConvertKanrenshaSeijidantaiDtoToAccessEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ConvertKanrenshaSeijidantaiDtoToAccessEntityLogic logic = new ConvertKanrenshaSeijidantaiDtoToAccessEntityLogic();

        KanrenshaSeijidantaiDto kanrenshaSeijidantaiDto = this.createSeijiDantaiDto();

        KanrenshaSeijidantaiAccessEntity accessEntity = logic.practice(kanrenshaSeijidantaiDto);

        assertEquals(kanrenshaSeijidantaiDto.getAccessId(), accessEntity.getKanrenshaSeijidantaiAccessId());
        assertEquals(kanrenshaSeijidantaiDto.getSeijidantaiKanrenshaCode(), accessEntity.getSeijidantaiKanrenshaCode());
        assertEquals(kanrenshaSeijidantaiDto.getMasterId(), accessEntity.getKanrenshaSeijidantaiId());
        assertEquals(kanrenshaSeijidantaiDto.getInputOrgNameDto().getOrgName(), accessEntity.getKanrenshaName());

        InputAccessDto inputAccessDto = kanrenshaSeijidantaiDto.getInputAccessDto();

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
