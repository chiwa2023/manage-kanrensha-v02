package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.GetKanrenshaMasterCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.GetKanrenshaMasterResultDto;

/**
 * GetKanrenshaMasterEntityService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetKanrenshaMasterEntityServiceTest.sql")
class GetKanrenshaMasterEntityServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetKanrenshaMasterEntityService getKanrenshaMasterEntityService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        GetKanrenshaMasterCapsuleDto capsuleDto0 = new GetKanrenshaMasterCapsuleDto();
        capsuleDto0.setKanrenshaRole("manager");
        assertThrows(IllegalArgumentException.class, () -> getKanrenshaMasterEntityService.practice(capsuleDto0));

        GetKanrenshaMasterCapsuleDto capsuleDto1 = new GetKanrenshaMasterCapsuleDto();
        capsuleDto1.setKanrenshaRole("kanrensha_person");
        capsuleDto1.setKanrenshaCode("DH-mkzsu-2mMW-rB8Y-Pl4zr");
        GetKanrenshaMasterResultDto resultDto1 = getKanrenshaMasterEntityService.practice(capsuleDto1);
        assertEquals(231, resultDto1.getMasterPersonEntity().getKanrenshaPersonMasterId());

        GetKanrenshaMasterCapsuleDto capsuleDto2 = new GetKanrenshaMasterCapsuleDto();
        capsuleDto2.setKanrenshaRole("kanrensha_person");
        capsuleDto2.setKanrenshaCode("ssss");
        GetKanrenshaMasterResultDto resultDto2 = getKanrenshaMasterEntityService.practice(capsuleDto2);
        assertTrue(resultDto2.getIsFailure());

        GetKanrenshaMasterCapsuleDto capsuleDto3 = new GetKanrenshaMasterCapsuleDto();
        capsuleDto3.setKanrenshaRole("kanrensha_kigyou_dt");
        capsuleDto3.setKanrenshaCode("1-2345-67-890123-TXhlkXh");
        GetKanrenshaMasterResultDto resultDto3 = getKanrenshaMasterEntityService.practice(capsuleDto3);
        assertEquals(140, resultDto3.getMasterKigyouDtEntity().getKanrenshaKigyouDtMasterId());

        GetKanrenshaMasterCapsuleDto capsuleDto4 = new GetKanrenshaMasterCapsuleDto();
        capsuleDto4.setKanrenshaRole("kanrensha_kigyou_dt");
        capsuleDto4.setKanrenshaCode("tttt");
        GetKanrenshaMasterResultDto resultDto4 = getKanrenshaMasterEntityService.practice(capsuleDto4);
        assertTrue(resultDto4.getIsFailure());

        GetKanrenshaMasterCapsuleDto capsuleDto5 = new GetKanrenshaMasterCapsuleDto();
        capsuleDto5.setKanrenshaRole("kanrensha_seijidantai");
        capsuleDto5.setKanrenshaCode("984-321V-kZNH-uJUw-Alcr1");
        GetKanrenshaMasterResultDto resultDto5 = getKanrenshaMasterEntityService.practice(capsuleDto5);
        assertEquals(323, resultDto5.getMasterSeijidantaiEntity().getKanrenshaSeijidantaiMasterId());

        GetKanrenshaMasterCapsuleDto capsuleDto6 = new GetKanrenshaMasterCapsuleDto();
        capsuleDto6.setKanrenshaRole("kanrensha_seijidantai");
        capsuleDto6.setKanrenshaCode("uuuu");
        GetKanrenshaMasterResultDto resultDto6 = getKanrenshaMasterEntityService.practice(capsuleDto6);
        assertTrue(resultDto6.getIsFailure());

    }

}
