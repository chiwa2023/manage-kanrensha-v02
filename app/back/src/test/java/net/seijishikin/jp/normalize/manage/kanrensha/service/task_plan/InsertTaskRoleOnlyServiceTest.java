package net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskListForUserInfoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.TaskPlan2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.TaskPlan2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertTaskRoleOnlyService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
//@Transactional
@Sql("InsertTaskRoleOnlyServiceTest.sql")
class InsertTaskRoleOnlyServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** 単体テスト */
    @Autowired
    private InsertTaskRoleOnlyService insertTaskRoleOnlyService;

    /** タスク計画Repository(2026) */
    @Autowired
    private TaskPlan2026Repository taskPlan2026Repository;

    /** テスト対象 */
    @Autowired
    private GetRoleSomeoneTaskService getRoleSomeoneTaskService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        Map<String, String> mapParam = new TreeMap<>();

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        LocalDateTime dateTime = LocalDateTime.of(2026, 11, 12, 12, 34, 56);

        InsertTaskPlanResultDto resultDto = insertTaskRoleOnlyService.practice(userDto, dateTime,
                TaskInfoConstants.MOVE_KANRENSHA_CODE_ACCEPT, mapParam);
        // 失敗していない
        assertFalse(resultDto.getIsFailure());

        List<TaskPlan2026Entity> list = taskPlan2026Repository.findAll();
        assertEquals(1, list.size());
        TaskPlan2026Entity entity = list.get(0);
        assertEquals(TaskInfoConstants.MOVE_KANRENSHA_CODE_ACCEPT, entity.getTaskInfoCode());
        assertEquals(0, entity.getTaskUserCode()); // ここが死活的重要

        // タスク情報の呼び出しパラメータの設定が悪く例外発生しているが実際の挙動に影響がないとして積極的に例外を握りつぶせている
        assertDoesNotThrow(
                () -> insertTaskRoleOnlyService.practice(userDto, dateTime, TaskInfoConstants.PROMOTE_ADMIN, mapParam));

        FrameworkCapsuleDto capsuleDto = new FrameworkCapsuleDto();
        capsuleDto.setUserDto(userDto);
        capsuleDto.getUserDto().getListRoles().add("ROLE_manager");

        TaskListForUserInfoResultDto resultDtoTask = getRoleSomeoneTaskService.practice(2026, capsuleDto);

        assertTrue(resultDtoTask.getIsRefreshed());

        List<TaskPlanBaseEntity> listThisYear = resultDtoTask.getListThisYear();

        assertEquals(1, listThisYear.size());
        TaskPlanBaseEntity entity00 = listThisYear.get(0);
        assertEquals(TaskInfoConstants.MOVE_KANRENSHA_CODE_ACCEPT, entity00.getTaskInfoCode());
    }

}
