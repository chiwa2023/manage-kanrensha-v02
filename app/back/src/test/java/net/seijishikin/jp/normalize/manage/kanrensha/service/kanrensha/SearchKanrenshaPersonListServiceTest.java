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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaPersonCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaPersonResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaPersonMasterEntity;

/**
 * SearchKanrenshaPersonListService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchKanrenshaPersonListServiceTest.sql")
class SearchKanrenshaPersonListServiceTest {
    // CHECKSTYLE:OFF MagicNUmber

    /** テスト対象 */
    @Autowired
    private SearchKanrenshaPersonListService searchKanrenshaPersonListService;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testCond() throws Exception {

        SearchKanrenshaPersonCapsuleDto capsuleDto = new SearchKanrenshaPersonCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setLimit(20);
        capsuleDto.setPageNumber(4);
        capsuleDto.setName("迂回献");
        capsuleDto.setAddress("宮崎県架空");
        capsuleDto.setShokugyou("素浪");

        SearchKanrenshaPersonResultDto resultDto = searchKanrenshaPersonListService.practice(capsuleDto);

        assertEquals(1, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber());

        List<KanrenshaPersonMasterEntity> list = resultDto.getListMasterPerson();
        assertEquals(1, list.size());

        KanrenshaPersonMasterEntity entity = list.get(0);
        assertEquals(231, entity.getKanrenshaPersonMasterId());
    }

    @Test
    @Tag("TableTruncate") // NOPMD
    void testAll() throws Exception {

        SearchKanrenshaPersonCapsuleDto capsuleDto = new SearchKanrenshaPersonCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setLimit(20);
        capsuleDto.setPageNumber(4);
        // 検索条件なし
        // capsuleDto.setName("迂回献");
        // capsuleDto.setAddress("宮崎県架空");
        // capsuleDto.setShokugyou("素浪");

        SearchKanrenshaPersonResultDto resultDto = searchKanrenshaPersonListService.practice(capsuleDto);

        assertEquals(4, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber());

        List<KanrenshaPersonMasterEntity> list = resultDto.getListMasterPerson();
        assertEquals(4, list.size());

        KanrenshaPersonMasterEntity entity0 = list.get(0);
        assertEquals(231, entity0.getKanrenshaPersonMasterId());

        KanrenshaPersonMasterEntity entity1 = list.get(1);
        assertEquals(233, entity1.getKanrenshaPersonMasterId());

        KanrenshaPersonMasterEntity entity2 = list.get(2);
        assertEquals(234, entity2.getKanrenshaPersonMasterId());

        KanrenshaPersonMasterEntity entity3 = list.get(3);
        assertEquals(235, entity3.getKanrenshaPersonMasterId());
    }

}
