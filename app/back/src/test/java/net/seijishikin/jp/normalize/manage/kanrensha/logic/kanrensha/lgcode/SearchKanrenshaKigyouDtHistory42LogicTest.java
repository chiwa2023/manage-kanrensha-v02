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

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaTableHistoryKigyouDtDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaKigyouDtResultDto;


/**
 * SearchKanrenshaKigyouDtHistory42Logic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchKanrenshaKigyouDtHistory42LogicTest.sql")
class SearchKanrenshaKigyouDtHistory42LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchKanrenshaKigyouDtHistory42Logic searchKanrenshaKigyouDtHistory42Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = new SearchHistoryKanrenshaCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setPageNumber(18);
        capsuleDto.setLimit(20);
        capsuleDto.setPrefCode("42");
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaKigyouDtHistory42Logic.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-894223-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-894223-4567896", dto1.getKigyouDtKanrenshaCode());
    }

}
