package net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchLgCodeCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchLgCodeResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressAllCityEntity;

/**
 * SearchAddressLgCodeService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchAddressLgCodeServiceTest.sql")
class SearchAddressLgCodeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchAddressLgCodeService searchAddressLgCodeService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchLgCodeCapsuleDto capsuleDto = new SearchLgCodeCapsuleDto();
        capsuleDto.setAllCount(131);
        capsuleDto.setPageNumber(7);
        capsuleDto.setLimit(20);
        capsuleDto.setPrefCode("03");
        capsuleDto.setSearchWords("手");

        SearchLgCodeResultDto resultDto = searchAddressLgCodeService.practice(capsuleDto);

        assertEquals(4, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber());

        List<AddressAllCityEntity> listAns = resultDto.getListEntity();

        assertEquals(4, listAns.size());

        assertEquals(241, listAns.get(0).getAddressAllCityId());
        assertEquals(242, listAns.get(1).getAddressAllCityId());
        assertEquals(243, listAns.get(2).getAddressAllCityId());
        assertEquals(244, listAns.get(3).getAddressAllCityId());
    }

}
