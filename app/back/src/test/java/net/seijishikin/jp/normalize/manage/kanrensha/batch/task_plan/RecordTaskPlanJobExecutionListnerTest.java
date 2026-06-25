package net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RecordTaskPlanJobExecutionListner単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("RecordTaskPlanJobExecutionListnerTest.sql")
class RecordTaskPlanJobExecutionListnerTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private RecordTaskPlanJobExecutionListner recordTaskPlanJobExecutionListner;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    @Test
    @Tag("ExternalService")
    void testCompleted() throws Exception {

        final Integer taskId = 453;
        recordTaskPlanJobExecutionListner.afterJob(this.getStepExecution(2026, 453, 187, BatchStatus.COMPLETED));

        TaskPlan2026Entity oldEntity = taskPlan2026Repository.findById(taskId).get();
        assertFalse(oldEntity.getIsLatest()); // 履歴になった

        TaskPlan2026Entity newEntity = taskPlan2026Repository.findById(460).get();
        assertTrue(newEntity.getIsLatest());
        assertTrue(newEntity.getIsFinished());
        assertFalse(newEntity.getIsSuspended());
        assertEquals(LocalDateTime.of(1990, 7, 24, 23, 34, 56), newEntity.getStartDatetime()); // 元の開始日時が維持
    }

    @Test
    @Tag("ExternalService")
    void testFailure() throws Exception {

        final Integer taskCode = 393;
        recordTaskPlanJobExecutionListner.afterJob(this.getStepExecution(2025, 523, 393, BatchStatus.FAILED));

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
        assertEquals(LocalDateTime.of(1990, 7, 24, 23, 34, 56), entity3.getStartDatetime());
    }

    private JobExecution getStepExecution(final Integer year, final Integer taskId, final Integer taskCode,
            final BatchStatus status) {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("exe_datetitme", LocalDateTime.now())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM, (long) userDto.getUserPersonId())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM, (long) userDto.getUserPersonCode())
                .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                .addLong(RecordTaskPlanJobExecutionListner.KEY_YEAR, (long) year) //
                .addLong(RecordTaskPlanJobExecutionListner.KEY_ID, (long) taskId) //
                .addLong(RecordTaskPlanJobExecutionListner.KEY_CODE, (long) taskCode) //
                .toJobParameters();

        JobExecution jobExecution = MetaDataInstanceFactory.createJobExecution("test", 1L, 1L, jobParameters);
        jobExecution.setStatus(status);
        return jobExecution;
    }

}
