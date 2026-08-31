package net.seijishikin.jp.normalize.manage.kanrensha.service.works_approval;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchApprovalAddressResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaAddressBaseEntity;

/**
 * SearchApprovalAddressPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchApprovalAddressPersonServiceTest.sql")
class SearchApprovalAddressPersonServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchApprovalAddressPersonService searchApprovalAddressPersonService;

    @Test
    @Tag("TableTruncate")
    void testPlusAccept() throws Exception {

        SearchWorksApprovalCapsuleDto capsuleDto = new SearchWorksApprovalCapsuleDto();
        capsuleDto.setIsExcludeFinishedTask(false);
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setStartDate(LocalDate.of(2025, 2, 1));
        capsuleDto.setEndDate(LocalDate.of(2025, 6, 1));

        SearchApprovalAddressResultDto resultDto = searchApprovalAddressPersonService.practice(capsuleDto);

        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(2, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());

        List<KanrenshaAddressBaseEntity> list = resultDto.getListAddress();
        assertEquals(201, list.get(0).getKanrenshaAddressId());
        assertEquals(202, list.get(1).getKanrenshaAddressId());
    }

    @Test
    @Tag("TableTruncate")
    void testWorks() throws Exception {

        SearchWorksApprovalCapsuleDto capsuleDto = new SearchWorksApprovalCapsuleDto();
        capsuleDto.setIsExcludeFinishedTask(true);
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setStartDate(LocalDate.of(2025, 2, 1));
        capsuleDto.setEndDate(LocalDate.of(2025, 6, 1));

        SearchApprovalAddressResultDto resultDto = searchApprovalAddressPersonService.practice(capsuleDto);

        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(1, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());

        List<KanrenshaAddressBaseEntity> list = resultDto.getListAddress();

        assertEquals(202, list.get(0).getKanrenshaAddressId());

        // 手でbindingしているので全チェック
        KanrenshaAddressBaseEntity entity0 = list.get(0);
        assertEquals(KanrenshaKbnConstants.PERSON, entity0.getKanrenshaKbn());
        assertEquals("宮崎県架空市橘通東", entity0.getAddressPostal());
        assertEquals("２丁目１０−１", entity0.getAddressBlock());
        assertEquals("宮崎県庁", entity0.getAddressBuilding());
        assertEquals("880", entity0.getPostalcode1());
        assertEquals("8501", entity0.getPostalcode2());
        assertEquals("131016", entity0.getLgCode());
        assertEquals("324", entity0.getMachiazaId());
        assertEquals("131", entity0.getBlkId());
        assertEquals("249", entity0.getPrcId());
        assertEquals("136", entity0.getRsdtId());
        assertEquals("978", entity0.getRsdt2Id());
        assertEquals(true, entity0.getIsPostalEdit());
        assertEquals(true, entity0.getIsBlockEdit());
        assertEquals(true, entity0.getIsBuildingEdit());
        assertEquals(false, entity0.getIsPostalAccept());
        assertEquals(false, entity0.getIsBlockAccept());
        assertEquals(false, entity0.getIsBuildingAccept());
    }

}
