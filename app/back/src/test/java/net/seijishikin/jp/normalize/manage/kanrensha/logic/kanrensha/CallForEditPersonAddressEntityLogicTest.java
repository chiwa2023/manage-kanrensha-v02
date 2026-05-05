package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaPersonDtoService;

/**
 * CallForEditPersonAddressEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditPersonAccessEntityLogicTest.sql")
class CallForEditPersonAddressEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallForEditPersonAddressEntityLogic callForEditPersonAddressEntityLogic;

    /** 関連者企業Dto取得Service */
    @Autowired
    private GetKanrenshaPersonDtoService getKanrenshaPersonDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertNull(callForEditPersonAddressEntityLogic.practice(this.getDto()));

        KanrenshaPersonDto kigyouDtDto00 = this.getDto();
        kigyouDtDto00.setAddressId(21);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditPersonAddressEntityLogic.practice(kigyouDtDto00));

        // KanrenshaKigyouDtDto kigyouDtDto01 = this.getDto();
        // kigyouDtDto01.getInputAddressDto().setAddressAll("");
        // assertNotNull(callForEditKigyouDtAddressEntityLogic.practice(kigyouDtDto01));

        KanrenshaPersonDto kigyouDtDto02 = this.getDto();
        kigyouDtDto02.getInputAddressDto().setAddressPostal("aaaa");
        assertNotNull(callForEditPersonAddressEntityLogic.practice(kigyouDtDto02));

        KanrenshaPersonDto kigyouDtDto03 = this.getDto();
        kigyouDtDto03.getInputAddressDto().setAddressBlock("bbbb");
        assertNotNull(callForEditPersonAddressEntityLogic.practice(kigyouDtDto03));

        KanrenshaPersonDto kigyouDtDto04 = this.getDto();
        kigyouDtDto04.getInputAddressDto().setAddressBuilding("cccc");
        assertNotNull(callForEditPersonAddressEntityLogic.practice(kigyouDtDto04));

        KanrenshaPersonDto kigyouDtDto05 = this.getDto();
        kigyouDtDto05.getInputAddressDto().setPostalcode1("zz");
        assertNotNull(callForEditPersonAddressEntityLogic.practice(kigyouDtDto05));

        KanrenshaPersonDto kigyouDtDto06 = this.getDto();
        kigyouDtDto06.getInputAddressDto().setPostalcode2("yy");
        assertNotNull(callForEditPersonAddressEntityLogic.practice(kigyouDtDto06));

        KanrenshaPersonDto kigyouDtDto07 = this.getDto();
        kigyouDtDto07.getInputAddressDto().setLgCode("a12");
        assertNotNull(callForEditPersonAddressEntityLogic.practice(kigyouDtDto07));

        KanrenshaPersonDto kigyouDtDto08 = this.getDto();
        kigyouDtDto08.getInputAddressDto().setMachiazaId("b23");
        assertNotNull(callForEditPersonAddressEntityLogic.practice(kigyouDtDto08));

        KanrenshaPersonDto kigyouDtDto09 = this.getDto();
        kigyouDtDto09.getInputAddressDto().setBlkId("c34");
        assertNotNull(callForEditPersonAddressEntityLogic.practice(kigyouDtDto09));

        KanrenshaPersonDto kigyouDtDto10 = this.getDto();
        kigyouDtDto10.getInputAddressDto().setPrcId("d45");
        assertNotNull(callForEditPersonAddressEntityLogic.practice(kigyouDtDto10));

        KanrenshaPersonDto kigyouDtDto11 = this.getDto();
        kigyouDtDto11.getInputAddressDto().setRsdtId("e56");
        assertNotNull(callForEditPersonAddressEntityLogic.practice(kigyouDtDto11));

        KanrenshaPersonDto kigyouDtDto12 = this.getDto();
        kigyouDtDto12.getInputAddressDto().setRsdt2Id("f67");
        assertNotNull(callForEditPersonAddressEntityLogic.practice(kigyouDtDto12));

        KanrenshaPersonDto kigyouDtDto13 = this.getDto();
        kigyouDtDto13.setMasterId(9471);
        assertNotNull(callForEditPersonAddressEntityLogic.practice(kigyouDtDto13));
    }

    private KanrenshaPersonDto getDto() {
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(391).get();
        return getKanrenshaPersonDtoService.practice(masterEntity);
    }
}
