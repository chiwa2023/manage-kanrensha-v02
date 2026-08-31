package net.seijishikin.jp.normalize.manage.kanrensha.service.task_plan;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskListForUserInfoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * GetNotCompletdTaskForUserInfoService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("GetNotCompletdTaskForUserInfoServiceTest.sql")
class GetNotCompletdTaskForUserInfoServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetNotCompletdTaskForUserInfoService getNotCompletdTaskForUserInfoService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        FrameworkCapsuleDto capsuleDto = new FrameworkCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        TaskListForUserInfoResultDto resultDto = getNotCompletdTaskForUserInfoService.practice(2026, capsuleDto);

        assertTrue(resultDto.getIsRefreshed());

        List<TaskPlanBaseEntity> listThisYear = resultDto.getListThisYear();
        assertEquals(5, listThisYear.size());
        assertEquals(429, listThisYear.get(0).getTaskPlanId());
        assertEquals(428, listThisYear.get(1).getTaskPlanId());
        assertEquals(427, listThisYear.get(2).getTaskPlanId());
        assertEquals(426, listThisYear.get(3).getTaskPlanId());
        assertEquals(425, listThisYear.get(4).getTaskPlanId());

        List<TaskPlanBaseEntity> listLastYear = resultDto.getListLastYear();
        assertEquals(3, listLastYear.size());
        assertEquals(426, listLastYear.get(0).getTaskPlanId());
        assertEquals(425, listLastYear.get(1).getTaskPlanId());
        assertEquals(424, listLastYear.get(2).getTaskPlanId());
    }

}
