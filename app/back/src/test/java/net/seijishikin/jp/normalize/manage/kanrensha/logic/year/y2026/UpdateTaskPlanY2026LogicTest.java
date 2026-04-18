package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan.CreateQueryParamDummyUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateTaskPlanY2026Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertTaskPlanY2026LogicTest.sql")
class UpdateTaskPlanY2026LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateTaskPlanY2026Logic updateTaskPlanY2026Logic;

    /** テスト対象 */
    @Autowired
    private InsertTaskPlanY2026Logic insertTaskPlanY2026Logic;

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    @Test
    void test() throws Exception {

        Integer taskCode = 101;
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime datetimeStart = LocalDateTime.of(2026, 7, 9, 11, 22, 33);
        InsertTaskPlanResultDto planDto = insertTaskPlanY2026Logic.practice(userDto, datetimeStart, taskCode,
                CreateQueryParamDummyUtil.practice());
        Integer newId = planDto.getTaskPlanId();

        LocalDateTime datetime = LocalDateTime.of(2027, 12, 5, 12, 34, 56);
        Boolean isFinished = true;
        Integer updateId = updateTaskPlanY2026Logic.practice(userDto, newId, datetime, isFinished);

        TaskPlan2026Entity deleteEntity = taskPlan2026Repository.findById(newId).get();

        assertEquals(newId, deleteEntity.getTaskPlanId());
        assertEquals(false, deleteEntity.getIsLatest());

        TaskPlan2026Entity updateEntity = taskPlan2026Repository.findById(updateId).get();
        assertEquals(updateId, updateEntity.getTaskPlanId());
        assertEquals(updateEntity.getTaskPlanCode(), updateEntity.getTaskPlanCode());
        assertEquals(updateEntity.getTaskInfoCode(), updateEntity.getTaskInfoCode());
        assertEquals(datetime, updateEntity.getEndDateimte());
        assertEquals(isFinished, updateEntity.getIsFinished());
        assertEquals(!isFinished, updateEntity.getIsSuspended());

        assertThrows(EmptyResultDataAccessException.class,
                () -> updateTaskPlanY2026Logic.practice(userDto, 691, datetime, isFinished));
    }

}
