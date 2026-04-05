package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

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
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanInfoDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.InsertTaskPlanY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearUpdateTaskStartAndEndService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SwitchYearUpdateTaskStartAndEndServiceTest.sql")
class SwitchYearUpdateTaskStartAndEndServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /**　テスト対象 */
    @Autowired
    private SwitchYearUpdateTaskStartAndEndService switchYearUpdateTaskStartAndEndService;

    /** タスク挿入Logic(2025) */
    @Autowired
    private InsertTaskPlanY2025Logic insertTaskPlanY2025Logic;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    
    @Test
    void test() {
        
        Integer taskCode = TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV;
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        TaskPlanInfoDto dto = insertTaskPlanY2025Logic.practice(userDto, taskCode);
        Integer newId = dto.getTaskPlanId();

        LocalDateTime datetime = LocalDateTime.of(2022, 12, 5, 12, 34, 56);
        Integer updateId = switchYearUpdateTaskStartAndEndService.practice(userDto, 2025, newId, datetime);

        TaskPlan2025Entity deleteEntity = taskPlan2025Repository.findById(newId).get();

        assertEquals(newId, deleteEntity.getTaskPlanId());
        assertEquals(false, deleteEntity.getIsLatest());

        TaskPlan2025Entity updateEntity = taskPlan2025Repository.findById(updateId).get();
        assertEquals(updateId, updateEntity.getTaskPlanId());
        assertEquals(updateEntity.getTaskPlanCode(), updateEntity.getTaskPlanCode());
        assertEquals(updateEntity.getTaskInfoCode(), updateEntity.getTaskInfoCode());
        assertEquals(datetime, updateEntity.getEndDateimte());
        Boolean isFinished = true;
        assertEquals(isFinished, updateEntity.getIsFinished());
        assertEquals(!isFinished, updateEntity.getIsSuspended());
        assertEquals(true, updateEntity.getIsLatest());
    }

}
