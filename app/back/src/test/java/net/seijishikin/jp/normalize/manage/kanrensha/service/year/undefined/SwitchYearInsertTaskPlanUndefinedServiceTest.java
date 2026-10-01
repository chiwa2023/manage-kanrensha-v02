package net.seijishikin.jp.normalize.manage.kanrensha.service.year.undefined;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan.CreateQueryParamDummyUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearInsertTaskPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SwitchYearInsertTaskPlanUndefinedServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    @Test
    @Tag("TableTruncate")
    void test2019() {

        final Integer taskCode = TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV;
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime dateTimeStart = LocalDateTime.of(1001, 12, 13, 11, 22, 33);

        LeastUserDto workUserDto = new LeastUserDto();
        workUserDto.setUserPersonCode(854);
        workUserDto.setUserPersonName("利用者　直子");

        assertThrows(IllegalArgumentException.class, () -> switchYearInsertTaskPlanService.practice(workUserDto,
                userDto, dateTimeStart, taskCode, CreateQueryParamDummyUtil.practice()));
    }

}
