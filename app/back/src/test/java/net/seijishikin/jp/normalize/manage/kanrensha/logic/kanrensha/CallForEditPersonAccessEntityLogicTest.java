package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
 * CallForEditPersonAccessEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditPersonAccessEntityLogicTest.sql")
class CallForEditPersonAccessEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallForEditPersonAccessEntityLogic callForEditPersonAccessEntityLogic;

    /** 関連者企業Dto取得Service */
    @Autowired
    private GetKanrenshaPersonDtoService getKanrenshaPersonDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaPersonMasterRepository kanrenshaPersonMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertNull(callForEditPersonAccessEntityLogic.practice(this.getDto()));

        KanrenshaPersonDto kigyouDtDto00 = this.getDto();
        kigyouDtDto00.setAccessId(21);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditPersonAccessEntityLogic.practice(kigyouDtDto00));

        KanrenshaPersonDto kigyouDtDto01 = this.getDto();
        kigyouDtDto01.getInputAccessDto().setPhon1("aaa");
        assertNotNull(callForEditPersonAccessEntityLogic.practice(kigyouDtDto01));

        KanrenshaPersonDto kigyouDtDto02 = this.getDto();
        kigyouDtDto02.getInputAccessDto().setPhon2("abc");
        assertNotNull(callForEditPersonAccessEntityLogic.practice(kigyouDtDto02));

        KanrenshaPersonDto kigyouDtDto03 = this.getDto();
        kigyouDtDto03.getInputAccessDto().setPhon3("bcd");
        assertNotNull(callForEditPersonAccessEntityLogic.practice(kigyouDtDto03));

        KanrenshaPersonDto kigyouDtDto04 = this.getDto();
        kigyouDtDto04.getInputAccessDto().setEmail("werxcv");
        assertNotNull(callForEditPersonAccessEntityLogic.practice(kigyouDtDto04));

        KanrenshaPersonDto kigyouDtDto05 = this.getDto();
        kigyouDtDto05.getInputAccessDto().setMyPortalUrl("aaa");
        assertNotNull(callForEditPersonAccessEntityLogic.practice(kigyouDtDto05));

        KanrenshaPersonDto kigyouDtDto06 = this.getDto();
        kigyouDtDto06.getInputAccessDto().setSnsServiceId(946);
        assertNotNull(callForEditPersonAccessEntityLogic.practice(kigyouDtDto06));

        KanrenshaPersonDto kigyouDtDto07 = this.getDto();
        kigyouDtDto07.getInputAccessDto().setSnsServiceCode(245);
        assertNotNull(callForEditPersonAccessEntityLogic.practice(kigyouDtDto07));

        KanrenshaPersonDto kigyouDtDto08 = this.getDto();
        kigyouDtDto08.getInputAccessDto().setSnsServiceName("aaaa");
        assertNotNull(callForEditPersonAccessEntityLogic.practice(kigyouDtDto08));

        KanrenshaPersonDto kigyouDtDto09 = this.getDto();
        kigyouDtDto09.getInputAccessDto().setSnsPortalUrl("wqzgrze");
        assertNotNull(callForEditPersonAccessEntityLogic.practice(kigyouDtDto09));

        KanrenshaPersonDto kigyouDtDto10 = this.getDto();
        kigyouDtDto10.getInputAccessDto().setSnsAccount("gzert");
        assertNotNull(callForEditPersonAccessEntityLogic.practice(kigyouDtDto10));
    }

    private KanrenshaPersonDto getDto() {
        KanrenshaPersonMasterEntity masterEntity = kanrenshaPersonMasterRepository.findById(391).get();
        return getKanrenshaPersonDtoService.practice(masterEntity);
    }

}
