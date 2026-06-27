package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaKigyouDtDtoService;

/**
 * CallForEditKigyouDtAddressEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditKigyouDtAccessEntityLogicTest.sql")
class CallForEditKigyouDtAddressEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallForEditKigyouDtAddressEntityLogic callForEditKigyouDtAddressEntityLogic;

    /** 関連者企業Dto取得Service */
    @Autowired
    private GetKanrenshaKigyouDtDtoService getKanrenshaKigyouDtDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertNull(callForEditKigyouDtAddressEntityLogic.practice(this.getDto()));

        KanrenshaKigyouDtDto kigyouDtDto00 = this.getDto();
        kigyouDtDto00.setAddressId(21);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto00));

        // KanrenshaKigyouDtDto kigyouDtDto01 = this.getDto();
        // kigyouDtDto01.getInputAddressDto().setAddressAll("");
        // assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto01));

        KanrenshaKigyouDtDto kigyouDtDto02 = this.getDto();
        kigyouDtDto02.getInputAddressDto().setAddressPostal("aaaa");
        assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto02));

        KanrenshaKigyouDtDto kigyouDtDto03 = this.getDto();
        kigyouDtDto03.getInputAddressDto().setAddressBlock("bbbb");
        assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto03));

        KanrenshaKigyouDtDto kigyouDtDto04 = this.getDto();
        kigyouDtDto04.getInputAddressDto().setAddressBuilding("cccc");
        assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto04));

        KanrenshaKigyouDtDto kigyouDtDto05 = this.getDto();
        kigyouDtDto05.getInputAddressDto().setPostalcode1("zz");
        assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto05));

        KanrenshaKigyouDtDto kigyouDtDto06 = this.getDto();
        kigyouDtDto06.getInputAddressDto().setPostalcode2("yy");
        assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto06));

        KanrenshaKigyouDtDto kigyouDtDto07 = this.getDto();
        kigyouDtDto07.getInputAddressDto().setLgCode("a12");
        assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto07));

        KanrenshaKigyouDtDto kigyouDtDto08 = this.getDto();
        kigyouDtDto08.getInputAddressDto().setMachiazaId("b23");
        assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto08));

        KanrenshaKigyouDtDto kigyouDtDto09 = this.getDto();
        kigyouDtDto09.getInputAddressDto().setBlkId("c34");
        assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto09));

        KanrenshaKigyouDtDto kigyouDtDto10 = this.getDto();
        kigyouDtDto10.getInputAddressDto().setPrcId("d45");
        assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto10));

        KanrenshaKigyouDtDto kigyouDtDto11 = this.getDto();
        kigyouDtDto11.getInputAddressDto().setRsdtId("e56");
        assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto11));

        KanrenshaKigyouDtDto kigyouDtDto12 = this.getDto();
        kigyouDtDto12.getInputAddressDto().setRsdt2Id("f67");
        assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto12));

        KanrenshaKigyouDtDto kigyouDtDto13 = this.getDto();
        kigyouDtDto13.setMasterId(9471);
        assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto13));
    }

    private KanrenshaKigyouDtDto getDto() {
        KanrenshaKigyouDtMasterEntity masterEntity = kanrenshaKigyouDtMasterRepository.findById(145).get();
        return getKanrenshaKigyouDtDtoService.practice(masterEntity);
    }

}
