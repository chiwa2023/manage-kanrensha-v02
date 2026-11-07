package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.KanrenshaCombineOrgDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.SearchKanrenshaCombineCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.combine.SearchKanrenshaCombineResultDto;

/**
 * SearchKanrenshaCombineY2020Logic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchKanrenshaCombineY2020LogicTest.sql")
class SearchKanrenshaCombineY2020LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchKanrenshaCombineY2020Logic searchKanrenshaCombineY2020Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 関連者区分指定
        SearchKanrenshaCombineCapsuleDto capsuleDto1 = this.createCapsuleDto();
        capsuleDto1.setKanrenshaKbn((short)1); // ありえない値
        SearchKanrenshaCombineResultDto resultDto1 = searchKanrenshaCombineY2020Logic.practice(capsuleDto1);
        List<KanrenshaCombineOrgDto> list1 = resultDto1.getListDto();
        assertTrue(list1.isEmpty());

        // 個人コード指定
        SearchKanrenshaCombineCapsuleDto capsuleDto2 = this.createCapsuleDto();
        capsuleDto2.setPersonCode("10");
        SearchKanrenshaCombineResultDto resultDto2 = searchKanrenshaCombineY2020Logic.practice(capsuleDto2);
        assertEquals(1, resultDto2.getAllCount());
        List<KanrenshaCombineOrgDto> list2 = resultDto2.getListDto();
        assertEquals(1, list2.size());
        KanrenshaCombineOrgDto dto2 = list2.get(0);
        assertEquals(2020, dto2.getTableYear());
        assertEquals(2, dto2.getKanrenshaCombineOrgId());

        // 団体コード指定
        SearchKanrenshaCombineCapsuleDto capsuleDto3 = this.createCapsuleDto();
        capsuleDto3.setOrgCode("20");
        SearchKanrenshaCombineResultDto resultDto3 = searchKanrenshaCombineY2020Logic.practice(capsuleDto3);
        assertEquals(1, resultDto3.getAllCount());
        List<KanrenshaCombineOrgDto> list3 = resultDto3.getListDto();
        assertEquals(1, list3.size());
        KanrenshaCombineOrgDto dto3 = list3.get(0);
        assertEquals(2020, dto3.getTableYear());
        assertEquals(3, dto3.getKanrenshaCombineOrgId());
        
        // ページング条件以外入力がない場合は最新全件
        SearchKanrenshaCombineCapsuleDto capsuleDto0 = this.createCapsuleDto();
        SearchKanrenshaCombineResultDto resultDto0 = searchKanrenshaCombineY2020Logic.practice(capsuleDto0);
        assertEquals(2, resultDto0.getAllCount());
        List<KanrenshaCombineOrgDto> list0 = resultDto0.getListDto();
        assertEquals(2, list0.size());
        
        KanrenshaCombineOrgDto dto00 = list0.get(0);
        assertEquals(2020, dto00.getTableYear());
        assertEquals(2, dto00.getKanrenshaCombineOrgId());
        KanrenshaCombineOrgDto dto01 = list0.get(1);
        assertEquals(2020, dto01.getTableYear());
        assertEquals(3, dto01.getKanrenshaCombineOrgId());
    }

    private SearchKanrenshaCombineCapsuleDto createCapsuleDto() {

        SearchKanrenshaCombineCapsuleDto capsuleDto = new SearchKanrenshaCombineCapsuleDto();
        capsuleDto.setAllCount(113);
        capsuleDto.setLimit(20);
        capsuleDto.setPageNumber(4);

        return capsuleDto;
    }

}
