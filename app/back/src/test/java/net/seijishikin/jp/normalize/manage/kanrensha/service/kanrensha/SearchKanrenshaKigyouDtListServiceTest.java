package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.KanrenshaKigyouDtMasterDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchKanrenshaKigyouDtCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.kanrensha.SearchKanrenshaKigyouDtResultDto;

/**
 * SearchKanrenshaKigyouDtListService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchKanrenshaKigyouDtListServiceTest.sql")
class SearchKanrenshaKigyouDtListServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchKanrenshaKigyouDtListService searchKanrenshaKigyouDtListService;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testCond() throws Exception {

        SearchKanrenshaKigyouDtCapsuleDto capsuleDto = new SearchKanrenshaKigyouDtCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setLimit(20);
        capsuleDto.setPageNumber(4);
        capsuleDto.setHoujinNo("1234");
        capsuleDto.setName("株式会社テ");
        capsuleDto.setAddress("東京都テスト区");
        capsuleDto.setDelegate("代表テ");

        SearchKanrenshaKigyouDtResultDto resultDto = searchKanrenshaKigyouDtListService.practice(capsuleDto);

        assertEquals(1, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber());

        List<KanrenshaKigyouDtMasterDto> list = resultDto.getListMasterKigyouDt();
        assertEquals(1, list.size());

        KanrenshaKigyouDtMasterDto entity = list.get(0);
        assertEquals(140, entity.getKanrenshaKigyouDtMasterId());
    }

    @Test
    @Tag("TableTruncate") // NOPMD
    void testAll() throws Exception {

        SearchKanrenshaKigyouDtCapsuleDto capsuleDto = new SearchKanrenshaKigyouDtCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setLimit(20);
        capsuleDto.setPageNumber(4);
        // 検索条件なし
        // capsuleDto.setHoujinNo("1234");
        // capsuleDto.setName("株式会社テ");
        // capsuleDto.setAddress("東京都テスト区");
        // capsuleDto.setDelegate("代表テ");

        SearchKanrenshaKigyouDtResultDto resultDto = searchKanrenshaKigyouDtListService.practice(capsuleDto);

        assertEquals(5, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber());

        List<KanrenshaKigyouDtMasterDto> list = resultDto.getListMasterKigyouDt();
        assertEquals(5, list.size());

        KanrenshaKigyouDtMasterDto entity0 = list.get(0);
        assertEquals(140, entity0.getKanrenshaKigyouDtMasterId());

        KanrenshaKigyouDtMasterDto entity1 = list.get(1);
        assertEquals(142, entity1.getKanrenshaKigyouDtMasterId());

        KanrenshaKigyouDtMasterDto entity2 = list.get(2);
        assertEquals(143, entity2.getKanrenshaKigyouDtMasterId());

        KanrenshaKigyouDtMasterDto entity3 = list.get(3);
        assertEquals(144, entity3.getKanrenshaKigyouDtMasterId());

        KanrenshaKigyouDtMasterDto entity4 = list.get(4);
        assertEquals(145, entity4.getKanrenshaKigyouDtMasterId());
        
    }

}
