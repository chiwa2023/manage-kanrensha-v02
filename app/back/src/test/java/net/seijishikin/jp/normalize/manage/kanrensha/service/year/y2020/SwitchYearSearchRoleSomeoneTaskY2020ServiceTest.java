package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.SearchTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TaskPlanBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearSearchRoleSomeoneTaskService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearSearchRoleSomeoneTaskY2020Service単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearSearchRoleSomeoneTaskY2020ServiceTest.sql")
class SwitchYearSearchRoleSomeoneTaskY2020ServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearSearchRoleSomeoneTaskService switchYearSearchRoleSomeoneTaskService;

    @Test
    @Tag("TableTruncate")
    void test2020() throws Exception {

        SearchTaskPlanCapsuleDto capsuleDto = new SearchTaskPlanCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.getUserDto().getListRoles().add("ROLE_manager"); // 運営者権限
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(15);
        capsuleDto.setStartDate(LocalDateTime.of(2020, 6, 1, 0, 0, 0));
        capsuleDto.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        // capsuleDto.setSearchTaskWord("名称4"); // 名称は使わない
        capsuleDto.setFlgFinished(1); // 有効
        capsuleDto.setFlgStart(1); // 有効
        capsuleDto.setFlgSuspended(1); // 有効
        // capsuleDto1.setInfoCodeList(listCode);タスク情報種類は使わない

        SearchTaskPlanResultDto resultDto = switchYearSearchRoleSomeoneTaskService.practice(capsuleDto);

        assertEquals(1, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber());

        List<TaskPlanBaseEntity> list1 = resultDto.getListTaskPlan();
        assertEquals(1, list1.size());
        assertEquals(205, list1.get(0).getTaskPlanId());
    }

}
