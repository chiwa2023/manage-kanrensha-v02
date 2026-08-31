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

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskListForUserInfoResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * GetRoleSomeoneTaskService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("GetRoleSomeoneTaskServiceTest.sql")
class GetRoleSomeoneTaskServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetRoleSomeoneTaskService getRoleSomeoneTaskService;
    
    
    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        FrameworkCapsuleDto capsuleDto = new FrameworkCapsuleDto();
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add("ROLE_admin");
        userDto.getListRoles().add("ROLE_kanrensha_person");

        capsuleDto.setUserDto(userDto);

        TaskListForUserInfoResultDto resultDto = getRoleSomeoneTaskService.practice(2026, capsuleDto);


        assertTrue(resultDto.getIsRefreshed());
        
        
        List<TaskPlanBaseEntity> listThisYear = resultDto.getListThisYear();
        
        assertEquals(2, listThisYear.size());
        TaskPlanBaseEntity entity00 = listThisYear.get(0);
        assertEquals(461, entity00.getTaskPlanId());
        assertEquals(2026, entity00.getTableYear());

        TaskPlanBaseEntity entity01 = listThisYear.get(1);
        assertEquals(464, entity01.getTaskPlanId());
        assertEquals(2026, entity01.getTableYear());

        
        List<TaskPlanBaseEntity> listLastYear = resultDto.getListLastYear();
        
        assertEquals(2, listLastYear.size());
        TaskPlanBaseEntity entity10 = listLastYear.get(0);
        assertEquals(561, entity10.getTaskPlanId());
        assertEquals(2025, entity10.getTableYear());

        TaskPlanBaseEntity entity11 = listLastYear.get(1);
        assertEquals(564, entity11.getTaskPlanId());
        assertEquals(2025, entity11.getTableYear());
    }

}
