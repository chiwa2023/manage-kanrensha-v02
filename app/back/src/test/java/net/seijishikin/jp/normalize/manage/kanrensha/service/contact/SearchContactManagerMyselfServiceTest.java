package net.seijishikin.jp.normalize.manage.kanrensha.service.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SearchContactManagerMyselfService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchContactManagerMyselfServiceTest.sql")
class SearchContactManagerMyselfServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchContactManagerMyselfService searchContactManagerMyselfService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchContactManagerCapsuleDto capsuleDto1 = new SearchContactManagerCapsuleDto();
        capsuleDto1.setLimit(20);
        capsuleDto1.setAllCount(124);
        capsuleDto1.setPageNumber(4);
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());

        SearchContactManagerResultDto resultDto1 = searchContactManagerMyselfService.practice(capsuleDto1);
        assertEquals(3, resultDto1.getAllCount());

        List<ContactManagerEntity> listAns = resultDto1.getListEntity();

        ContactManagerEntity entity0 = listAns.get(0);
        assertEquals(326, entity0.getContactManagerId());

        ContactManagerEntity entity1 = listAns.get(1);
        assertEquals(327, entity1.getContactManagerId());

        ContactManagerEntity entity2 = listAns.get(2);
        assertEquals(328, entity2.getContactManagerId());
    }

}
