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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchPostalCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.postal.SearchPostalCodeResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressPostalEntity;

/**
 * SearchPostalCodeService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchPostalCodeServiceTest.sql")
class SearchPostalCodeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchPostalCodeService searchPostalCodeService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 取得できない
        SearchPostalCodeCapsuleDto capsuleDto0 = this.createCapsuleDto();
        capsuleDto0.setSearchAddressName("aa");
        SearchPostalCodeResultDto resultDto0 = searchPostalCodeService.practice(capsuleDto0);
        assertEquals(0, resultDto0.getAllCount());
        assertEquals(capsuleDto0.getLimit(), resultDto0.getLimit());
        assertTrue(resultDto0.getListItem().isEmpty());

        // 未指定(＝最新全件)
        SearchPostalCodeCapsuleDto capsuleDto1 = this.createCapsuleDto();
        SearchPostalCodeResultDto resultDto1 = searchPostalCodeService.practice(capsuleDto1);
        assertEquals(5, resultDto1.getAllCount());
        assertEquals(capsuleDto1.getLimit(), resultDto0.getLimit());
        assertEquals(0, resultDto1.getPageNumber());

        List<AddressPostalEntity> listAns1 = resultDto1.getListItem();

        assertEquals(5, listAns1.size());
        assertEquals(442, listAns1.get(0).getAddressPostalId());
        assertEquals(443, listAns1.get(1).getAddressPostalId());
        assertEquals(444, listAns1.get(2).getAddressPostalId());
        assertEquals(445, listAns1.get(3).getAddressPostalId());
        assertEquals(446, listAns1.get(4).getAddressPostalId());

        // 全指定
        SearchPostalCodeCapsuleDto capsuleDto2 = this.createCapsuleDto();
        capsuleDto2.setSearchPostalcode1("02");
        capsuleDto2.setSearchPostalcode2("45");
        capsuleDto2.setSearchAddressName("北海札");

        SearchPostalCodeResultDto resultDto2 = searchPostalCodeService.practice(capsuleDto2);
        assertEquals(1, resultDto2.getAllCount());
        assertEquals(capsuleDto2.getLimit(), resultDto0.getLimit());
        assertEquals(0, resultDto2.getPageNumber());

        List<AddressPostalEntity> listAns2 = resultDto2.getListItem();

        assertEquals(1, listAns2.size());
        assertEquals(446, listAns2.get(0).getAddressPostalId());

    }

    private SearchPostalCodeCapsuleDto createCapsuleDto() {

        SearchPostalCodeCapsuleDto capsuleDto = new SearchPostalCodeCapsuleDto();
        capsuleDto.setAllCount(73);
        capsuleDto.setLimit(20);
        capsuleDto.setPageNumber(4);

        return capsuleDto;
    }

}
