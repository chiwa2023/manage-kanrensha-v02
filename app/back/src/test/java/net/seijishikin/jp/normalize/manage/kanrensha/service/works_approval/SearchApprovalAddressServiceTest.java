package net.seijishikin.jp.normalize.manage.kanrensha.service.works_approval;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.KanrenshaKbnConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchApprovalAddressResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.KanrenshaAddressBaseEntity;

/**
 * SearchApprovalAddressService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchApprovalAddressServiceTest.sql")
class SearchApprovalAddressServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchApprovalAddressService searchApprovalAddressService;

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

        SearchApprovalAddressResultDto resultDto = searchApprovalAddressService.practice(capsuleDto);

        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(6, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());

        List<KanrenshaAddressBaseEntity> list = resultDto.getListAddress();
        assertEquals(201, list.get(0).getKanrenshaAddressId());
        assertEquals(202, list.get(1).getKanrenshaAddressId());
        assertEquals(101, list.get(2).getKanrenshaAddressId());
        assertEquals(102, list.get(3).getKanrenshaAddressId());
        assertEquals(301, list.get(4).getKanrenshaAddressId());
        assertEquals(302, list.get(5).getKanrenshaAddressId());
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

        SearchApprovalAddressResultDto resultDto = searchApprovalAddressService.practice(capsuleDto);

        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(3, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());

        List<KanrenshaAddressBaseEntity> list = resultDto.getListAddress();

        assertEquals(202, list.get(0).getKanrenshaAddressId());
        assertEquals(102, list.get(1).getKanrenshaAddressId());
        assertEquals(302, list.get(2).getKanrenshaAddressId());

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

        KanrenshaAddressBaseEntity entity1 = list.get(1);
        assertEquals(KanrenshaKbnConstants.KIGYOU_DT, entity1.getKanrenshaKbn());
        assertEquals("宮崎県架空市橘通東", entity1.getAddressPostal());
        assertEquals("２丁目１０−１", entity1.getAddressBlock());
        assertEquals("宮崎県庁", entity1.getAddressBuilding());
        assertEquals("880", entity1.getPostalcode1());
        assertEquals("8501", entity1.getPostalcode2());
        assertEquals("131016", entity1.getLgCode());
        assertEquals("324", entity1.getMachiazaId());
        assertEquals("131", entity1.getBlkId());
        assertEquals("249", entity1.getPrcId());
        assertEquals("136", entity1.getRsdtId());
        assertEquals("978", entity1.getRsdt2Id());
        assertEquals(true, entity1.getIsPostalEdit());
        assertEquals(true, entity1.getIsBlockEdit());
        assertEquals(true, entity1.getIsBuildingEdit());
        assertEquals(false, entity1.getIsPostalAccept());
        assertEquals(true, entity1.getIsBlockAccept());
        assertEquals(false, entity1.getIsBuildingAccept());

        KanrenshaAddressBaseEntity entity2 = list.get(2);
        assertEquals(KanrenshaKbnConstants.SEIJIDANTAI, entity2.getKanrenshaKbn());
        assertEquals("宮崎県架空市橘通東", entity2.getAddressPostal());
        assertEquals("２丁目１０−１", entity2.getAddressBlock());
        assertEquals("宮崎県庁", entity2.getAddressBuilding());
        assertEquals("880", entity2.getPostalcode1());
        assertEquals("8501", entity2.getPostalcode2());
        assertEquals("131016", entity2.getLgCode());
        assertEquals("324", entity2.getMachiazaId());
        assertEquals("131", entity2.getBlkId());
        assertEquals("249", entity2.getPrcId());
        assertEquals("136", entity2.getRsdtId());
        assertEquals("978", entity2.getRsdt2Id());
        assertEquals(true, entity2.getIsPostalEdit());
        assertEquals(true, entity2.getIsBlockEdit());
        assertEquals(true, entity2.getIsBuildingEdit());
        assertEquals(false, entity2.getIsPostalAccept());
        assertEquals(false, entity2.getIsBlockAccept());
        assertEquals(false, entity2.getIsBuildingAccept());

    }

}
