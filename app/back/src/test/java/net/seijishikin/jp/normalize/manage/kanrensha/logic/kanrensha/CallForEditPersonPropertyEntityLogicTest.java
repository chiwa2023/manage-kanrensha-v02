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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaPersonDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaPersonMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaPersonDtoService;

/**
 * CallForEditPersonPropertyEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditPersonAccessEntityLogicTest.sql")
class CallForEditPersonPropertyEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallForEditPersonPropertyEntityLogic callForEditPersonPropertyEntityLogic;

    /** 関連者企業Dto取得Service */
    @Autowired
    private GetKanrenshaPersonDtoService getKanrenshaPersonDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertNull(callForEditPersonPropertyEntityLogic.practice(this.getDto()));

        KanrenshaPersonDto kigyouDtDto00 = this.getDto();
        kigyouDtDto00.setPropertyId(21);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditPersonPropertyEntityLogic.practice(kigyouDtDto00));

        KanrenshaPersonDto kigyouDtDto01 = this.getDto();
        kigyouDtDto01.getInputPersonNameDto().setAllName("ewteg");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto01));

        KanrenshaPersonDto kigyouDtDto02 = this.getDto();
        kigyouDtDto02.setPersonKanrenshaCode("wpbv");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto02));

        KanrenshaPersonDto kigyouDtDto03 = this.getDto();
        kigyouDtDto03.getInputPersonNameDto().setLastName("wert");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto03));

        KanrenshaPersonDto kigyouDtDto04 = this.getDto();
        kigyouDtDto04.getInputPersonNameDto().setFirstName("tyj");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto04));

        KanrenshaPersonDto kigyouDtDto05 = this.getDto();
        kigyouDtDto05.getInputPersonNameDto().setMiddleName("q4asdf");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto05));

        KanrenshaPersonDto kigyouDtDto06 = this.getDto();
        kigyouDtDto06.getInputPersonNameDto().setLastNameKana("rewtr");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto06));

        KanrenshaPersonDto kigyouDtDto07 = this.getDto();
        kigyouDtDto07.getInputPersonNameDto().setFirstNameKana("nbvrxtt");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto07));

        KanrenshaPersonDto kigyouDtDto08 = this.getDto();
        kigyouDtDto08.getInputPersonNameDto().setMiddleNameKana("axsd");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto08));

        KanrenshaPersonDto kigyouDtDto09 = this.getDto();
        kigyouDtDto09.getInputShokugyouDto().setGyoushu("acsd");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto09));

        KanrenshaPersonDto kigyouDtDto10 = this.getDto();
        kigyouDtDto10.getInputShokugyouDto().setYakushoku("nbdf");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto10));

        KanrenshaPersonDto kigyouDtDto11 = this.getDto();
        kigyouDtDto11.getInputShokugyouDto().setShokugyouUserWrite("mrdg");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto11));

        KanrenshaPersonDto kigyouDtDto12 = this.getDto();
        kigyouDtDto12.getInputShokugyouDto().setHoujinNo("vbn");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto12));

        KanrenshaPersonDto kigyouDtDto13 = this.getDto();
        kigyouDtDto13.getInputShokugyouDto().setHoujinName("wrewsd");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto13));

        KanrenshaPersonDto kigyouDtDto14 = this.getDto();
        kigyouDtDto14.getInputShokugyouDto().setHoujinAddress("EQWED");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto14));

        KanrenshaPersonDto kigyouDtDto15 = this.getDto();
        kigyouDtDto15.setIsForeign(false);
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto15));

        KanrenshaPersonDto kigyouDtDto16 = this.getDto();
        kigyouDtDto16.setMasterId(96);
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto16));
        
        KanrenshaPersonDto kigyouDtDto17 = this.getDto();
        kigyouDtDto17.getInputPersonNameDto().setAllNameKana("ssfd");
        assertNotNull(callForEditPersonPropertyEntityLogic.practice(kigyouDtDto17));
        
    }

    private KanrenshaPersonDto getDto() {
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(391).get();
        return getKanrenshaPersonDtoService.practice(masterEntity);
    }

}
