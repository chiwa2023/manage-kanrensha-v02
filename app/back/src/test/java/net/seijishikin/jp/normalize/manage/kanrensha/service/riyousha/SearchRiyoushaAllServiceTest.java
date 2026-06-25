package net.seijishikin.jp.normalize.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAllCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAllResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchViewCombineAliveRiyoushaResultDto;

/**
 * SearchRiyoushaAllService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchRiyoushaAllServiceTest.sql")
class SearchRiyoushaAllServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchRiyoushaAllService searchRiyoushaAllService;

    @Test
    @Tag("NaturalSearch")
    void test() throws Exception {

        SearchRiyoushaAllCapsuleDto capsuleDto0 = new SearchRiyoushaAllCapsuleDto();
        capsuleDto0.setIsPartnerApiSearch(false);
        capsuleDto0.setIsManagerSearch(false);
        capsuleDto0.setIsPartnerApiSearch(false);
        capsuleDto0.setLimit(20);
        capsuleDto0.setSearchNaturalWords("花子");
        capsuleDto0.setPageNumber(0);

        SearchRiyoushaAllResultDto resultDto0 = searchRiyoushaAllService.practice(capsuleDto0);

        // すべての検索をしないよう、フラグを立てていないので空しか戻らない
        assertTrue(resultDto0.getListAllRiyousha().isEmpty());

        SearchRiyoushaAllCapsuleDto capsuleDto1 = new SearchRiyoushaAllCapsuleDto();
        capsuleDto1.setIsPartnerApiSearch(true);
        capsuleDto1.setIsManagerSearch(true);
        capsuleDto1.setIsAdminSearch(true);
        capsuleDto1.setLimit(20);
        capsuleDto1.setSearchNaturalWords("花子");
        capsuleDto1.setPageNumber(0);

        SearchRiyoushaAllResultDto resultDto1 = searchRiyoushaAllService.practice(capsuleDto1);

        assertEquals(5, resultDto1.getAllCount());
        assertEquals(capsuleDto1.getLimit(), resultDto1.getLimit());
        assertEquals(0, resultDto1.getPageNumber());

        List<SearchViewCombineAliveRiyoushaResultDto> listAllRiyousha = resultDto1.getListAllRiyousha();
        assertEquals(resultDto1.getAllCount(), listAllRiyousha.size());

        final String admin = "admin";
        final String manager = "manager";
        final String partner = "partner_api";

        SearchViewCombineAliveRiyoushaResultDto dto0 = listAllRiyousha.get(0);
        assertEquals(284, dto0.getRiyoushaId());
        assertEquals(manager, dto0.getRoleBase());
        assertEquals(manager, dto0.getRoleHas());
        assertEquals("管理者 マリア花子3", dto0.getAllName());

        SearchViewCombineAliveRiyoushaResultDto dto1 = listAllRiyousha.get(1);
        assertEquals(327, dto1.getRiyoushaId());
        assertEquals(partner, dto1.getRoleBase());
        assertEquals(admin, dto1.getRoleHas());

        SearchViewCombineAliveRiyoushaResultDto dto2 = listAllRiyousha.get(2);
        assertEquals(327, dto2.getRiyoushaId());
        assertEquals(partner, dto2.getRoleBase());
        assertEquals(manager, dto2.getRoleHas());

        SearchViewCombineAliveRiyoushaResultDto dto3 = listAllRiyousha.get(3);
        assertEquals(327, dto3.getRiyoushaId());
        assertEquals(partner, dto3.getRoleBase());
        assertEquals(partner, dto3.getRoleHas());

        SearchViewCombineAliveRiyoushaResultDto dto4 = listAllRiyousha.get(4);
        assertEquals(325, dto4.getRiyoushaId());
        assertEquals(partner, dto4.getRoleBase());
        assertEquals(partner, dto4.getRoleHas());
    }

}
