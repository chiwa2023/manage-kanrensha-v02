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
 * CallForEditPersonMasterEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditPersonAccessEntityLogicTest.sql")
class CallForEditPersonMasterEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallForEditPersonMasterEntityLogic callForEditPersonMasterEntityLogic;

    /** 関連者企業Dto取得Service */
    @Autowired
    private GetKanrenshaPersonDtoService getKanrenshaPersonDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertNull(callForEditPersonMasterEntityLogic.practice(this.getDto()));

        KanrenshaPersonDto kigyouDtDto00 = this.getDto();
        kigyouDtDto00.setMasterId(21);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditPersonMasterEntityLogic.practice(kigyouDtDto00));

        KanrenshaPersonDto kigyouDtDto01 = this.getDto();
        kigyouDtDto01.getInputAddressDto().setAddressAll("dwet");
        assertNotNull(callForEditPersonMasterEntityLogic.practice(kigyouDtDto01));

        KanrenshaPersonDto kigyouDtDto02 = this.getDto();
        kigyouDtDto02.getInputPersonNameDto().setAllName("asfd");
        assertNotNull(callForEditPersonMasterEntityLogic.practice(kigyouDtDto02));

        KanrenshaPersonDto kigyouDtDto03 = this.getDto();
        kigyouDtDto03.getInputShokugyouDto().setAllShokugyou("werewt");
        assertNotNull(callForEditPersonMasterEntityLogic.practice(kigyouDtDto03));

        KanrenshaPersonDto kigyouDtDto04 = this.getDto();
        kigyouDtDto04.setPersonKanrenshaCode("nrfg");
        assertNotNull(callForEditPersonMasterEntityLogic.practice(kigyouDtDto04));
    }

    private KanrenshaPersonDto getDto() {
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(391).get();
        return getKanrenshaPersonDtoService.practice(masterEntity);
    }

}
