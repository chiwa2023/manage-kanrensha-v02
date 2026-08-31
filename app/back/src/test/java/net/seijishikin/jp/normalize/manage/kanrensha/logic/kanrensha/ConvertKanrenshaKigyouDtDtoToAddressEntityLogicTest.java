package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.seijishikin.jp.normalize.common_tool.dto.input.InputAccessDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputAddressDto;
import net.seijishikin.jp.normalize.common_tool.dto.input.InputOrgNameDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.HoujinShubetsuConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.InputKanrenshaPersonLeastDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtAddressEntity;

/**
 * ConvertKanrenshaKigyouDtDtoToAddressEntityLogic単体テスト
 */
class ConvertKanrenshaKigyouDtDtoToAddressEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ConvertKanrenshaKigyouDtDtoToAddressEntityLogic logic = new ConvertKanrenshaKigyouDtDtoToAddressEntityLogic();

        KanrenshaKigyouDtDto kanrenshaKigyouDtDto = this.createKigyouDtDto();

        KanrenshaKigyouDtAddressEntity addressEntity = logic.practice(kanrenshaKigyouDtDto);

        assertEquals(kanrenshaKigyouDtDto.getAddressId(), addressEntity.getKanrenshaKigyouDtAddressId());
        assertEquals(kanrenshaKigyouDtDto.getMasterId(), addressEntity.getKanrenshaKigyouDtId());
        assertEquals(kanrenshaKigyouDtDto.getKigyouDtKanrenshaCode(), addressEntity.getKigyouDtKanrenshaCode());
        assertEquals(kanrenshaKigyouDtDto.getInputOrgNameDto().getOrgName(), addressEntity.getKanrenshaName());

        InputAddressDto inputAddressDto = kanrenshaKigyouDtDto.getInputAddressDto();

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

    }

    private KanrenshaKigyouDtDto createKigyouDtDto() {

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

        // 共通Id類
        kanrenshaKigyouDtDto.setMasterId(328);
        kanrenshaKigyouDtDto.setAccessId(295);
        kanrenshaKigyouDtDto.setAddressId(44);
        kanrenshaKigyouDtDto.setPropertyId(22);
        kanrenshaKigyouDtDto.setKigyouDtKanrenshaCode("919193");

        return kanrenshaKigyouDtDto;
    }

}
