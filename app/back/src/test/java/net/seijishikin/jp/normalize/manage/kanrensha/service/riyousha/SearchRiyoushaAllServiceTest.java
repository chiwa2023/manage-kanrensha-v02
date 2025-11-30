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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAdminResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAllCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaAllResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaManagerResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.riyousha.SearchRiyoushaPartnerApiResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaAdminMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaManagerMasterEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.RiyoushaPartnerApiMasterEntity;

/**
 * SearchRiyoushaAllService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql({ "SearchRiyoushaAdminServiceTest.sql", "SearchRiyoushaManagerServiceTest.sql",
        "SearchRiyoushaPartnerAppiServiceTest.sql" })
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
        capsuleDto0.setSearchNaturalWords("管理者");
        capsuleDto0.setPageNumber(0);

        SearchRiyoushaAllResultDto resultDto0 = searchRiyoushaAllService.practice(capsuleDto0);

        // すべての検索をしないよう、フラグを立てていないので空しか戻らない
        assertTrue(resultDto0.getSearchRiyoushaPartnerApiResultDto().getListRiyoushaPartner().isEmpty());
        assertTrue(resultDto0.getSearchRiyoushaManagerResultDto().getListRiyoushaManager().isEmpty());
        assertTrue(resultDto0.getSearchRiyoushaPartnerApiResultDto().getListRiyoushaPartner().isEmpty());

        SearchRiyoushaAllCapsuleDto capsuleDto1 = new SearchRiyoushaAllCapsuleDto();
        capsuleDto1.setIsPartnerApiSearch(true);
        capsuleDto1.setIsManagerSearch(true);
        capsuleDto1.setIsAdminSearch(true);
        capsuleDto1.setLimit(20);
        capsuleDto1.setSearchNaturalWords("管理者");
        capsuleDto1.setPageNumber(0);

        SearchRiyoushaAllResultDto resultDto1 = searchRiyoushaAllService.practice(capsuleDto1);

        SearchRiyoushaPartnerApiResultDto resultDtoPartner = resultDto1.getSearchRiyoushaPartnerApiResultDto();

        assertEquals(2, resultDtoPartner.getAllCount());
        assertEquals(capsuleDto1.getLimit(), resultDtoPartner.getLimit());
        assertEquals(0, resultDtoPartner.getPageNumber());

        assertNotNull(resultDtoPartner.getListRiyoushaPartner());

        List<RiyoushaPartnerApiMasterEntity> listPartner = resultDtoPartner.getListRiyoushaPartner();

        RiyoushaPartnerApiMasterEntity entityPartner00 = listPartner.get(0);
        RiyoushaPartnerApiMasterEntity entityPartner01 = listPartner.get(1);

        assertEquals(1, entityPartner00.getRiyoushaPartnerApiMasterId());
        assertEquals(3, entityPartner01.getRiyoushaPartnerApiMasterId());

        SearchRiyoushaManagerResultDto resultDtoManager = resultDto1.getSearchRiyoushaManagerResultDto();

        assertEquals(2, resultDtoManager.getAllCount());
        assertEquals(capsuleDto1.getLimit(), resultDtoManager.getLimit());
        assertEquals(0, resultDtoManager.getPageNumber());

        assertNotNull(resultDtoManager.getListRiyoushaManager());

        List<RiyoushaManagerMasterEntity> listManager = resultDtoManager.getListRiyoushaManager();

        RiyoushaManagerMasterEntity entityManager00 = listManager.get(0);
        RiyoushaManagerMasterEntity entityManager01 = listManager.get(1);

        assertEquals(1, entityManager00.getRiyoushaManagerMasterId());
        assertEquals(3, entityManager01.getRiyoushaManagerMasterId());

        SearchRiyoushaAdminResultDto resultDtoAdmin = resultDto1.getSearchRiyoushaAdminResultDto();

        assertEquals(2, resultDtoAdmin.getAllCount());
        assertEquals(capsuleDto1.getLimit(), resultDtoAdmin.getLimit());
        assertEquals(0, resultDtoAdmin.getPageNumber());

        assertNotNull(resultDtoAdmin.getListRiyoushaAdmin());

        List<RiyoushaAdminMasterEntity> listAdmin = resultDtoAdmin.getListRiyoushaAdmin();

        RiyoushaAdminMasterEntity entityAdmin00 = listAdmin.get(0);
        RiyoushaAdminMasterEntity entityAdmin01 = listAdmin.get(1);

        assertEquals(1, entityAdmin00.getRiyoushaAdminMasterId());
        assertEquals(3, entityAdmin01.getRiyoushaAdminMasterId());
    }

}
