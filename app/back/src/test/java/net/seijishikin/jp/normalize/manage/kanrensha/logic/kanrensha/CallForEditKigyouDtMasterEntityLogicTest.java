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
 * CallForEditKigyouDtMasterEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditKigyouDtAccessEntityLogicTest.sql")
class CallForEditKigyouDtMasterEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallForEditKigyouDtMasterEntityLogic callForEditKigyouDtMasterEntityLogic;

    /** 関連者企業Dto取得Service */
    @Autowired
    private GetKanrenshaKigyouDtDtoService getKanrenshaKigyouDtDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertNull(callForEditKigyouDtMasterEntityLogic.practice(this.getDto()));

        KanrenshaKigyouDtDto kigyouDtDto00 = this.getDto();
        kigyouDtDto00.setMasterId(21);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditKigyouDtMasterEntityLogic.practice(kigyouDtDto00));

        KanrenshaKigyouDtDto kigyouDtDto01 = this.getDto();
        kigyouDtDto01.getInputAddressDto().setAddressAll("dwet");
        assertNotNull(callForEditKigyouDtMasterEntityLogic.practice(kigyouDtDto01));

        KanrenshaKigyouDtDto kigyouDtDto02 = this.getDto();
        kigyouDtDto02.getInputOrgNameDto().setOrgName("obe");
        assertNotNull(callForEditKigyouDtMasterEntityLogic.practice(kigyouDtDto02));

        KanrenshaKigyouDtDto kigyouDtDto03 = this.getDto();
        kigyouDtDto03.setKigyouDtKanrenshaCode("wpbv");
        assertNotNull(callForEditKigyouDtMasterEntityLogic.practice(kigyouDtDto03));

        KanrenshaKigyouDtDto kigyouDtDto04 = this.getDto();
        kigyouDtDto04.getOrgDelegateLeastDto().setPersonName("qfdsf");
        assertNotNull(callForEditKigyouDtMasterEntityLogic.practice(kigyouDtDto04));

        KanrenshaKigyouDtDto kigyouDtDto05 = this.getDto();
        kigyouDtDto05.setHoujinNo("1zsdf");
        assertNotNull(callForEditKigyouDtMasterEntityLogic.practice(kigyouDtDto05));

    }

    private KanrenshaKigyouDtDto getDto() {
        KanrenshaKigyouDtMasterEntity masterEntity = kanrenshaKigyouDtMasterRepository.findById(145).get();
        return getKanrenshaKigyouDtDtoService.practice(masterEntity);
    }

}
