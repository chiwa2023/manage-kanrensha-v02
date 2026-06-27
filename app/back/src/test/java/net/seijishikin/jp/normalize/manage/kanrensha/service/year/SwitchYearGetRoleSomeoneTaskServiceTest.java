package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearGetRoleSomeoneTaskService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearGetRoleSomeoneTaskServiceTest.sql")
class SwitchYearGetRoleSomeoneTaskServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearGetRoleSomeoneTaskService switchYearGetRoleSomeoneTaskService;

    @Test
    @Tag("TableTruncate")
    void test() {
        assertThrows(IllegalArgumentException.class,
                () -> switchYearGetRoleSomeoneTaskService.practice(1001, CreateLeastUserForTestUtil.practice()));

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        // 権限を全削除してもタスク計画では空リストが返るだけで特に問題はない
        List<TaskPlanBaseEntity> listAns = switchYearGetRoleSomeoneTaskService.practice(2026, userDto);
        assertEquals(0, listAns.size());
    }

    @Test
    @Tag("TableTruncate")
    void test2026() {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add("ROLE_admin");
        userDto.getListRoles().add("ROLE_kanrensha_person");

        List<TaskPlanBaseEntity> listAns = switchYearGetRoleSomeoneTaskService.practice(2026, userDto);

        assertEquals(2, listAns.size());
        TaskPlanBaseEntity entity0 = listAns.get(0);
        assertEquals(461, entity0.getTaskPlanId());
        assertEquals(2026, entity0.getTableYear());

        TaskPlanBaseEntity entity1 = listAns.get(1);
        assertEquals(464, entity1.getTaskPlanId());
        assertEquals(2026, entity1.getTableYear());
    }

}
