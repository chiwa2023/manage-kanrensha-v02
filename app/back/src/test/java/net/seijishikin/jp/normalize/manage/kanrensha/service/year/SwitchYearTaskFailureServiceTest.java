package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearTaskFailureService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SwitchYearTaskFailureServiceTest.sql")
class SwitchYearTaskFailureServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearTaskFailureService switchYearTaskFailureService;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    @Test
    @Tag("TableTruncate")
    void test2025() throws Exception {

        final Integer year = 2025;
        final Integer taskId = 523;
        final Integer taskCode = 393;
        LocalDateTime endDateTime = LocalDateTime.of(2022, 12, 5, 12, 34, 56);
        switchYearTaskFailureService.practice(year, CreateLeastUserForTestUtil.practice(), taskId, taskCode,
                endDateTime);

        List<TaskPlan2025Entity> list = taskPlan2025Repository.findByTaskPlanCode(taskCode);
        assertEquals(4, list.size()); // 1件増えた

        TaskPlan2025Entity entity0 = list.get(0);
        assertEquals(522, entity0.getTaskPlanId());
        assertEquals(taskCode, entity0.getTaskPlanCode());
        assertFalse(entity0.getIsLatest());

        TaskPlan2025Entity entity1 = list.get(1);
        assertEquals(523, entity1.getTaskPlanId());
        assertEquals(taskCode, entity1.getTaskPlanCode());
        assertFalse(entity1.getIsLatest());

        TaskPlan2025Entity entity2 = list.get(2);
        assertEquals(524, entity2.getTaskPlanId());
        assertEquals(taskCode, entity2.getTaskPlanCode());
        assertFalse(entity2.getIsLatest());

        TaskPlan2025Entity entity3 = list.get(3);
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
