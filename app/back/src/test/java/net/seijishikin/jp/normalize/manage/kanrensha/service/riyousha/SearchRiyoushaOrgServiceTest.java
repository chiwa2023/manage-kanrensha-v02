package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaOrgCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaOrgResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaOrgMasterEntity;

/**
 * SearchRiyoushaOrgService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchRiyoushaOrgServiceTest.sql")
class SearchRiyoushaOrgServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchRiyoushaOrgService searchRiyoushaOrgService;

    @Test
    @Tag("NaturalSearch")
    void test() throws Exception {

        SearchRiyoushaOrgCapsuleDto capsuleDto = new SearchRiyoushaOrgCapsuleDto();
        capsuleDto.setLimit(20);
        capsuleDto.setAllCount(6);
        capsuleDto.setPageNumber(3);
        capsuleDto.setSearchNaturalWords("IT");

        SearchRiyoushaOrgResultDto resultDto = searchRiyoushaOrgService.practice(capsuleDto);

        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(2, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());

        List<RiyoushaOrgMasterEntity> listEntity = resultDto.getListRiyoushaOrg();
        assertEquals(resultDto.getAllCount(), listEntity.size());

        RiyoushaOrgMasterEntity entity0 = listEntity.get(0);
        assertEquals(316, entity0.getRiyoushaOrgMasterId());

        RiyoushaOrgMasterEntity entity1 = listEntity.get(1);
        assertEquals(317, entity1.getRiyoushaOrgMasterId());
    }

}
