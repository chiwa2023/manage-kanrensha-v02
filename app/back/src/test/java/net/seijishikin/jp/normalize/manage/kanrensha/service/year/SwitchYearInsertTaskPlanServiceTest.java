package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan.CreateQueryParamDummyUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearInsertTaskPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SwitchYearInsertTaskPlanServiceTest.sql")
class SwitchYearInsertTaskPlanServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    @Test
    @Tag("TableTruncate")
    void test2026() {

        final Integer taskCode = TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV;
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime dateTimeStart = LocalDateTime.of(2026, 12, 13, 11, 22, 33);

        LeastUserDto workUserDto = new LeastUserDto();
        workUserDto.setUserPersonCode(854);
        workUserDto.setUserPersonName("利用者　直子");

        InsertTaskPlanResultDto dto = switchYearInsertTaskPlanService.practice(workUserDto, userDto, dateTimeStart,
                taskCode, CreateQueryParamDummyUtil.practice());

        TaskPlan2026Entity entity = taskPlan2026Repository.findById(dto.getTaskPlanId()).get();

        assertEquals(true, entity.getIsLatest());

        assertEquals(dateTimeStart.getYear(), entity.getTableYear());
        assertEquals(taskCode, entity.getTaskInfoCode());
        assertEquals("郵便番号差分修正", entity.getTaskPlanName());
        assertEquals(dateTimeStart, entity.getStartDatetime());
        assertEquals(DtoEntityInitialValueInterface.INIT_TIMESTAMP, entity.getEndDateimte());
        assertEquals(true, entity.getIsStart());
        assertEquals(false, entity.getIsSuspended());
        assertEquals(false, entity.getIsFinished());
        assertEquals("admin,manager", entity.getRoleList());
        assertEquals("http://localhost:6180/kanrensha-manage/edit-page?asd=123&zxc=456", entity.getTransferPass());
        assertEquals(workUserDto.getUserPersonCode(), entity.getTaskUserCode());
        assertEquals(workUserDto.getUserPersonName(), entity.getTaskUserName());
    }

}
