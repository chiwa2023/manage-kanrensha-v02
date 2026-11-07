package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2024;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.KanrenshaCombineOrgDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.SearchKanrenshaCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.SearchKanrenshaCombineResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearSearchKanrenshaCombineService;

/**
 * SwitchYearSearchKanrenshaCombineService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearSearchKanrenshaCombineY2024ServiceTest.sql")
class SwitchYearSearchKanrenshaCombineY2024ServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearSearchKanrenshaCombineService switchYearSearchKanrenshaCombineService;

    @Test
    @Tag("TableTruncate")
    void test2024() throws Exception {

        // 2024年が取得される
        SearchKanrenshaCombineCapsuleDto capsuleDto0 = new SearchKanrenshaCombineCapsuleDto();
        capsuleDto0.setTableYear(2024);
        capsuleDto0.setAllCount(149);
        capsuleDto0.setLimit(20);
        capsuleDto0.setPageNumber(3);

        SearchKanrenshaCombineResultDto resultDto0 = switchYearSearchKanrenshaCombineService.practice(capsuleDto0);
        assertEquals(2, resultDto0.getAllCount());
        List<KanrenshaCombineOrgDto> list0 = resultDto0.getListDto();
        assertEquals(2, list0.size());

        KanrenshaCombineOrgDto dto00 = list0.get(0);
        assertEquals(2024, dto00.getTableYear());
        assertEquals(2, dto00.getKanrenshaCombineOrgId());
        KanrenshaCombineOrgDto dto01 = list0.get(1);
        assertEquals(2024, dto01.getTableYear());
        assertEquals(3, dto01.getKanrenshaCombineOrgId());
    }

}
