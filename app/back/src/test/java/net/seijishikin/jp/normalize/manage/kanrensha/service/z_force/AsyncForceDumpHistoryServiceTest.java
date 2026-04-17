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

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanInfoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.z_force.ForceDumpCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AsyncForceDumpHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("AsyncForceDumpHistoryServiceTest.sql")
class AsyncForceDumpHistoryServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private AsyncForceDumpHistoryService asyncForceDumpHistoryService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final Integer year = 2026;
        InsertTaskPlanResultDto planDto1 = new InsertTaskPlanResultDto();
        planDto1.setTaskPlanId(453);
        planDto1.setTaskPlanCode(187);

        InsertTaskPlanResultDto planDto2 = new InsertTaskPlanResultDto();
        planDto2.setTaskPlanId(461);
        planDto2.setTaskPlanCode(188);

        InsertTaskPlanResultDto planDto3 = new InsertTaskPlanResultDto();
        planDto3.setTaskPlanId(462);
        planDto3.setTaskPlanCode(189);

        ForceDumpCapsuleDto capsuleDto = new ForceDumpCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.setDateEnd(LocalDate.of(2024, 1, 1));
        capsuleDto.setIsExecuteKigyouDt(true);
        capsuleDto.setIsExecutePerson(true);
        capsuleDto.setIsExecuteSeijidantai(true);

        assertDoesNotThrow(() -> asyncForceDumpHistoryService.practice(year, planDto1, planDto2, planDto3, capsuleDto));
    }

}
