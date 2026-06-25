package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_min;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.wktbl_min.SearchWkTblMinSeijidantaiPagingResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SearchBulkMasterMinSeijidantaiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SearchBulkMasterMinSeijidantaiServiceTest.sql")
class SearchBulkMasterMinSeijidantaiServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchBulkMasterMinSeijidantaiService searchBulkMasterMinSeijidantaiService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        SearchWkTbPagingCapsuleDto capsuleDto00 = this.createCapsuleDto();
        SearchWkTblMinSeijidantaiPagingResultDto resultDto00 = searchBulkMasterMinSeijidantaiService
                .practice(capsuleDto00);
        assertEquals(1, resultDto00.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto01 = this.createCapsuleDto();
        capsuleDto01.setHasHistorry(true);
        SearchWkTblMinSeijidantaiPagingResultDto resultDto01 = searchBulkMasterMinSeijidantaiService
                .practice(capsuleDto01);
        assertEquals(2, resultDto01.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto02 = this.createCapsuleDto();
        capsuleDto02.setHasHistorry(true);
        capsuleDto02.setHasAffectNot(true);
        SearchWkTblMinSeijidantaiPagingResultDto resultDto02 = searchBulkMasterMinSeijidantaiService
                .practice(capsuleDto02);
        assertEquals(3, resultDto02.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto03 = this.createCapsuleDto();
        capsuleDto03.setHasHistorry(true);
        capsuleDto03.setHasAffectNot(true);
        capsuleDto03.setHasFinished(true);
        SearchWkTblMinSeijidantaiPagingResultDto resultDto03 = searchBulkMasterMinSeijidantaiService
                .practice(capsuleDto03);
        assertEquals(4, resultDto03.getAllCount());

        // 検索条件変更によるページ番号初期化
        SearchWkTbPagingCapsuleDto capsuleDto04 = this.createCapsuleDto();
        capsuleDto04.setPageNumber(100);
        SearchWkTblMinSeijidantaiPagingResultDto resultDto04 = searchBulkMasterMinSeijidantaiService
                .practice(capsuleDto04);
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
