package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei.SearchTimerYoteiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei.SearchTimerYoteiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;

/**
 * SearchTimerYoteiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchTimerYoteiServiceTest.sql")
@Transactional
class SearchTimerYoteiServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchTimerYoteiService searchTimerYoteiService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final Integer limit = 20;

        // 日時検索条件なし
        // 0件
        SearchTimerYoteiCapsuleDto capsuleDto0 = new SearchTimerYoteiCapsuleDto();
        capsuleDto0.setIsPeriodSearch(false);
        capsuleDto0.setLimit(limit);
        capsuleDto0.setAllCount(100);
        capsuleDto0.setPageNumber(4);
        List<Short> listKbnNot = new ArrayList<>();
        listKbnNot.add((short) 851);
        capsuleDto0.setListYoteiKbn(listKbnNot);

        SearchTimerYoteiResultDto resultDto0 = searchTimerYoteiService.practice(capsuleDto0);
        assertEquals(0, resultDto0.getAllCount());
        assertEquals(limit, resultDto0.getLimit());
        assertEquals(0, resultDto0.getPageNumber());
        assertTrue(resultDto0.getListEntity().isEmpty());

        // リスト取得(ページ番号補正)
        SearchTimerYoteiCapsuleDto capsuleDto1 = new SearchTimerYoteiCapsuleDto();
        capsuleDto1.setIsPeriodSearch(false);
        capsuleDto1.setLimit(limit);
        capsuleDto1.setAllCount(99);
        capsuleDto1.setPageNumber(3);
        List<Short> listKbn = new ArrayList<>();
        listKbn.add((short) 243);
        capsuleDto1.setListYoteiKbn(listKbn);

        SearchTimerYoteiResultDto resultDto1 = searchTimerYoteiService.practice(capsuleDto1);
        assertEquals(limit, resultDto1.getLimit());
        assertEquals(2, resultDto1.getAllCount());
        assertEquals(0, resultDto1.getPageNumber());
        List<TimerYoteiEntity> listAns1 = resultDto1.getListEntity();
        assertEquals(2, listAns1.size());
        assertEquals(125, listAns1.get(0).getTimerYoteiId());
        assertEquals(126, listAns1.get(1).getTimerYoteiId());

        // 日時検索条件あり
        // 0件
        SearchTimerYoteiCapsuleDto capsuleDto2 = new SearchTimerYoteiCapsuleDto();
        capsuleDto2.setIsPeriodSearch(true);
        capsuleDto2.setLimit(limit);
        capsuleDto2.setAllCount(98);
        capsuleDto2.setPageNumber(5);
        capsuleDto2.setStartDateTime(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
        capsuleDto2.setEndDateTime(LocalDateTime.of(2022, 1, 2, 0, 0, 0));
        capsuleDto2.setListYoteiKbn(listKbn);

        SearchTimerYoteiResultDto resultDto2 = searchTimerYoteiService.practice(capsuleDto2);
        assertEquals(0, resultDto2.getAllCount());
        assertEquals(limit, resultDto2.getLimit());
        assertEquals(0, resultDto2.getPageNumber());
        assertTrue(resultDto2.getListEntity().isEmpty());

        // リスト取得(ページ番号補正)
        SearchTimerYoteiCapsuleDto capsuleDto3 = new SearchTimerYoteiCapsuleDto();
        capsuleDto3.setIsPeriodSearch(true);
        capsuleDto3.setLimit(limit);
        capsuleDto3.setAllCount(102);
        capsuleDto3.setPageNumber(6);
        capsuleDto3.setStartDateTime(LocalDateTime.of(2023, 1, 1, 0, 0, 0));
        capsuleDto3.setEndDateTime(LocalDateTime.of(2023, 12, 31, 0, 0, 0));
        capsuleDto3.setListYoteiKbn(listKbn);

        SearchTimerYoteiResultDto resultDto3 = searchTimerYoteiService.practice(capsuleDto3);

        assertEquals(limit, resultDto3.getLimit());
        assertEquals(1, resultDto3.getAllCount());
        assertEquals(0, resultDto3.getPageNumber());

        List<TimerYoteiEntity> listAns3 = resultDto3.getListEntity();
        assertEquals(1, listAns3.size());
        assertEquals(125, listAns1.get(0).getTimerYoteiId());
    }

}
