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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaKigyouDtDtoService;

/**
 * CallForEditKigyouDtAccessEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditKigyouDtAccessEntityLogicTest.sql")
class CallForEditKigyouDtAccessEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallForEditKigyouDtAccessEntityLogic callForEditKigyouDtAccessEntityLogic;

    /** 関連者企業Dto取得Service */
    @Autowired
    private GetKanrenshaKigyouDtDtoService getKanrenshaKigyouDtDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertNull(callForEditKigyouDtAccessEntityLogic.practice(this.getDto()));

        KanrenshaKigyouDtDto kigyouDtDto00 = this.getDto();
        kigyouDtDto00.setAccessId(21);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditKigyouDtAccessEntityLogic.practice(kigyouDtDto00));

        KanrenshaKigyouDtDto kigyouDtDto01 = this.getDto();
        kigyouDtDto01.getInputAccessDto().setPhon1("aaa");
        assertNotNull(callForEditKigyouDtAccessEntityLogic.practice(kigyouDtDto01));

        KanrenshaKigyouDtDto kigyouDtDto02 = this.getDto();
        kigyouDtDto02.getInputAccessDto().setPhon2("abc");
        assertNotNull(callForEditKigyouDtAccessEntityLogic.practice(kigyouDtDto02));

        KanrenshaKigyouDtDto kigyouDtDto03 = this.getDto();
        kigyouDtDto03.getInputAccessDto().setPhon3("bcd");
        assertNotNull(callForEditKigyouDtAccessEntityLogic.practice(kigyouDtDto03));

        KanrenshaKigyouDtDto kigyouDtDto04 = this.getDto();
        kigyouDtDto04.getInputAccessDto().setEmail("werxcv");
        assertNotNull(callForEditKigyouDtAccessEntityLogic.practice(kigyouDtDto04));

        KanrenshaKigyouDtDto kigyouDtDto05 = this.getDto();
        kigyouDtDto05.getInputAccessDto().setMyPortalUrl("aaa");
        assertNotNull(callForEditKigyouDtAccessEntityLogic.practice(kigyouDtDto05));

        KanrenshaKigyouDtDto kigyouDtDto06 = this.getDto();
        kigyouDtDto06.getInputAccessDto().setSnsServiceId(946);
        assertNotNull(callForEditKigyouDtAccessEntityLogic.practice(kigyouDtDto06));

        KanrenshaKigyouDtDto kigyouDtDto07 = this.getDto();
        kigyouDtDto07.getInputAccessDto().setSnsServiceCode(245);
        assertNotNull(callForEditKigyouDtAccessEntityLogic.practice(kigyouDtDto07));

        KanrenshaKigyouDtDto kigyouDtDto08 = this.getDto();
        kigyouDtDto08.getInputAccessDto().setSnsServiceName("aaaa");
        assertNotNull(callForEditKigyouDtAccessEntityLogic.practice(kigyouDtDto08));

        KanrenshaKigyouDtDto kigyouDtDto09 = this.getDto();
        kigyouDtDto09.getInputAccessDto().setSnsPortalUrl("wqzgrze");
        assertNotNull(callForEditKigyouDtAccessEntityLogic.practice(kigyouDtDto09));

        KanrenshaKigyouDtDto kigyouDtDto10 = this.getDto();
        kigyouDtDto10.getInputAccessDto().setSnsAccount("gzert");
        assertNotNull(callForEditKigyouDtAccessEntityLogic.practice(kigyouDtDto10));

    }

    private KanrenshaKigyouDtDto getDto() {
        KanrenshaKigyouDtMasterEntity masterEntity = kanrenshaKigyouDtMasterRepository.findById(145).get();
        return getKanrenshaKigyouDtDtoService.practice(masterEntity);
    }

}
