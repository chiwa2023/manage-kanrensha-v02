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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.PagingIntegerCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt.SearchAddressCityDeleteResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.AddressCityDeleteEntity;

/**
 * SearchAllCityDeleteService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchAllCityDeleteServiceTest.sql")
class SearchAllCityDeleteServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchAllCityDeleteService searchAllCityDeleteService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        PagingIntegerCapsuleDto capsuleDto = new PagingIntegerCapsuleDto();
        capsuleDto.setAllCount(145);
        capsuleDto.setPageNumber(7);
        capsuleDto.setLimit(20);

        SearchAddressCityDeleteResultDto resultDto = searchAllCityDeleteService.practice(capsuleDto);
        assertEquals(2, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());

        List<AddressCityDeleteEntity> listAns = resultDto.getListEntity();
        assertEquals(2, listAns.size());

        assertEquals(159, listAns.get(0).getAddressCityDeleteId());
        assertEquals(161, listAns.get(1).getAddressCityDeleteId());
    }

}
