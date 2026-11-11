package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaTableHistorySeijidantaiDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaSeijidantaiResultDto;


/**
 * SearchKanrenshaSeijidantaiHistory26Logic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchKanrenshaSeijidantaiHistory26LogicTest.sql")
class SearchKanrenshaSeijidantaiHistory26LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchKanrenshaSeijidantaiHistory26Logic searchKanrenshaSeijidantaiHistory26Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = new SearchHistoryKanrenshaCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setPageNumber(18);
        capsuleDto.setLimit(20);
        capsuleDto.setPrefCode("26");
        capsuleDto.setSearchNaturalWords("スト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaSeijidantaiHistory26Logic
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

    }

}
