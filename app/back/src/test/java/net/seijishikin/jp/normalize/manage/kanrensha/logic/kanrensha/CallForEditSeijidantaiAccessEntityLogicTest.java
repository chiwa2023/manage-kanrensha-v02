package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaSeijidantaiDtoService;

/**
 * CallForEditSeijidantaiAccessEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditSeijidantaiAccessEntityLogicTest.sql")
class CallForEditSeijidantaiAccessEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallForEditSeijidantaiAccessEntityLogic callForEditSeijidantaiAccessEntityLogic;

    /** 関連者政治団体Dto取得Service */
    @Autowired
    private GetKanrenshaSeijidantaiDtoService getKanrenshaSeijidantaiDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertNull(callForEditSeijidantaiAccessEntityLogic.practice(this.getDto()));

        KanrenshaSeijidantaiDto kigyouDtDto00 = this.getDto();
        kigyouDtDto00.setAccessId(21);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditSeijidantaiAccessEntityLogic.practice(kigyouDtDto00));

        KanrenshaSeijidantaiDto kigyouDtDto01 = this.getDto();
        kigyouDtDto01.getInputAccessDto().setPhon1("aaa");
        assertNotNull(callForEditSeijidantaiAccessEntityLogic.practice(kigyouDtDto01));

        KanrenshaSeijidantaiDto kigyouDtDto02 = this.getDto();
        kigyouDtDto02.getInputAccessDto().setPhon2("abc");
        assertNotNull(callForEditSeijidantaiAccessEntityLogic.practice(kigyouDtDto02));

        KanrenshaSeijidantaiDto kigyouDtDto03 = this.getDto();
        kigyouDtDto03.getInputAccessDto().setPhon3("bcd");
        assertNotNull(callForEditSeijidantaiAccessEntityLogic.practice(kigyouDtDto03));

        KanrenshaSeijidantaiDto kigyouDtDto04 = this.getDto();
        kigyouDtDto04.getInputAccessDto().setEmail("werxcv");
        assertNotNull(callForEditSeijidantaiAccessEntityLogic.practice(kigyouDtDto04));

        KanrenshaSeijidantaiDto kigyouDtDto05 = this.getDto();
        kigyouDtDto05.getInputAccessDto().setMyPortalUrl("aaa");
        assertNotNull(callForEditSeijidantaiAccessEntityLogic.practice(kigyouDtDto05));

        KanrenshaSeijidantaiDto kigyouDtDto06 = this.getDto();
        kigyouDtDto06.getInputAccessDto().setSnsServiceId(946);
        assertNotNull(callForEditSeijidantaiAccessEntityLogic.practice(kigyouDtDto06));

        KanrenshaSeijidantaiDto kigyouDtDto07 = this.getDto();
        kigyouDtDto07.getInputAccessDto().setSnsServiceCode(245);
        assertNotNull(callForEditSeijidantaiAccessEntityLogic.practice(kigyouDtDto07));

        KanrenshaSeijidantaiDto kigyouDtDto08 = this.getDto();
        kigyouDtDto08.getInputAccessDto().setSnsServiceName("aaaa");
        assertNotNull(callForEditSeijidantaiAccessEntityLogic.practice(kigyouDtDto08));

        KanrenshaSeijidantaiDto kigyouDtDto09 = this.getDto();
        kigyouDtDto09.getInputAccessDto().setSnsPortalUrl("wqzgrze");
        assertNotNull(callForEditSeijidantaiAccessEntityLogic.practice(kigyouDtDto09));

        KanrenshaSeijidantaiDto kigyouDtDto10 = this.getDto();
        kigyouDtDto10.getInputAccessDto().setSnsAccount("gzert");
        assertNotNull(callForEditSeijidantaiAccessEntityLogic.practice(kigyouDtDto10));
    }

    private KanrenshaSeijidantaiDto getDto() {
        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(1013).get();
        return getKanrenshaSeijidantaiDtoService.practice(masterEntity);
    }

}
