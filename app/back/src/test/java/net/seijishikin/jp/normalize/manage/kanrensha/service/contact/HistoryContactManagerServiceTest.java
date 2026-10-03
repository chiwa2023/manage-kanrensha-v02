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

/**
 * HistoryContactManagerService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("HistoryContactManagerServiceTest.sql")
class HistoryContactManagerServiceTest {
    // CHECKSTYLE:OFF MagicNumer

    /** テスト対象 */
    @Autowired
    private HistoryContactManagerService historyContactManagerService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchContactManagerCapsuleDto capsuleDto = new SearchContactManagerCapsuleDto();
        capsuleDto.setContactManagerCode(26);

        SearchContactManagerResultDto resultDto = historyContactManagerService.practice(capsuleDto);

        List<ContactManagerEntity> listAns = resultDto.getListEntity();
        assertEquals(3, listAns.size());

        ContactManagerEntity entity0 = listAns.get(0);
        assertEquals(324, entity0.getContactManagerId());

        ContactManagerEntity entity1 = listAns.get(1);
        assertEquals(325, entity1.getContactManagerId());

        ContactManagerEntity entity2 = listAns.get(2);
        assertEquals(327, entity2.getContactManagerId());
    }

}
