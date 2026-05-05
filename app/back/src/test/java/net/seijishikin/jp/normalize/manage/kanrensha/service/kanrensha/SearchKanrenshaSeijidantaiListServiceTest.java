package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.SeijidantaiDantaiKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaSeijidantaiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;

/**
 * SearchKanrenshaSeijidantaiListService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchKanrenshaSeijidantaiListServiceTest.sql")
class SearchKanrenshaSeijidantaiListServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchKanrenshaSeijidantaiListService searchKanrenshaSeijidantaiListService;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testCond() throws Exception {

        SearchKanrenshaSeijidantaiCapsuleDto capsuleDto = new SearchKanrenshaSeijidantaiCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setLimit(20);
        capsuleDto.setPageNumber(4);
        capsuleDto.setPoliOrgNo("98-");
        capsuleDto.setName("ちゃらん");
        capsuleDto.setAddress("宮崎県架空市");
        capsuleDto.setDelegate("代表");
        List<String> listKbn = new ArrayList<>();
        listKbn.add(SeijidantaiDantaiKbnConstants.DANTAI_KBN_04);
        capsuleDto.setListDantaiKbn(listKbn);

        SearchKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaSeijidantaiListService.practice(capsuleDto);

        assertEquals(1, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber());

        List<KanrenshaSeijidantaiMasterEntity> list = resultDto.getListMasterSeijidantai();
        assertEquals(1, list.size());

        KanrenshaSeijidantaiMasterEntity entiy = list.get(0);
        assertEquals(323, entiy.getKanrenshaSeijidantaiMasterId());
    }

    @Test
    @Tag("TableTruncate") // NOPMD
    void testAll() throws Exception {

        SearchKanrenshaSeijidantaiCapsuleDto capsuleDto = new SearchKanrenshaSeijidantaiCapsuleDto();
        capsuleDto.setAllCount(124);
        capsuleDto.setLimit(20);
        capsuleDto.setPageNumber(4);
        // 検索条件なし
        // capsuleDto.setHoujinNo("1234");
        // capsuleDto.setName("株式会社テ");
        // capsuleDto.setAddress("東京都テスト区");
        // capsuleDto.setDelegate("代表テ");
        List<String> listKbn = new ArrayList<>();
        listKbn.add(SeijidantaiDantaiKbnConstants.DANTAI_KBN_01);
        listKbn.add(SeijidantaiDantaiKbnConstants.DANTAI_KBN_02);
        listKbn.add(SeijidantaiDantaiKbnConstants.DANTAI_KBN_03);
        listKbn.add(SeijidantaiDantaiKbnConstants.DANTAI_KBN_04);
        listKbn.add(SeijidantaiDantaiKbnConstants.DANTAI_KBN_05);
        listKbn.add(SeijidantaiDantaiKbnConstants.DANTAI_KBN_06);
        capsuleDto.setListDantaiKbn(listKbn);

        SearchKanrenshaSeijidantaiResultDto resultDto = searchKanrenshaSeijidantaiListService.practice(capsuleDto);

        assertEquals(6, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber());

        List<KanrenshaSeijidantaiMasterEntity> list = resultDto.getListMasterSeijidantai();
        assertEquals(6, list.size());

        KanrenshaSeijidantaiMasterEntity entiy0 = list.get(0);
        assertEquals(323, entiy0.getKanrenshaSeijidantaiMasterId());

        KanrenshaSeijidantaiMasterEntity entiy1 = list.get(1);
        assertEquals(325, entiy1.getKanrenshaSeijidantaiMasterId());

        KanrenshaSeijidantaiMasterEntity entiy2 = list.get(2);
        assertEquals(326, entiy2.getKanrenshaSeijidantaiMasterId());

        KanrenshaSeijidantaiMasterEntity entiy3 = list.get(3);
        assertEquals(327, entiy3.getKanrenshaSeijidantaiMasterId());

        KanrenshaSeijidantaiMasterEntity entiy4 = list.get(4);
        assertEquals(328, entiy4.getKanrenshaSeijidantaiMasterId());

        KanrenshaSeijidantaiMasterEntity entiy5 = list.get(5);
        assertEquals(329, entiy5.getKanrenshaSeijidantaiMasterId());
    }

}
