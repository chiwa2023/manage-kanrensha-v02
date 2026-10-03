package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeSearchCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.MoveKanrenshaCodeSearchResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaCodeMoveEntity;

/**
 * MoveCodeKanrenshaSearchService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("MoveCodeKanrenshaSearchServiceTest.sql")
class MoveCodeKanrenshaSearchServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private MoveCodeKanrenshaSearchService moveCodeKanrenshaSearchService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 期間条件でヒットしない
        MoveKanrenshaCodeSearchCapsuleDto capsuleDto0 = this.getSearchCondition();
        capsuleDto0.setStartDate(LocalDate.of(2000, 1, 1));
        capsuleDto0.setEndDate(LocalDate.of(2000, 1, 2));

        MoveKanrenshaCodeSearchResultDto resultDto0 = moveCodeKanrenshaSearchService.practice(capsuleDto0);
        assertEquals(0, resultDto0.getAllCount());

        MoveKanrenshaCodeSearchCapsuleDto capsuleDto1 = this.getSearchCondition();
        MoveKanrenshaCodeSearchResultDto resultDto1 = moveCodeKanrenshaSearchService.practice(capsuleDto1);
        assertEquals(4, resultDto1.getAllCount());
        assertEquals(0, resultDto1.getPageNumber());
        List<KanrenshaCodeMoveEntity> list1 = resultDto1.getListEntity();
        assertEquals(4, list1.size());
        assertEquals(244, list1.get(0).getKanrenshaCodeMoveId());
        assertEquals(245, list1.get(1).getKanrenshaCodeMoveId());
        assertEquals(246, list1.get(2).getKanrenshaCodeMoveId());
        assertEquals(247, list1.get(3).getKanrenshaCodeMoveId());

        // 却下だけを抽出
        MoveKanrenshaCodeSearchCapsuleDto capsuleDto2 = this.getSearchCondition();
        capsuleDto2.setIsPromoteSearch(false);
        capsuleDto2.setIsAcceptSearch(false);
        capsuleDto2.setIsResearchSearch(false);

        MoveKanrenshaCodeSearchResultDto resultDto2 = moveCodeKanrenshaSearchService.practice(capsuleDto2);
        assertEquals(1, resultDto2.getAllCount());
        assertEquals(0, resultDto2.getPageNumber());
        List<KanrenshaCodeMoveEntity> list2 = resultDto2.getListEntity();
        assertEquals(1, list2.size());
        assertEquals(245, list2.get(0).getKanrenshaCodeMoveId());
    }

    private MoveKanrenshaCodeSearchCapsuleDto getSearchCondition() {

        MoveKanrenshaCodeSearchCapsuleDto capsuleDto = new MoveKanrenshaCodeSearchCapsuleDto();
        capsuleDto.setLimit(20);

        capsuleDto.setAllCount(114);
        capsuleDto.setPageNumber(7);

        capsuleDto.setStartDate(LocalDate.of(2026, 8, 24));
        capsuleDto.setEndDate(LocalDate.of(2026, 9, 24));
        capsuleDto.setIsPromoteSearch(true);
        capsuleDto.setIsRejectSearch(true);
        capsuleDto.setIsAcceptSearch(true);
        capsuleDto.setIsResearchSearch(true);
        return capsuleDto;

    }

}
