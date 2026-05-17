package net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
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

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.common_tool.entity.AllTabeDataHistoryInterface;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.task_plan.CreateQueryParamDummyUtil;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertTaskPlanService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("InsertTaskPlanServiceTest.sql")
class InsertTaskPlanServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertTaskPlanService insertTaskPlanService;

    /** タスク計画Repository */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    @Test
    @Tag("ExternalService") // NOPMD
    void test() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime createDatetime = LocalDateTime.of(2026, 12, 5, 12, 34, 56);
        FrameworkMessageAndResultDto resultDto = insertTaskPlanService.practice(userDto, createDatetime,
                TaskInfoConstants.PROMOTE_ADMIN, CreateQueryParamDummyUtil.practice());
        assertFalse(resultDto.getIsFailure());

        List<TaskPlan2026Entity> listPlan = taskPlan2026Repository.findAll();
        assertEquals(1, listPlan.size());

        TaskPlan2026Entity planEntity = listPlan.get(0);
        assertEquals(createDatetime.getYear(), planEntity.getTableYear());
        assertEquals(1, planEntity.getTaskPlanCode());
        assertEquals("SE権限推薦処理", planEntity.getTaskPlanName());
        assertEquals(901, planEntity.getTaskInfoCode());

        assertEquals(createDatetime, planEntity.getStartDatetime());
        assertEquals(AllTabeDataHistoryInterface.INIT_TIMESTAMP, planEntity.getEndDateimte());
        assertEquals(false, planEntity.getIsFinished());
        assertEquals(true, planEntity.getIsStart());
        assertEquals(false, planEntity.getIsSuspended());
        assertEquals("admin,manager", planEntity.getRoleList());
        assertEquals("pageUrl", planEntity.getTransferPass());
    }

    @Test
    @Tag("ExternalService")
    void testWrongTaskCode() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime createDatetime = LocalDateTime.of(2026, 12, 5, 12, 34, 56);
        FrameworkMessageAndResultDto resultDto = insertTaskPlanService.practice(userDto, createDatetime, 646, null);
        assertTrue(resultDto.getIsFailure());
        assertEquals("指定されたタスク情報が存在しません(646)", resultDto.getMessage());
    }

    @Test
    @Tag("ExternalService")
    void testPlanNotRecord() {
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime createDatetime = LocalDateTime.of(1990, 12, 5, 12, 34, 56);
        FrameworkMessageAndResultDto resultDto = insertTaskPlanService.practice(userDto, createDatetime,
                TaskInfoConstants.PROMOTE_ADMIN, null);
        assertTrue(resultDto.getIsFailure());
        assertEquals("タスク計画が登録できませんでした", resultDto.getMessage());
    }

    @Test
    @Tag("ExternalService")
    void testWrongUserId() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime createDatetime = LocalDateTime.of(2026, 12, 5, 12, 34, 56);
        userDto.setUserPersonId(394);
        FrameworkMessageAndResultDto resultDto = insertTaskPlanService.practice(userDto, createDatetime,
                TaskInfoConstants.PROMOTE_ADMIN, null);
        assertTrue(resultDto.getIsFailure());
        assertEquals("ユーザ情報がが取得できませんでした", resultDto.getMessage());
    }

}
