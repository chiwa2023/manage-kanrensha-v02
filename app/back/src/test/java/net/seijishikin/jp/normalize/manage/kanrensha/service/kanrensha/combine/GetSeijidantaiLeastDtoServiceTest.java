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
 * GetSeijidantaiLeastDtoService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetSeijidantaiLeastDtoServiceTest.sql")
class GetSeijidantaiLeastDtoServiceTest {

    /** テスト対象 */
    @Autowired
    private GetSeijidantaiLeastDtoService getSeijidantaiLeastDtoService;

    @Test
    @Tag("TableTruncate")
    void testCond() throws Exception {

        final String code = "984-321V-kZNH-uJUw-Alcr1";
        GetKanrenshaCombineLeastResultDto resultDto0 = getSeijidantaiLeastDtoService.practice(code);
        assertEquals(code, resultDto0.getKanrenshaCode());
        assertEquals("ちゃらんぽらん政治団体1218", resultDto0.getKanrenshaName());

        final String BLANK = "";
        GetKanrenshaCombineLeastResultDto resultDto1 = getSeijidantaiLeastDtoService.practice("aaa-bbb");
        assertEquals(BLANK, resultDto1.getKanrenshaCode());
        assertEquals(BLANK, resultDto1.getKanrenshaName());
    }

}
