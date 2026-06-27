package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchWkTblPostalCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchWkTblPostalCodeResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblPostalEditEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SearchWktblPostalCodeService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchWktblPostalCodeServiceTest.sql")
class SearchWktblPostalCodeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchWktblPostalCodeService searchWktblPostalCodeService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // データ未取得
        SearchWkTblPostalCodeCapsuleDto capsuleDto0 = new SearchWkTblPostalCodeCapsuleDto();
        capsuleDto0.getUserDto().setUserPersonCode(1007);
        capsuleDto0.setAllCount(7);
        capsuleDto0.setLimit(20);
        capsuleDto0.setPageNumber(41);

        SearchWkTblPostalCodeResultDto resultDto0 = searchWktblPostalCodeService.practice(capsuleDto0);
        assertEquals(0, resultDto0.getAllCount());
        assertEquals(capsuleDto0.getLimit(), resultDto0.getLimit());
        assertTrue(resultDto0.getListEntity().isEmpty());

        // データ取得
        SearchWkTblPostalCodeCapsuleDto capsuleDto1 = new SearchWkTblPostalCodeCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setAllCount(7);
        capsuleDto1.setLimit(20);
        capsuleDto1.setPageNumber(41);

        SearchWkTblPostalCodeResultDto resultDto1 = searchWktblPostalCodeService.practice(capsuleDto1);
        assertEquals(2, resultDto1.getAllCount());
        assertEquals(capsuleDto1.getLimit(), resultDto1.getLimit());
        assertEquals(0, resultDto1.getPageNumber());

        List<WkTblPostalEditEntity> listAns1 = resultDto1.getListEntity();

        assertEquals(2, listAns1.size());
        assertEquals(624, listAns1.get(0).getWkTblPostalEditId());
        assertEquals(625, listAns1.get(1).getWkTblPostalEditId());

        // データ取得(履歴)
        SearchWkTblPostalCodeCapsuleDto capsuleDto2 = new SearchWkTblPostalCodeCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto2.setAllCount(7);
        capsuleDto2.setLimit(20);
        capsuleDto2.setPageNumber(41);
        capsuleDto2.setIsSearchHistory(true);

        SearchWkTblPostalCodeResultDto resultDto2 = searchWktblPostalCodeService.practice(capsuleDto2);
        assertEquals(3, resultDto2.getAllCount());
        assertEquals(capsuleDto2.getLimit(), resultDto2.getLimit());
        assertEquals(0, resultDto2.getPageNumber());

        List<WkTblPostalEditEntity> listAns2 = resultDto2.getListEntity();

        assertEquals(3, listAns2.size());
        assertEquals(624, listAns2.get(0).getWkTblPostalEditId());
        assertEquals(625, listAns2.get(1).getWkTblPostalEditId());
        assertEquals(626, listAns2.get(2).getWkTblPostalEditId());

        // データ取得(履歴と自動修復)
        SearchWkTblPostalCodeCapsuleDto capsuleDto3 = new SearchWkTblPostalCodeCapsuleDto();
        capsuleDto3.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto3.setAllCount(7);
        capsuleDto3.setLimit(20);
        capsuleDto3.setPageNumber(41);
        capsuleDto3.setIsSearchHistory(true);
        capsuleDto3.setIsSearchRepair(true);

        SearchWkTblPostalCodeResultDto resultDto3 = searchWktblPostalCodeService.practice(capsuleDto3);
        assertEquals(4, resultDto3.getAllCount());
        assertEquals(capsuleDto3.getLimit(), resultDto3.getLimit());
        assertEquals(0, resultDto3.getPageNumber());

        List<WkTblPostalEditEntity> listAns3 = resultDto3.getListEntity();

        assertEquals(4, listAns3.size());
        assertEquals(624, listAns3.get(0).getWkTblPostalEditId());
        assertEquals(625, listAns3.get(1).getWkTblPostalEditId());
        assertEquals(626, listAns3.get(2).getWkTblPostalEditId());
        assertEquals(627, listAns3.get(3).getWkTblPostalEditId());
    }

}
