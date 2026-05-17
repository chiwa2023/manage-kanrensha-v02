package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputPersonNameDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputShokugyouDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonAccessEntity;

/**
 * ConvertKanrenshaPersonDtoToAccessEntityLogic単体テスト
 */
class ConvertKanrenshaPersonDtoToAccessEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ConvertKanrenshaPersonDtoToAccessEntityLogic logic = new ConvertKanrenshaPersonDtoToAccessEntityLogic();
        KanrenshaPersonDto kanrenshaPersonDto = this.createPersonDto();

        KanrenshaPersonAccessEntity accessEntity = logic.practice(kanrenshaPersonDto);

        assertEquals(kanrenshaPersonDto.getAccessId(), accessEntity.getKanrenshaPersonAccessId());
        assertEquals(kanrenshaPersonDto.getPersonKanrenshaCode(), accessEntity.getPersonKanrenshaCode());
        assertEquals(kanrenshaPersonDto.getMasterId(), accessEntity.getKanrenshaPersonId());
        assertEquals(kanrenshaPersonDto.getInputPersonNameDto().getAllName(), accessEntity.getKanrenshaName());

        InputAccessDto inputAccessDto = kanrenshaPersonDto.getInputAccessDto();
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

    private KanrenshaPersonDto createPersonDto() {

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

        // 共通Id類
        kanrenshaPersonDto.setMasterId(328);
        kanrenshaPersonDto.setAccessId(295);
        kanrenshaPersonDto.setAddressId(44);
        kanrenshaPersonDto.setPropertyId(22);
        kanrenshaPersonDto.setPersonKanrenshaCode("919193");

        return kanrenshaPersonDto;
    }

}
