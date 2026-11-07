package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha.combine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.GetKanrenshaCombineLeastResultDto;

/**
 * GetKigyouDtLeastDtoService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetKigyouDtLeastDtoServiceTest.sql")
class GetKigyouDtLeastDtoServiceTest {

    /** テスト対象 */
    @Autowired
    private GetKigyouDtLeastDtoService getKigyouDtLeastDtoService;

    @Test
    @Tag("TableTruncate")
    void testCond() throws Exception {

        final String code = "1-2345-67-7RJC4W-BLXyCUF";
        GetKanrenshaCombineLeastResultDto resultDto0 = getKigyouDtLeastDtoService.practice(code);
        assertEquals(code, resultDto0.getKanrenshaCode());
        assertEquals("テスト株式会社148", resultDto0.getKanrenshaName());

        final String BLANK = "";
        GetKanrenshaCombineLeastResultDto resultDto1 = getKigyouDtLeastDtoService.practice("aaa-bbb");
        assertEquals(BLANK, resultDto1.getKanrenshaCode());
        assertEquals(BLANK, resultDto1.getKanrenshaName());
    }

}
