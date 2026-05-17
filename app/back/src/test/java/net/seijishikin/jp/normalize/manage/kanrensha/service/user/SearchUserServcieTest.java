package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SearchUserCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.SearchUserEntityResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;

/**
 * SearchUserServcieTest単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
//@Transactional
@Sql("SearchUserServcieTest.sql")
class SearchUserServcieTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchUserServcie searchUserServcie;

    @Test
    @Tag("TableTruncate")
    void test() {

        SearchUserCapsuleDto capsuleDto = new SearchUserCapsuleDto();
        capsuleDto.setName("bb");
        capsuleDto.getListRole().add("manager");
        capsuleDto.getListRole().add("kanrensha_person");
        capsuleDto.setAllCount(25);
        capsuleDto.setPageNumber(4);
        capsuleDto.setLimit(20);

        SearchUserEntityResultDto resultDto = searchUserServcie.practice(capsuleDto);

        assertEquals(2, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber());

        List<UserPersonEntity> list = resultDto.getListPersonEntity();

        assertEquals(2, list.size());

        assertEquals(81, list.get(0).getUserPersonId());
        assertEquals(82, list.get(1).getUserPersonId());
    }

}
