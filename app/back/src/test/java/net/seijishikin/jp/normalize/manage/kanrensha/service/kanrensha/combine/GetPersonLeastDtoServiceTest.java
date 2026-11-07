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
 * GetPersonLeastDtoService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetPersonLeastDtoServiceTest.sql")
class GetPersonLeastDtoServiceTest {

    /** テスト対象 */
    @Autowired
    private GetPersonLeastDtoService getPersonLeastDtoService;

    @Test
    @Tag("TableTruncate")
    void testCond() throws Exception {

        final String code = "DH-mkzsu-2mMW-rB8Y-Pl4zr";
        GetKanrenshaCombineLeastResultDto resultDto0 = getPersonLeastDtoService.practice(code);
        assertEquals(code, resultDto0.getKanrenshaCode());
        assertEquals("迂回献金　ミカエル太郎394", resultDto0.getKanrenshaName());

        final String BLANK = "";
        GetKanrenshaCombineLeastResultDto resultDto1 = getPersonLeastDtoService.practice("aaa-bbb");
        assertEquals(BLANK, resultDto1.getKanrenshaCode());
        assertEquals(BLANK, resultDto1.getKanrenshaName());
    }

}
