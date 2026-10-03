package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2026;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearTaskSuccessService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearTaskSuccessService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SwitchYearTaskSuccessY2026ServiceTest.sql")
class SwitchYearTaskSuccessY2026ServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearTaskSuccessService switchYearTaskSuccessService;

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    @Test
    @Tag("TableTruncate")
    void test2026() throws Exception {

        final Integer year = 2026;
        final Integer taskId = 453;
        LocalDateTime endDateTime = LocalDateTime.of(2022, 12, 5, 12, 34, 56);
        Integer newId = switchYearTaskSuccessService.practice(year, CreateLeastUserForTestUtil.practice(), taskId,
                endDateTime);

        TaskPlan2026Entity oldEntity = taskPlan2026Repository.findById(taskId).get();
        assertFalse(oldEntity.getIsLatest()); // 履歴になった

        TaskPlan2026Entity newEntity = taskPlan2026Repository.findById(newId).get();
        assertTrue(newEntity.getIsLatest());
        assertTrue(newEntity.getIsFinished());
        assertFalse(newEntity.getIsSuspended());
        assertEquals(endDateTime, newEntity.getEndDateimte());
        assertEquals(LocalDateTime.of(1990, 7, 24, 23, 34, 56), newEntity.getStartDatetime()); // 元の開始日時が維持
    }

}
