package net.seijishikin.jp.normalize.manage.kanrensha.batch.task_plan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
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
import net.seijishikin.jp.normalize.common_tool.utils.CreateUserLeastDtoByBatchParamUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.TaskPlan2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2025.InsertTaskPlanY2025Logic;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RecordTaskPlanTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearInsertTaskPlanServiceTest.sql")
class RecordTaskPlanTaskletTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private RecordTaskPlanTasklet recordTaskPlanTasklet;

    /** タスク計画挿入Logic(2025) */
    @Autowired
    private InsertTaskPlanY2025Logic insertTaskPlanY2025Logic;

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    @Test
    @Transactional
    void test() throws Exception {

        Integer year = 2025;
        Integer taskCode = 101;

        // 指定年度タスク計画を追加
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        Integer taskId = insertTaskPlanY2025Logic.practice(userDto, taskCode);

        recordTaskPlanTasklet.beforeStep(this.getStepExecution((long) year, (long) taskId));
        recordTaskPlanTasklet.execute(null, null);

        TaskPlan2025Entity insertEntity = taskPlan2025Repository.findById(taskId).get();
        assertEquals(false, insertEntity.getIsLatest());

        // 同じコードの最新は1件のみでそのデータは完了状態
        List<TaskPlan2025Entity> list = taskPlan2025Repository
                .findByTaskPlanCodeAndIsLatest(insertEntity.getTaskPlanCode(), true);
        assertEquals(1, list.size());
        TaskPlan2025Entity updateEntity = list.get(0);
        assertEquals(true, updateEntity.getIsLatest());
        assertEquals(true, updateEntity.getIsFinished());
        assertEquals(false, updateEntity.getIsSuspended());
    }

    private StepExecution getStepExecution(final Long year, final Long taskId) {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLocalDateTime("exe_datetitme", LocalDateTime.now())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_ID_PARAM, (long) userDto.getUserPersonId())
                .addLong(CreateUserLeastDtoByBatchParamUtil.USER_CODE_PARAM, (long) userDto.getUserPersonCode())
                .addString(CreateUserLeastDtoByBatchParamUtil.USER_NAME_PARAM, userDto.getUserPersonName())
                .addLong(RecordTaskPlanTasklet.KEY_YEAR, year) //
                .addLong(RecordTaskPlanTasklet.KEY_ID, taskId)

                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }
}
