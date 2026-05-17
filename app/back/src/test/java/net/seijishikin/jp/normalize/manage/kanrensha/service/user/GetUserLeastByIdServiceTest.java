package net.seijishikin.jp.normalize.manage.kanrensha.service.user;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.GetUserDtoResultDto;

/**
 * GetUserLeastByIdService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("GetUserLeastByIdServiceTest.sql")
class GetUserLeastByIdServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetUserLeastByIdService getUserLeastByIdService;

    @Test
    @Tag("TableTruncate")
    void testSuccess() {

        final Integer editId = 82;

        GetUserDtoResultDto resultDto = getUserLeastByIdService.practcie(editId);
        assertFalse(resultDto.getIsFailure());

        LeastUserDto userDto = resultDto.getUserDto();
        assertEquals(82, userDto.getUserPersonId());
        assertEquals(83, userDto.getUserPersonCode());
        assertEquals("bbb", userDto.getUserPersonName());
        assertEquals(true, resultDto.getIsAlertTaskStart());
        assertEquals(true, resultDto.getIsAlertTaskEnd());

        List<String> listRole = userDto.getListRoles();
        assertEquals(3, listRole.size());

        assertEquals("manager", listRole.get(0));
        assertEquals("partner_api", listRole.get(1));
        assertEquals("kanrensha_kigyou_dt", listRole.get(2));

        assertEquals(913, userDto.getRiyoushaCode());
        assertEquals("partner_api", userDto.getRiyoushaRole());
        assertEquals("ndfx", userDto.getKanrenshaCode());
        assertEquals("kanrensha_kigyou_dt", userDto.getKanrenshaRole());
    }

    @Test
    @Tag("TableTruncate")
    void testFailure() {

        final Integer editId = 1999;

        GetUserDtoResultDto resultDto = getUserLeastByIdService.practcie(editId);
        assertTrue(resultDto.getIsFailure());
    }

}
