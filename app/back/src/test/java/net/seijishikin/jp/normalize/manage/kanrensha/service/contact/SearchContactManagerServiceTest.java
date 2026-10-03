package net.seijishikin.jp.normalize.manage.kanrensha.service.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.SearchContactManagerCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.contact.SearchContactManagerResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.ContactManagerEntity;

/**
 * SearchContactManagerService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchContactManagerServiceTest.sql")
class SearchContactManagerServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** 単体テスト */
    @Autowired
    private SearchContactManagerService searchContactManagerService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchContactManagerCapsuleDto capsuleDto0 = new SearchContactManagerCapsuleDto();
        capsuleDto0.setLimit(20);
        capsuleDto0.setAllCount(124);
        capsuleDto0.setPageNumber(4);
        capsuleDto0.setStartDate(LocalDate.of(2099, 1, 1));
        capsuleDto0.setEndDate(LocalDate.of(2099, 1, 2));

        // 検索結果が空
        SearchContactManagerResultDto resultDto0 = searchContactManagerService.practice(capsuleDto0);
        assertEquals(0, resultDto0.getAllCount());
        assertTrue(resultDto0.getListEntity().isEmpty());

        SearchContactManagerCapsuleDto capsuleDto1 = new SearchContactManagerCapsuleDto();
        capsuleDto1.setLimit(20);
        capsuleDto1.setAllCount(124);
        capsuleDto1.setPageNumber(4);
        capsuleDto1.setStartDate(LocalDate.of(2025, 11, 1));
        capsuleDto1.setEndDate(LocalDate.of(2026, 1, 4));
        capsuleDto1.setIsSearchClose(true);

        SearchContactManagerResultDto resultDto1 = searchContactManagerService.practice(capsuleDto1);
        assertEquals(3, resultDto1.getAllCount());

        List<ContactManagerEntity> listAns = resultDto1.getListEntity();

        ContactManagerEntity entity0 = listAns.get(0);
        assertEquals(325, entity0.getContactManagerId());

        ContactManagerEntity entity1 = listAns.get(1);
        assertEquals(326, entity1.getContactManagerId());

        ContactManagerEntity entity2 = listAns.get(2);
        assertEquals(327, entity2.getContactManagerId());
    }

}
