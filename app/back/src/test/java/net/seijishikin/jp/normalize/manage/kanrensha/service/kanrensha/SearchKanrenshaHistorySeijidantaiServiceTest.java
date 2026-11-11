package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.GetPrefectureLgCodeService;

/**
 * SearchKanrenshaHistorySeijidantaiService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchKanrenshaHistorySeijidantaiServiceTest.sql")
class SearchKanrenshaHistorySeijidantaiServiceTest { // NOPMD TooManyMethods
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchKanrenshaHistorySeijidantaiService searchKanrenshaHistorySeijidantaiService;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testUndefined() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode("a24"); // 県コードでない
        capsuleDto.setSearchNaturalWords("ト政治"); // NOPMD

        assertThrows(IllegalArgumentException.class,
                () -> searchKanrenshaHistorySeijidantaiService.practice(capsuleDto));
    }

    @Test
    @Tag("TableTruncate")
    void test01() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_01_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode()); // NOPMD

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode()); // NOPMD

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_01_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test02() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_02_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_02_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test03() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_03_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_03_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test04() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_04_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_04_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test05() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_05_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_05_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test06() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_06_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_06_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test07() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_07_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_07_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test08() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_08_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_08_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test09() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_09_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_09_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test10() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_10_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_10_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test11() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_11_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_11_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test12() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_12_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_12_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test13() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_13_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_13_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test14() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_14_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_14_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test15() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_15_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_15_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test16() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_16_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_16_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test17() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_17_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_17_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test18() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_18_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_18_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test19() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_19_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_19_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test20() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_20_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_20_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test21() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_21_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_21_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test22() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_22_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_22_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test23() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_23_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_23_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test24() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_24_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_24_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test25() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_25_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_25_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test26() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_26_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_26_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test27() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_27_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_27_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test28() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_28_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_28_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test29() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_29_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_29_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test30() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_30_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_30_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test31() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_31_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_31_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test32() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_32_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_32_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test33() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_33_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_33_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test34() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_34_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_34_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test35() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_35_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_35_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test36() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_36_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_36_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test37() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_37_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_37_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test38() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_38_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_38_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test39() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_39_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_39_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test40() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_40_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_40_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test41() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_41_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_41_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test42() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_42_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_42_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test43() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_43_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_43_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test44() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_44_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_44_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test45() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_45_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_45_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test46() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_46_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_46_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test47() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_47_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

        assertTrue(dto0.getAllAddress().startsWith(GetPrefectureLgCodeService.PREF_47_LABEL));
    }

    @Test
    @Tag("TableTruncate")
    void test99() {

        SearchHistoryKanrenshaCapsuleDto capsuleDto = this.createCapsuleDto();
        capsuleDto.setPrefCode(GetPrefectureLgCodeService.PREF_99_CODE);
        capsuleDto.setSearchNaturalWords("ト政治");

        SearchHistoryKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaHistorySeijidantaiService
                .practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(2, resultDto.getListHistoryDto().size());

        List<KanrenshaTableHistorySeijidantaiDto> listHistoryDto = resultDto.getListHistoryDto();

        KanrenshaTableHistorySeijidantaiDto dto0 = listHistoryDto.get(0);
        assertEquals("3-1310-12-345678-1234563", dto0.getSeijidantaiKanrenshaCode());

        KanrenshaTableHistorySeijidantaiDto dto1 = listHistoryDto.get(1);
        assertEquals("3-1310-12-345678-1234564", dto1.getSeijidantaiKanrenshaCode());

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
