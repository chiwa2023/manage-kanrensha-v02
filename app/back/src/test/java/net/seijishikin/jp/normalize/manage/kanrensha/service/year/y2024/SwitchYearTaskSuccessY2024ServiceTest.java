package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2024;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2024.TaskPlan2024Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2024.TaskPlan2024Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearTaskSuccessService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearTaskSuccessService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SwitchYearTaskSuccessY2024ServiceTest.sql")
class SwitchYearTaskSuccessY2024ServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearTaskSuccessService switchYearTaskSuccessService;

    /** タスク計画Repository(2024) */
    @Autowired
    private TaskPlan2024Repository taskPlan2024Repository;

    @Test
    @Tag("TableTruncate")
    void test2024() throws Exception {

        final Integer year = 2024;
        final Integer taskId = 453;
        LocalDateTime endDateTime = LocalDateTime.of(2022, 12, 5, 12, 34, 56);
        Integer newId = switchYearTaskSuccessService.practice(year, CreateLeastUserForTestUtil.practice(), taskId,
                endDateTime);

        TaskPlan2024Entity oldEntity = taskPlan2024Repository.findById(taskId).get();
        assertFalse(oldEntity.getIsLatest()); // 履歴になった

        TaskPlan2024Entity newEntity = taskPlan2024Repository.findById(newId).get();
        assertTrue(newEntity.getIsLatest());
        assertTrue(newEntity.getIsFinished());
        assertFalse(newEntity.getIsSuspended());
        assertEquals(endDateTime, newEntity.getEndDateimte());
        assertEquals(LocalDateTime.of(1990, 7, 24, 23, 34, 56), newEntity.getStartDatetime()); // 元の開始日時が維持
    }

}
