package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2025;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearGetRoleSomeoneTaskService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearGetRoleSomeoneTaskService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearGetRoleSomeoneTaskY2025ServiceTest.sql")
class SwitchYearGetRoleSomeoneTaskY2025ServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearGetRoleSomeoneTaskService switchYearGetRoleSomeoneTaskService;

    @Test
    @Tag("TableTruncate")
    void test2025() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add("ROLE_admin");
        userDto.getListRoles().add("ROLE_kanrensha_person");

        List<TaskPlanBaseEntity> listAns = switchYearGetRoleSomeoneTaskService.practice(2025, userDto);

        assertEquals(2, listAns.size());
        TaskPlanBaseEntity entity0 = listAns.get(0);
        assertEquals(461, entity0.getTaskPlanId());
        assertEquals(2025, entity0.getTableYear());

        TaskPlanBaseEntity entity1 = listAns.get(1);
        assertEquals(464, entity1.getTaskPlanId());
        assertEquals(2025, entity1.getTableYear());
    }

}
