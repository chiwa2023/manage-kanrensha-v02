package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.List;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2027.TaskPlan2027Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2027.TaskPlan2027Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateTaskListFailureY2027Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("UpdateTaskListFailureY2027LogicTest.sql")
class UpdateTaskListFailureY2027LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateTaskListFailureY2027Logic updateTaskListFailureY2027Logic;

    /** タスク計画Repository(2027) */
    @Autowired
    private TaskPlan2027Repository taskPlan2027Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final Integer taskId = 523;
        final Integer taskCode = 393;
        LocalDateTime endDateTime = LocalDateTime.of(2022, 12, 5, 12, 34, 56);
        updateTaskListFailureY2027Logic.practice(CreateLeastUserForTestUtil.practice(), taskId, taskCode, endDateTime);

        List<TaskPlan2027Entity> list = taskPlan2027Repository.findByTaskPlanCode(taskCode);
        assertEquals(4, list.size()); // 1件増えた

        TaskPlan2027Entity entity0 = list.get(0);
        assertEquals(522, entity0.getTaskPlanId());
        assertEquals(taskCode, entity0.getTaskPlanCode());
        assertFalse(entity0.getIsLatest());

        TaskPlan2027Entity entity1 = list.get(1);
        assertEquals(523, entity1.getTaskPlanId());
        assertEquals(taskCode, entity1.getTaskPlanCode());
        assertFalse(entity1.getIsLatest());

        TaskPlan2027Entity entity2 = list.get(2);
        assertEquals(524, entity2.getTaskPlanId());
        assertEquals(taskCode, entity2.getTaskPlanCode());
        assertFalse(entity2.getIsLatest());

        TaskPlan2027Entity entity3 = list.get(3);
        assertEquals(525, entity3.getTaskPlanId());
        assertEquals(taskCode, entity3.getTaskPlanCode());
        assertTrue(entity3.getIsLatest());
        assertTrue(entity3.getIsStart());
        assertFalse(entity3.getIsFinished());
        assertTrue(entity3.getIsSuspended());
        assertEquals(endDateTime, entity3.getEndDateimte());
        assertEquals(LocalDateTime.of(1990, 7, 24, 23, 34, 56), entity3.getStartDatetime());
    }

}
