package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaSeijidantaiDtoService;

/**
 * CallForEditSeijidantaiAddressEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditSeijidantaiAccessEntityLogicTest.sql")
class CallForEditSeijidantaiAddressEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallForEditSeijidantaiAddressEntityLogic callForEditSeijidantaiAddressEntityLogic;

    /** 関連者政治団体Dto取得Service */
    @Autowired
    private GetKanrenshaSeijidantaiDtoService getKanrenshaSeijidantaiDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertNull(callForEditSeijidantaiAddressEntityLogic.practice(this.getDto()));

        KanrenshaSeijidantaiDto kigyouDtDto00 = this.getDto();
        kigyouDtDto00.setAddressId(21);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto00));

        // KanrenshaKigyouDtDto kigyouDtDto01 = this.getDto();
        // kigyouDtDto01.getInputAddressDto().setAddressAll("");
        // assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto01));

        KanrenshaSeijidantaiDto kigyouDtDto02 = this.getDto();
        kigyouDtDto02.getInputAddressDto().setAddressPostal("aaaa");
        assertNotNull(callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto02));

        KanrenshaSeijidantaiDto kigyouDtDto03 = this.getDto();
        kigyouDtDto03.getInputAddressDto().setAddressBlock("bbbb");
        assertNotNull(callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto03));

        KanrenshaSeijidantaiDto kigyouDtDto04 = this.getDto();
        kigyouDtDto04.getInputAddressDto().setAddressBuilding("cccc");
        assertNotNull(callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto04));

        KanrenshaSeijidantaiDto kigyouDtDto05 = this.getDto();
        kigyouDtDto05.getInputAddressDto().setPostalcode1("zz");
        assertNotNull(callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto05));

        KanrenshaSeijidantaiDto kigyouDtDto06 = this.getDto();
        kigyouDtDto06.getInputAddressDto().setPostalcode2("yy");
        assertNotNull(callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto06));

        KanrenshaSeijidantaiDto kigyouDtDto07 = this.getDto();
        kigyouDtDto07.getInputAddressDto().setLgCode("a12");
        assertNotNull(callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto07));

        KanrenshaSeijidantaiDto kigyouDtDto08 = this.getDto();
        kigyouDtDto08.getInputAddressDto().setMachiazaId("b23");
        assertNotNull(callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto08));

        KanrenshaSeijidantaiDto kigyouDtDto09 = this.getDto();
        kigyouDtDto09.getInputAddressDto().setBlkId("c34");
        assertNotNull(callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto09));

        KanrenshaSeijidantaiDto kigyouDtDto10 = this.getDto();
        kigyouDtDto10.getInputAddressDto().setPrcId("d45");
        assertNotNull(callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto10));

        KanrenshaSeijidantaiDto kigyouDtDto11 = this.getDto();
        kigyouDtDto11.getInputAddressDto().setRsdtId("e56");
        assertNotNull(callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto11));

        KanrenshaSeijidantaiDto kigyouDtDto12 = this.getDto();
        kigyouDtDto12.getInputAddressDto().setRsdt2Id("f67");
        assertNotNull(callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto12));

        KanrenshaSeijidantaiDto kigyouDtDto13 = this.getDto();
        kigyouDtDto13.setMasterId(9471);
        assertNotNull(callForEditSeijidantaiAddressEntityLogic.practice(kigyouDtDto13));
    }

    private KanrenshaSeijidantaiDto getDto() {
        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(1013).get();
        return getKanrenshaSeijidantaiDtoService.practice(masterEntity);
    }

}
