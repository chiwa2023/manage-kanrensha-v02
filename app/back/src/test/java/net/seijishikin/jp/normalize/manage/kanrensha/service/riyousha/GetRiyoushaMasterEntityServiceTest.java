package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetRiyoushaMasterCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.GetRiyoushaMasterResultDto;

/**
 * GetRiyoushaMasterEntityService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetRiyoushaMasterEntityServiceTest.sql")
class GetRiyoushaMasterEntityServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetRiyoushaMasterEntityService getRiyoushaMasterEntityService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        GetRiyoushaMasterCapsuleDto capsuleDto0 = new GetRiyoushaMasterCapsuleDto();
        capsuleDto0.setRiyoushaRole("kigyou_dt");
        assertThrows(IllegalArgumentException.class, () -> getRiyoushaMasterEntityService.practice(capsuleDto0));

        GetRiyoushaMasterCapsuleDto capsuleDto1 = new GetRiyoushaMasterCapsuleDto();
        capsuleDto1.setRiyoushaRole("manager");
        capsuleDto1.setRiyoushaCode(12);
        GetRiyoushaMasterResultDto resultDto1 = getRiyoushaMasterEntityService.practice(capsuleDto1);
        assertEquals(103, resultDto1.getManagerMasterEntity().getRiyoushaManagerMasterId());

        GetRiyoushaMasterCapsuleDto capsuleDto2 = new GetRiyoushaMasterCapsuleDto();
        capsuleDto2.setRiyoushaRole("manager");
        capsuleDto2.setRiyoushaCode(99);
        GetRiyoushaMasterResultDto resultDto2 = getRiyoushaMasterEntityService.practice(capsuleDto2);
        assertTrue(resultDto2.getIsFailure());

        GetRiyoushaMasterCapsuleDto capsuleDto3 = new GetRiyoushaMasterCapsuleDto();
        capsuleDto3.setRiyoushaRole("partner_api");
        capsuleDto3.setRiyoushaCode(22);
        GetRiyoushaMasterResultDto resultDto3 = getRiyoushaMasterEntityService.practice(capsuleDto3);
        assertEquals(203, resultDto3.getPartnerApiMasterEntity().getRiyoushaPartnerApiMasterId());

        GetRiyoushaMasterCapsuleDto capsuleDto4 = new GetRiyoushaMasterCapsuleDto();
        capsuleDto4.setRiyoushaRole("partner_api");
        capsuleDto4.setRiyoushaCode(100);
        GetRiyoushaMasterResultDto resultDto4 = getRiyoushaMasterEntityService.practice(capsuleDto4);
        assertTrue(resultDto4.getIsFailure());
    }

}
