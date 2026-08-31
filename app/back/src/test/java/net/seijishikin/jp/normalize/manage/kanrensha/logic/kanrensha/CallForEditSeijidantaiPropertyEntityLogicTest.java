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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaSeijidantaiDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaSeijidantaiMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaSeijidantaiDtoService;

/**
 * CallForEditSeijidantaiPropertyEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditSeijidantaiAccessEntityLogicTest.sql")
class CallForEditSeijidantaiPropertyEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallForEditSeijidantaiPropertyEntityLogic callForEditSeijidantaiPropertyEntityLogic;

    /** 関連者政治団体Dto取得Service */
    @Autowired
    private GetKanrenshaSeijidantaiDtoService getKanrenshaSeijidantaiDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaSeijidantaiMasterRepository kanrenshaSeijidantaiMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertNull(callForEditSeijidantaiPropertyEntityLogic.practice(this.getDto()));

        KanrenshaSeijidantaiDto kigyouDtDto00 = this.getDto();
        kigyouDtDto00.setPropertyId(21);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditSeijidantaiPropertyEntityLogic.practice(kigyouDtDto00));

        KanrenshaSeijidantaiDto kigyouDtDto02 = this.getDto();
        kigyouDtDto02.getInputOrgNameDto().setOrgName("obe");
        assertNotNull(callForEditSeijidantaiPropertyEntityLogic.practice(kigyouDtDto02));

        KanrenshaSeijidantaiDto kigyouDtDto03 = this.getDto();
        kigyouDtDto03.setSeijidantaiKanrenshaCode("wpbv");
        assertNotNull(callForEditSeijidantaiPropertyEntityLogic.practice(kigyouDtDto03));

        KanrenshaSeijidantaiDto kigyouDtDto04 = this.getDto();
        kigyouDtDto04.getInputOrgNameDto().setOrgNameKana("dsg");
        assertNotNull(callForEditSeijidantaiPropertyEntityLogic.practice(kigyouDtDto04));

        KanrenshaSeijidantaiDto kigyouDtDto05 = this.getDto();
        kigyouDtDto05.getAccounrMgrLeastDto().setPersonKanrenshaCode("wqfasdz");
        assertNotNull(callForEditSeijidantaiPropertyEntityLogic.practice(kigyouDtDto05));

        KanrenshaSeijidantaiDto kigyouDtDto06 = this.getDto();
        kigyouDtDto06.getAccounrMgrLeastDto().setPersonName("fxczvz");
        assertNotNull(callForEditSeijidantaiPropertyEntityLogic.practice(kigyouDtDto06));

        KanrenshaSeijidantaiDto kigyouDtDto07 = this.getDto();
        kigyouDtDto07.setMasterId(364);
        assertNotNull(callForEditSeijidantaiPropertyEntityLogic.practice(kigyouDtDto07));

    }

    private KanrenshaSeijidantaiDto getDto() {
        KanrenshaSeijidantaiMasterEntity masterEntity = kanrenshaSeijidantaiMasterRepository.findById(1013).get();
        return getKanrenshaSeijidantaiDtoService.practice(masterEntity);
    }

}
