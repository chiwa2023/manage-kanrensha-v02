package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_combine.SearchWkTblCombineOrgPagingResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SearchCombineOrganizationService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchCombineOrganizationServiceTest.sql")
class SearchCombineOrganizationServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchCombineOrganizationService searchCombineOrganizationService;

    @Test
    @Tag("TableTruncate")
    void testKigyouDt() {

        final Short kanrenshaKbn = KanrenshaKbnConstants.KIGYOU_DT;

        SearchWkTbPagingCapsuleDto capsuleDto00 = this.createCapsuleDto();
        SearchWkTblCombineOrgPagingResultDto resultDto00 = searchCombineOrganizationService.practice(kanrenshaKbn,
                capsuleDto00);
        assertEquals(1, resultDto00.getAllCount());
        assertEquals(kanrenshaKbn, resultDto00.getListCombineOrg().get(0).getKanrenshaKbn());

        SearchWkTbPagingCapsuleDto capsuleDto01 = this.createCapsuleDto();
        capsuleDto01.setHasHistorry(true);
        SearchWkTblCombineOrgPagingResultDto resultDto01 = searchCombineOrganizationService.practice(kanrenshaKbn,
                capsuleDto01);
        assertEquals(2, resultDto01.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto02 = this.createCapsuleDto();
        capsuleDto02.setHasHistorry(true);
        capsuleDto02.setHasAffectNot(true);
        SearchWkTblCombineOrgPagingResultDto resultDto02 = searchCombineOrganizationService.practice(kanrenshaKbn,
                capsuleDto02);
        assertEquals(3, resultDto02.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto03 = this.createCapsuleDto();
        capsuleDto03.setHasHistorry(true);
        capsuleDto03.setHasAffectNot(true);
        capsuleDto03.setHasFinished(true);
        SearchWkTblCombineOrgPagingResultDto resultDto03 = searchCombineOrganizationService.practice(kanrenshaKbn,
                capsuleDto03);
        assertEquals(4, resultDto03.getAllCount());

        // 検索条件変更によるページ番号初期化
        SearchWkTbPagingCapsuleDto capsuleDto04 = this.createCapsuleDto();
        capsuleDto04.setPageNumber(100);
        SearchWkTblCombineOrgPagingResultDto resultDto04 = searchCombineOrganizationService.practice(kanrenshaKbn,
                capsuleDto04);
        assertEquals(0, resultDto04.getPageNumber());
    }

    @Test
    @Tag("TableTruncate")
    void testSeijidantai() {

        final Short kanrenshaKbn = KanrenshaKbnConstants.SEIJIDANTAI;

        SearchWkTbPagingCapsuleDto capsuleDto00 = this.createCapsuleDto();
        SearchWkTblCombineOrgPagingResultDto resultDto00 = searchCombineOrganizationService.practice(kanrenshaKbn,
                capsuleDto00);
        assertEquals(1, resultDto00.getAllCount());
        assertEquals(kanrenshaKbn, resultDto00.getListCombineOrg().get(0).getKanrenshaKbn());

        SearchWkTbPagingCapsuleDto capsuleDto01 = this.createCapsuleDto();
        capsuleDto01.setHasHistorry(true);
        SearchWkTblCombineOrgPagingResultDto resultDto01 = searchCombineOrganizationService.practice(kanrenshaKbn,
                capsuleDto01);
        assertEquals(2, resultDto01.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto02 = this.createCapsuleDto();
        capsuleDto02.setHasHistorry(true);
        capsuleDto02.setHasAffectNot(true);
        SearchWkTblCombineOrgPagingResultDto resultDto02 = searchCombineOrganizationService.practice(kanrenshaKbn,
                capsuleDto02);
        assertEquals(3, resultDto02.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto03 = this.createCapsuleDto();
        capsuleDto03.setHasHistorry(true);
        capsuleDto03.setHasAffectNot(true);
        capsuleDto03.setHasFinished(true);
        SearchWkTblCombineOrgPagingResultDto resultDto03 = searchCombineOrganizationService.practice(kanrenshaKbn,
                capsuleDto03);
        assertEquals(4, resultDto03.getAllCount());

        // 検索条件変更によるページ番号初期化
        SearchWkTbPagingCapsuleDto capsuleDto04 = this.createCapsuleDto();
        capsuleDto04.setPageNumber(100);
        SearchWkTblCombineOrgPagingResultDto resultDto04 = searchCombineOrganizationService.practice(kanrenshaKbn,
                capsuleDto04);
        assertEquals(0, resultDto04.getPageNumber());
    }

    private SearchWkTbPagingCapsuleDto createCapsuleDto() {
        SearchWkTbPagingCapsuleDto capsuleDto = new SearchWkTbPagingCapsuleDto();
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        return capsuleDto;
    }

}
