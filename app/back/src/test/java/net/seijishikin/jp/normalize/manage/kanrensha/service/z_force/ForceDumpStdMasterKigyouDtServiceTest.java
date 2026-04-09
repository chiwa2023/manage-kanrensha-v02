package net.seijishikin.jp.normalize.manage.kanrensha.service.z_force;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;

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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanInfoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ForceDumpStdMasterKigyouDtService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("ForceDumpStdMasterKigyouDtServiceTest.sql")
class ForceDumpStdMasterKigyouDtServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ForceDumpStdMasterKigyouDtService forceDumpStdMasterKigyouDtService;

    @Test
    @Tag("TableTruncate")
    void test() {

        final Integer year = 2026;
        TaskPlanInfoDto planDto = new TaskPlanInfoDto();
        planDto.setTaskPlanId(453);
        planDto.setTaskPlanCode(187);

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        assertDoesNotThrow(() -> forceDumpStdMasterKigyouDtService.practice(year, planDto, LocalDate.of(2024, 1, 1),userDto));
    }

}
