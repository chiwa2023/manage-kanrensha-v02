package net.seijishikin.jp.normalize.manage.kanrensha.service.sns;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import net.seijishikin.jp.normalize.common_tool.dto.NaturalTextSearchPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sns.SearchSnsServiceResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.SnsServiceEntity;

/**
 * SearchSnsDataService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchSnsDataServiceTest.sql")
@Transactional
class SearchSnsDataServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchSnsDataService searchSnsDataService;

    @Test
    @Tag("NaturalSearch")
    void test() throws Exception {

        NaturalTextSearchPagingCapsuleDto capsuleDto = new NaturalTextSearchPagingCapsuleDto();
        capsuleDto.setAllCount(121);
        capsuleDto.setLimit(20);
        capsuleDto.setPageNumber(4);
        capsuleDto.setSearchNaturalWords("sns");

        SearchSnsServiceResultDto resultDto = searchSnsDataService.practice(capsuleDto);

        assertEquals(0, resultDto.getPageNumber()); // とんちんかんなページ番号は修正される
        assertEquals(20, resultDto.getLimit());
        assertEquals(3, resultDto.getAllCount());

        List<SnsServiceEntity> list = resultDto.getList();
        assertEquals(resultDto.getAllCount(), list.size());

        SnsServiceEntity entity0 = list.get(0);
        assertEquals(672, entity0.getSnsServiceId());
        SnsServiceEntity entity1 = list.get(1);
        assertEquals(673, entity1.getSnsServiceId());
        SnsServiceEntity entity2 = list.get(2);
        assertEquals(674, entity2.getSnsServiceId());
    }

}
