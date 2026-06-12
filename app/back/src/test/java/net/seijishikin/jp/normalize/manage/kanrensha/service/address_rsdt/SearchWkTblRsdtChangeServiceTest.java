package net.seijishikin.jp.normalize.manage.kanrensha.service.address_rsdt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchWkTblAddressRsdtCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchWkTblRsdtChangeResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.WkTblAddressRsdtChangeEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SearchWkTblRsdtChangeService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchWkTblRsdtChangeServiceTest.sql")
class SearchWkTblRsdtChangeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchWkTblRsdtChangeService searchWkTblRsdtChangeService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // データ未取得
        SearchWkTblAddressRsdtCapsuleDto capsuleDto0 = new SearchWkTblAddressRsdtCapsuleDto();
        capsuleDto0.getUserDto().setUserPersonCode(1007);
        capsuleDto0.setAllCount(7);
        capsuleDto0.setLimit(20);
        capsuleDto0.setPageNumber(41);

        SearchWkTblRsdtChangeResultDto resultDto0 = searchWkTblRsdtChangeService.practice(capsuleDto0);
        assertEquals(0, resultDto0.getAllCount());
        assertEquals(capsuleDto0.getLimit(), resultDto0.getLimit());
        assertTrue(resultDto0.getListEntity().isEmpty());

        // データ取得
        SearchWkTblAddressRsdtCapsuleDto capsuleDto1 = new SearchWkTblAddressRsdtCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto1.setAllCount(7);
        capsuleDto1.setLimit(20);
        capsuleDto1.setPageNumber(41);

        SearchWkTblRsdtChangeResultDto resultDto1 = searchWkTblRsdtChangeService.practice(capsuleDto1);
        assertEquals(2, resultDto1.getAllCount());
        assertEquals(capsuleDto1.getLimit(), resultDto1.getLimit());
        assertEquals(0, resultDto1.getPageNumber());

        List<WkTblAddressRsdtChangeEntity> listAns1 = resultDto1.getListEntity();

        assertEquals(2, listAns1.size());
        assertEquals(332, listAns1.get(0).getWkTblAddressRsdtChangeId());
        assertEquals(333, listAns1.get(1).getWkTblAddressRsdtChangeId());

        // データ取得(履歴)
        SearchWkTblAddressRsdtCapsuleDto capsuleDto2 = new SearchWkTblAddressRsdtCapsuleDto();
        capsuleDto2.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto2.setAllCount(7);
        capsuleDto2.setLimit(20);
        capsuleDto2.setPageNumber(41);
        capsuleDto2.setIsSearchHistory(true);

        SearchWkTblRsdtChangeResultDto resultDto2 = searchWkTblRsdtChangeService.practice(capsuleDto2);
        assertEquals(4, resultDto2.getAllCount());
        assertEquals(capsuleDto2.getLimit(), resultDto2.getLimit());
        assertEquals(0, resultDto2.getPageNumber());

        List<WkTblAddressRsdtChangeEntity> listAns2 = resultDto2.getListEntity();

        assertEquals(4, listAns2.size());
        assertEquals(332, listAns2.get(0).getWkTblAddressRsdtChangeId());
        assertEquals(333, listAns2.get(1).getWkTblAddressRsdtChangeId());
        assertEquals(334, listAns2.get(2).getWkTblAddressRsdtChangeId());
        assertEquals(335, listAns2.get(3).getWkTblAddressRsdtChangeId());
    }

}
