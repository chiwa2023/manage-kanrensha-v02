package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2027;

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
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SearchRoleSomeoneTaskY2027Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchRoleSomeoneTaskY2027LogicTest.sql")
class SearchRoleSomeoneTaskY2027LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchRoleSomeoneTaskY2027Logic searchRoleSomeoneTaskY2027Logic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchTaskPlanCapsuleDto capsuleDto = new SearchTaskPlanCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.getUserDto().getListRoles().add("ROLE_manager"); // 運営者権限
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(15);
        capsuleDto.setStartDate(LocalDateTime.of(2027, 6, 1, 0, 0, 0));
        capsuleDto.setEndDate(LocalDateTime.of(2027, 12, 31, 23, 59, 59));
        // capsuleDto.setSearchTaskWord("名称4"); // 名称は使わない
        capsuleDto.setFlgFinished(1); // 有効
        capsuleDto.setFlgStart(1); // 有効
        capsuleDto.setFlgSuspended(1); // 有効
        // capsuleDto1.setInfoCodeList(listCode);タスク情報種類は使わない

        SearchTaskPlanResultDto resultDto = searchRoleSomeoneTaskY2027Logic.practice(capsuleDto);

        assertEquals(1, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(0, resultDto.getPageNumber());

        List<TaskPlanBaseEntity> list1 = resultDto.getListTaskPlan();
        assertEquals(1, list1.size());
        assertEquals(205, list1.get(0).getTaskPlanId());
    }

}
