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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.HoujinShubetsuConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.KanrenshaKigyouDtDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaKigyouDtMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.KanrenshaKigyouDtMasterRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.GetKanrenshaKigyouDtDtoService;

/**
 * CallForEditKigyouDtPropertyEntityLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CallForEditKigyouDtAccessEntityLogicTest.sql")
class CallForEditKigyouDtPropertyEntityLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CallForEditKigyouDtPropertyEntityLogic callForEditKigyouDtPropertyEntityLogic;

    /** 関連者企業Dto取得Service */
    @Autowired
    private GetKanrenshaKigyouDtDtoService getKanrenshaKigyouDtDtoService;

    /** マスタRepository */
    @Autowired
    private KanrenshaKigyouDtMasterRepository kanrenshaKigyouDtMasterRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        assertNull(callForEditKigyouDtPropertyEntityLogic.practice(this.getDto()));

        KanrenshaKigyouDtDto kigyouDtDto00 = this.getDto();
        kigyouDtDto00.setPropertyId(21);
        assertThrows(EmptyResultDataAccessException.class,
                () -> callForEditKigyouDtPropertyEntityLogic.practice(kigyouDtDto00));

        KanrenshaKigyouDtDto kigyouDtDto01 = this.getDto();
        kigyouDtDto01.setHoujinSbts(HoujinShubetsuConstants.CHIHOU_ORG);
        assertNotNull(callForEditKigyouDtPropertyEntityLogic.practice(kigyouDtDto01));

        KanrenshaKigyouDtDto kigyouDtDto02 = this.getDto();
        kigyouDtDto02.getInputOrgNameDto().setOrgName("obe");
        assertNotNull(callForEditKigyouDtPropertyEntityLogic.practice(kigyouDtDto02));

        KanrenshaKigyouDtDto kigyouDtDto03 = this.getDto();
        kigyouDtDto03.setKigyouDtKanrenshaCode("wpbv");
        assertNotNull(callForEditKigyouDtPropertyEntityLogic.practice(kigyouDtDto03));

        KanrenshaKigyouDtDto kigyouDtDto04 = this.getDto();
        kigyouDtDto04.setIsShiten(false);
        assertNotNull(callForEditKigyouDtPropertyEntityLogic.practice(kigyouDtDto04));

        KanrenshaKigyouDtDto kigyouDtDto05 = this.getDto();
        kigyouDtDto05.getOrgDelegateLeastDto().setPersonKanrenshaCode("myf");
        assertNotNull(callForEditKigyouDtPropertyEntityLogic.practice(kigyouDtDto05));

        KanrenshaKigyouDtDto kigyouDtDto06 = this.getDto();
        kigyouDtDto06.setMasterId(342);
        assertNotNull(callForEditKigyouDtPropertyEntityLogic.practice(kigyouDtDto06));
    }

    private KanrenshaKigyouDtDto getDto() {
        KanrenshaKigyouDtMasterEntity masterEntity = kanrenshaKigyouDtMasterRepository.findById(145).get();
        return getKanrenshaKigyouDtDtoService.practice(masterEntity);
    }

}
