package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * SearchKanrenshaHistoryKigyouDtService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchKanrenshaHistoryKigyouDtServiceTest.sql")
class SearchKanrenshaHistoryKigyouDtServiceTest { //NOPMD TooManyMethods
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchKanrenshaHistoryKigyouDtService searchKanrenshaHistoryKigyouDtService;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testUndefined() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode("a24"); // 県コードでない
        capsuleDto.setSearchNaturalWords("り企");

        assertThrows(IllegalArgumentException.class, () -> searchKanrenshaHistoryKigyouDtService.practice(capsuleDto));
    }

    @Test
    @Tag("TableTruncate")
    void test01() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode("01");
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-890123-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-890123-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_01_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test02() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_02_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-890223-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-890223-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_02_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test03() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_03_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-890323-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-890323-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_03_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test04() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_04_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-890423-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-890423-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_04_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test05() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_05_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-890523-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-890523-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_05_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test06() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_06_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-890623-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-890623-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_06_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test07() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_07_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-890723-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-890723-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_07_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test08() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_08_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-890823-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-890823-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_08_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test09() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_09_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-890923-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-890923-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_09_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test10() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_10_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-891023-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-891023-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_10_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test11() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_11_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-891123-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-891123-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_11_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test12() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_12_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-891223-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-891223-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_12_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test13() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_13_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-891323-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-891323-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_13_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test14() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_14_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-891423-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-891423-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_14_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test15() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_15_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-891523-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-891523-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_15_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test16() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_16_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-891623-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-891623-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_16_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test17() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_17_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-891723-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-891723-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_17_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test18() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_18_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-891823-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-891823-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_18_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test19() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_19_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-891923-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-891923-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_19_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test20() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_20_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-892023-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-892023-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_20_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test21() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_21_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-892123-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-892123-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_21_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test22() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_22_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-892223-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-892223-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_22_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test23() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_23_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-892323-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-892323-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_23_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test24() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_24_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-892423-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-892423-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_24_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test25() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_25_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-892523-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-892523-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_25_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test26() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_26_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-892623-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-892623-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_26_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test27() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_27_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-892723-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-892723-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_27_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test28() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_28_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-892823-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-892823-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_28_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test29() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_29_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-892923-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-892923-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_29_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test30() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_30_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-893023-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-893023-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_30_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test31() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_31_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-893123-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-893123-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_31_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test32() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_32_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-893223-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-893223-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_32_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test33() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_33_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-893323-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-893323-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_33_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test34() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_34_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-893423-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-893423-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_34_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test35() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_35_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-893523-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-893523-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_35_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test36() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_36_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-893623-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-893623-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_36_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test37() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_37_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-893723-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-893723-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_37_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test38() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_38_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-893823-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-893823-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_38_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test39() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_39_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-893923-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-893923-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_39_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test40() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_40_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-894023-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-894023-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_40_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test41() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_41_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-894123-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-894123-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_41_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test42() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_42_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-894223-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-894223-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_42_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test43() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_43_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-894323-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-894323-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_43_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test44() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_44_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-894423-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-894423-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_44_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test45() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_45_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-894523-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-894523-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_45_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test46() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_46_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-894623-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-894623-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_46_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test47() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_47_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-894723-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-894723-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_47_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test99() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_99_CODE);
        capsuleDto.setSearchNaturalWords("り企");

        SearchHistoryKanrenshaKigyouDtResultDto resultDto = searchKanrenshaHistoryKigyouDtService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistoryKigyouDtDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistoryKigyouDtDto dto0 = listHistoryDto.get(0);
        assertEquals("1-2345-67-899923-4567895", dto0.getKigyouDtKanrenshaCode());

        KanrenshaTableHistoryKigyouDtDto dto1 = listHistoryDto.get(1);
        assertEquals("1-2345-67-899923-4567896", dto1.getKigyouDtKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_99_LABEL));
    }

    private SearchHistoryKanrenshaCapsuleDto createCapsuleDto() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = new SearchHistoryKanrenshaCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setPageNumber(18);
        capsuleDto.setLimit(20);

        return capsuleDto;
    }
}
