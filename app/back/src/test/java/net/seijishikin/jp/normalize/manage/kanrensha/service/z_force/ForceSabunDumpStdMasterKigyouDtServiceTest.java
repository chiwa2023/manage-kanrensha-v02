package net.seijishikin.jp.normalize.manage.kanrensha.service.z_force;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;

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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ForceDumpStdMasterSabunKigyouDtService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("ForceSabunDumpStdMasterKigyouDtServiceTest.sql")
class ForceSabunDumpStdMasterKigyouDtServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ForceSabunDumpStdMasterKigyouDtService forceSabunDumpStdMasterKigyouDtService;

    @Test
    @Tag("TableTruncate")
    void test() {

        final Integer year = 2026;
        InsertTaskPlanResultDto planDto = new InsertTaskPlanResultDto();
        planDto.setTaskPlanId(453);
        planDto.setTaskPlanCode(187);

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        assertDoesNotThrow(() -> forceSabunDumpStdMasterKigyouDtService.practice(year, planDto,
                LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), userDto));
    }

}
