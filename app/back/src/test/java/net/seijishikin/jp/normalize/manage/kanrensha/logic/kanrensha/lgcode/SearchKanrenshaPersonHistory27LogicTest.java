package net.seijishikin.jp.normalize.manage.kanrensha.logic.kanrensha.lgcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaTableHistoryPersonDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchHistoryKanrenshaPersonResultDto;


/**
 * SearchKanrenshaPersonHistory27Logic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchKanrenshaPersonHistory27LogicTest.sql")
class SearchKanrenshaPersonHistory27LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchKanrenshaPersonHistory27Logic searchKanrenshaPersonHistory27Logic;

    @Test
    void test() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = new SearchHistoryKanrenshaCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setPageNumber(18);
        capsuleDto.setLimit(20);
        capsuleDto.setPrefCode("27");
        capsuleDto.setSearchNaturalWords("太郎");

        SearchHistoryKanrenshaPersonResultDto resultDto = searchKanrenshaPersonHistory27Logic.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryPersonDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryPersonDto dto0 = listHistoryDto.get(0);
        assertEquals("2-2345-67-892723-4567896", dto0.getPersonKanrenshaCode());

        KanrenshaTableHistoryPersonDto dto1 = listHistoryDto.get(1);
        assertEquals("2-2345-67-892723-4567897", dto1.getPersonKanrenshaCode());
    }

}
