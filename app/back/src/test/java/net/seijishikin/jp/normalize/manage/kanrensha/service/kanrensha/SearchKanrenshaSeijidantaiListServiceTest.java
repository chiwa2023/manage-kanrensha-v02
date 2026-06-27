package net.seijishikin.jp.normalize.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.SeijidantaiDantaiKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaSeijidantaiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.SearchKanrenshaSeijidantaiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaSeijidantaiMasterEntity;

/**
 * SearchKanrenshaSeijidantaiListService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
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

        KanrenshaSeijidantaiMasterEntity entity = list.get(0);
        assertEquals(323, entity.getKanrenshaSeijidantaiMasterId());
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

        KanrenshaSeijidantaiMasterEntity entity0 = list.get(0);
        assertEquals(323, entity0.getKanrenshaSeijidantaiMasterId());

        KanrenshaSeijidantaiMasterEntity entity1 = list.get(1);
        assertEquals(325, entity1.getKanrenshaSeijidantaiMasterId());

        KanrenshaSeijidantaiMasterEntity entity2 = list.get(2);
        assertEquals(326, entity2.getKanrenshaSeijidantaiMasterId());

        KanrenshaSeijidantaiMasterEntity entity3 = list.get(3);
        assertEquals(327, entity3.getKanrenshaSeijidantaiMasterId());

        KanrenshaSeijidantaiMasterEntity entity4 = list.get(4);
        assertEquals(328, entity4.getKanrenshaSeijidantaiMasterId());

        KanrenshaSeijidantaiMasterEntity entity5 = list.get(5);
        assertEquals(329, entity5.getKanrenshaSeijidantaiMasterId());
    }

}
