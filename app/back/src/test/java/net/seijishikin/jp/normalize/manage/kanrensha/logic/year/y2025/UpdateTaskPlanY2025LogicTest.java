package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateTaskPlanY2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("InsertTaskPlanY2025LogicTest.sql")
@Transactional
class UpdateTaskPlanY2025LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateTaskPlanY2025Logic updateTaskPlanY2025Logic;

    /** テスト対象 */
    @Autowired
    private InsertTaskPlanY2025Logic insertTaskPlanY2025Logic;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        Integer taskCode = 101;
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        Integer newId = insertTaskPlanY2025Logic.practice(userDto, taskCode);

        LocalDateTime datetime = LocalDateTime.of(2022, 12, 5, 12, 34, 56);
        Boolean isFinished = true;
        Integer updateId = updateTaskPlanY2025Logic.practice(userDto, newId, datetime, isFinished);

        TaskPlan2025Entity deleteEntity = taskPlan2025Repository.findById(newId).get();

        assertEquals(newId, deleteEntity.getTaskPlanId());
        assertEquals(false, deleteEntity.getIsLatest());

        TaskPlan2025Entity updateEntity = taskPlan2025Repository.findById(updateId).get();
        assertEquals(updateId, updateEntity.getTaskPlanId());
        assertEquals(updateEntity.getTaskPlanCode(), updateEntity.getTaskPlanCode());
        assertEquals(updateEntity.getTaskInfoCode(), updateEntity.getTaskInfoCode());
        assertEquals(datetime, updateEntity.getEndDateimte());
        assertEquals(isFinished, updateEntity.getIsFinished());
        assertEquals(!isFinished, updateEntity.getIsSuspended());

        assertThrows(EmptyResultDataAccessException.class,
                () -> updateTaskPlanY2025Logic.practice(userDto, 691, datetime, isFinished));
    }

}
