package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaPartnerApiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaPartnerApiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;

/**
 * SearchRiyoushaPartnerAppiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchRiyoushaPartnerAppiServiceTest.sql")
class SearchRiyoushaPartnerAppiServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchRiyoushaPartnerAppiService searchRiyoushaPartnerAppiService;

    @Test
    @Tag("NaturalSearch")
    void test() throws Exception {

        SearchRiyoushaPartnerApiCapsuleDto capsuleDto = new SearchRiyoushaPartnerApiCapsuleDto();
        capsuleDto.setLimit(20);
        capsuleDto.setSearchNaturalWords("管理者");
        capsuleDto.setPageNumber(0);

        SearchRiyoushaPartnerApiResultDto resultDto = searchRiyoushaPartnerAppiService.practice(capsuleDto);

        assertEquals(2, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber());

        assertNotNull(resultDto.getListRiyoushaPartner());

        List<RiyoushaPartnerApiMasterEntity> list = resultDto.getListRiyoushaPartner();

        RiyoushaPartnerApiMasterEntity entity00 = list.get(0);
        RiyoushaPartnerApiMasterEntity entity01 = list.get(1);

        assertEquals(1, entity00.getRiyoushaPartnerApiMasterId());
        assertEquals(3, entity01.getRiyoushaPartnerApiMasterId());

        // 無茶なページ番号は補正
        capsuleDto.setPageNumber(66);
        SearchRiyoushaPartnerApiResultDto resultDto1 = searchRiyoushaPartnerAppiService.practice(capsuleDto);
        assertEquals(2, resultDto1.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto1.getLimit());
        assertEquals(0, resultDto1.getPageNumber());
        assertEquals(2, resultDto1.getListRiyoushaPartner().size());

        // 検索できなくても必要なDtoは戻る
        capsuleDto.setSearchNaturalWords("検索できない");
        capsuleDto.setPageNumber(3);
        SearchRiyoushaPartnerApiResultDto resultDto2 = searchRiyoushaPartnerAppiService.practice(capsuleDto);
        assertEquals(0, resultDto2.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto2.getLimit());
        assertEquals(0, resultDto2.getPageNumber());
        assertTrue(resultDto2.getListRiyoushaPartner().isEmpty());

    }

}
