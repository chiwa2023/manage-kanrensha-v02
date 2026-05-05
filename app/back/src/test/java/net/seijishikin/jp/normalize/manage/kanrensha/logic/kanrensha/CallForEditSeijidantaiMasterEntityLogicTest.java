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
 * CallForEditSeijidantaiMasterEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditSeijidantaiAccessEntityLogicTest.sql")
class CallForEditSeijidantaiMasterEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallForEditSeijidantaiMasterEntityLogic callForEditSeijidantaiMasterEntityLogic;

    /** 関連者政治団体Dto取得Service */
    @Autowired
    private GetKanrenshaSeijidantaiDtoService getKanrenshaSeijidantaiDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertNull(callForEditSeijidantaiMasterEntityLogic.practice(this.getDto()));

        KanrenshaSeijidantaiDto kigyouDtDto00 = this.getDto();
        kigyouDtDto00.setMasterId(21);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditSeijidantaiMasterEntityLogic.practice(kigyouDtDto00));

        KanrenshaSeijidantaiDto kigyouDtDto01 = this.getDto();
        kigyouDtDto01.getInputAddressDto().setAddressAll("dwet");
        assertNotNull(callForEditSeijidantaiMasterEntityLogic.practice(kigyouDtDto01));

        KanrenshaSeijidantaiDto kigyouDtDto02 = this.getDto();
        kigyouDtDto02.getInputOrgNameDto().setOrgName("obe");
        assertNotNull(callForEditSeijidantaiMasterEntityLogic.practice(kigyouDtDto02));

        KanrenshaSeijidantaiDto kigyouDtDto03 = this.getDto();
        kigyouDtDto03.setSeijidantaiKanrenshaCode("wpbv");
        assertNotNull(callForEditSeijidantaiMasterEntityLogic.practice(kigyouDtDto03));

        KanrenshaSeijidantaiDto kigyouDtDto04 = this.getDto();
        kigyouDtDto04.getOrgDelegateLeastDto().setPersonName("qfdsf");
        assertNotNull(callForEditSeijidantaiMasterEntityLogic.practice(kigyouDtDto04));

        KanrenshaSeijidantaiDto kigyouDtDto05 = this.getDto();
        kigyouDtDto05.setPoliOrgNo("1zsdf");
        assertNotNull(callForEditSeijidantaiMasterEntityLogic.practice(kigyouDtDto05));

        KanrenshaSeijidantaiDto kigyouDtDto06 = this.getDto();
        kigyouDtDto06.setDantaiKbn("95");
        assertNotNull(callForEditSeijidantaiMasterEntityLogic.practice(kigyouDtDto06));

    }

    private KanrenshaSeijidantaiDto getDto() {
        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(1013).get();
        return getKanrenshaSeijidantaiDtoService.practice(masterEntity);
    }

}
