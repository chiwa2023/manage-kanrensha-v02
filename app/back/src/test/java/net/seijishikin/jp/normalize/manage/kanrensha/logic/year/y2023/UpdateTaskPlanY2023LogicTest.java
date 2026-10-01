package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2023.TaskPlan2023Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan.CreateQueryParamDummyUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2023.TaskPlan2023Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateTaskPlanY2023Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertTaskPlanY2023LogicTest.sql")
class UpdateTaskPlanY2023LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateTaskPlanY2023Logic updateTaskPlanY2023Logic;

    /** タスク挿入Logic(2023) */
    @Autowired
    private InsertTaskPlanY2023Logic insertTaskPlanY2023Logic;

    /** タスク計画Repository(2023) */
    @Autowired
    private TaskPlan2023Repository taskPlan2023Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        Integer taskCode = TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV;
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime datetimeStart = LocalDateTime.of(2023, 1, 5, 11, 22, 33);
        LeastUserDto workUserDto = new LeastUserDto(); 
        workUserDto.setUserPersonCode(854);
        workUserDto.setUserPersonName("利用者　直子");

        InsertTaskPlanResultDto dto = insertTaskPlanY2023Logic.practice(workUserDto,userDto, datetimeStart, taskCode,
                CreateQueryParamDummyUtil.practice());
        Integer newId = dto.getTaskPlanId();

        LocalDateTime datetime = LocalDateTime.of(2026, 12, 5, 12, 34, 56);
        Boolean isFinished = true;
        Integer updateId = updateTaskPlanY2023Logic.practice(userDto, newId, datetime, isFinished);

        TaskPlan2023Entity deleteEntity = taskPlan2023Repository.findById(newId).get();

        assertEquals(newId, deleteEntity.getTaskPlanId());
        assertEquals(false, deleteEntity.getIsLatest());

        TaskPlan2023Entity updateEntity = taskPlan2023Repository.findById(updateId).get();
        assertEquals(updateId, updateEntity.getTaskPlanId());
        assertEquals(updateEntity.getTaskPlanCode(), updateEntity.getTaskPlanCode());
        assertEquals(updateEntity.getTaskInfoCode(), updateEntity.getTaskInfoCode());
        assertEquals(workUserDto.getUserPersonCode(), updateEntity.getTaskUserCode());
        assertEquals(workUserDto.getUserPersonName(), updateEntity.getTaskUserName());
        assertEquals(datetime, updateEntity.getEndDateimte());
        assertEquals(isFinished, updateEntity.getIsFinished());
        assertEquals(!isFinished, updateEntity.getIsSuspended());
        assertEquals(true, updateEntity.getIsLatest());

        assertThrows(EmptyResultDataAccessException.class,
                () -> updateTaskPlanY2023Logic.practice(userDto, 691, datetime, isFinished));
    }

}
